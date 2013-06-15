package com.acme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.acme.model.user.User;


public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	@Query("SELECT u FROM User u JOIN u.roles r WHERE KEY(r)=3")
	public List<User> findAllUserTypeCompany();
	
	@Query("SELECT u FROM User u WHERE u.username LIKE :username")
	public User findUserByUsername(@Param("username")String username);
}
