package model.exam;

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

import com.google.common.collect.Lists;

import model.certification.Certification;
import model.geography.Language;

@Entity
public class Exam {
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	private Integer id;
	private ExamType typeExam;
	private List<Question> questions= Lists.newArrayList();
	private Language language;
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

	@OneToMany(mappedBy="exam",targetEntity=Question.class,orphanRemoval=true,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@IndexColumn(name="id")
	public List<Question> getQuestions() {
		return questions;
	}

	@ManyToOne
	public Language getLanguage() {
		return language;
	}

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------

	@ManyToOne
	public Certification getCertification() {
		return certification;
	}

	public void setCertification(Certification certification) {
		this.certification = certification;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((language == null) ? 0 : language.hashCode());
		result = prime * result
				+ ((questions == null) ? 0 : questions.hashCode());
		result = prime * result
				+ ((typeExam == null) ? 0 : typeExam.hashCode());
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
		Exam other = (Exam) obj;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		if (typeExam != other.typeExam)
			return false;
		return true;
	}

}
