package repository;

import java.util.List;

import model.certification.Certification;
import model.certification.FamilyProfessional;
import model.exam.Exam;
import model.exam.Option;
import model.exam.Question;
import model.exam.TestQuestion;
import model.examination.PreRegister;
import model.user.Company;

public interface CertificationRepository {
	public List<Certification> getAllCertifications();
	public List<Certification> getAllCertificationsFromCompany(Company c);
	public List<Certification> searchCertificationFromFamilyProfessional(FamilyProfessional f);
	public List<FamilyProfessional> getAllFamilyProfessional();
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
