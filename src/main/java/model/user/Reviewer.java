package model.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import model.certification.Certification;
import model.geography.Language;

@Entity
@DiscriminatorValue("2")
public class Reviewer extends Role {
	// -------------------------------------------------------------
	// Attributes
	// -------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Language> languages;

	private List<Certification> certifications;

	// -------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------
	public Reviewer() {
		super();
	}

	// -------------------------------------------------------------
	// Getters & Setters
	// -------------------------------------------------------------
	@Transient
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "reviewer_language", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "languages_name"))
	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	@Transient
	@ManyToMany
	@JoinTable(name = "reviewer_certification", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "certifications_id"))
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<Certification> getCertifications() {
		return certifications;
	}

	public void setCertifications(List<Certification> certifications) {
		this.certifications = certifications;
	}

}
