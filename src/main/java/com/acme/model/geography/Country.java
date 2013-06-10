package com.acme.model.geography;

import java.util.List;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
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
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
	private Set<State> states;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public Country() {
		this.states=Sets.newHashSet();
	}

	//Constructor solo para thymeleaf
	public Country(String id) {
		super();
		this.id = Long.valueOf(id);
		this.states=Sets.newHashSet();
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
	public String toString() {
		return name;
	}
	
	public State createState(String name){
		State temp = new State();
		temp.setName(name);
		this.states.add(temp);
		return temp;
	}
}
