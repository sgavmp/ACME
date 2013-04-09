package com.acme.model.exam;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.AbstractPersistable;

import com.acme.model.certification.Certification;
import com.acme.model.geography.Language;
import com.google.common.collect.Lists;


@Entity
public class Exam extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8243512739923130848L;
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	private ExamType typeExam;
	@OneToMany(mappedBy="exam",targetEntity=Question.class,orphanRemoval=true,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@IndexColumn(name="id")
	private List<Question> questions= Lists.newArrayList();
	@ManyToOne
	private Language language;
	@ManyToOne
	private Certification certification;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public Exam() {
		super();
	}

	public Exam(ExamType typeExam, Language language) {
		super();
		this.typeExam = typeExam;
		this.language = language;
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------
	public ExamType getTypeExam() {
		return typeExam;
	}

	
	public List<Question> getQuestions() {
		return questions;
	}


	public Language getLanguage() {
		return language;
	}

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------


	public Certification getCertification() {
		return certification;
	}

	public void setCertification(Certification certification) {
		this.certification = certification;
	}

	public void setTypeExam(ExamType typeExam) {
		this.typeExam = typeExam;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
	
	public Question createQuestion(String questionText,Double valor) {
		if (this.typeExam==ExamType.OPEN_ANSWER) {
			Question q = new Question(questionText, valor);
			q.setExam(this);
			this.questions.add(q);
			return q;
		}
		else
			return null;
	}
	
	public TestQuestion createTestQuestion(String questionText,Double valor) {
		if (this.typeExam!=ExamType.OPEN_ANSWER) {
			TestQuestion q = new TestQuestion(questionText, valor);
			q.setExam(this);
			this.questions.add(q);
			return q;
		}
		else
			return null;
	}

	public String print() {
		String text = "";
		text += "Tipo de examen: " + this.typeExam.toString()
				+ "        Idioma: " + this.language.toString() + "\n";
		for (Question q : this.questions) {
			text += "1. " + q.getQuestionText() + "\n";
			if (this.typeExam == ExamType.MULTITEST
					|| this.typeExam == ExamType.TEST) {
				TestQuestion tq = (TestQuestion) q;
				char letra = 'a';
				for (Option o : tq.getOptions()) {
					text += letra + ")" + o.getTextOption() + "\n";
					letra++;
				}
				text += "\n";
			}
			text += "\n\n";
		}
		return text;
	}

}
