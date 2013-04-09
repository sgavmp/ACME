package com.acme.model.examination;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.Parent;
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
