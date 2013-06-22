package com.acme.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.acme.model.geography.State;

public interface StateRepository  extends PagingAndSortingRepository<State, Long>  {

}
