package com.acme.repository;

import java.util.List;

import javax.management.relation.Role;

import com.acme.model.examination.Register;
import com.acme.model.user.Customer;
import com.acme.model.user.User;
import com.acme.model.user.UserType;


public interface UserRepository {
	public void updateUser(User u);
	public void removeUser(User u);
	public List<User> getAllRole(UserType comp);
	public List<User> getAllUser();
	public void persistUser(User u);
	public User getUserById(Long id);
}
