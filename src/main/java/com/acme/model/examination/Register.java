package com.acme.model.examination;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import com.acme.model.AbstractPersistable;
import com.acme.model.Pay;
import com.acme.model.user.User;


@Entity
public class Register extends AbstractPersistable<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2670012436864334124L;
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	private Pay pay;
	@Type(type="yes_no")
	private boolean passed;
	private String comment;
	private Double calification;
	@OneToMany(mappedBy="register",targetEntity=AnswerExam.class,cascade=CascadeType.ALL)
	private List<AnswerExam> answerExam;
	@ManyToOne(fetch=FetchType.EAGER)
	private Examination examination;
	@ManyToOne(fetch=FetchType.EAGER)
	private User customer;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public Register() {
		super();
		this.pay=null;
		this.examination = null;
		this.customer = null;
	}

	public Register(Pay pay, Examination examination,
			User customer) {
		super();
		this.pay=pay;
		this.examination = examination;
		this.customer = customer;
	}
	
	public Register(Examination examination,
			User customer) {
		super();
		this.pay=null;
		this.examination = examination;
		this.customer = customer;
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------

	
	public Examination getExamination() {
		return examination;
	}

	public void setExamination(Examination examination) {
		this.examination = examination;
	}


	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	
	
	public Pay getPay() {
		return pay;
	}

	public void setPay(Pay pay) {
		this.pay = pay;
	}


	
	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Double getCalification() {
		return calification;
	}

	public void setCalification(Double calification) {
		this.calification = calification;
	}

	
	public List<AnswerExam> getAnswerExam() {
		return answerExam;
	}

	private void setAnswerExam(List<AnswerExam> answerExam) {
		this.answerExam = answerExam;
	}

	//-------------------------------------------------------------
	// Methods
	//-------------------------------------------------------------
	public void saveAnswers(List<AnswerExam> answers) {
		setAnswerExam(answers);
	}

	public String printAnswers() {
//		String text = "";
//		text += "Nombre del Aspirante: " + this.customer.getUser().getName()
//				+ " " + this.customer.getUser().getSurname() + "\n";
//		text += "Tipo de examen: " + this.examination.getExam().getTypeExam().toString()
//				+ "        Idioma: " + this.examination.getExam().getLanguage().toString()
//				+ "\n";
//		for (AnswerExam q : this.getAnswerExam()) {
//			text += "1. " + q.getQuestion().getQuestionText() + "\n";
//			if (this.examination.getExam().getTypeExam() == ExamType.MULTITEST
//					|| this.examination.getExam().getTypeExam() == ExamType.TEST) {
//				TestAnswerExam tq = (TestAnswerExam) q;
//				char letra = 'a';
//				for (Option o : tq.getOptions()) {
//					text += letra + ")" + o.getTextOption() + "\n";
//					letra++;
//				}
//				text += "\n";
//			}
//			text += "\n\n";
//		}
//		return text;
		return "";
	}

	public void correctTestExam() {
		Double points = 0.0;
		for (AnswerExam a : this.answerExam) {
			TestAnswerExam ta = (TestAnswerExam) a;
			points += ta.correctTestAnswer();
		}
		this.setCalification(points);
		if (this.getExamination().getCertification().getRequirementCalification()<=this.getCalification()){
			this.setPassed(true);
		}
		else
		{
			this.setPassed(false);
		}
	}
	
	public String passedToString(){
		if (this.passed){
			return "APROBADO";
		}
		else{
			return "SUSPENDIDO";
		}
	}

	public String printResult() {
//		return "El aspirante " + this.customer.getUser().getName() + " "
//				+ this.customer.getUser().getSurname()
//				+ " ha obtenido una calificacion de "
//				+ this.calification.toString() + ", segun los requisitos del certificado el aspirante ha "+ this.passedToString();
		return "";
	}
	
	public boolean payRegister(Pay pay){
		if (this.pay == null)
		{
			this.pay=pay;
			return true;
		}
		else {
			return false;
		}
	}

	
}
