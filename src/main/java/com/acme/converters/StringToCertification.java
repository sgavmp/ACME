package com.acme.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.acme.model.certification.Certification;
import com.acme.repository.CertificationRepository;


@Component
public class StringToCertification implements Converter<String, Certification> {

	@Autowired
	CertificationRepository certificationRepository;

	@Override
	public Certification convert(String text) {
		Certification result;
		Long id;

		try {
			id = Long.valueOf(text);
			result = certificationRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
