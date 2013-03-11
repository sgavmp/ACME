package repository;

import java.util.List;

import model.certification.Certification;
import model.examination.Examination;
import model.examination.PreRegister;
import model.examination.Register;

public interface ExaminationRepository {
	public List<Examination> getAllExamination();
	public List<Register> getAllRegisters();
	public void removeRegister(Register r);
	public void removeExamination(Examination e);
	public void persistRegister(Register r);
	public void persistExamination(Examination e);
	public void updateExamination(Examination e);
	public void updateRegister(Register r);
}
