package com.acme.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;
import com.acme.model.exam.Exam;
import com.acme.model.exam.Option;
import com.acme.model.exam.Question;
import com.acme.model.exam.TestQuestion;
import com.acme.model.examination.Examination;
import com.acme.model.examination.PreRegister;
import com.acme.model.user.Company;


public interface CertificationRepository  extends CrudRepository<Certification, Long> {

}
