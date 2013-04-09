package com.acme.repository;

import org.springframework.data.repository.CrudRepository;

import com.acme.model.examination.Examination;
import com.acme.model.geography.State;

public interface StateRepository  extends CrudRepository<State, Long>  {

}
