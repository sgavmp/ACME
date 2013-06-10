package com.acme.model.geography;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

	//Constructor solo para thymeleaf
	public City(String id) {
		super();
		this.id = Long.valueOf(id);
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
