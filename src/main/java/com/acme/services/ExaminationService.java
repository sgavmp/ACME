package com.acme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.model.examination.Examination;
import com.acme.repository.ExaminationRepository;
import com.acme.repository.StateRepository;

@Service
public class ExaminationService {

	@Autowired
	private ExaminationRepository repositoryExamination;
	
	public List<Examination> getAllExamination() {
		return (List<Examination>) repositoryExamination.findAll();
	}
	
	public Examination getExaminationById(Long id) {
		return repositoryExamination.findOne(id);
	}
	
	public Examination saveExamination(Examination e) {
		return repositoryExamination.save(e);
	}
	
	public void removeExamination(Examination e) {
		repositoryExamination.delete(e);
	}
	
	public void removeExamination(Long id) {
		repositoryExamination.delete(id);
	}
}
