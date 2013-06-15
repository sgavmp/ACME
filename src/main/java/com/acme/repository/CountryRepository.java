package com.acme.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.acme.model.geography.Country;

public interface CountryRepository  extends PagingAndSortingRepository<Country, Long>  {

}
