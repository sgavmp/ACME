package com.acme.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;

public interface CertificationService{
	public List<Certification> getAllCertification();
	public Certification getCertificationById(Integer id);
	public void removeCertificationById(Integer id);
	public void updateCertification(Certification cert);
	public void createCertification(Certification cert);
	public List<FamilyProfessional> getAllFamilyProfessional();
	public FamilyProfessional getFamilyProfessionalByName(String name);
}
