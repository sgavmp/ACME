package com.acme.model.examination;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Type;

import com.acme.model.exam.Question;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ANSWER_TYPE")
public abstract class AnswerExam {
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	
	private Long id;
	private Double puntuation;
	protected Question question;
	private String text;
	private Register register;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public AnswerExam() {

	}

	public AnswerExam(Double puntuation, Question question) {
		super();
		this.puntuation = puntuation;
		this.question = question;
	}

	public AnswerExam(Question question) {
		super();
		this.question = question;
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------
	@ManyToOne
	@JoinColumn(name="QUESTION_ID",referencedColumnName="id")
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	

	public void setId(Long id) {
		this.id = id;
	}

	public void setPuntuation(Double puntuation) {
		this.puntuation = puntuation;
	}

	public Double getPuntuation() {
		return puntuation;
	}

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		AnswerExam other = (AnswerExam) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@OneToOne
	public Register getRegister() {
		return register;
	}

	public void setRegister(Register register) {
		this.register = register;
	}

}
