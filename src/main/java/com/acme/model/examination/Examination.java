package com.acme.model.examination;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.acme.model.Office;
import com.acme.model.Pay;
import com.acme.model.certification.Certification;
import com.acme.model.exam.Exam;
import com.acme.model.user.Customer;
import com.acme.model.user.Reviewer;
import com.acme.model.user.Role;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;


@Entity
public class Examination extends AbstractPersistable<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4256362697482687006L;
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	@Temporal(value = TemporalType.DATE)
	private Date dateRealization;
	@Temporal(value = TemporalType.DATE)
	private Date dateLimitRegister;
	private Integer maxCustomer;
	private Integer minCustomer;
	@ManyToOne
	private Certification certification;
	private Pay payCost;
	@ManyToOne
	private Exam exam;
	@ManyToOne
	private Office realizationPlace;
	@OneToMany(mappedBy="examination",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Register> registers;
	@ManyToOne
	private Reviewer reviewer;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public Examination() {
		super();
		this.registers= Sets.newHashSet();
	}

	public Examination(Date dateRealization, Date dateLimitRegister,
			Integer maxCustomer, Integer minCustomer,
			Certification certification, Office realizationPlace, Reviewer reviewer) {
		super();
		this.dateRealization = dateRealization;
		this.dateLimitRegister = dateLimitRegister;
		this.maxCustomer = maxCustomer;
		this.minCustomer = minCustomer;

		this.certification = certification;
		this.realizationPlace = realizationPlace;
		this.exam = this.certification.getRandomExam();
		this.setReviewer(reviewer);
		this.registers= Sets.newHashSet();
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------
	
	public Date getDateRealization() {
		return dateRealization;
	}

	public void setDateRealization(Date dateRealization) {
		this.dateRealization = dateRealization;
	}

	
	public Date getDateLimitRegister() {
		return dateLimitRegister;
	}

	public void setDateLimitRegister(Date dateLimitRegister) {
		this.dateLimitRegister = dateLimitRegister;
	}


	public Integer getMaxCustomer() {
		return maxCustomer;
	}

	public void setMaxCustomer(Integer maxCustomer) {
		this.maxCustomer = maxCustomer;
	}

	public Integer getMinCustomer() {
		return minCustomer;
	}

	public void setMinCustomer(Integer minCustomer) {
		this.minCustomer = minCustomer;
	}

	
	public Certification getCertification() {
		return certification;
	}

	public void setCertification(Certification certification) {
		this.certification = certification;
	}

	
	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	
	public Office getRealizationPlace() {
		return realizationPlace;
	}

	public void setRealizationPlace(Office realizationPlace) {
		this.realizationPlace = realizationPlace;
	}

	
	public Set<Register> getRegisters() {
		return registers;
	}

	public void setRegisters(Set<Register> registers) {
		this.registers = registers;
	}

	
	public Reviewer getReviewer() {
		return reviewer;
	}

	public void setReviewer(Reviewer reviewer) {
		this.reviewer = reviewer;
	}

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------
	public void correctTestExams() {
		for (Register r : this.registers){
			r.correctTestExam();
		}
	}

	public void addRegister(Register r) {
		this.registers.add(r);
	}

	public void removeRegister(Register r) {
		this.registers.remove(r);
	}

	@Transient
	public List<Role> getCustomers() {
		return (List<Role>) Iterables.transform(this.registers,
				new FunctionRegisterToCustomer());
	}

	public Register createRegisterWithPay(Pay pay, Customer customer) {
		Register temp = new Register(pay, this, customer);
		this.addRegister(temp);
		customer.addRegister(temp);
		return temp;
	}
	
	public Register createRegisterWithoutPay(Customer customer) {
		Register temp = new Register(this, customer);
		this.addRegister(temp);
		customer.addRegister(temp);
		return temp;
	}

}
