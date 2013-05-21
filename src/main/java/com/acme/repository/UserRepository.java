package com.acme.repository;

import java.util.List;

import javax.management.relation.Role;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.acme.model.examination.Register;
import com.acme.model.user.Customer;
import com.acme.model.user.User;
import com.acme.model.user.UserType;


public interface UserRepository extends CrudRepository<User, Long> {
	
	@Query("SELECT u FROM AcmeUser u JOIN u.roles r WHERE KEY(r)=3")
	public List<User> findAllUserTypeCompany();
	
	@Query("SELECT u FROM AcmeUser u WHERE u.username LIKE :username")
	public User findUserByUsername(@Param("username")String username);
}