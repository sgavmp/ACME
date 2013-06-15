package com.acme.model.user;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.acme.model.certification.Certification;
import com.acme.model.geography.Language;


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

	@Transient
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "reviewer_language", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "languages_name"))
	private List<Language> languages;

	@Transient
	@ManyToMany
	@JoinTable(name = "reviewer_certification", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "certifications_id"))
	@LazyCollection(LazyCollectionOption.FALSE)
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
	
	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	
	public List<Certification> getCertifications() {
		return certifications;
	}

	public void setCertifications(List<Certification> certifications) {
		this.certifications = certifications;
	}

}
