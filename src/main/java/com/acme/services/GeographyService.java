package com.acme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.model.geography.City;
import com.acme.model.geography.Country;
import com.acme.model.geography.State;
import com.acme.repository.CityRepository;
import com.acme.repository.CountryRepository;
import com.acme.repository.StateRepository;

@Service
public class GeographyService {
	
	@Autowired
	private CityRepository repositorycity;
	
	@Autowired
	private StateRepository repositorystate;
	
	@Autowired
	private CountryRepository repositorycountry;
	
	public List<Country> getAllCountry() {
		return (List<Country>) repositorycountry.findAll();
	}
	
	public Country getCountryById(Long id) {
		return repositorycountry.findOne(id);
	}
	
	public List<State> getAllState() {
		return (List<State>) repositorystate.findAll();
	}
	
	public State getStateById(Long id) {
		return repositorystate.findOne(id);
	}
	
	public List<City> getAllCity() {
		return (List<City>) repositorycity.findAll();
	}
	
	public City getCityById(Long id) {
		return repositorycity.findOne(id);
	}
}
