package com.acme.repository;

import org.springframework.data.repository.CrudRepository;

import com.acme.model.examination.Examination;
import com.acme.model.examination.PreRegister;

public interface PreRegisterRepository  extends CrudRepository<PreRegister, Long> {

}
