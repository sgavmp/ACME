package com.acme.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.acme.model.certification.Certification;


public interface CertificationRepository  extends PagingAndSortingRepository<Certification, Long> {

}
