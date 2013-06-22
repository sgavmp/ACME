package com.acme.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acme.exception.UserNoExistException;
import com.acme.model.geography.Country;
import com.acme.model.user.User;
import com.acme.services.GeographyService;
import com.acme.services.UserService;

@Controller
@RequestMapping({ "/profile" })
public class ProfileController {

	@Autowired
	private UserService serviceuser;

	@Autowired
	private GeographyService servicegeography;

	@ModelAttribute("allCountry")
	public List<Country> getAllCountry() {
		return servicegeography.getAllCountry();
	}

	// Devuelve el usuario autentificado
	@RequestMapping(method = RequestMethod.GET)
	public String gettUser(Model model,
			RedirectAttributes redirectAttrs, HttpServletRequest request) {
		User u = serviceuser.getUserByUsername(request.getUserPrincipal().getName());
		if (u == null) {
			redirectAttrs.addFlashAttribute("error", "usuario.noexist");
			return "redirect:/acme/profile";
		}
		model.addAttribute("statesInCountry", u.getCountry().getStates());
		model.addAttribute("citiesByState", u.getState().getCities());
		model.addAttribute("user", u);
		model.addAttribute("isNew", false);
		model.addAttribute("isProfile", true);
		model.addAttribute("activeMenu", "profile");
		return "/user/oneUser";
	}

	// Modifica el usuario autentficado
	@RequestMapping(method = RequestMethod.POST)
	public String editUser(@ModelAttribute("user") @Valid User u, Model model,
			RedirectAttributes redirectAttrs, HttpServletRequest request,
			BindingResult result) {
		// Comprueba si hay errores de validacion
		if (result.hasErrors()) {
			model.addAttribute("isNew", false);
			model.addAttribute("user", u);
			model.addAttribute("isProfile", true);
			model.addAttribute("activeMenu", "profile");
			return "/user/oneUser";
		}
		User user;
		try {
			user = serviceuser.getUserById(u.getId());
		} catch (UserNoExistException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
			return "redirect:/acme/profile";
		}
		if (u.getId()!=serviceuser.getUserByUsername(request.getUserPrincipal().getName()).getId()) {
			redirectAttrs.addFlashAttribute("error", "usuario.incorrecto");
			return "redirect:/acme/profile";
		}
		user.setValuesFromUser(u);
		User uMod = serviceuser.updateUser(user);
		model.addAttribute("user", uMod);
		model.addAttribute("isNew", false);
		model.addAttribute("isProfile", true);
		model.addAttribute("activeMenu", "profile");
		model.addAttribute("info", "user.modify");
		return "/user/oneUser";
	}
}
