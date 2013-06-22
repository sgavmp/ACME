package com.acme.model.certification;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.validator.constraints.NotBlank;

import com.acme.model.AbstractPersistable;
import com.acme.model.IntervalDate;
import com.acme.model.Office;
import com.acme.model.exam.Exam;
import com.acme.model.exam.ExamType;
import com.acme.model.examination.Examination;
import com.acme.model.examination.PreRegister;
import com.acme.model.geography.Language;
import com.acme.model.user.Customer;
import com.acme.model.user.User;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Entity
@Indexed()
public class Certification extends AbstractPersistable<Long> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2546415602571603134L;

	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------

	@NotNull(message = "certification.name.null")
	@NotBlank(message = "certification.name.null")
	@Field()
	private String name;

	@NotNull(message = "certification.description.null")
	@NotBlank(message = "certification.description.null")
	@Field()
	private String description;

	@NotNull(message = "certification.cost.null")
	@Digits(integer = 4, fraction = 2, message = "certification.cost.number")
	@Min(value = 0, message = "certification.cost.negative")
	private Double cost;

	@NotNull(message = "certification.pricepublic.null")
	@Digits(integer = 4, fraction = 2, message = "certification.pricepublic.number")
	@Min(value = 0, message = "certification.pricepublic.negative")
	private Double pricePublic;

	@NotNull(message = "certification.validez.null")
	@NotBlank(message = "certification.validez.null")
	private String validez;

	@NotNull(message = "certification.company.null")
	@OneToOne
	@IndexedEmbedded
	private User company;

	@NotNull(message = "certification.family.null")
	@ManyToOne
	private FamilyProfessional familyProfessional;

	@NotNull(message = "certification.calification.null")
	@Digits(integer = 2, fraction = 1, message = "certification.calification.number")
	@Min(value = 0, message = "certification.calification.negative")
	private Double requirementCalification;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "requirement")
	@Fetch(FetchMode.SELECT)
	private List<String> requirements;

	@OneToMany(mappedBy = "certification", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Exam.class, fetch = FetchType.EAGER)
	private Set<Exam> exams;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public Certification() {
		super();
		this.requirements = Lists.newArrayList();
		this.exams = Sets.newHashSet();
	}

	public Certification(String name, String description, Double cost,
			Double pricePublic, String validez, User company,
			FamilyProfessional familyProfessional, List<String> requirements,
			Double requirementCalification) {
		super();
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.pricePublic = pricePublic;
		this.validez = validez;
		this.company = company;
		this.familyProfessional = familyProfessional;
		this.requirements = requirements;
		this.exams = Sets.newHashSet();
		this.requirementCalification = requirementCalification;
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getValidez() {
		return validez;
	}

	public void setValidez(String validez) {
		this.validez = validez;
	}

	public FamilyProfessional getFamilyProfessional() {
		return familyProfessional;
	}

	public void setFamilyProfessional(FamilyProfessional familyProfessional) {
		this.familyProfessional = familyProfessional;
	}

	public List<String> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<String> requirements) {
		this.requirements = requirements;
	}

	public User getCompany() {
		return company;
	}

	public void setCompany(User company) {
		this.company = company;
	}

	public Double getRequirementCalification() {
		return requirementCalification;
	}

	public void setRequirementCalification(Double requirementCalification) {
		this.requirementCalification = requirementCalification;
	}

	// -------------------------------------------------------------
	// Methods
	// -------------------------------------------------------------

	public Double getPricePublic() {
		return pricePublic;
	}

	public void setPricePublic(Double pricePublic) {
		this.pricePublic = pricePublic;
	}

	public Set<Exam> getExams() {
		return exams;
	}

	public void setExams(Set<Exam> exams) {
		this.exams = exams;
	}

	public void addRequiremnt(String r) {
		this.requirements.add(r);
	}

	public void removeRequirement(String r) {
		this.requirements.remove(r);
	}

	public void removeRequirement(Integer r) {
		this.requirements.remove(this.requirements.get(r));
	}

	public void addExam(Exam e) {
		if (this.exams == null) {
			this.exams = Sets.newHashSet();
			;
		}
		this.exams.add(e);
	}

	public void removeExam(Exam e) {
		this.exams.remove(e);
	}

	public Exam addExamToCertification(ExamType typeExam, Language language) {
		Exam temp = new Exam(typeExam, language);
		this.addExam(temp);
		temp.setCertification(this);
		return temp;
	}

	public PreRegister createPreRegisterCustomer(IntervalDate intervaloDeseado,
			Customer customer) {
		PreRegister temp = new PreRegister(intervaloDeseado, customer, this);
		customer.addPreRegister(temp);
		return temp;
	}

	public Exam getRandomExam() {
		if (!this.exams.isEmpty()) {
			int size = exams.size();
			Random rand = new Random();
			return (Exam) exams.toArray()[rand.nextInt(size)];
		}
		else
			return null;
	}

	public Examination createNewExaminationForCertification(
			Date dateRealization, Date dateLimit, Integer maxCustomer,
			Integer minCustomer, Office place, User reviewer) {
		Examination temp = new Examination(dateRealization, dateLimit,
				maxCustomer, minCustomer, this, place, reviewer);
		return temp;
	}

}