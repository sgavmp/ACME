package com.acme.model.examination;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

import com.acme.model.Pay;
import com.acme.model.exam.ExamType;
import com.acme.model.exam.Option;
import com.acme.model.user.Customer;
import com.acme.model.user.Role;


@Entity
public class Register implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	private Integer id;
	private Pay pay;
	private boolean passed;
	private String comment;
	private Double calification;
	private List<AnswerExam> answerExam;
	private Examination examination;
	private Role customer;

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
			Customer customer) {
		super();
		this.pay=pay;
		this.examination = examination;
		this.customer = customer;
	}
	
	public Register(Examination examination,
			Customer customer) {
		super();
		this.pay=null;
		this.examination = examination;
		this.customer = customer;
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------

	@ManyToOne(targetEntity=Examination.class)
	public Examination getExamination() {
		return examination;
	}

	public void setExamination(Examination examination) {
		this.examination = examination;
	}

	@ManyToOne(targetEntity=Role.class,fetch=FetchType.EAGER)
	public Role getCustomer() {
		return customer;
	}

	public void setCustomer(Role customer) {
		this.customer = customer;
	}
	
	@Id
	@GeneratedValue
	public Integer getId(){
		return this.id;
	}
	
	
	public Pay getPay() {
		return pay;
	}

	public void setPay(Pay pay) {
		this.pay = pay;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Type(type="yes_no")
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

	@OneToMany(mappedBy="register",targetEntity=AnswerExam.class,cascade=CascadeType.ALL)
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
		if (this.getExamination().getCertification().getRequirementCalification().getCalificationNum()<=this.getCalification()){
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
		Register other = (Register) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
}
