package com.acme.model.certification;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FamilyProfessional {
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	
	private Long id;
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

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FamilyProfessional other = (FamilyProfessional) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
