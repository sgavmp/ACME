package model.examination;

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

import model.IntervalDate;
import model.certification.Certification;
import model.user.Customer;

@Entity
public class PreRegister implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6903251566068008472L;
	// -------------------------------------------------------------
	// Attributes
	// ------------------------------------------------------------- 
	private Integer id;
	private IntervalDate intervaloDeseado;
	private Certification certification;
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
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------
	public IntervalDate getIntervaloDeseado() {
		return intervaloDeseado;
	}

	@ManyToOne
	public Customer getCustomer() {
		return customer;
	}

	public void setIntervaloDeseado(IntervalDate intervaloDeseado) {
		this.intervaloDeseado = intervaloDeseado;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne
	public Certification getCertification() {
		return certification;
	}

	public void setCertification(Certification certification) {
		this.certification = certification;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((certification == null) ? 0 : certification.hashCode());
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		PreRegister other = (PreRegister) obj;
		if (certification == null) {
			if (other.certification != null)
				return false;
		} else if (!certification.equals(other.certification))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------
	
	

}
