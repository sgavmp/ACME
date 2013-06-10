package com.acme.util.validation.html;

import javax.persistence.Id;

import org.thymeleaf.dom.Element;

import net.sourceforge.html5val.ValidationPerformer;

public class IdPerformer implements ValidationPerformer<Id>{

	@Override
	public Class<Id> getConstraintClass() {
		return Id.class;
	}

	@Override
	public void putValidationCodeInto(Id constraint, Element element) {
		element.setAttribute("readonly", "readonly");
	}

}
