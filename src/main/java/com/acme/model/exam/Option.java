package com.acme.model.exam;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import com.acme.model.AbstractPersistable;

@Entity(name="options")
public class Option extends AbstractPersistable<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8850921131236959093L;
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	@ManyToOne
	private Question question;
	private String textOption;
	@Type(type="yes_no")
	private boolean isCorrect=true;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public Option() {
		super();
	}

	public Option(String textOption, boolean isCorrect) {
		super();
		this.textOption = textOption;
		this.isCorrect = isCorrect;
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------
	public String getTextOption() {
		return textOption;
	}
	

	public void setTextOption(String textOption) {
		this.textOption = textOption;
	}

	
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	
	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((textOption == null) ? 0 : textOption.hashCode());
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
		Option other = (Option) obj;
		if (textOption == null) {
			if (other.textOption != null)
				return false;
		} else if (!textOption.equals(other.textOption))
			return false;
		return true;
	}

}
