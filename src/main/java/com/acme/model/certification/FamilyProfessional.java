package com.acme.model.certification;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

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
	
	public FamilyProfessional(Long id, String name){
		this.id=id;
		this.name=name;
	}
	
	//Constructor usado solo para Thymeleaf
	public FamilyProfessional(String id) {
		super();
		this.id = Long.decode(id);
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
