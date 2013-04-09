package com.acme.repository;

import org.springframework.data.repository.CrudRepository;

import com.acme.model.Office;
import com.acme.model.examination.Examination;

public interface OfficeRepository  extends CrudRepository<Office, Long> {

}
