package com.acme.controllers;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;
import com.acme.model.examination.Examination;
import com.acme.model.geography.City;
import com.acme.model.geography.Country;
import com.acme.model.geography.State;
import com.acme.model.user.Admin;
import com.acme.model.user.Company;
import com.acme.model.user.Customer;
import com.acme.model.user.Reviewer;
import com.acme.model.user.User;
import com.acme.model.user.UserType;
import com.acme.model.user.Worker;
import com.acme.services.CertificationService;
import com.acme.services.ExaminationService;
import com.acme.services.GeographyService;
import com.acme.services.UserService;

@Controller
@RequestMapping({ "/examination" })
public class ExaminationController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ExaminationService serviceexamination;
	
	@Autowired
	private CertificationService servicecertification;
	
	@ModelAttribute("allExamination")
	public List<Examination> getAllExaminations() {
		return serviceexamination.getAllExamination();
	}
	
	@ModelAttribute("allCertifications")
	public List<Certification> getAllCertifications() {
		return servicecertification.getAllCertification();
	}
	
	// Muestra un listado con todos las convocatorias y el formulario para crear una nueva
	@RequestMapping({ "", "/**", "/list" })
	public String showAllExamination(Model model) {
		model.addAttribute("activeMenu", "examination");
		model.addAttribute("isNew", true);
		model.addAttribute("exam", new Examination());
		return "/examination/listExamination";
	}
	
	@RequestMapping(value = "/edit/id/{idExam}", method = RequestMethod.GET)
	public String editExamination(@PathVariable Long idExam, Model model,
			RedirectAttributes redirectAttrs, HttpServletRequest response) {
		Examination exam=serviceexamination.getExaminationById(idExam);
		if (exam == null) {
			redirectAttrs.addFlashAttribute("error", "certification.noexist");
			response.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE,
					HttpStatus.NOT_FOUND);
			return "redirect:/acme/certification/family";
		}
		model.addAttribute("exam", exam);
		model.addAttribute("isNew", false);
		model.addAttribute("activeMenu", "examination");
		return "/examination/listExamination";
	}
	
	@RequestMapping(value = "/create", params = "create", method = RequestMethod.POST)
	public String createExamination(Model model,
			@ModelAttribute("exam") @Valid Examination exam,
			BindingResult result, RedirectAttributes redirectAttrs) {
		// Comprueba si hay errores de validacion
		if (result.hasErrors()) {
			model.addAttribute("isNew", true);
			model.addAttribute("activeMenu", "examination");
			return "/examination/listExamination";
		}
		exam.setCertification(servicecertification.getCertificationById(exam.getCertification().getId()));
		serviceexamination.saveExamination(exam);
		redirectAttrs.addFlashAttribute("info", "examination.create");
		return "redirect:/acme/examination/";
	}
	
	@RequestMapping(value = "/delete/id/{idExam}", method = RequestMethod.GET)
	public String deleteExamination(@PathVariable Long idExam, Model model,
			RedirectAttributes redirectAttrs, HttpServletRequest response) {
		try {
			Examination exam=serviceexamination.getExaminationById(idExam);
			if (exam == null) {
				redirectAttrs.addFlashAttribute("error", "familia.noexist");
				response.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE,
						HttpStatus.NOT_FOUND);
			}
			serviceexamination.removeExamination(idExam);
			redirectAttrs.addFlashAttribute("info", "examination.delete");
		}
		catch (Exception e){
			redirectAttrs.addFlashAttribute("error", "examination.asociado");
		}
		finally
		{
			return "redirect:/acme/examination/";
		}
	}

	// Modifica los datos de la familia profesional pasado por POST
	@RequestMapping(value = "/edit/id/{idExam}", method = RequestMethod.POST)
	public String editExamination(@PathVariable Integer idExam, Model model,
			@ModelAttribute("exam") @Valid Examination exam,
			BindingResult result) {
		// Comprueba si hay errores de validacion
		if (result.hasErrors()) {
			model.addAttribute("isNew", false);
			model.addAttribute("exam", exam);
			model.addAttribute("activeMenu", "examination");
			return "/examination/listExamination";
		}
		serviceexamination.saveExamination(exam);
		model.addAttribute("activeMenu", "examination");
		model.addAttribute("isNew", true);
		model.addAttribute("info", "exam.modify");
		return "/examination/listExamination";
	}
}
