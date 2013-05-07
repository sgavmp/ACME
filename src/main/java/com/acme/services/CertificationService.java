package com.acme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;
import com.acme.repository.CertificationRepository;
import com.acme.repository.FamilyProfessionalRepository;

@Service
public class CertificationService {

	@Autowired
	private CertificationRepository repositorycert;
	@Autowired
	private FamilyProfessionalRepository repositoryfamily;
	
	@Transactional
	@Cacheable(value = "certifications")
	public List<Certification> getAllCertification() {
		return (List<Certification>) repositorycert.findAll();
	}
	
	@Transactional
	@Cacheable(value = "certification",key="#id")
	public Certification getCertificationById(Long id) {
		return repositorycert.findOne(id);
	}

	@Transactional
	@Caching(evict={@CacheEvict(value="certifications",allEntries = true, beforeInvocation = false), @CacheEvict(value="certification", key="#id")})
	public void removeCertificationById(Long id) {
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
	public FamilyProfessional getFamilyProfessionalById(Long id) {
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
	

}
