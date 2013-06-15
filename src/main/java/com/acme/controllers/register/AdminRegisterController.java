package com.acme.controllers.register;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acme.exception.RegisterNoExistException;
import com.acme.model.examination.Register;
import com.acme.services.RegisterService;

@Controller
@RequestMapping({ "/admin/register" })
public class AdminRegisterController {
	
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
		model.addAttribute("isAdmin",true);
		return "/register/listRegister";
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
			return "redirect:/acme/admin/register/";
		}
	}

}
