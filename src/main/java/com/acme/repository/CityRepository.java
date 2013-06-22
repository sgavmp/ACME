package com.acme.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.acme.model.geography.City;

public interface CityRepository  extends PagingAndSortingRepository<City, Long>  {

}
