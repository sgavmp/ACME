package com.acme.model;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class IntervalDate {
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date iniDate;
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date endDate;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public IntervalDate() {
		super();
	}

	public IntervalDate(Date iniDate, Date endDate) {
		super();
		this.iniDate = iniDate;
		this.endDate = endDate;
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------
	public Date getIniDate() {
		return iniDate;
	}

	public Date getEndDate() {
		return endDate;
	}
	
	

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------

	public void setIniDate(Date iniDate) {
		this.iniDate = iniDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((iniDate == null) ? 0 : iniDate.hashCode());
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
		IntervalDate other = (IntervalDate) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (iniDate == null) {
			if (other.iniDate != null)
				return false;
		} else if (!iniDate.equals(other.iniDate))
			return false;
		return true;
	}

}
