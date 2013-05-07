package com.acme.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.acme.model.certification.Certification;
import com.acme.model.user.User;
import com.acme.services.CertificationService;
import com.acme.services.UserService;

@Controller
@RequestMapping({ "/user" })
public class UserController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserService serviceuser;

	@ModelAttribute("allUsers")
	public List<User> getAllUsers() {
		return serviceuser.getAllUsers();
	}

	// Muestra un listado con todos los usuarios
	@RequestMapping({ "", "/**", "/list" })
	public String showAllUSer(Model model) {
		model.addAttribute("activeMenu", "users");
		return "/user/listUser";
	}

	// Muestra el formulario de crear un nuevo usuario
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createCertificate(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("isNew", true);
		model.addAttribute("activeMenu", "users");
		return "/user/oneUser";
	}
}
