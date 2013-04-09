package com.acme.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;
import com.acme.model.exam.Exam;
import com.acme.model.exam.Option;
import com.acme.model.exam.Question;
import com.acme.model.exam.TestQuestion;
import com.acme.model.examination.PreRegister;
import com.acme.model.user.Company;


@Repository
public class CertificationRepositoryImpl implements CertificationRepository {

	protected EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext(unitName = "Grupo15.ACME")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	public List<Certification> getAllCertifications() {
		return getEntityManager().createQuery("SELECT c FROM Certification c",
				Certification.class).getResultList();
	}

	@Transactional
	public List<Certification> getAllCertificationsFromCompany(Company c) {
		return getEntityManager()
				.createQuery(
						"SELECT c FROM Certification c WHERE c.company=:empresa",
						Certification.class).setParameter("empresa", c)
				.getResultList();
	}

	@Transactional
	public List<Certification> searchCertificationFromFamilyProfessional(
			FamilyProfessional f) {
		return getEntityManager()
				.createNamedQuery(
						"SELECT c FROM Certification c WHERE c.familyProfessional=:fp",
						Certification.class).setParameter("fp", f)
				.getResultList();
	}

	@Transactional
	public List<FamilyProfessional> getAllFamilyProfessional() {
		return getEntityManager().createQuery(
				"SELECT f FROM FamilyProfessional f", FamilyProfessional.class)
				.getResultList();
	}

	@Transactional
	public FamilyProfessional getFamilyProfessionalByName(String name) {
		List<FamilyProfessional> searchResults = getEntityManager()
				.createQuery("SELECT f FROM FamilyProfessional f WHERE f.name = :nombre", FamilyProfessional.class)
				.setParameter("nombre", name)
				.getResultList();
		return searchResults.get(0);
	}

	@Transactional
	public void removeCertification(Certification c) {
		getEntityManager().remove(getEntityManager().merge(c));
	}

	@Transactional
	public void removeFProfessional(FamilyProfessional fp) {
		getEntityManager().remove(getEntityManager().merge(fp));

	}

	@Transactional
	public void removeExam(Exam e) {
		getEntityManager().remove(getEntityManager().merge(e));

	}

	@Transactional
	public Exam getExamById(Long id) {
		return getEntityManager().find(Exam.class, id);

	}

	@Transactional
	public Certification getCertificationById(Long id) {
		return getEntityManager().find(Certification.class, id);
	}

	@Transactional
	public void removePreRegister(PreRegister p) {
		Query q = getEntityManager().createQuery("DELETE FROM PreRegister p WHERE p.id=:id");
		q.setParameter("id", p.getId());
		q.executeUpdate();

	}

	@Transactional
	public void persistCertification(Certification c) {
		getEntityManager().persist(c);
	}

	@Transactional
	public void persistFProfessional(FamilyProfessional fp) {
		getEntityManager().persist(fp);
	}

	@Transactional
	public void persistPreRegister(PreRegister p) {
		getEntityManager().persist(p);
	}

	@Transactional
	public void updateCertification(Certification c) {
		getEntityManager().merge(c);
	}

	@Transactional
	public void updateFProfessional(FamilyProfessional fp) {
		getEntityManager().merge(fp);
	}

	@Transactional
	public void updatePreRegister(PreRegister p) {
		getEntityManager().merge(p);
	}

	@Transactional
	public FamilyProfessional getFamilyProfessionalById(Long id) {
		return getEntityManager().find(FamilyProfessional.class, id);
	}


}
