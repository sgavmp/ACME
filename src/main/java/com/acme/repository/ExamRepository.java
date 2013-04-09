package com.acme.repository;

import org.springframework.data.repository.CrudRepository;

import com.acme.model.examination.Examination;

public interface ExamRepository  extends CrudRepository<ExamRepository, Long> {

}
