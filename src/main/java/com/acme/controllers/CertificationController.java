package com.acme.controllers;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.ws.http.HTTPException;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.transaction.annotation.Propagation;
import org.thymeleaf.util.Validate;

import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;
import com.acme.model.user.Company;
import com.acme.model.user.User;
import com.acme.services.CertificationService;
import com.acme.services.UserService;
import com.google.common.collect.Lists;

@Controller
@RequestMapping({ "/certification" })
public class CertificationController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CertificationService servicecertification;

	@Autowired
	private UserService serviceuser;

	public CertificationController() {
		super();
	}

	@ModelAttribute("allCertifications")
	public List<Certification> allCertifications() {
		return servicecertification.getAllCertification();
	}

	@ModelAttribute("allFamilyProfessional")
	public List<FamilyProfessional> getAllFamily() {
		return servicecertification.getAllFamilyProfessional();
	}

	@ModelAttribute("allCompany")
	public List<User> getAllCompany() {
		return serviceuser.getAllCompanyWithId();
	}

	// Añade un nuevo requisito cuando se esta creando un nuevo certificado
	@RequestMapping(value = "/create", params = "addRow", method = RequestMethod.POST)
	public String addRowCreate(
			@ModelAttribute("cert") final Certification cert,
			final BindingResult bindingResult, Model model) {
		cert.addRequiremnt("");
		model.addAttribute("isNew", true);
		model.addAttribute("activeMenu", "certification");
		return "/certification/oneCertification";
	}

	// Borra un requisito cuando se esta creando un nuevo certificado
	@RequestMapping(value = "/create", params = "removeRow", method = RequestMethod.POST)
	public String removeRowCreate(@ModelAttribute("cert") Certification cert,
			final BindingResult bindingResult, final HttpServletRequest req,
			Model model) {
		final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
		cert.removeRequirement(rowId);
		model.addAttribute("isNew", true);
		model.addAttribute("activeMenu", "certification");
		return "/certification/oneCertification";
	}

	// Crea un requisito cuando se esta modificando un certificado
	@RequestMapping(value = "/edit/id/{idcert}", params = "addRow", method = RequestMethod.POST)
	public String addRowEdit(@ModelAttribute("cert") Certification cert,
			final BindingResult bindingResult, Model model) {
		cert.addRequiremnt("");
		model.addAttribute("isNew", false);
		model.addAttribute("activeMenu", "certification");
		return "/certification/oneCertification";
	}

	// Bora un requisito cuando se esta modificando un certificado
	@RequestMapping(value = "/edit/id/{idcert}", params = "removeRow", method = RequestMethod.POST)
	public String removeRowEdit(
			@ModelAttribute("cert") final Certification cert,
			final BindingResult bindingResult, final HttpServletRequest req,
			Model model) {
		final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
		cert.removeRequirement(rowId);
		model.addAttribute("isNew", false);
		model.addAttribute("activeMenu", "certification");
		return "/certification/oneCertification";
	}

	// Devuelve el certificado con id indicado en la URL
	@RequestMapping(value = "/edit/id/{idcert}", method = RequestMethod.GET)
	public String editCertificate(@PathVariable Long idcert, Model model,
			RedirectAttributes redirectAttrs, HttpServletRequest response) {
		Certification cert = servicecertification.getCertificationById(idcert);
		if (cert == null) {
			redirectAttrs.addFlashAttribute("error", "certification.noexist");
			response.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE,
					HttpStatus.NOT_FOUND);
			return "redirect:/acme/certification/create";
		}
		model.addAttribute("cert", cert);
		model.addAttribute("isNew", false);
		model.addAttribute("activeMenu", "certification");
		return "/certification/oneCertification";
	}

	// Modifica los datos del certificado pasado por POST
	@RequestMapping(value = "/edit/id/{idcert}", method = RequestMethod.POST)
	public String editCertificate(@PathVariable Integer idcert, Model model,
			@ModelAttribute("cert") @Valid Certification cert,
			BindingResult result) {
		// Comprueba si hay errores de validacion
		if (result.hasErrors()) {
			model.addAttribute("isNew", false);
			model.addAttribute("cert", cert);
			model.addAttribute("activeMenu", "certification");
			return "/certification/oneCertification";
		}
		cert.setFamilyProfessional(servicecertification
				.getFamilyProfessionalById(cert.getFamilyProfessional().getId()));
		cert.setCompany(serviceuser.getUserById(cert.getCompany().getId()));
		servicecertification.updateCertification(cert);
		model.addAttribute("activeMenu", "certification");
		model.addAttribute("cert", cert);
		model.addAttribute("isNew", false);
		model.addAttribute("info", "certification.modify");
		return "/certification/oneCertification";
	}

	// Borra el certificado indicado en la URL por id
	@RequestMapping(value = "/delete/id/{idcert}", method = RequestMethod.GET)
	public String deleteCertificate(@PathVariable Long idcert, Model model,
			RedirectAttributes redirectAttrs, HttpServletRequest response) {
		Certification cert = servicecertification.getCertificationById(idcert);
		if (cert == null) {
			redirectAttrs.addFlashAttribute("error", "certification.noexist");
			response.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE,
					HttpStatus.NOT_FOUND);
			return "redirect:/acme/certification";
		}
		servicecertification.removeCertificationById(idcert);
		redirectAttrs.addFlashAttribute("info", "certification.delete");
		return "redirect:/acme/certification/";
	}

	// Muestra el formulario de crear un nuevo certificado
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createCertificate(Model model) {
		Certification cert = new Certification();
		model.addAttribute("cert", cert);
		model.addAttribute("isNew", true);
		model.addAttribute("activeMenu", "certification");
		return "/certification/oneCertification";
	}

	// Crea un nuevo certificado con los datos pasado por POST
	@RequestMapping(value = "/create", params = "create", method = RequestMethod.POST)
	public String createCertificate(Model model,
			@ModelAttribute("cert") @Valid Certification cert,
			BindingResult result, RedirectAttributes redirectAttrs) {
		// Comprueba si hay errores de validacion
		if (result.hasErrors()) {
			model.addAttribute("isNew", true);
			model.addAttribute("activeMenu", "certification");
			return "/certification/oneCertification";
		}
		cert.setFamilyProfessional(servicecertification
				.getFamilyProfessionalById(cert.getFamilyProfessional().getId()));
		cert.setCompany(serviceuser.getUserById(cert.getCompany().getId()));
		servicecertification.createCertification(cert);
		redirectAttrs.addAttribute("id", cert.getId()).addFlashAttribute(
				"info", "certification.create");
		return "redirect:/acme/certification/edit/id/{id}";
	}

	// Muestra un listado con todos los certificados
	@RequestMapping({ "/**", "/list" })
	public String showAllCertificate(Model model) {
		model.addAttribute("activeMenu", "certification");
		return "/certification/listCertification";
	}

	// Muestra el formulario de crear una nueva familia profesional y el listado
	// de todas las existentes
	@RequestMapping(value = "/family", method = RequestMethod.GET)
	public String createFamily(Model model) {
		FamilyProfessional family = new FamilyProfessional();
		model.addAttribute("family", family);
		model.addAttribute("isNew", true);
		model.addAttribute("activeMenu", "certification");
		return "/certification/familyprofessional";
	}

	// Devuelve el certificado con id indicado en la URL
	@RequestMapping(value = "/family/edit/id/{idfamiy}", method = RequestMethod.GET)
	public String editFamily(@PathVariable Long idfamiy, Model model,
			RedirectAttributes redirectAttrs, HttpServletRequest response) {
		FamilyProfessional family = servicecertification
				.getFamilyProfessionalById(idfamiy);
		if (family == null) {
			redirectAttrs.addFlashAttribute("error", "certification.noexist");
			response.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE,
					HttpStatus.NOT_FOUND);
			return "redirect:/acme/certification/family";
		}
		model.addAttribute("family", family);
		model.addAttribute("isNew", false);
		model.addAttribute("activeMenu", "certification");
		return "/certification/familyprofessional";
	}

	// Crea una nueva familia profesional con los datos pasado por POST
	@RequestMapping(value = "/family/create", params = "create", method = RequestMethod.POST)
	public String createCertificate(Model model,
			@ModelAttribute("family") @Valid FamilyProfessional family,
			BindingResult result, RedirectAttributes redirectAttrs) {
		// Comprueba si hay errores de validacion
		if (result.hasErrors()) {
			model.addAttribute("isNew", true);
			model.addAttribute("activeMenu", "certification");
			return "/certification/familyprofessional";
		}
		servicecertification.createFamilyProfessional(family);
		return "redirect:/acme/certification/family";
	}

	// Borra la familia profesional indicado en la URL por id
	@RequestMapping(value = "/family/delete/id/{idfamiy}", method = RequestMethod.GET)
	public String deleteFamily(@PathVariable Long idfamiy, Model model,
			RedirectAttributes redirectAttrs, HttpServletRequest response) {
		try {
			FamilyProfessional family = servicecertification.getFamilyProfessionalById(idfamiy);
			if (family == null) {
				redirectAttrs.addFlashAttribute("error", "familia.noexist");
				response.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE,
						HttpStatus.NOT_FOUND);
			}
			servicecertification.removeFamilyProfessional(idfamiy);
			redirectAttrs.addFlashAttribute("info", "familia.delete");
		}
		catch (Exception e){
			redirectAttrs.addFlashAttribute("error", "familia.asociado");
		}
		finally{
			return "redirect:/acme/certification/family";
		}
	}

	// Modifica los datos de la familia profesional pasado por POST
	@RequestMapping(value = "/family/edit/id/{idfamiy}", method = RequestMethod.POST)
	public String editFamily(@PathVariable Integer idfamiy, Model model,
			@ModelAttribute("family") @Valid FamilyProfessional family,
			BindingResult result) {
		// Comprueba si hay errores de validacion
		if (result.hasErrors()) {
			model.addAttribute("isNew", false);
			model.addAttribute("family", family);
			model.addAttribute("activeMenu", "certification");
			return "/certification/familyprofessional";
		}
		servicecertification.updateFamilyProfessional(family);
		model.addAttribute("activeMenu", "certification");
		model.addAttribute("family", family);
		model.addAttribute("isNew", false);
		model.addAttribute("info", "family.modify");
		return "/certification/familyprofessional";
	}
}
