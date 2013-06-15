package com.acme.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.acme.model.certification.FamilyProfessional;
import com.acme.repository.FamilyProfessionalRepository;


@Component
public class StringToFamilyProfessional implements Converter<String, FamilyProfessional> {

	@Autowired
	FamilyProfessionalRepository repositoryFamilyProfessional;

	@Override
	public FamilyProfessional convert(String text) {
		FamilyProfessional result;
		Long id;

		try {
			id = Long.valueOf(text);
			result = repositoryFamilyProfessional.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
