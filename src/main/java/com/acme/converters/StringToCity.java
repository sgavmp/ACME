package com.acme.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.acme.model.geography.City;
import com.acme.repository.CityRepository;



@Component
public class StringToCity implements Converter<String, City> {

	@Autowired
	CityRepository repositoryCity;

	@Override
	public City convert(String text) {
		City result;
		Long id;

		try {
			id = Long.valueOf(text);
			result = repositoryCity.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
