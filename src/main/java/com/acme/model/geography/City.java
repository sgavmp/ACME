package com.acme.model.geography;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class City {
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	@ManyToOne
	@JoinColumn(name = "state_id", referencedColumnName = "id")
	private State state;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public City() {
	}

	public City(String name) {
		super();
		this.name = name;
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------
	public String getName() {
		return name;
	}

	public Integer getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
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
		City other = (City) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
