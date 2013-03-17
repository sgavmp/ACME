package com.acme.model.exam;

import com.google.common.base.Predicate;

public class IsOptionCorrectPredicates implements Predicate<Option> {

	public boolean apply(Option arg0) {
		return (arg0.isCorrect()==true);
	}

}
