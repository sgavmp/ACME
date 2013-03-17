package com.acme.model.examination;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.acme.model.exam.Option;
import com.acme.model.exam.Question;
import com.acme.model.exam.TestQuestion;


@Entity
@DiscriminatorValue("T")
public class TestAnswerExam extends AnswerExam {
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	private List<Option> options;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public TestAnswerExam() {
		super();
	}
	
	public TestAnswerExam(Question question, List<Option> arrayList) {
		super();
		this.question = question;
		this.options = arrayList;
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------
	@ManyToMany
	@JoinTable(name="answerexam_options", 
	joinColumns={@JoinColumn(name="AnswerExam_id", referencedColumnName = "id")},
	inverseJoinColumns={@JoinColumn(name="options_id", referencedColumnName = "id")})
	public List<Option> getOptions() {
		return options;
	}
	
	

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public Question getQuestion() {
		return question;
	}

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------
	public Double correctTestAnswer() {
		Double pointCorrect = 1.0 / ((TestQuestion) this.question).getCorrectsOptions().size();
		Double pointIncorrect = pointCorrect / 2;
		Double punt = 0.0;
		for (Option o : this.options) {
			if (((TestQuestion) question).getCorrectsOptions().contains(o)) {
				punt += pointCorrect;
			} else {
				punt -= pointIncorrect;
				if (punt < 0.0) {
					punt = 0.0;
				}
			}
		}
		Double totalPoint = this.question.getValor() * punt;
		this.setPuntuation(totalPoint);
		return totalPoint;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((options == null) ? 0 : options.hashCode());
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
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
		TestAnswerExam other = (TestAnswerExam) obj;
		if (options == null) {
			if (other.options != null)
				return false;
		} else if (!options.equals(other.options))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}

}
