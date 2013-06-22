package com.acme.model.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Worker extends Role {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1065921407393177680L;

	public Worker() {

	}

}
