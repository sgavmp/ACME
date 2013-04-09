package com.acme.model.geography;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.acme.model.AbstractPersistable;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Entity
public class Country extends AbstractPersistable<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2889976891697785126L;
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	private String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "country",
            fetch = FetchType.EAGER)
	private Set<State> states;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public Country() {
		this.states=Sets.newHashSet();
	}

	public Country(String name, Set<State> states) {
		super();
		this.name = name;
		this.states = states;
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------
	public String getName() {
		return name;
	}

	public Set<State> getStates() {
		return states;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStates(Set<State> states) {
		this.states = states;
	}

	// -------------------------------------------------------------
	// Method
	// -------------------------------------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((states == null) ? 0 : states.hashCode());
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
		Country other = (Country) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (states == null) {
			if (other.states != null)
				return false;
		} else if (!states.equals(other.states))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}
	
	public State createState(String name){
		State temp = new State();
		temp.setName(name);
		temp.setCountry(this);
		this.states.add(temp);
		return temp;
	}
}
