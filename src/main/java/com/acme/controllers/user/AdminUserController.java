package com.acme.controllers.user;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acme.exception.PageNumberIncorrectException;
import com.acme.exception.UserNoExistException;
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
import com.acme.services.GeographyService;
import com.acme.services.UserService;

@Controller
@RequestMapping({ "/admin/user" })
public class AdminUserController {

	@Autowired
	private UserService serviceuser;

	@Autowired
	private GeographyService servicegeography;

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
		model.addAttribute("activeMenu", "user");
		return "/user/oneUser";
	}

	// Devuelve el usuario con id indicado en la URL
	@RequestMapping(value = "/edit/id/{iduser}", method = RequestMethod.GET)
	public String gettUser(@PathVariable Long iduser, Model model,
			RedirectAttributes redirectAttrs, HttpServletRequest response) {
		User u;
		try {
			u = serviceuser.getUserById(iduser);
		} catch (UserNoExistException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
			return "redirect:/acme/admin/user/create";
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
	public String createRoleAdmin(@PathVariable Long iduser,
			@ModelAttribute("user") User u, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttrs) {
		User user;
		try {
			user = serviceuser.getUserById(u.getId());
		} catch (UserNoExistException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
			return "redirect:/acme/admin/user/create";
		}
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
	public String createRoleCustomer(@PathVariable Long iduser,
			@ModelAttribute("user") User u, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttrs) {
		User user;
		try {
			user = serviceuser.getUserById(u.getId());
		} catch (UserNoExistException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
			return "redirect:/acme/admin/user/create";
		}
		user.addRoleToUser(new Customer(), UserType.ROLE_CUSTOMER);
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
	public String createRoleWroker(@PathVariable Long iduser,
			@ModelAttribute("user") User u, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttrs) {
		User user;
		try {
			user = serviceuser.getUserById(u.getId());
		} catch (UserNoExistException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
			return "redirect:/acme/admin/user/create";
		}
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
	public String createRoleCompany(@PathVariable Long iduser,
			@ModelAttribute("user") User u, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttrs) {
		User user;
		try {
			user = serviceuser.getUserById(u.getId());
		} catch (UserNoExistException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
			return "redirect:/acme/admin/user/create";
		}
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
	public String createRoleReviewer(@PathVariable Long iduser,
			@ModelAttribute("user") User u, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttrs) {
		User user;
		try {
			user = serviceuser.getUserById(u.getId());
		} catch (UserNoExistException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
			return "redirect:/acme/admin/user/create";
		}
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
	public String removeRoleAdmin(@PathVariable Long iduser,
			@ModelAttribute("user") User u, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttrs) {
		User user;
		try {
			user = serviceuser.getUserById(u.getId());
		} catch (UserNoExistException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
			return "redirect:/acme/admin/user/create";
		}
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
	public String removeRoleCustomer(@PathVariable Long iduser,
			@ModelAttribute("user") User u, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttrs) {
		User user;
		try {
			user = serviceuser.getUserById(u.getId());
		} catch (UserNoExistException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
			return "redirect:/acme/admin/user/create";
		}
		user.removeRoleToUser(UserType.ROLE_CUSTOMER);
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
	public String removeRoleWroker(@PathVariable Long iduser,
			@ModelAttribute("user") User u, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttrs) {
		User user;
		try {
			user = serviceuser.getUserById(u.getId());
		} catch (UserNoExistException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
			return "redirect:/acme/admin/user/create";
		}
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
	public String removeRoleCompany(@PathVariable Long iduser,
			@ModelAttribute("user") User u, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttrs) {
		User user;
		try {
			user = serviceuser.getUserById(u.getId());
		} catch (UserNoExistException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
			return "redirect:/acme/admin/user/create";
		}
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
	public String removeRoleReviewer(@PathVariable Long iduser,
			@ModelAttribute("user") User u, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttrs) {
		User user;
		try {
			user = serviceuser.getUserById(u.getId());
		} catch (UserNoExistException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
			return "redirect:/acme/admin/user/create";
		}
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
		User user;
		try {
			user = serviceuser.getUserById(u.getId());
		} catch (UserNoExistException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
			return "redirect:/acme/admin/user/create";
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
		return "redirect:/acme/admin/user/edit/id/{id}";
	}

	// Borra el usuario indicado en la URL por id
	@RequestMapping(value = "/delete/id/{iduser}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable Long iduser, Model model,
			RedirectAttributes redirectAttrs, HttpServletRequest response) {
		User user;
		try {
			user = serviceuser.getUserById(iduser);
		} catch (UserNoExistException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
			return "redirect:/acme/admin/user/create";
		}
		try {
			serviceuser.removeUser(user.getId());
		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("error", "exception.noborrar");
			return "redirect:/acme/admin/user/";
		}
		redirectAttrs.addFlashAttribute("info", "user.delete");
		return "redirect:/acme/admin/user/";
	}

	@RequestMapping(value = "/search/", method = RequestMethod.GET, params = { "search" })
	public String searchUsers(@RequestParam(value = "search") String text,
			Model model) {
		Page<User> users = serviceuser.searchUsers(text, 0);
		model.addAttribute("activeMenu", "user");
		model.addAttribute("search", text);
		model.addAttribute("isSearch", true);
		model.addAttribute("allUsers", users);
		return "/user/listUser";
	}

	@RequestMapping(value = "/search/page/{page}", method = RequestMethod.GET)
	public String searchUsers(@RequestParam(value = "search") String text,
			@PathVariable Integer page, Model model) {
		Page<User> users = serviceuser.searchUsers(text, page);
		model.addAttribute("activeMenu", "user");
		model.addAttribute("search", text);
		model.addAttribute("isSearch", true);
		model.addAttribute("allUsers", users);
		return "/user/listUser";
	}

	// Muestra un listado con todos los usuarios
	@SuppressWarnings("finally")
	@RequestMapping({ "/**" })
	public String showAllUSer(Model model) {
		Page<User> users = null;
		try {
			users = serviceuser.getAllUsers(0);
		} catch (PageNumberIncorrectException e) {
			model.addAttribute("error", e.getMessage());
			users = serviceuser.getAllUsers(0);
		} finally {
			model.addAttribute("allUsers", users);
			model.addAttribute("activeMenu", "user");
			model.addAttribute("isNew", true);
			return "/user/listUser";
		}
	}

	// Muestra un listado con todos los certificados
	@SuppressWarnings("finally")
	@RequestMapping(value = "/page/{p}", method = RequestMethod.GET)
	public String showAllUsers(Model model, @PathVariable("p") Integer p) {
		Page<User> users=null;
		try {
			users = serviceuser.getAllUsers(p);
		} catch (PageNumberIncorrectException e) {
			model.addAttribute("error", "pagination.sobrepasado");
			users = serviceuser.getAllUsers(0);
		} finally {
			model.addAttribute("allUsers", users);
			model.addAttribute("activeMenu", "user");
			model.addAttribute("isNew", true);
			return "/user/listUser";
		}
	}
}
