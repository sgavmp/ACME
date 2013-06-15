package com.acme.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.acme.model.Office;

public interface OfficeRepository  extends PagingAndSortingRepository<Office, Long> {

}
