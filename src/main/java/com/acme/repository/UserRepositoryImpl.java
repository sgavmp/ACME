package com.acme.repository;

import java.util.List;

import javax.management.relation.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acme.model.examination.Register;
import com.acme.model.user.Customer;
import com.acme.model.user.User;
import com.acme.model.user.UserType;


@Repository
public class UserRepositoryImpl implements UserRepository {

	protected EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext(unitName = "Grupo15.ACME")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	public boolean existUser(String userName) {
		List<User> usuarios = getEntityManager()
				.createQuery("SELECT u FROM User u WHERE u.username=:user",
						User.class).setParameter("user", userName)
				.getResultList();
		return (usuarios.size() > 0);
	}

	@Transactional
	public User getUser(String userName, String password) {
		return getEntityManager()
				.createQuery(
						"SELECT u FROM User u WHERE u.username=:user AND u.password=:pass",
						User.class).setParameter("user", userName)
				.setParameter("pass", password).getSingleResult();
	}

	@Transactional
	public void updateUser(User u) {
		getEntityManager().merge(u);
	}

	@Transactional
	public void removeUser(User u) {
		getEntityManager().remove(getEntityManager().merge(u));
	}

	@Transactional
	public List<User> getAllRole(UserType rol) {
		return getEntityManager()
				.createQuery("SELECT u FROM User u JOIN u.roles r WHERE KEY(r)=:rol ",
						User.class).setParameter("rol", rol).getResultList();
	}

	@Transactional
	public void persistUser(User u) {
		getEntityManager().persist(u);
	}

	@Transactional
	public List<User> getAllUser() {
		return getEntityManager().createQuery("SELECT u FROM User u",User.class).getResultList();
	}


	@Transactional
	public User getUserById(Integer id) {
		return getEntityManager().find(User.class, id);
	}

}
