package repository;

import java.util.List;

import javax.management.relation.Role;

import model.examination.Register;
import model.user.Customer;
import model.user.User;
import model.user.UserType;

public interface UserRepository {
	public void updateUser(User u);
	public void removeUser(User u);
	public List<User> getAllRole(UserType comp);
	public List<User> getAllUser();
	public void persistUser(User u);
	public User getUserById(Integer id);
}
