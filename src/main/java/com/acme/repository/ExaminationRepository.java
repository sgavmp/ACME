package com.acme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.acme.model.examination.Examination;
import com.acme.model.user.User;


public interface ExaminationRepository extends PagingAndSortingRepository<Examination, Long> {

	@Query("SELECT r.examination FROM Register r WHERE r.customer = :user")
	public List<Examination> findExaminationsByUserId(@Param("user")User user);
	
}
