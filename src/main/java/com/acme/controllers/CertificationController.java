package com.acme.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.transaction.annotation.Propagation;
import org.thymeleaf.util.Validate;

import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;
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

	@RequestMapping(value = "/create", params = "addRow",method = RequestMethod.POST)
	public String addRowCreate(@ModelAttribute("cert") final Certification cert,
			final BindingResult bindingResult, Model model) {
		cert.addRequiremnt("");
		model.addAttribute("isNew", true);
		model.addAttribute("activeMenu", "certification");
		return "/certification/oneCertification";
	}

	@RequestMapping(value = "/create", params = "removeRow", method = RequestMethod.POST)
	public String removeRowCreate(@ModelAttribute("cert") final Certification cert,
			final BindingResult bindingResult, final HttpServletRequest req,
			Model model) {
		final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
		cert.removeRequirement(rowId);
		model.addAttribute("isNew", true);
		model.addAttribute("activeMenu", "certification");
		return "/certification/oneCertification";
	}
	
	@RequestMapping(value = "/edit/id/{idcert}", params = "addRow",method = RequestMethod.POST)
	public String addRowEdit(@ModelAttribute("cert") final Certification cert,
			final BindingResult bindingResult, Model model) {
		cert.addRequiremnt("");
		model.addAttribute("isNew", false);
		model.addAttribute("activeMenu", "certification");
		return "/certification/oneCertification";
	}

	@RequestMapping(value = "/edit/id/{idcert}", params = "removeRow", method = RequestMethod.POST)
	public String removeRowEdit(@ModelAttribute("cert") final Certification cert,
			final BindingResult bindingResult, final HttpServletRequest req,
			Model model) {
		final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
		cert.removeRequirement(rowId);
		model.addAttribute("isNew", false);
		model.addAttribute("activeMenu", "certification");
		return "/certification/oneCertification";
	}

	@RequestMapping(value = "/edit/id/{idcert}", method = RequestMethod.GET)
	public String editCertificate(@PathVariable Integer idcert, Model model) {
		Certification cert = servicecertification.getCertificationById(idcert);
		model.addAttribute("cert", cert);
		model.addAttribute("isNew", false);
		model.addAttribute("activeMenu", "certification");
		return "/certification/oneCertification";
	}

	@RequestMapping(value = "/edit/id/{idcert}", method = RequestMethod.POST)
	public String editCertificate(@PathVariable Integer idcert, Model model,
			@ModelAttribute("cert") Certification cert, BindingResult result) {
		servicecertification.updateCertification(cert);
		model.addAttribute("activeMenu", "certification");
		model.addAttribute("cert", cert);
		return "/certification/oneCertification";
	}

	@RequestMapping(value = "/delete/id/{idcert}", method = RequestMethod.DELETE)
	public String deleteCertificate(@PathVariable Integer idcert, Model model) {
		servicecertification.removeCertificationById(idcert);
		model.addAttribute("activeMenu", "certification");
		return "/certification/listCertification";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createCertificate(Model model) {
		Certification cert = new Certification();
		model.addAttribute("cert", cert);
		model.addAttribute("isNew", true);
		model.addAttribute("activeMenu", "certification");
		return "/certification/oneCertification";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createCertificate(Model model,
			@ModelAttribute("cert") Certification cert, BindingResult result) {
		servicecertification.createCertification(cert);
		model.addAttribute("cert", cert);
		model.addAttribute("isNew", false);
		model.addAttribute("activeMenu", "certification");
		return "/certification/oneCertification";
	}

	@RequestMapping({ "/**", "/list" })
	public String showAllCertificate(Model model) {
		model.addAttribute("activeMenu", "certification");
		return "/certification/listCertification";
	}
}
