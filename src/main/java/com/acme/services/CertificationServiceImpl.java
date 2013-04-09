package com.acme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;
import com.acme.repository.CertificationRepository;

@Service
public class CertificationServiceImpl implements CertificationService {

	@Autowired
	private CertificationRepository repositorycert;
	
	@Transactional
	public List<Certification> getAllCertification() {
		return repositorycert.getAllCertifications();
	}
	
	@Transactional
	public Certification getCertificationById(Long id) {
		return repositorycert.getCertificationById(id);
	}

	@Transactional
	public void removeCertificationById(Long id) {
		repositorycert.removeCertification(repositorycert.getCertificationById(id));
	}

	@Transactional
	public void updateCertification(Certification cert) {
		repositorycert.updateCertification(cert);

	}

	@Transactional
	public void createCertification(Certification cert) {
		repositorycert.persistCertification(cert);
		
	}

	@Transactional
	public List<FamilyProfessional> getAllFamilyProfessional() {
		return repositorycert.getAllFamilyProfessional();
	}
	
	@Transactional
	public FamilyProfessional getFamilyProfessionalById(Long id) {
		return repositorycert.getFamilyProfessionalById(id);
	}
	

}
