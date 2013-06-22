package com.acme.model.examination;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.acme.model.AbstractPersistable;
import com.acme.model.IntervalDate;
import com.acme.model.certification.Certification;
import com.acme.model.user.Customer;


@Entity
public class PreRegister extends AbstractPersistable<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6903251566068008472L;
	// -------------------------------------------------------------
	// Attributes
	// ------------------------------------------------------------- 
	private IntervalDate intervaloDeseado;
	@ManyToOne
	private Certification certification;
	@ManyToOne
	private Customer customer;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public PreRegister() {
		super();
	}

	public PreRegister(IntervalDate intervaloDeseado, Customer customer, Certification certification) {
		super();
		this.intervaloDeseado = intervaloDeseado;
		this.customer = customer;
		this.certification=certification;
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------
	public IntervalDate getIntervaloDeseado() {
		return intervaloDeseado;
	}


	public Customer getCustomer() {
		return customer;
	}

	public void setIntervaloDeseado(IntervalDate intervaloDeseado) {
		this.intervaloDeseado = intervaloDeseado;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Certification getCertification() {
		return certification;
	}

	public void setCertification(Certification certification) {
		this.certification = certification;
	}

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------
	
	

}
