package com.acme.model;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Pay {
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	private Double importPay;
	
	private Date datePay;
	private String concept;
	private MethodPay method;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public Pay() {
		super();
	}

	public Pay(Double importPay, Date datePay, String concept, MethodPay method) {
		super();
		this.importPay = importPay;
		this.datePay = datePay;
		this.concept = concept;
		this.method = method;
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------
	public Double getImportPay() {
		return importPay;
	}

	@Temporal(value=TemporalType.TIMESTAMP)
	public Date getDatePay() {
		return datePay;
	}

	public String getConcept() {
		return concept;
	}

	public void setImportPay(Double importPay) {
		this.importPay = importPay;
	}

	public void setDatePay(Date datePay) {
		this.datePay = datePay;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	@ManyToOne
	public MethodPay getMethod() {
		return method;
	}

	public void setMethod(MethodPay method) {
		this.method = method;
	}

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((concept == null) ? 0 : concept.hashCode());
		result = prime * result + ((datePay == null) ? 0 : datePay.hashCode());
		result = prime * result
				+ ((importPay == null) ? 0 : importPay.hashCode());
		result = prime * result + ((method == null) ? 0 : method.hashCode());
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
		Pay other = (Pay) obj;
		if (concept == null) {
			if (other.concept != null)
				return false;
		} else if (!concept.equals(other.concept))
			return false;
		if (datePay == null) {
			if (other.datePay != null)
				return false;
		} else if (!datePay.equals(other.datePay))
			return false;
		if (importPay == null) {
			if (other.importPay != null)
				return false;
		} else if (!importPay.equals(other.importPay))
			return false;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		return true;
	}

}
