package com.acme.controllers;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acme.model.geography.Country;
import com.acme.model.geography.State;
import com.acme.model.user.Customer;
import com.acme.model.user.User;
import com.acme.model.user.UserType;
import com.acme.services.GeographyService;
import com.acme.services.UserService;

@Controller
@RequestMapping({ "/signin" })
public class SigninController {

	@Autowired
	private UserService serviceuser;

	@Autowired
	private GeographyService servicegeography;

	@ModelAttribute("allCountry")
	public List<Country> getAllCountry() {
		return servicegeography.getAllCountry();
	}

	// Muestra el formulario de crear un nuevo usuario
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String createCertificate(Model model) {
		User user = new User();
		Set<State> provincias = getAllCountry().get(0).getStates();
		model.addAttribute("statesInCountry", provincias);
		model.addAttribute("citiesByState",
				((State) provincias.toArray()[0]).getCities());
		model.addAttribute("user", user);
		model.addAttribute("isNew", true);
		model.addAttribute("isSignIn", true);
		model.addAttribute("activeMenu", "users");
		return "/user/oneUser";
	}

	// Crea un nuevo usuario de rol customer con los datos pasado por POST
	@RequestMapping(value = "", params = "create", method = RequestMethod.POST)
	public String createUser(Model model,
			@ModelAttribute("user") @Valid User user, BindingResult result,
			RedirectAttributes redirectAttrs) {
		// Comprueba si hay errores de validacion
		if (result.hasErrors()) {
			model.addAttribute("isNew", true);
			model.addAttribute("activeMenu", "user");
			return "/user/oneUser";
		}
		user.setCity(servicegeography.getCityById(user.getCity().getId()));
		user.setState(servicegeography.getStateById(user.getState().getId()));
		user.setCountry(servicegeography.getCountryById(user.getCountry()
				.getId()));
		user.addRoleToUser(new Customer(), UserType.ROLE_CUSTOMER);
		serviceuser.createUser(user);
		redirectAttrs.addAttribute("id", user.getId()).addFlashAttribute(
				"info", "user.create");
		return "redirect:/acme/profile";
	}
}
