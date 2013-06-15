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
@RequestMapping({ "/ajax" })
public class AjaxController {

	@Autowired
	private MessageSource messageSource;

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

}
