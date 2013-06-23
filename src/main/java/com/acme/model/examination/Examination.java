package com.acme.model.examination;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.thymeleaf.expression.Dates;

import com.acme.exception.DateIncorrectException;
import com.acme.model.AbstractPersistable;
import com.acme.model.Office;
import com.acme.model.Pay;
import com.acme.model.certification.Certification;
import com.acme.model.exam.Exam;
import com.acme.model.user.User;

@Entity
@Indexed
public class Examination extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4256362697482687006L;
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	@Temporal(TemporalType.DATE)
	@NotNull(message = "examination.daterealization.notnull")
	@Future(message = "examination.daterealization.futuro")
	private Date dateRealization;

	@Temporal(value = TemporalType.DATE)
	@NotNull(message = "examination.datelimitregister.notnull")
	@Future(message = "examination.datelimitregister.futuro")
	private Date dateLimitRegister;

	@Temporal(TemporalType.TIME)
	@NotNull(message = "examination.timerealization.nontull")
	private Date timeRealization;

	private Integer maxCustomer;

	private Integer minCustomer;

	@ManyToOne
	@NotNull(message = "examination.daterealization.nontull")
	@IndexedEmbedded
	private Certification certification;

	private Pay payCost;

	@ManyToOne
	private Exam exam;

	@ManyToOne
	private Office realizationPlace;

	@ManyToOne
	private User reviewer;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public Examination() {
		super();
	}

	public Examination(Date dateRealization, Date dateLimitRegister,
			Integer maxCustomer, Integer minCustomer,
			Certification certification, Office realizationPlace, User reviewer) {
		super();
		this.dateRealization = dateRealization;
		this.dateLimitRegister = dateLimitRegister;
		this.maxCustomer = maxCustomer;
		this.minCustomer = minCustomer;

		this.certification = certification;
		this.realizationPlace = realizationPlace;
		this.exam = this.certification.getRandomExam();
		this.setReviewer(reviewer);
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------

	public Date getDateRealization() {
		return dateRealization;
	}

	public void setDateRealization(Date dateRealization)
			throws DateIncorrectException {
		if (this.dateLimitRegister != null)
			if (this.dateLimitRegister.after(dateRealization))
				throw new DateIncorrectException("exception.date");
		this.dateRealization = dateRealization;
	}

	public Date getDateLimitRegister() {
		return dateLimitRegister;
	}

	public void setDateLimitRegister(Date dateLimitRegister)
			throws DateIncorrectException {
		if (this.dateRealization != null)
			if (this.dateRealization.before(dateLimitRegister))
				throw new DateIncorrectException("exception.date");
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

	public User getReviewer() {
		return reviewer;
	}

	public void setReviewer(User reviewer) {
		this.reviewer = reviewer;
	}

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------
	public Register createRegisterWithPay(Pay pay, User customer)
			throws DateIncorrectException {
		if (this.dateLimitRegister != null
				&& this.dateLimitRegister.after(Dates.createNow()))
			throw new DateIncorrectException("exception.date");
		Register temp = new Register(pay, this, customer);
		return temp;
	}

	public Register createRegisterWithoutPay(User customer) throws DateIncorrectException {
		Date today = new Date();
		if (this.dateLimitRegister != null && this.dateLimitRegister.before(today)) {
			throw new DateIncorrectException("exception.date");
		}
		Register temp = new Register(this, customer);
		return temp;
	}

	public Date getTimeRealization() {
		return timeRealization;
	}

	public void setTimeRealization(Date timeRealization) {
		this.timeRealization = timeRealization;
	}

	public Pay getPayCost() {
		return payCost;
	}

	public void setPayCost(Pay payCost) {
		this.payCost = payCost;
	}

	public boolean isOpen() {
		if (Dates.createToday().before(this.dateLimitRegister)) {
			return true;
		} else {
			return false;
		}
	}

}
