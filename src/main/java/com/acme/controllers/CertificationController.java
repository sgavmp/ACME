package com.acme.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.util.Validate;

import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;
import com.acme.services.CertificationService;
import com.google.common.collect.Lists;


@Controller
@RequestMapping({"/certification"})
public class CertificationController {

	@Autowired
    private MessageSource messageSource;
	
	@Autowired
	private CertificationService servicecertification;

	
	public CertificationController(){
		super();
	}
	
	@ModelAttribute("allCertifications")
    public List<Certification> allCertifications() {
        return servicecertification.getAllCertification();
    }
	
	@ModelAttribute("allFamilyProfessional")
	public List<FamilyProfessional> getAllFamily(){
		return servicecertification.getAllFamilyProfessional();
	}
	
	
	@RequestMapping(value = "/edit/id/{id}", method = RequestMethod.GET)
    public String editCertificate(@PathVariable Integer id,Model model) {
		Certification cert = servicecertification.getCertificationById(id);
		model.addAttribute("cert",cert);
		model.addAttribute("isNew", false);
		model.addAttribute("activeMenu", "certification");
        return "/certification/oneCertification";
    }
	
	@RequestMapping(value = "/edit/id/{id}", method = RequestMethod.POST)
    public String editCertificate(@PathVariable Integer id,@ModelAttribute Certification cert,Model model) {
		servicecertification.updateCertification(cert);
		model.addAttribute("activeMenu", "certification");
		model.addAttribute("cert", cert);
        return "/certification/oneCertification";
    }
	
	@RequestMapping(value = "/delete/id/{id}", method = RequestMethod.DELETE)
    public String deleteCertificate(@PathVariable Integer id,Model model) {
		servicecertification.removeCertificationById(id);
		model.addAttribute("activeMenu", "certification");
        return "/certification/listCertification";
    }
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createCertificate(Model model) {
		Certification cert = new Certification();
		model.addAttribute("cert",cert);
		model.addAttribute("isNew", true);
		model.addAttribute("activeMenu", "certification");
        return "/certification/oneCertification";
    }
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createCertificate(Model model, @ModelAttribute Certification cert) {
		servicecertification.createCertification(cert);
		model.addAttribute("cert", cert);
		model.addAttribute("isNew", true);
		model.addAttribute("activeMenu", "certification");
        return "/certification/oneCertification";
    }
	
	@RequestMapping({"/**","/list"})
    public String showAllCertificate(Model model) {
		model.addAttribute("activeMenu", "certification");
        return "/certification/listCertification";
    }
}
