package com.acme.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;
import org.apache.commons.lang.time.DateUtils;
import com.acme.repository.UserRepository;



@Component
public class StringToUser implements Converter<String, Date> {

	@Autowired
	UserRepository repositoryUser;

	@Override
	public Date convert(String text) {
		Date result;
		String[] formats = {"'T'HH:mm:ss", "yyyy-MM-dd"};
		try {
			result = DateUtils.parseDate(text, formats);;
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
