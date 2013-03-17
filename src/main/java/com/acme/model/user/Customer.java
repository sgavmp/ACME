package com.acme.model.user;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.acme.model.examination.PreRegister;
import com.acme.model.examination.Register;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;


@Entity
@DiscriminatorValue("4")
public class Customer extends Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	private Set<Register> registers;
	private Set<PreRegister> preregister;
	

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public Customer() {
		super();
		this.preregister=Sets.newHashSet();
		this.registers=Sets.newHashSet();
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="customer", fetch=FetchType.EAGER)
	public Set<Register> getRegisters() {
		return registers;
	}

	public void setRegisters(Set<Register> registers) {
		this.registers = registers;
	}
	
	public void addRegister(Register r){
		this.registers.add(r);
	}
	
	public void removeRegister(Register r){
		this.registers.remove(r);
	}

	@OneToMany(mappedBy="customer",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	public Set<PreRegister> getPreregister() {
		return preregister;
	}

	public void setPreregister(Set<PreRegister> preregister) {
		this.preregister = preregister;
	}
	
	public void addPreRegister(PreRegister preregister){
		this.preregister.add(preregister);
	}
	
	public void removePreRegister(PreRegister preregister){
		this.preregister.remove(preregister);
	}
}
