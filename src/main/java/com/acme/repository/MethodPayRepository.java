package com.acme.repository;

import org.springframework.data.repository.CrudRepository;

import com.acme.model.MethodPay;
import com.acme.model.examination.Examination;

public interface MethodPayRepository  extends CrudRepository<MethodPay, Long> {

}
