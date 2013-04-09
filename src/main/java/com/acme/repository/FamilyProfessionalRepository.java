package com.acme.repository;

import org.springframework.data.repository.CrudRepository;

import com.acme.model.certification.FamilyProfessional;
import com.acme.model.examination.Examination;

public interface FamilyProfessionalRepository extends CrudRepository<FamilyProfessional, Long> {

}
