package com.acme.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.acme.model.certification.Certification;
import com.acme.model.examination.Examination;
import com.acme.model.examination.PreRegister;
import com.acme.model.examination.Register;


public interface ExaminationRepository extends CrudRepository<Examination, Long> {

}
