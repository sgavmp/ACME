package com.acme.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.acme.model.MethodPay;

public interface MethodPayRepository  extends PagingAndSortingRepository<MethodPay, Long> {

}
