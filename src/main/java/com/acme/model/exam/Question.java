package com.acme.model.exam;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="QUESTION_TYPE")
@DiscriminatorValue("O")
public class Question extends AbstractPersistable<Long> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3925147493424849292L;
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	@ManyToOne
	@JoinColumn(name="EXAM_ID")
	private Exam exam;
	private String questionText;
	private Double valor;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public Question() {

	}

	public Question(String questionText, Double valor) {
		super();
		this.questionText = questionText;
		this.valor = valor;
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------
	public String getQuestionText() {
		return questionText;
	}

	public Double getValor() {
		return valor;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------
}
