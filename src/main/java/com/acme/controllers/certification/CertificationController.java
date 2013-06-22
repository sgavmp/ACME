package com.acme.controllers.certification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.acme.exception.PageNumberIncorrectException;
import com.acme.model.certification.Certification;
import com.acme.services.CertificationService;

@Controller
@RequestMapping({ "/certification" })
public class CertificationController {

	@Autowired
	private CertificationService servicecertification;

	public CertificationController() {
		super();
	}

	// Muestra un listado con todos los certificados, paginado
	@SuppressWarnings("finally")
	@RequestMapping(value = "/page/{p}", method = RequestMethod.GET)
	public String showAllCertificate(Model model, @PathVariable("p") Integer p) {
		Page<Certification> certifications = null;
		try {
			certifications = servicecertification.getAllCertification(p);
		} catch (PageNumberIncorrectException e) {
			model.addAttribute("error", e.getMessage());
			certifications = servicecertification.getAllCertification(0);
		} finally {
			model.addAttribute("allCertifications", certifications);
			model.addAttribute("activeMenu", "certification");
			return "/certification/listCertification";
		}
	}
	
	// Muestra un listado de la busqueda realizada, paginado
	@SuppressWarnings("finally")
	@RequestMapping(value = "/search/", method = RequestMethod.GET, params = { "search" })
	public String searchCertifications(
			@RequestParam(value = "search") String text, Model model) {
		Page<Certification> certifications = null;
		try {
			certifications = servicecertification.searchCertification(text, 0);
		} catch (PageNumberIncorrectException e) {
			model.addAttribute("error", e.getMessage());
		} finally {
			model.addAttribute("activeMenu", "certification");
			model.addAttribute("search", text);
			model.addAttribute("isSearch", true);
			model.addAttribute("allCertifications", certifications);
			return "/certification/listCertification";
		}
	}

	// Muestra un listado de la busqueda realizada, paginado, la pagina indicada
	@SuppressWarnings("finally")
	@RequestMapping(value = "/search/page/{page}", method = RequestMethod.GET)
	public String searchCertifications(
			@RequestParam(value = "search") String text,
			@PathVariable Integer page, Model model) {
		Page<Certification> certifications=null;
		try {
			certifications = servicecertification.searchCertification(text,
					page);
		} catch (PageNumberIncorrectException e) {
			model.addAttribute("error", e.getMessage());
		} finally {
			model.addAttribute("activeMenu", "certification");
			model.addAttribute("search", text);
			model.addAttribute("isSearch", true);
			model.addAttribute("allCertifications", certifications);
			return "/certification/listCertification";
		}
	}

	// Muestra un listado con todos los certificados, paginado
	@SuppressWarnings("finally")
	@RequestMapping(value = "/**")
	public String showAllCertificate(Model model) {
		Page<Certification> certifications = null;
		try {
			certifications = servicecertification.getAllCertification(0);
		} catch (PageNumberIncorrectException e) {
			model.addAttribute("error", e.getMessage());
		} finally {
			model.addAttribute("activeMenu", "certification");
			model.addAttribute("allCertifications", certifications);
			return "/certification/listCertification";
		}
	}
}
