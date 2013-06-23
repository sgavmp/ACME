package com.acme.controllers.examination;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.acme.exception.PageNumberIncorrectException;
import com.acme.model.certification.Certification;
import com.acme.model.examination.Examination;
import com.acme.model.user.User;
import com.acme.services.CertificationService;
import com.acme.services.ExaminationService;
import com.acme.services.UserService;
import com.google.common.collect.Lists;

@Controller
@RequestMapping({ "/examination" })
public class ExaminationController {

	@Autowired
	private ExaminationService serviceexamination;

	@Autowired
	private UserService userService;

	@Autowired
	private CertificationService servicecertification;

	@ModelAttribute("allCertifications")
	public List<Certification> getAllCertifications() {
		return servicecertification.getAllCertification();
	}

	// Muestra un listado con todos los certificados
	@SuppressWarnings("finally")
	@RequestMapping(value = "/page/{p}", method = RequestMethod.GET)
	public String showAllExamination(Model model, @PathVariable("p") Integer p,
			HttpServletRequest request) {
		List<Examination> exams= Lists.newArrayList();
		if (request.getUserPrincipal() != null) {	
			User u = userService.getUserByUsername(request.getUserPrincipal()
					.getName());
			if (u != null)
				exams = serviceexamination.findExaminationsByUserId(u);
		}
		model.addAttribute("registers", exams);
		Page<Examination> examinations = null;
		try {
			examinations = serviceexamination.getAllExamination(p);
		} catch (PageNumberIncorrectException e) {
			model.addAttribute("error", e.getMessage());
			examinations = serviceexamination.getAllExamination(0);
		} finally {
			model.addAttribute("allExamination", examinations);
			model.addAttribute("activeMenu", "examination");
			model.addAttribute("isNew", true);
			model.addAttribute("exam", new Examination());
			return "/examination/listExamination";
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/search/", method = RequestMethod.GET, params = { "search" })
	public String searchExaminations(
			@RequestParam(value = "search") String text, Model model,
			HttpServletRequest request) {
		List<Examination> exams= Lists.newArrayList();
		if (request.getUserPrincipal() != null) {	
			User u = userService.getUserByUsername(request.getUserPrincipal()
					.getName());
			if (u != null)
				exams = serviceexamination.findExaminationsByUserId(u);
		}
		model.addAttribute("registers", exams);
		Page<Examination> examinations = null;
		try {
			examinations = serviceexamination.searchExamination(text, 0);
		} catch (PageNumberIncorrectException e) {
			model.addAttribute("error", e.getMessage());
		} finally {
			model.addAttribute("activeMenu", "examination");
			model.addAttribute("search", text);
			model.addAttribute("isSearch", true);
			model.addAttribute("allExamination", examinations);
			return "/examination/listExamination";
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/search/page/{page}", method = RequestMethod.GET)
	public String searchExaminations(
			@RequestParam(value = "search") String text,
			@PathVariable Integer page, Model model, HttpServletRequest request) {
		List<Examination> exams= Lists.newArrayList();
		if (request.getUserPrincipal() != null) {	
			User u = userService.getUserByUsername(request.getUserPrincipal()
					.getName());
			if (u != null)
				exams = serviceexamination.findExaminationsByUserId(u);
		}
		model.addAttribute("registers", exams);
		Page<Examination> examinations = null;
		try {
			examinations = serviceexamination.searchExamination(text, page);
		} catch (PageNumberIncorrectException e) {
			model.addAttribute("error", e.getMessage());
		} finally {
			model.addAttribute("activeMenu", "examination");
			model.addAttribute("search", text);
			model.addAttribute("isSearch", true);
			model.addAttribute("allExamination", examinations);
			return "/examination/listExamination";
		}
	}

	// Muestra un listado con todos las convocatorias y el formulario para crear
	// una nueva
	@SuppressWarnings("finally")
	@RequestMapping(value = "/**")
	public String showAllExamination(Model model, HttpServletRequest request) {
		List<Examination> exams= Lists.newArrayList();
		if (request.getUserPrincipal() != null) {	
			User u = userService.getUserByUsername(request.getUserPrincipal()
					.getName());
			if (u != null)
				exams = serviceexamination.findExaminationsByUserId(u);
		}
		model.addAttribute("registers", exams);
		Page<Examination> examinations = null;
		try {
			examinations = serviceexamination.getAllExamination(0);
		} catch (PageNumberIncorrectException e) {
			model.addAttribute("error", e.getMessage());
		} finally {
			model.addAttribute("allExamination", examinations);
			model.addAttribute("activeMenu", "examination");
			model.addAttribute("isNew", true);
			if (!model.containsAttribute("exam"))
				model.addAttribute("exam", new Examination());
			return "/examination/listExamination";
		}
	}
}
