package com.acme.model.user;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.acme.model.examination.Examination;
import com.acme.model.examination.PreRegister;
import com.acme.model.examination.Register;
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
	@OneToMany(mappedBy="customer",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private Set<PreRegister> preregister;
	

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public Customer() {
		super();
		this.preregister=Sets.newHashSet();
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------	

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
