package com.acme.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Service;

import com.acme.model.certification.Certification;
import com.acme.model.examination.Examination;
import com.acme.model.user.User;

@Service
public class UtilService {

	@PersistenceContext
	private EntityManager em;
	
	public void createIndexSearch() throws InterruptedException{
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
		fullTextEntityManager.createIndexer(Certification.class).purgeAllOnStart(true).startAndWait();
		fullTextEntityManager.createIndexer(User.class).purgeAllOnStart(true).startAndWait();
		fullTextEntityManager.createIndexer(Examination.class).purgeAllOnStart(true).startAndWait();
	}
}
