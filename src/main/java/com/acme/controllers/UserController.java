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
import com.acme.services.GeographyService;
import com.acme.services.UserService;

@Controller
@RequestMapping({ "/user" })
public class UserController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserService serviceuser;

	@Autowired
	private GeographyService servicegeography;

	@ModelAttribute("allUsers")
	public List<User> getAllUsers() {
		return serviceuser.getAllUsers();
	}

	@RequestMapping(value = "/country/{id}/states", method = RequestMethod.GET)
	public @ResponseBody
	Set<State> getStatesByCountryId(@PathVariable Long id, Model model) {
		return servicegeography.getCountryById(id).getStates();
	}

	@RequestMapping(value = "/state/{id}/cities", method = RequestMethod.GET)
	public @ResponseBody
	Set<City> getCitiesByStateId(@PathVariable Long id, Model model) {
		return servicegeography.getStateById(id).getCities();
	}

	@ModelAttribute("allCountry")
	public List<Country> getAllCountry() {
		return servicegeography.getAllCountry();
	}

	// Muestra el formulario de crear un nuevo usuario
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createCertificate(Model model) {
		User user = new User();
		Set<State> provincias = getAllCountry().get(0).getStates();
		model.addAttribute("statesInCountry", provincias);
		model.addAttribute("citiesByState",
				((State) provincias.toArray()[0]).getCities());
		model.addAttribute("user", user);
		model.addAttribute("isNew", true);
		model.addAttribute("activeMenu", "users");
		return "/user/oneUser";
	}

	// Devuelve el usuario con id indicado en la URL
	@RequestMapping(value = "/edit/id/{iduser}", method = RequestMethod.GET)
	public String gettUser(@PathVariable Long iduser, Model model,
			RedirectAttributes redirectAttrs, HttpServletRequest response) {
		User u = serviceuser.getUserById(iduser);
		if (u == null) {
			redirectAttrs.addFlashAttribute("error", "usuario.noexist");
			response.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE,
					HttpStatus.NOT_FOUND);
			return "redirect:/acme/user/create";
		}
		model.addAttribute("statesInCountry", u.getCountry().getStates());
		model.addAttribute("citiesByState", u.getState().getCities());
		model.addAttribute("user", u);
		model.addAttribute("isNew", false);
		model.addAttribute("activeMenu", "user");
		return "/user/oneUser";
	}

	// Crea el rol de Admin
	@RequestMapping(value = "/edit/id/{iduser}", params = "createRoleAdmin", method = RequestMethod.POST)
	public String createRoleAdmin(@ModelAttribute("user") User u,
			final BindingResult bindingResult, Model model) {
		User user = serviceuser.getUserById(u.getId());
		user.addRoleToUser(new Admin(), UserType.ROLE_ADMIN);
		user = serviceuser.updateUser(user);
		model.addAttribute("statesInCountry", user.getCountry().getStates());
		model.addAttribute("citiesByState", user.getState().getCities());
		model.addAttribute("user", user);
		model.addAttribute("activeMenu", "user");
		model.addAttribute("info", "user.admin.crete");
		return "/user/oneUser";
	}

	// Crea el rol de Customer
	@RequestMapping(value = "/edit/id/{iduser}", params = "createRoleCustomer", method = RequestMethod.POST)
	public String createRoleCustomer(@ModelAttribute("user") User u,
			final BindingResult bindingResult, Model model) {
		User user = serviceuser.getUserById(u.getId());
		user.addRoleToUser(new Customer(), UserType.ROLE_USER);
		user = serviceuser.updateUser(user);
		model.addAttribute("statesInCountry", user.getCountry().getStates());
		model.addAttribute("citiesByState", user.getState().getCities());
		model.addAttribute("user", user);
		model.addAttribute("activeMenu", "user");
		model.addAttribute("info", "user.customer.crete");
		return "/user/oneUser";
	}

	// Crea el rol de Company
	@RequestMapping(value = "/edit/id/{iduser}", params = "createRoleCompany", method = RequestMethod.POST)
	public String createRoleWroker(@ModelAttribute("user") User u,
			final BindingResult bindingResult, Model model) {
		User user = serviceuser.getUserById(u.getId());
		user.addRoleToUser(new Company(), UserType.ROLE_COMPANY);
		user = serviceuser.updateUser(user);
		model.addAttribute("statesInCountry", user.getCountry().getStates());
		model.addAttribute("citiesByState", user.getState().getCities());
		model.addAttribute("user", user);
		model.addAttribute("activeMenu", "user");
		model.addAttribute("info", "user.company.crete");
		return "/user/oneUser";
	}

	// Crea el rol de Reviewer
	@RequestMapping(value = "/edit/id/{iduser}", params = "createRoleReviewer", method = RequestMethod.POST)
	public String createRoleCompany(@ModelAttribute("user") User u,
			final BindingResult bindingResult, Model model) {
		User user = serviceuser.getUserById(u.getId());
		user.addRoleToUser(new Reviewer(), UserType.ROLE_REVIEWER);
		user = serviceuser.updateUser(user);
		model.addAttribute("statesInCountry", user.getCountry().getStates());
		model.addAttribute("citiesByState", user.getState().getCities());
		model.addAttribute("user", user);
		model.addAttribute("activeMenu", "user");
		model.addAttribute("info", "user.reviewer.crete");
		return "/user/oneUser";
	}

	// Crea el rol de Worker
	@RequestMapping(value = "/edit/id/{iduser}", params = "createRoleWorker", method = RequestMethod.POST)
	public String createRoleReviewer(@ModelAttribute("user") User u,
			final BindingResult bindingResult, Model model) {
		User user = serviceuser.getUserById(u.getId());
		user.addRoleToUser(new Worker(), UserType.ROLE_WORKER);
		user = serviceuser.updateUser(user);
		model.addAttribute("statesInCountry", user.getCountry().getStates());
		model.addAttribute("citiesByState", user.getState().getCities());
		model.addAttribute("user", user);
		model.addAttribute("activeMenu", "user");
		model.addAttribute("info", "user.worker.crete");
		return "/user/oneUser";
	}

	// Borra el rol de Administrador
	@RequestMapping(value = "/edit/id/{iduser}", params = "removeRoleAdmin", method = RequestMethod.POST)
	public String removeRoleAdmin(@ModelAttribute("user") User u,
			final BindingResult bindingResult, Model model) {
		User user = serviceuser.getUserById(u.getId());
		user.removeRoleToUser(UserType.ROLE_ADMIN);
		user = serviceuser.updateUser(user);
		model.addAttribute("statesInCountry", user.getCountry().getStates());
		model.addAttribute("citiesByState", user.getState().getCities());
		model.addAttribute("user", user);
		model.addAttribute("activeMenu", "user");
		model.addAttribute("info", "user.admin.remove");
		return "/user/oneUser";
	}

	// Borra el rol de Customer
	@RequestMapping(value = "/edit/id/{iduser}", params = "removeRoleCustomer", method = RequestMethod.POST)
	public String removeRoleCustomer(@ModelAttribute("user") User u,
			final BindingResult bindingResult, Model model) {
		User user = serviceuser.getUserById(u.getId());
		user.removeRoleToUser(UserType.ROLE_USER);
		user = serviceuser.updateUser(user);
		model.addAttribute("statesInCountry", user.getCountry().getStates());
		model.addAttribute("citiesByState", user.getState().getCities());
		model.addAttribute("user", user);
		model.addAttribute("activeMenu", "user");
		model.addAttribute("info", "user.customer.remove");
		return "/user/oneUser";
	}

	// Borra el rol de Company
	@RequestMapping(value = "/edit/id/{iduser}", params = "removeRoleCompany", method = RequestMethod.POST)
	public String removeRoleWroker(@ModelAttribute("user") User u,
			final BindingResult bindingResult, Model model) {
		User user = serviceuser.getUserById(u.getId());
		user.removeRoleToUser(UserType.ROLE_COMPANY);
		user = serviceuser.updateUser(user);
		model.addAttribute("statesInCountry", user.getCountry().getStates());
		model.addAttribute("citiesByState", user.getState().getCities());
		model.addAttribute("user", user);
		model.addAttribute("activeMenu", "user");
		model.addAttribute("info", "user.company.remove");
		return "/user/oneUser";
	}

	// Borra el rol de Reviewer
	@RequestMapping(value = "/edit/id/{iduser}", params = "removeRoleReviewer", method = RequestMethod.POST)
	public String removeRoleCompany(@ModelAttribute("user") User u,
			final BindingResult bindingResult, Model model) {
		User user = serviceuser.getUserById(u.getId());
		user.removeRoleToUser(UserType.ROLE_REVIEWER);
		user = serviceuser.updateUser(user);
		model.addAttribute("statesInCountry", user.getCountry().getStates());
		model.addAttribute("citiesByState", user.getState().getCities());
		model.addAttribute("user", user);
		model.addAttribute("activeMenu", "user");
		model.addAttribute("info", "user.reviewer.remove");
		return "/user/oneUser";
	}

	// Borra el rol de Worker
	@RequestMapping(value = "/edit/id/{iduser}", params = "removeRoleWorker", method = RequestMethod.POST)
	public String removeRoleReviewer(@ModelAttribute("user") User u,
			final BindingResult bindingResult, Model model) {
		User user = serviceuser.getUserById(u.getId());
		user.removeRoleToUser(UserType.ROLE_WORKER);
		user = serviceuser.updateUser(user);
		model.addAttribute("statesInCountry", user.getCountry().getStates());
		model.addAttribute("citiesByState", user.getState().getCities());
		model.addAttribute("user", user);
		model.addAttribute("activeMenu", "user");
		model.addAttribute("info", "user.worker.remove");
		return "/user/oneUser";
	}

	// Modifica el usuario con el id dado
	@RequestMapping(value = "/edit/id/{iduser}", method = RequestMethod.POST)
	public String editUser(@PathVariable Long iduser,
			@ModelAttribute("user") @Valid User u, Model model,
			RedirectAttributes redirectAttrs, HttpServletRequest response,
			BindingResult result) {
		// Comprueba si hay errores de validacion
		if (result.hasErrors()) {
			model.addAttribute("isNew", false);
			model.addAttribute("user", u);
			model.addAttribute("activeMenu", "user");
			return "/user/oneUser";
		}
		User user = serviceuser.getUserById(iduser);
		if (u == null) {
			redirectAttrs.addFlashAttribute("error", "usuario.noexist");
			response.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE,
					HttpStatus.NOT_FOUND);
			return "redirect:/acme/user/create";
		}
		user.setValuesFromUser(u);
		user.setCity(servicegeography.getCityById(user.getCity().getId()));
		user.setState(servicegeography.getStateById(user.getState().getId()));
		user.setCountry(servicegeography.getCountryById(user.getCountry()
				.getId()));
		User uMod = serviceuser.updateUser(user);
		model.addAttribute("user", uMod);
		model.addAttribute("isNew", false);
		model.addAttribute("activeMenu", "user");
		model.addAttribute("info", "user.modify");
		return "/user/oneUser";
	}

	// Crea un nuevo usuario con los datos pasado por POST
	@RequestMapping(value = "/create", params = "create", method = RequestMethod.POST)
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
		serviceuser.createUser(user);
		redirectAttrs.addAttribute("id", user.getId()).addFlashAttribute(
				"info", "user.create");
		return "redirect:/acme/user/edit/id/{id}";
	}

	// Borra el usuario indicado en la URL por id
	@RequestMapping(value = "/delete/id/{iduser}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable Long iduser, Model model,
			RedirectAttributes redirectAttrs, HttpServletRequest response) {
		User user = serviceuser.getUserById(iduser);
		if (user == null) {
			redirectAttrs.addFlashAttribute("error", "user.noexist");
			response.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE,
					HttpStatus.NOT_FOUND);
			return "redirect:/acme/user/";
		}
		serviceuser.removeUser(iduser);
		redirectAttrs.addFlashAttribute("info", "user.delete");
		return "redirect:/acme/user/";
	}

	// Muestra un listado con todos los usuarios
	@RequestMapping({ "", "/**", "/list" })
	public String showAllUSer(Model model) {
		model.addAttribute("activeMenu", "users");
		return "/user/listUser";
	}
}
