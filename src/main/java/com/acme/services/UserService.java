package com.acme.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acme.exception.PageNumberIncorrectException;
import com.acme.exception.UserNoExistException;
import com.acme.model.user.User;
import com.acme.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repositoryuser;
	
	@Autowired
	private ShaPasswordEncoder passwordEncoder;
	
	@PersistenceContext
	private EntityManager em;
	
	public List<User> getAllCompanyWithId() {
		return repositoryuser.findAllUserTypeCompany();
	}

	public User getUserById(Long id) throws UserNoExistException{
		if (!repositoryuser.exists(id)) {
			throw new UserNoExistException("exception.user.noexist");
		}
		return repositoryuser.findOne(id);
	}

	public List<User> getAllUsers() {
		return (List<User>) repositoryuser.findAll();
	}
	
	public User updateUser(User u){
		if (!u.isPassChange())
			return repositoryuser.save(u);
		else {
			u=repositoryuser.save(u);
			String pass=passwordEncoder.encodePassword(u.getPassword(), u.getId());
			u.setPassword(pass);
			return repositoryuser.save(u);
		}
	}
	
	public User createUser(User u){
		if (!u.isPassChange())
			return repositoryuser.save(u);
		else {
			u=repositoryuser.save(u);
			String pass=passwordEncoder.encodePassword(u.getPassword(), u.getId());
			u.setPassword(pass);
			return repositoryuser.save(u);
		}
	}
	
	public void removeUser(User u){
		repositoryuser.delete(u);
	}
	
	public void removeUser(Long id) throws Exception{
		repositoryuser.delete(id);
	}
	
	public User getUserByUsername(String username){
		return repositoryuser.findUserByUsername(username);
	}
	
	@Transactional
	public Page<User> searchUsers(String text, Integer page) {
		FullTextEntityManager fullTextEntityManager = 
			    org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		QueryBuilder qb = fullTextEntityManager.getSearchFactory()
		    .buildQueryBuilder().forEntity( User.class ).get();
		org.apache.lucene.search.Query query = qb
		  .keyword()
		  .wildcard()
		  .onField("name")
		  .andField("username")
		  .andField("surname")
		  .andField("email")
		  .ignoreAnalyzer()
		  .ignoreFieldBridge()
		  .matching("*"+text+"*")
		  .createQuery();

		javax.persistence.Query persistenceQuery = 
		    fullTextEntityManager.createFullTextQuery(query, User.class);

		Integer size=persistenceQuery.getResultList().size();
		List<User> content= persistenceQuery.setFirstResult(page*10).setMaxResults((page*10)+10).getResultList();
		Page<User> result = new PageImpl<User>(content,new PageRequest(page,10),size);
		if (page>=result.getTotalPages() & page!=0)
			throw new NumberFormatException("exception.page.upper");
		else if (page<0)
			throw new NumberFormatException("exception.page.lower");
		else
			return result;
	}

	public Page<User> getAllUsers(int page) throws PageNumberIncorrectException{
		if (page<0)
			throw new PageNumberIncorrectException("exception.page.upper");
		Page<User> users =repositoryuser.findAll(new PageRequest(page, 10));
		if (page>=users.getTotalPages() & page!=0)
			throw new PageNumberIncorrectException("exception.page.lower");
		else
			return users;
	}

	public ShaPasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(ShaPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
}
