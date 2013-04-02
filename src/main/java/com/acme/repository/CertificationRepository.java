package com.acme.repository;

import java.util.List;

import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;
import com.acme.model.exam.Exam;
import com.acme.model.exam.Option;
import com.acme.model.exam.Question;
import com.acme.model.exam.TestQuestion;
import com.acme.model.examination.PreRegister;
import com.acme.model.user.Company;


public interface CertificationRepository {
	public List<Certification> getAllCertifications();
	public List<Certification> getAllCertificationsFromCompany(Company c);
	public List<Certification> searchCertificationFromFamilyProfessional(FamilyProfessional f);
	public List<FamilyProfessional> getAllFamilyProfessional();
	public FamilyProfessional getFamilyProfessionalByName(String name);
	public FamilyProfessional getFamilyProfessionalById(Integer id);
	public void removeCertification(Certification c);
	public void removeExam(Exam e);
	public void removeFProfessional(FamilyProfessional fp);
	public void removePreRegister(PreRegister p);
	public void persistCertification(Certification c);
	public void updateCertification(Certification c);
	public void persistFProfessional(FamilyProfessional fp);
	public void updateFProfessional(FamilyProfessional fp);
	public void persistPreRegister(PreRegister p);
	public void updatePreRegister(PreRegister  p);
	public Certification getCertificationById(Integer id);
}
