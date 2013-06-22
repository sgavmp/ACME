package com.acme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.acme.model.examination.Register;
import com.acme.model.user.User;

public interface RegisterRepository  extends PagingAndSortingRepository<Register, Long> {

	@Query("SELECT r FROM Register r WHERE r.customer=:user")
	List<Register> findByUser(@Param("user")User id);

}
