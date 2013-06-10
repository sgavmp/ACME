package com.acme.model.examination;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.acme.model.exam.Question;


@Entity
@DiscriminatorValue("T")
@Access(AccessType.PROPERTY)
public class OpenAnswerExam extends AnswerExam {
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	private String answer;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------

	public OpenAnswerExam(Question question, String answer) {
		super(question);
		this.answer = answer;
	}

	public OpenAnswerExam() {
		super();
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------
	public String getAnswer() {
		return answer;
	}
	
	

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpenAnswerExam other = (OpenAnswerExam) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		return true;
	}

}
