package com.acme.controllers.register;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acme.exception.DateIncorrectException;
import com.acme.exception.ExaminationNoExistException;
import com.acme.exception.RegisterNoExistException;
import com.acme.model.examination.Examination;
import com.acme.model.examination.Register;
import com.acme.model.user.User;
import com.acme.services.ExaminationService;
import com.acme.services.RegisterService;
import com.acme.services.UserService;

@Controller
@RequestMapping({ "/customer/register" })
public class CustomerRegisterController {

	@Autowired
	private ExaminationService serviceexamination;
	
	@Autowired
	private UserService serviceuser;
	
	@Autowired
	private RegisterService serviceregister;
	
	@ModelAttribute("allRegisters")
	public List<Register> getAllCertifications(HttpServletRequest request) {
		return serviceregister.allRegisterByUserUsername(request.getUserPrincipal().getName());
	}
	
	// Muestra un listado con todos las convocatorias y el formulario para crear una nueva
	@RequestMapping({ "", "/**", "/list" })
	public String showAllExamination(Model model,RedirectAttributes redirectAttrs) {
		model.addAttribute("activeMenu", "register");
		return "/register/listRegister";
	}
	
	@SuppressWarnings("finally")
	@RequestMapping(value = "/examination/id/{idExam}", method = RequestMethod.GET)
	public String createRegister(@PathVariable Long idExam, Model model,
			RedirectAttributes redirectAttrs, HttpServletRequest request) {
		try {
			Examination exam=serviceexamination.getExaminationById(idExam);
			User u = serviceuser.getUserByUsername(request.getUserPrincipal().getName());
			Register reg=exam.createRegisterWithoutPay(u);
			serviceregister.saveRegister(reg);
			redirectAttrs.addFlashAttribute("info", "registro.creado");
		}
		catch (DateIncorrectException e){
			redirectAttrs.addFlashAttribute("error", e.getMessage());
		}
		catch (ExaminationNoExistException e){
			redirectAttrs.addFlashAttribute("error", e.getMessage());
		}
		finally
		{
			return "redirect:/acme/customer/register/";
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/delete/id/{idReg}", method = RequestMethod.GET)
	public String deleteExamination(@PathVariable Long idReg, Model model,
			RedirectAttributes redirectAttrs) {
		try {
			Register reg=serviceregister.getRegisterById(idReg);
			serviceregister.removeRegister(reg);
			redirectAttrs.addFlashAttribute("info", "registro.delete");
		}
		catch (RegisterNoExistException e){
			redirectAttrs.addFlashAttribute("error", e.getMessage());
		}
		catch (Exception e){
			redirectAttrs.addFlashAttribute("error", "registro.noborrar");
		}
		finally
		{
			return "redirect:/acme/customer/register/";
		}
	}

}
