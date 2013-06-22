package com.acme.model.geography;

import javax.persistence.Entity;

import com.acme.model.AbstractPersistable;

@Entity
public class Language extends AbstractPersistable<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8646714856440200415L;
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	private String name;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public Language() {

	}

	public Language(String name) {
		super();
		this.name = name;
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//-------------------------------------------------------------
	// Methods
	//-------------------------------------------------------------
	@Override
	public String toString() {
		return name;
	}
	
}
