package com.acme.model.examination;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import com.acme.model.AbstractPersistable;
import com.acme.model.Pay;
import com.acme.model.user.User;


@Entity
public class Register extends AbstractPersistable<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2670012436864334124L;
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	private Pay pay;
	@Type(type="yes_no")
	private boolean passed;
	private String comment;
	private Double calification;
	@ManyToOne(fetch=FetchType.EAGER)
	private Examination examination;
	@ManyToOne(fetch=FetchType.EAGER)
	private User customer;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public Register() {
		super();
		this.pay=null;
		this.examination = null;
		this.customer = null;
	}

	public Register(Pay pay, Examination examination,
			User customer) {
		super();
		this.pay=pay;
		this.examination = examination;
		this.customer = customer;
	}
	
	public Register(Examination examination,
			User customer) {
		super();
		this.pay=null;
		this.examination = examination;
		this.customer = customer;
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------

	
	public Examination getExamination() {
		return examination;
	}

	public void setExamination(Examination examination) {
		this.examination = examination;
	}


	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	
	
	public Pay getPay() {
		return pay;
	}

	public void setPay(Pay pay) {
		this.pay = pay;
	}


	
	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Double getCalification() {
		return calification;
	}

	public void setCalification(Double calification) {
		this.calification = calification;
	}

	//-------------------------------------------------------------
	// Methods
	//-------------------------------------------------------------

	
	public boolean payRegister(Pay pay){
		if (this.pay == null)
		{
			this.pay=pay;
			return true;
		}
		else {
			return false;
		}
	}

	
}
