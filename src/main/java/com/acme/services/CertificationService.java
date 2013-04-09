package com.acme.services;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;

public interface CertificationService{
	public List<Certification> getAllCertification();
	public Certification getCertificationById(Long id);
	public void removeCertificationById(Long id);
	public Certification updateCertification(Certification cert);
	public Certification createCertification(Certification cert);
	public List<FamilyProfessional> getAllFamilyProfessional();
	public FamilyProfessional getFamilyProfessionalById(Long id);
}
