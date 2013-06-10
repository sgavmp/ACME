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
import com.acme.model.examination.Register;
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
import com.acme.services.RegisterService;
import com.acme.services.UserService;

@Controller
@RequestMapping({ "/register" })
public class RegisterController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ExaminationService serviceexamination;
	
	@Autowired
	private UserService serviceuser;
	
	@Autowired
	private RegisterService serviceregister;
	
	@ModelAttribute("allRegisters")
	public List<Register> getAllCertifications() {
		return serviceregister.getAllRegisters();
	}
	
	// Muestra un listado con todos las convocatorias y el formulario para crear una nueva
	@RequestMapping({ "", "/**", "/list" })
	public String showAllExamination(Model model) {
		model.addAttribute("activeMenu", "register");
		model.addAttribute("isNew", true);
		return "/register/listRegister";
	}
	
	@RequestMapping(value = "/examination/id/{idExam}", method = RequestMethod.GET)
	public String createRegister(@PathVariable Long idExam, Model model,
			RedirectAttributes redirectAttrs, HttpServletRequest request) {
		try {
			Examination exam=serviceexamination.getExaminationById(idExam);
			if (exam == null) {
				redirectAttrs.addFlashAttribute("error", "familia.noexist");
				request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE,
						HttpStatus.NOT_FOUND);
			}
			User u = serviceuser.getUserByUsername(request.getUserPrincipal().getName());
			Register reg=exam.createRegisterWithoutPay(u);
			serviceregister.saveRegister(reg);
			redirectAttrs.addFlashAttribute("info", "registro.creado");
		}
		catch (Exception e){
			redirectAttrs.addFlashAttribute("error", "examination.noexist");
		}
		finally
		{
			return "redirect:/acme/register/";
		}
	}

	@RequestMapping(value = "/delete/id/{idReg}", method = RequestMethod.GET)
	public String deleteExamination(@PathVariable Long idReg, Model model,
			RedirectAttributes redirectAttrs, HttpServletRequest response) {
		try {
			Register reg=serviceregister.getRegisterById(idReg);
			if (reg == null) {
				redirectAttrs.addFlashAttribute("error", "familia.noexist");
				response.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE,
						HttpStatus.NOT_FOUND);
			}
			serviceregister.removeRegister(reg);
			redirectAttrs.addFlashAttribute("info", "registro.delete");
		}
		catch (Exception e){
			redirectAttrs.addFlashAttribute("error", "registro.noborrar");
		}
		finally
		{
			return "redirect:/acme/register/";
		}
	}

}
