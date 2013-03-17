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

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="QUESTION_TYPE")
@DiscriminatorValue("O")
public class Question implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	private Integer id;
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

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	@ManyToOne
	@JoinColumn(name="EXAM_ID")
	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((questionText == null) ? 0 : questionText.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		Question other = (Question) obj;
		if (questionText == null) {
			if (other.questionText != null)
				return false;
		} else if (!questionText.equals(other.questionText))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
}
