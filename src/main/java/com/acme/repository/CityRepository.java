package com.acme.repository;

import org.springframework.data.repository.CrudRepository;

import com.acme.model.examination.Examination;
import com.acme.model.geography.City;
import com.acme.model.geography.Country;
import com.acme.model.geography.State;

public interface CityRepository  extends CrudRepository<City, Long>  {

}
