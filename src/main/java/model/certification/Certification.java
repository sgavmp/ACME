package model.certification;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Type;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import model.IntervalDate;
import model.Office;
import model.exam.Exam;
import model.exam.ExamType;
import model.exam.Option;
import model.exam.Question;
import model.examination.Examination;
import model.examination.PreRegister;
import model.geography.Language;
import model.user.Company;
import model.user.Customer;
import model.user.Reviewer;

@Entity
public class Certification {
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------
	private Integer id;
	private String name;
	private String description;
	private Double cost;
	private Double pricePublic;
	private String validez;
	private Company company;
	private FamilyProfessional familyProfessional;
	private RequirementCalification requirementCalification;
	private List<Requirement> requirements;
	private Set<Examination> examinations;
	private Set<PreRegister> preRegisters;
	private List<Exam> exams;
	
	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public Certification() {
		super();
		this.requirements = Lists.newArrayList();
		this.examinations = Sets.newHashSet();
		this.preRegisters = Sets.newHashSet();
		this.exams = Lists.newArrayList();
	}

	public Certification(String name, String description, Double cost,
			Double pricePublic, String validez, Company company,
			FamilyProfessional familyProfessional,
			List<Requirement> requirements,
			RequirementCalification requirementCalification) {
		super();
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.pricePublic = pricePublic;
		this.validez = validez;
		this.company = company;
		this.familyProfessional = familyProfessional;
		this.requirements = requirements;
		this.exams = Lists.newArrayList();
		this.preRegisters = Sets.newHashSet();
		this.examinations = Sets.newHashSet();
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

	@ManyToOne
	public FamilyProfessional getFamilyProfessional() {
		return familyProfessional;
	}

	public void setFamilyProfessional(FamilyProfessional familyProfessional) {
		this.familyProfessional = familyProfessional;
	}

	@ElementCollection
	@CollectionTable(name="requirement")
	public List<Requirement> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<Requirement> requirements) {
		this.requirements = requirements;
	}

	@OneToMany(mappedBy="certification",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	public Set<Examination> getExaminations() {
		return examinations;
	}

	public void setExaminations(Set<Examination> examinations) {
		this.examinations = examinations;
	}

	@OneToMany(mappedBy="certification",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	public Set<PreRegister> getPreRegisters() {
		return preRegisters;
	}

	public void setPreRegisters(Set<PreRegister> preRegisters) {
		this.preRegisters = preRegisters;
	}

	@OneToOne
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Embedded
	public RequirementCalification getRequirementCalification() {
		return requirementCalification;
	}

	public void setRequirementCalification(RequirementCalification requirementCalification) {
		this.requirementCalification = requirementCalification;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
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

	@OneToMany(mappedBy="certification",cascade=CascadeType.ALL,orphanRemoval=true,targetEntity=Exam.class,fetch=FetchType.EAGER)
	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	public void addRequiremnt(Requirement r) {
		this.requirements.add(r);
	}

	public void removeRequirement(Requirement r) {
		this.requirements.remove(r);
	}

	public void addExamination(Examination e) {
		this.examinations.add(e);
	}

	public void removeExamination(Examination e) {
		this.examinations.remove(e);
	}

	public void addPreRegister(PreRegister p) {
		if (this.preRegisters == null) {
			this.preRegisters = Sets.newHashSet();
		}
		this.preRegisters.add(p);
	}

	public void removePreRegister(PreRegister p) {
		this.preRegisters.remove(p);
	}

	public void addExam(Exam e) {
		if (this.exams == null) {
			this.exams = Lists.newArrayList();
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

	public Requirement createRequirement(String name, String description) {
		Requirement temp = new Requirement(name, description);
		this.addRequiremnt(temp);
		return temp;
	}

	public Requirement createRequirementOfCalification(String name,
			String description, Double calification) {
		Requirement temp = new RequirementCalification(calification, name,
				description);
		this.addRequiremnt(temp);
		return temp;
	}

	public PreRegister createPreRegisterCustomer(IntervalDate intervaloDeseado,
			Customer customer) {
		PreRegister temp = new PreRegister(intervaloDeseado, customer,this);
		this.addPreRegister(temp);
		customer.addPreRegister(temp);
		return temp;
	}

	@Transient
	public Exam getRandomExam() {
		int size = exams.size();
		Random rand = new Random();
		return (Exam) exams.toArray()[rand.nextInt(size)];
	}

	public Examination createNewExaminationForCertification(
			Date dateRealization, Date dateLimit, Integer maxCustomer,
			Integer minCustomer, Office place, Reviewer reviewer) {
		Examination temp = new Examination(dateRealization, dateLimit,
				maxCustomer, minCustomer, this, place, reviewer);
		this.addExamination(temp);
		return temp;
	}

	public void removePreRegisterCustomer(PreRegister pre) {
		this.removePreRegister(pre);
		pre.getCustomer().removePreRegister(pre);
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
		Certification other = (Certification) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

}
