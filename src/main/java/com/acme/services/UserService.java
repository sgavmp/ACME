package com.acme.services;

import java.util.List;
import java.util.Map;

import com.acme.model.user.User;

public interface UserService {
	public List<User> getAllCompanyWithId();
	public User getUserById(Long id);
}
