package com.acme.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acme.model.MethodPay;
import com.acme.model.Office;
import com.acme.model.geography.City;
import com.acme.model.geography.Country;
import com.acme.model.geography.Language;
import com.acme.model.geography.State;


@Repository
public class AuxiliarRepositoryImpl implements AuxiliarRepository {
	
	protected EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}
	@PersistenceContext(unitName="Grupo15.ACME")
	public void setEntityManager(EntityManager entityManager) {
		 this.entityManager = entityManager;
	}
	
	@Transactional
	public List<Country> getAllCountry() {
		return getEntityManager().createQuery("SELECT c FROM Country c",Country.class).getResultList();
	}

	@Transactional
	public List<Language> getAllLanguage() {
		return getEntityManager().createQuery("SELECT l FROM Language l",Language.class).getResultList();
	}

	@Transactional
	public List<Office> getAllOffice() {
		return getEntityManager().createQuery("SELECT o FROM Office o",Office.class).getResultList();
	}

	@Transactional
	public List<Office> getAllOfficeFromCountry(Country c) {
		return getEntityManager().createQuery("SELECT o FROM Office o JOIN o.city c JOIN c.state s WHERE s.country=:identificador",Office.class).setParameter("identificador", c).getResultList();
	}

	@Transactional
	public List<Office> getAllOfficeFromState(State s) {
		return getEntityManager().createQuery("SELECT o FROM Office o JOIN o.city c JOIN c.state WHERE c.state=:identificador",Office.class).setParameter("identificador", s).getResultList();
	}

	@Transactional
	public List<Office> getAllOfficeFromCity(City c) {
		return getEntityManager().createQuery("SELECT o FROM Office o WHERE city=:identificador",Office.class).setParameter("identificador", c).getResultList();
	}

	@Transactional
	public List<MethodPay> getAllMethodPay() {
		return getEntityManager().createQuery("SELECT m FROM MethodPay m",MethodPay.class).getResultList();
	}

	@Transactional
	public void persistCountry(Country c) {
		getEntityManager().persist(c);

	}

	@Transactional
	public void persistLanguage(Language l) {
		getEntityManager().persist(l);

	}

	@Transactional
	public void peristOffice(Office o) {
		getEntityManager().persist(o);

	}

	@Transactional
	public void persistMethodPay(MethodPay mp) {
		getEntityManager().persist(mp);

	}

	@Transactional
	public void updateCountry(Country c) {
		getEntityManager().merge(c);

	}

	@Transactional
	public void updateLanguage(Language l) {
		getEntityManager().merge(l);

	}

	@Transactional
	public void updateOffice(Office o) {
		getEntityManager().merge(o);

	}

	@Transactional
	public void updateMethodPay(MethodPay mp) {
		getEntityManager().merge(mp);

	}

	@Transactional
	public void removeCountry(Country c) {
		getEntityManager().remove(getEntityManager().merge(c));

	}

	@Transactional
	public void removeLanguage(Language l) {
		getEntityManager().remove(getEntityManager().merge(l));

	}

	@Transactional
	public void removeOffice(Office o) {
		getEntityManager().remove(getEntityManager().merge(o));

	}

	@Transactional
	public void removeMethodPay(MethodPay mp) {
		getEntityManager().remove(getEntityManager().merge(mp));

	}
	@Transactional
	public Office getOfficeById(Integer id) {
		return getEntityManager().find(Office.class, id);
	}
	
	@Transactional
	public Language getLanguageById(Integer id) {
		return getEntityManager().find(Language.class, id);
	}
	
	@Transactional
	public Country getCountryById(Integer id) {
		return getEntityManager().find(Country.class, id);
	}
	
	@Transactional
	public MethodPay getMethodPayById(Integer id) {
		return getEntityManager().find(MethodPay.class, id);
	}

}
