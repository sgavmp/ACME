package com.acme.model.geography;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.acme.model.AbstractPersistable;
import com.google.common.collect.Sets;

@Entity
public class State extends AbstractPersistable<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1394668146985629345L;
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	private String name;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<City> cities;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public State() {
		this.cities=Sets.newHashSet();

	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------
	public String getName() {
		return name;
	}

	public Set<City> getCities() {
		return cities;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

	// -------------------------------------------------------------
	// Method
	// -------------------------------------------------------------
	@Override
	public String toString() {
		return name;
	}

	public City createCity(String name){
		City temp = new City();
		temp.setName(name);
		this.cities.add(temp);
		return temp;
	}
}
