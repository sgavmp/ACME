package com.acme.controllers.examination;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acme.exception.CertificationNoExistException;
import com.acme.exception.ExaminationNoExistException;
import com.acme.exception.PageNumberIncorrectException;
import com.acme.model.certification.Certification;
import com.acme.model.examination.Examination;
import com.acme.services.CertificationService;
import com.acme.services.ExaminationService;

@Controller
@RequestMapping({ "/admin/examination" })
public class AdminExaminationController {

	@Autowired
	private ExaminationService serviceexamination;
	
	@Autowired
	private CertificationService servicecertification;

	@ModelAttribute("allExamination")
	public Page<Examination> gettAllExamination() throws PageNumberIncorrectException{
		return serviceexamination.getAllExamination(0);
	}
	
	@ModelAttribute("allCertifications")
	public List<Certification> getAllCertifications() {
		return servicecertification.getAllCertification();
	}
	
	@RequestMapping(value = "/edit/id/{idExam}", method = RequestMethod.GET)
	public String editExamination(@PathVariable Long idExam, Model model,
			RedirectAttributes redirectAttrs) {
		Examination exam;
		try {
			exam = serviceexamination.getExaminationById(idExam);
		} catch (ExaminationNoExistException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
			return "redirect:/acme/certification/family";
		}
		model.addAttribute("exam", exam);
		model.addAttribute("isNew", false);
		model.addAttribute("noList", true);
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
			model.addAttribute("noList", true);
			return "examination/listExamination";
		}
		try {
			exam.setCertification(servicecertification.getCertificationById(exam.getCertification().getId()));
		} catch (CertificationNoExistException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
			return "redirect:/acme/examination/";
		}
		serviceexamination.saveExamination(exam);
		redirectAttrs.addFlashAttribute("info", "examination.create");
		return "redirect:/acme/examination/";
	}
	
	@SuppressWarnings("finally")
	@RequestMapping(value = "/delete/id/{idExam}", method = RequestMethod.GET)
	public String deleteExamination(@PathVariable Long idExam, Model model,
			RedirectAttributes redirectAttrs) {
		try {
			Examination exam=serviceexamination.getExaminationById(idExam);
			serviceexamination.removeExamination(idExam);
			redirectAttrs.addFlashAttribute("info", "examination.delete");
		}
		catch (ExaminationNoExistException e) {
			redirectAttrs.addFlashAttribute("error", "familia.noexist");
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
			model.addAttribute("noList", true);
			return "/examination/listExamination";
		}
		serviceexamination.saveExamination(exam);
		model.addAttribute("activeMenu", "examination");
		model.addAttribute("isNew", true);
		model.addAttribute("info", "examination.modify");
		model.addAttribute("noList", true);
		return "/examination/listExamination";
	}
}
