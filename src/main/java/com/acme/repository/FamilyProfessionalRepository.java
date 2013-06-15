package com.acme.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.acme.model.certification.FamilyProfessional;

public interface FamilyProfessionalRepository extends PagingAndSortingRepository<FamilyProfessional, Long> {

}
