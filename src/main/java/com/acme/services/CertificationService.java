package com.acme.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acme.exception.CertificationNoExistException;
import com.acme.exception.FamilyProfessionalNoExistException;
import com.acme.exception.PageNumberIncorrectException;
import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;
import com.acme.repository.CertificationRepository;
import com.acme.repository.FamilyProfessionalRepository;
import com.google.common.base.Strings;

@Service
public class CertificationService {

	@Autowired
	private CertificationRepository repositorycert;
	
	@Autowired
	private FamilyProfessionalRepository repositoryfamily;
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	@Cacheable(value = "certifications")
	public Page<Certification> getAllCertification(Integer page) throws PageNumberIncorrectException{
		if (page<0)
			throw new PageNumberIncorrectException("exception.page.upper");
		Page<Certification> certs = repositorycert.findAll(new PageRequest(page, 10));
		if (page>=certs.getTotalPages() & page!=0)
			throw new PageNumberIncorrectException("exception.page.lower");
		else
			return certs;
	}
	
	public List<Certification> getAllCertification() {
		return (List<Certification>) repositorycert.findAll();
	}
	
	@Transactional
	@Cacheable(value = "certification",key="#id")
	public Certification getCertificationById(Long id) throws CertificationNoExistException {
		if (!repositorycert.exists(id)) {
			throw new CertificationNoExistException("exception.certification.noexist");
		}
		return repositorycert.findOne(id);
	}

	@Transactional
	@Caching(evict={@CacheEvict(value="certifications",allEntries = true, beforeInvocation = false), @CacheEvict(value="certification", key="#id")})
	public void removeCertificationById(Long id) throws Exception{
		repositorycert.delete(id);
	}

	@Transactional
	@Caching(evict={@CacheEvict(value="certifications",allEntries = true, beforeInvocation = false)}, put={@CachePut(value="certification", key="#cert.id")})
	public Certification updateCertification(Certification cert) {
		return repositorycert.save(cert);

	}

	@Transactional
	@Caching(evict={@CacheEvict(value="certifications",allEntries = true, beforeInvocation = false)}, cacheable={@Cacheable(value="certification", key="#cert.id")})
	public Certification createCertification(Certification cert) {
		return repositorycert.save(cert);
		
	}

	@Transactional
	@Cacheable(value = "familyprofessional")
	public List<FamilyProfessional> getAllFamilyProfessional() {
		return (List<FamilyProfessional>) repositoryfamily.findAll();
	}
	
	@Transactional
	public FamilyProfessional getFamilyProfessionalById(Long id) throws FamilyProfessionalNoExistException {
		if (!repositoryfamily.exists(id)) {
			throw new FamilyProfessionalNoExistException("exception.family.noexist");
		}
		return repositoryfamily.findOne(id);
	}

	@Transactional
	@CacheEvict(value="familyprofessional",allEntries = true)
	public FamilyProfessional createFamilyProfessional(FamilyProfessional family) {
		return repositoryfamily.save(family);
	}

	@Transactional
	@CacheEvict(value="familyprofessional",allEntries = true)
	public void removeFamilyProfessional(Long id) {
		repositoryfamily.delete(id);
	}

	@Transactional
	@CacheEvict(value="familyprofessional",allEntries = true)
	public FamilyProfessional updateFamilyProfessional(FamilyProfessional family) {
		return repositoryfamily.save(family);
	}
	
	public long countCertifications() {
		return repositorycert.count();
	}
	
	@Transactional
	public Page<Certification> searchCertification(String s,Integer page) throws PageNumberIncorrectException {
		s=s.toLowerCase();
		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity( Certification.class ).get();
		org.apache.lucene.search.Query query = qb.keyword()
		  .wildcard().onField("name").andField("description").andField("company.name").andField("company.username")
		  .andField("company.surname").andField("company.email").ignoreAnalyzer().ignoreFieldBridge().matching("*"+s+"*").createQuery();

		javax.persistence.Query persistenceQuery = fullTextEntityManager.createFullTextQuery(query, Certification.class);

		Integer size=persistenceQuery.getResultList().size();
		List<Certification> content= persistenceQuery.setFirstResult(page*10).setMaxResults((page*10)+10).getResultList();
		Page<Certification> result = new PageImpl<Certification>(content,new PageRequest(page,10),size);
		if (page>=result.getTotalPages() & page!=0)
			throw new NumberFormatException("exception.page.upper");
		else if (page<0)
			throw new NumberFormatException("exception.page.lower");
		else
			return result;
	}
	

}
