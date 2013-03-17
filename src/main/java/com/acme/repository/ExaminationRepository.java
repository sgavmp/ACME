package com.acme.repository;

import java.util.List;

import com.acme.model.certification.Certification;
import com.acme.model.examination.Examination;
import com.acme.model.examination.PreRegister;
import com.acme.model.examination.Register;


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
