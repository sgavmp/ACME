package com.acme.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.acme.model.examination.Examination;


public interface ExaminationRepository extends PagingAndSortingRepository<Examination, Long> {

}
