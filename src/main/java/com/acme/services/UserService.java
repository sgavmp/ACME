package com.acme.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.model.user.User;
import com.acme.model.user.UserType;
import com.acme.repository.UserRepository;
import com.google.common.collect.Collections2;
import com.google.common.collect.Maps;

@Service
public class UserService {

	@Autowired
	private UserRepository repositoryuser;
	
	public List<User> getAllCompanyWithId() {
		return repositoryuser.findAllUserTypeCompany();
	}

	public User getUserById(Long id) {
		return repositoryuser.findOne(id);
	}

	public List<User> getAllUsers() {
		return (List<User>) repositoryuser.findAll();
	}
	
	public User updateUser(User u){
		return repositoryuser.save(u);
	}
	
	public User createUser(User u){
		return repositoryuser.save(u);
	}
	
	public void removeUser(User u){
		repositoryuser.delete(u);
	}
	
	public void removeUser(Long id){
		repositoryuser.delete(id);
	}
	
	public User getUserByUsername(String username){
		return repositoryuser.findUserByUsername(username);
	}
}
