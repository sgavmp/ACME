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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acme.exception.ExaminationNoExistException;
import com.acme.exception.PageNumberIncorrectException;
import com.acme.exception.RegisterFromExaminationExist;
import com.acme.model.examination.Examination;
import com.acme.model.examination.Register;
import com.acme.model.user.User;
import com.acme.repository.ExaminationRepository;

@Service
public class ExaminationService {

	@Autowired
	private ExaminationRepository repositoryExamination;
	
	@Autowired
	private RegisterService serviceregister;
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Examination> getAllExamination() {
		return (List<Examination>) repositoryExamination.findAll();
	}
	
	public Examination getExaminationById(Long id) throws ExaminationNoExistException{
		if (!repositoryExamination.exists(id)) {
			throw new ExaminationNoExistException("exception.examination.noexist");
		}
		return repositoryExamination.findOne(id);
	}
	
	public Examination saveExamination(Examination e) {
		return repositoryExamination.save(e);
	}
	
	public void removeExamination(Examination e) {
		repositoryExamination.delete(e);
	}
	
	public void removeExamination(Long id) {
		repositoryExamination.delete(id);
	}
	
	public List<Examination> findExaminationsByUserId(User user) {
		return repositoryExamination.findExaminationsByUserId(user);
	}

	public Page<Examination> getAllExamination(int page) throws PageNumberIncorrectException{
		if (page<0)
			throw new PageNumberIncorrectException("exception.page.upper");
		Page<Examination> exams = repositoryExamination.findAll(new PageRequest(page,10));
		if (page>=exams.getTotalPages() & page!=0)
			throw new PageNumberIncorrectException("exception.page.lower");
		else
			return exams;
	}

	@Transactional
	public Page<Examination> searchExamination(String text, Integer page) throws PageNumberIncorrectException{
		text=text.toLowerCase();
		FullTextEntityManager fullTextEntityManager = 
			    org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		QueryBuilder qb = fullTextEntityManager.getSearchFactory()
		    .buildQueryBuilder().forEntity( Examination.class ).get();
		org.apache.lucene.search.Query query = qb.keyword().wildcard().onField("certification.name")
		  .ignoreAnalyzer().ignoreFieldBridge().matching("*"+text+"*").createQuery();

		javax.persistence.Query persistenceQuery = fullTextEntityManager.createFullTextQuery(query, Examination.class);
		Integer size=persistenceQuery.getResultList().size();
		List<Examination> content= persistenceQuery.setFirstResult(page*10).setMaxResults((page*10)+10).getResultList();
		Page<Examination> result = new PageImpl<Examination>(content,new PageRequest(page,10),size);
		if (page>=result.getTotalPages() & page!=0)
			throw new NumberFormatException("exception.page.upper");
		else if (page<0)
			throw new NumberFormatException("exception.page.lower");
		else
			return result;
	}
	
	@Transactional
	public Register createRegisterFromExamination(Long examid,User user) throws Exception {
		Examination exam=this.getExaminationById(examid);
		List<Examination> examinationRegisters = this.findExaminationsByUserId(user);
		if (examinationRegisters.contains(exam))
			throw new RegisterFromExaminationExist("exception.register.exist");
		Register reg=exam.createRegisterWithoutPay(user);
		return serviceregister.saveRegister(reg);
	}
}
