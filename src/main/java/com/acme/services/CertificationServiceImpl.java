package com.acme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;
import com.acme.repository.CertificationRepository;

@Service
public class CertificationServiceImpl implements CertificationService {

	@Autowired
	private CertificationRepository repositorycert;
	
	public List<Certification> getAllCertification() {
		return repositorycert.getAllCertifications();
	}

	public Certification getCertificationById(Integer id) {
		return repositorycert.getCertificationById(id);
	}

	public void removeCertificationById(Integer id) {
		repositorycert.removeCertification(repositorycert.getCertificationById(id));

	}

	public void updateCertification(Certification cert) {
		repositorycert.updateCertification(cert);

	}

	public void createCertification(Certification cert) {
		repositorycert.persistCertification(cert);
		
	}

	public List<FamilyProfessional> getAllFamilyProfessional() {
		return repositorycert.getAllFamilyProfessional();
	}
	
	public FamilyProfessional getFamilyProfessionalByName(String name) {
		return repositorycert.getFamilyProfessionalByName(name);
	}
	

}
