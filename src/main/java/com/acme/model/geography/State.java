package com.acme.model.geography;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Entity
public class State extends AbstractPersistable<Long>{
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	private String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "state",
            fetch = FetchType.EAGER)
	private Set<City> cities;
	@ManyToOne
	@JoinColumn(name = "country_id", referencedColumnName = "id")
	private Country country;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public State() {
		this.cities=Sets.newHashSet();

	}

	public State(String name, Set<City> cities) {
		super();
		this.name = name;
		this.cities = cities;
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
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
		temp.setState(this);
		this.cities.add(temp);
		return temp;
	}
}
