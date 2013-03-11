package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.certification.Certification;
import model.examination.Examination;
import model.examination.PreRegister;
import model.examination.Register;

@Repository
public class ExaminationRepositoryImpl implements ExaminationRepository {

	protected EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext(unitName = "Grupo15.ACME")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	public List<Examination> getAllExamination() {
		return getEntityManager().createQuery("SELECT e FROM Examination e",
				Examination.class).getResultList();
	}

	@Transactional
	public List<Register> getAllRegisters() {
		return getEntityManager().createQuery("SELECT r FROM Register r",
				Register.class).getResultList();
	}

	@Transactional
	public void removeRegister(Register r) {
		r.getExamination().removeRegister(r);
		getEntityManager().remove(r);
	}

	@Transactional
	public void removeExamination(Examination e) {
		e.getCertification().removeExamination(e);
		getEntityManager().remove(e);
	}

	@Transactional
	public void persistRegister(Register r) {
		getEntityManager().persist(r);
	}

	@Transactional
	public void persistExamination(Examination e) {
		getEntityManager().persist(e);
	}

	@Transactional
	public void updateRegister(Register r) {
		getEntityManager().merge(r);
		
	}

	@Transactional
	public void updateExamination(Examination e) {
		getEntityManager().merge(e);
	}

}
