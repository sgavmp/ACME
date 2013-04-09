package com.acme.repository;

import org.springframework.data.repository.CrudRepository;

import com.acme.model.examination.Examination;
import com.acme.model.examination.Register;

public interface RegisterRepository  extends CrudRepository<Register, Long> {

}
