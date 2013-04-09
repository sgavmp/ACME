package com.acme.repository;

import java.util.List;

import com.acme.model.MethodPay;
import com.acme.model.Office;
import com.acme.model.geography.City;
import com.acme.model.geography.Country;
import com.acme.model.geography.Language;
import com.acme.model.geography.State;


public interface AuxiliarRepository {
	public List<Country> getAllCountry();
	public List<Language> getAllLanguage();
	public List<Office> getAllOffice();
	public List<Office> getAllOfficeFromCountry(Country c);
	public List<Office> getAllOfficeFromState(State s);
	public List<Office> getAllOfficeFromCity(City c);
	public List<MethodPay> getAllMethodPay();
	public void persistCountry(Country c);
	public void persistLanguage(Language l);
	public void peristOffice(Office o);
	public void persistMethodPay(MethodPay mp);
	public void updateCountry(Country c);
	public void updateLanguage(Language l);
	public void updateOffice(Office o);
	public void updateMethodPay(MethodPay mp);
	public void removeCountry(Country c);
	public void removeLanguage(Language l);
	public void removeOffice(Office o);
	public void removeMethodPay(MethodPay mp);
	public Office getOfficeById(Long id);
	public Language getLanguageById(Long id);
	public Country getCountryById(Long id);
	public MethodPay getMethodPayById(Long id);
}
