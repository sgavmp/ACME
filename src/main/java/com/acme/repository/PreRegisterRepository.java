package com.acme.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.acme.model.examination.PreRegister;

public interface PreRegisterRepository  extends PagingAndSortingRepository<PreRegister, Long> {

}
