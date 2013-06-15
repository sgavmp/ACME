package com.acme.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.acme.model.certification.Certification;
import com.acme.model.geography.Country;
import com.acme.repository.CertificationRepository;
import com.acme.repository.CountryRepository;
import com.acme.services.GeographyService;


@Component
public class StringToCountry implements Converter<String, Country> {

	@Autowired
	CountryRepository repositoryCountry;

	@Override
	public Country convert(String text) {
		Country result;
		Long id;

		try {
			id = Long.valueOf(text);
			result = repositoryCountry.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
