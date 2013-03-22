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
public class UserServiceImpl implements UserService {

	 @Autowired
	 private UserRepository repositoryuser;
	
	@Override
	public List<User> getAllCompanyWithId() {
		return repositoryuser.getAllRole(UserType.COMPANY);
	}

}
