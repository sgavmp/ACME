package com.acme.controllers.certification;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acme.exception.CertificationNoExistException;
import com.acme.exception.FamilyProfessionalNoExistException;
import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;
import com.acme.model.user.User;
import com.acme.services.CertificationService;
import com.acme.services.UserService;

@Controller
@RequestMapping({ "/admin/certification" })
public class AdminCertificationController {

	@Autowired
	private CertificationService servicecertification;

	@Autowired
	private UserService serviceuser;

	public AdminCertificationController() {
		super();
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
			@ModelAttribute("cert") Certification cert,
			BindingResult bindingResult, Model model) {
		cert.addRequiremnt("");
		model.addAttribute("isNew", true);
		model.addAttribute("activeMenu", "certification");
		return "/certification/oneCertification";
	}

	// Borra un requisito cuando se esta creando un nuevo certificado
	@RequestMapping(value = "/create", params = "removeRow", method = RequestMethod.POST)
	public String removeRowCreate(@ModelAttribute("cert") Certification cert,
			BindingResult bindingResult, HttpServletRequest req,
			Model model) {
		Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
		cert.removeRequirement(rowId);
		model.addAttribute("isNew", true);
		model.addAttribute("activeMenu", "certification");
		return "/certification/oneCertification";
	}

	// Crea un requisito cuando se esta modificando un certificado
	@RequestMapping(value = "/edit/id/{idcert}", params = "addRow", method = RequestMethod.POST)
	public String addRowEdit(@PathVariable Long idcert, @ModelAttribute("cert") Certification cert,
			BindingResult bindingResult, Model model) {
		cert.addRequiremnt("");
		model.addAttribute("isNew", false);
		model.addAttribute("activeMenu", "certification");
		return "/certification/oneCertification";
	}

	// Borra un requisito cuando se esta modificando un certificado
	@RequestMapping(value = "/edit/id/{idcert}", params = "removeRow", method = RequestMethod.POST)
	public String removeRowEdit(@PathVariable Long idcert,
			@ModelAttribute("cert") Certification cert, BindingResult bindingResult, HttpServletRequest req,
			Model model) {
		Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
		cert.removeRequirement(rowId);
		model.addAttribute("isNew", false);
		model.addAttribute("activeMenu", "certification");
		return "/certification/oneCertification";
	}

	// Devuelve el certificado con id indicado en la URL
	@RequestMapping(value = "/edit/id/{idcert}", method = RequestMethod.GET)
	public String editCertificate(@PathVariable Long idcert, Model model,
			RedirectAttributes redirectAttrs) {
		Certification cert;
		try {
			cert = servicecertification.getCertificationById(idcert);
		} catch (CertificationNoExistException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
			return "redirect:/acme/certification/";
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
			BindingResult result, RedirectAttributes redirectAttrs) {
		model.addAttribute("isNew", false);
		model.addAttribute("cert", cert);
		model.addAttribute("activeMenu", "certification");
		// Comprueba si hay errores de validacion
		if (result.hasErrors()) {
			return "/certification/oneCertification";
		}
		try {
		cert=servicecertification.updateCertification(cert);
		}
		catch(StaleObjectStateException e) {
			model.addAttribute("error", "exception.lockexception");
			try {
				cert=servicecertification.getCertificationById(cert.getId());
			} catch (CertificationNoExistException e1) {
				redirectAttrs.addFlashAttribute("error", e.getMessage());
				return "redirect:/acme/certification/";
			}
			model.addAttribute("cert", cert);
			return "/certification/oneCertification";
		}
		model.addAttribute("info", "certification.modify");
		return "/certification/oneCertification";
	}

	// Borra el certificado indicado en la URL por id
	@RequestMapping(value = "/delete/id/{idcert}", method = RequestMethod.GET)
	public String deleteCertificate(@PathVariable Long idcert, Model model,
			RedirectAttributes redirectAttrs) {
		Certification cert;
		try {
			cert = servicecertification.getCertificationById(idcert);
		} catch (CertificationNoExistException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
			return "redirect:/acme/certification/";
		} 
		try {
			servicecertification.removeCertificationById(cert.getId());
		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("error", "exception.noborrar");
			return "redirect:/acme/certification/";
		}
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
		cert=servicecertification.createCertification(cert);
		redirectAttrs.addAttribute("id", cert.getId()).addFlashAttribute(
				"info", "certification.create");
		return "redirect:/acme/admin/certification/edit/id/{id}";
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
			RedirectAttributes redirectAttrs) {
		FamilyProfessional family;
		try {
			family = servicecertification.getFamilyProfessionalById(idfamiy);
		} catch (FamilyProfessionalNoExistException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
			return "redirect:/acme/admin/certification/family";
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
			BindingResult result) {
		// Comprueba si hay errores de validacion
		if (result.hasErrors()) {
			model.addAttribute("isNew", true);
			model.addAttribute("activeMenu", "certification");
			return "/certification/familyprofessional";
		}
		servicecertification.createFamilyProfessional(family);
		return "redirect:/acme/admin/certification/family";
	}

	// Borra la familia profesional indicado en la URL por id
	@SuppressWarnings("finally")
	@RequestMapping(value = "/family/delete/id/{idfamiy}", method = RequestMethod.GET)
	public String deleteFamily(@PathVariable Long idfamiy, Model model,
			RedirectAttributes redirectAttrs) {
		try {
			FamilyProfessional family = servicecertification.getFamilyProfessionalById(idfamiy);
			servicecertification.removeFamilyProfessional(idfamiy);
			redirectAttrs.addFlashAttribute("info", "familia.delete"); 	
		}
		catch (FamilyProfessionalNoExistException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
		}
		catch (Exception e){
			redirectAttrs.addFlashAttribute("error", "familia.asociado");
		}
		finally {
			return "redirect:/acme/admin/certification/family";
		}
	}

	// Modifica los datos de la familia profesional pasado por POST
	@RequestMapping(value = "/family/edit/id/{idfamiy}", method = RequestMethod.POST)
	public String editFamily(@PathVariable Integer idfamiy, Model model,
			@ModelAttribute("family") @Valid FamilyProfessional family,
			BindingResult result) {
		model.addAttribute("isNew", false);
		model.addAttribute("family", family);
		model.addAttribute("activeMenu", "certification");
		// Comprueba si hay errores de validacion
		if (result.hasErrors()) {
			return "/certification/familyprofessional";
		}
		servicecertification.updateFamilyProfessional(family);
		model.addAttribute("info", "family.modify");
		return "/certification/familyprofessional";
	}
}
