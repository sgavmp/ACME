package com.acme.model.certification;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.acme.model.AbstractPersistable;

@Entity
public class FamilyProfessional extends AbstractPersistable<Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3651955527542314937L;
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	
	private String name;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public FamilyProfessional() {
		super();
	}
	
	public FamilyProfessional(String name) {
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

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------

}
