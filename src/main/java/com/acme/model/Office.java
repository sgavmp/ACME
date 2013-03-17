package com.acme.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.acme.model.geography.City;
import com.acme.model.geography.Country;
import com.acme.model.geography.State;


@Entity
public class Office {
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	private Integer id;
	private String address;
	private Integer numPostal;
	private String director;
	private String phone;
	private String fax;
	private City city;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public Office() {
		super();
	}

	public Office(String address, Integer numPostal, String director,
			String phone, String fax, City city) {
		super();
		this.address = address;
		this.numPostal = numPostal;
		this.director = director;
		this.phone = phone;
		this.fax = fax;
		this.city = city;
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------
	public String getAddress() {
		return address;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumPostal() {
		return numPostal;
	}

	public String getDirector() {
		return director;
	}

	public String getPhone() {
		return phone;
	}

	public String getFax() {
		return fax;
	}

	@ManyToOne
	public City getCity() {
		return city;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setNumPostal(Integer numPostal) {
		this.numPostal = numPostal;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setCity(City city) {
		this.city = city;
	}

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result
				+ ((director == null) ? 0 : director.hashCode());
		result = prime * result + ((fax == null) ? 0 : fax.hashCode());
		result = prime * result
				+ ((numPostal == null) ? 0 : numPostal.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		Office other = (Office) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (director == null) {
			if (other.director != null)
				return false;
		} else if (!director.equals(other.director))
			return false;
		if (fax == null) {
			if (other.fax != null)
				return false;
		} else if (!fax.equals(other.fax))
			return false;
		if (numPostal == null) {
			if (other.numPostal != null)
				return false;
		} else if (!numPostal.equals(other.numPostal))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

}
