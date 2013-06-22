package com.acme.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.acme.model.geography.State;
import com.acme.repository.StateRepository;


@Component
public class StringToState implements Converter<String, State> {

	@Autowired
	StateRepository repositoryState;

	@Override
	public State convert(String text) {
		State result;
		Long id;

		try {
			id = Long.valueOf(text);
			result = repositoryState.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
