package com.acme.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.acme.model.user.User;
import com.acme.repository.UserRepository;



@Component
public class StringToDate implements Converter<String, User> {

	@Autowired
	UserRepository repositoryUser;

	@Override
	public User convert(String text) {
		User result;
		Long id;

		try {
			id = Long.valueOf(text);
			result = repositoryUser.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
