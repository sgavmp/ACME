package com.acme.model.geography;

import javax.persistence.Entity;

import com.acme.model.AbstractPersistable;

@Entity
public class City extends AbstractPersistable<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8778257476424365728L;
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	private String name;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public City() {
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

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------
	
	@Override
	public String toString() {
		return name;
	}

}
