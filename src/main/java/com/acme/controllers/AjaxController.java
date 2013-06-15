package com.acme.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acme.model.geography.City;
import com.acme.model.geography.State;
import com.acme.services.GeographyService;

@Controller
@RequestMapping({ "/ajax" })
public class AjaxController {

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
