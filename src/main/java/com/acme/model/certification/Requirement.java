package com.acme.model.certification;

import javax.persistence.Embeddable;

@Embeddable
public class Requirement {
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	private String name;
	private String description;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public Requirement() {
		super();
	}

	public Requirement(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
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
		Requirement other = (Requirement) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
