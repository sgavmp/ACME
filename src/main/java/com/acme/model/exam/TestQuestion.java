package com.acme.model.exam;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Entity
@DiscriminatorValue("T")
public class TestQuestion extends Question {
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	private Set<Option> options=Sets.newHashSet();

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public TestQuestion() {

	}

	public TestQuestion(String questionText, Double valor) {
		super(questionText, valor);
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------

	@OneToMany(targetEntity=Option.class,mappedBy="question",cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.EAGER)
	public Set<Option> getOptions() {
		return options;
	}

	@Transient
	public Set<Option> getCorrectsOptions() {
		return (Set<Option>) Collections2.filter(this.options, new IsOptionCorrectPredicates());
	}

	public void setOptions(Set<Option> options) {
		this.options = options;
	}

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------
	
	public Option createOption(String textOption,boolean isCorrect) {
		Option op = new Option(textOption,isCorrect);
		op.setQuestion(this);
		this.options.add(op);
		return op;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((options == null) ? 0 : options.hashCode());
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
		TestQuestion other = (TestQuestion) obj;
		if (options == null) {
			if (other.options != null)
				return false;
		} else if (!options.equals(other.options))
			return false;
		return true;
	}
	
	

}
