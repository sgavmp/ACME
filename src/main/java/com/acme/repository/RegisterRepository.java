package com.acme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.acme.model.examination.Examination;
import com.acme.model.examination.Register;

public interface RegisterRepository  extends CrudRepository<Register, Long> {

	@Query("SELECT r FROM Register r WHERE r.customer LIKE :userid")
	List<Register> findByUserId(@Param("userid")Long id);

}
