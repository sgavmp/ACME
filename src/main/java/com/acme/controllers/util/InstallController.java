package com.acme.controllers.util;

import static org.junit.Assert.fail;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acme.exception.DateIncorrectException;
import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;
import com.acme.model.examination.Examination;
import com.acme.model.examination.Register;
import com.acme.model.geography.City;
import com.acme.model.geography.Country;
import com.acme.model.geography.State;
import com.acme.model.user.Admin;
import com.acme.model.user.Company;
import com.acme.model.user.Customer;
import com.acme.model.user.User;
import com.acme.model.user.UserType;
import com.acme.repository.CountryRepository;
import com.acme.services.CertificationService;
import com.acme.services.ExaminationService;
import com.acme.services.RegisterService;
import com.acme.services.UserService;
import com.acme.services.UtilService;
import com.google.common.collect.Lists;

@Controller
@RequestMapping({ "/install" })
public class InstallController {

	@Autowired
	private CertificationService certrep;

	@Autowired
	private UserService serviceuser;

	@Autowired
	private ExaminationService examinationrep;

	@Autowired
	private RegisterService registerservice;

	@Autowired
	private CountryRepository countryrep;

	// Muestra el menu de opciones
	@RequestMapping({ "", "/**" })
	public String showUtils(Model model) throws Exception {
		if (serviceuser.getAllUsers().size() == 0
				&& examinationrep.getAllExamination().size() == 0
				&& certrep.getAllCertification().size() == 0
				&& registerservice.getAllRegisters().size() == 0) {
			// Creamos una serie de paises
			Country mCountry = new Country();
			mCountry.setName("España");

			State mState = new State();
			mState.setName("Sevilla");

			City mCity = new City();
			mCity.setName("Sevilla");
			mState.getCities().add(mCity);

			State c = new State();
			c.setName("Cadiz");
			c.createCity("Cadiz");
			City jerez = new City();
			jerez.setName("Jerez");
			c.getCities().add(jerez);

			Country c3 = new Country();
			c3.setName("Francia");
			State p = new State();
			p.setName("Paris");
			p.createCity("Paris");
			c3.getStates().add(p);

			mCountry.getStates().add(mState);
			mCountry.getStates().add(c);
			mCountry = countryrep.save(mCountry);

			Customer customer = new Customer();
			User user2 = new User();
			user2.setUsername("flintstone");
			user2.setEmail("stone@flint.es");
			user2.setMobilephone("66666666");
			user2.setName("Paco");
			user2.setSurname("Picapiedras");
			user2.setPassword("123456");
			user2.setPhone("34234234");
			user2.addRoleToUser(customer, UserType.ROLE_CUSTOMER);
			user2.setCity(mCity);
			user2.setState(mState);
			user2.setCountry(mCountry);
			serviceuser.createUser(user2);

			customer = new Customer();
			User user = new User();
			user.setUsername("tontin");
			user.setEmail("tontin@schtroumpfs.es");
			user.setMobilephone("66666666");
			user.setName("Tontín");
			user.setSurname("Smurf");
			user.setPassword("123456");
			user.setPhone("34234234");
			user.addRoleToUser(customer, UserType.ROLE_COMPANY);
			user.setCity(mCity);
			user.setState(mState);
			user.setCountry(mCountry);
			serviceuser.createUser(user);

			// Creamos un usuario con rol de company, customer y admin
			Company comp = new Company();
			Customer cust = new Customer();
			Admin admin = new Admin();
			User mCompany = new User();
			mCompany.setUsername("Microsfot");
			mCompany.setEmail("pepe@msn.es");
			mCompany.setMobilephone("66666666");
			mCompany.setName("Bill");
			mCompany.setSurname("Alonso");
			mCompany.setPassword("123456");
			mCompany.setPhone("34234234");
			mCompany.addRoleToUser(comp, UserType.ROLE_REVIEWER);
			mCompany.addRoleToUser(cust, UserType.ROLE_CUSTOMER);
			mCompany.addRoleToUser(admin, UserType.ROLE_ADMIN);
			mCompany.setCity(jerez);
			mCompany.setState(c);
			mCompany.setCountry(mCountry);
			mCompany = serviceuser.createUser(mCompany);

			FamilyProfessional mProfessionalFamily = new FamilyProfessional(
					"Idomas");
			mProfessionalFamily = certrep
					.createFamilyProfessional(mProfessionalFamily);

			Certification mCertification = new Certification(
					"Ingles B1",
					"Certificado de nivel B1 de idiomas para Inlges expedido por el Instituto de Idiomas de la Universidad de Sevilla",
					(Double) 15.0, (Double) 30.0, "No caduca", user,
					mProfessionalFamily, Lists
							.newArrayList("Requisito de prueba"), 5.0);
			mCertification = certrep.createCertification(mCertification);

			Examination mExamination = new Examination();
			mExamination.setDateRealization(new Date(1379949576000L));
			mExamination.setDateLimitRegister(new Date(1374592776000L));
			mExamination.setTimeRealization(new Date(1379949576000L));
			mExamination.setMinCustomer(10);
			mExamination.setMaxCustomer(100);
			mExamination.setCertification(mCertification);
			mExamination.setReviewer(mCompany);
			examinationrep.saveExamination(mExamination);
			
			mCertification = new Certification(
					"Ingles B2",
					"Certificado de nivel B2 de idiomas para Inlges expedido por el Instituto de Idiomas de la Universidad de Sevilla",
					(Double) 15.0, (Double) 30.0, "No caduca", user,
					mProfessionalFamily, Lists
							.newArrayList("Requisito de prueba"), 5.0);
			mCertification = certrep.createCertification(mCertification);
			
			mCertification = new Certification(
					"Ingles C1",
					"Certificado de nivel C1 de idiomas para Inlges expedido por el Instituto de Idiomas de la Universidad de Sevilla",
					(Double) 15.0, (Double) 30.0, "No caduca", user,
					mProfessionalFamily, Lists
							.newArrayList("Requisito de prueba"), 5.0);
			mCertification = certrep.createCertification(mCertification);
			
			mCertification = new Certification(
					"Frances B1",
					"Certificado de nivel B1 de idiomas para Frances expedido por el Instituto de Idiomas de la Universidad de Sevilla",
					(Double) 15.0, (Double) 30.0, "No caduca", user,
					mProfessionalFamily, Lists
							.newArrayList("Requisito de prueba"), 5.0);
			mCertification = certrep.createCertification(mCertification);
			
			mCertification = new Certification(
					"Frances B2",
					"Certificado de nivel B2 de idiomas para Frances expedido por el Instituto de Idiomas de la Universidad de Sevilla",
					(Double) 15.0, (Double) 30.0, "No caduca", user,
					mProfessionalFamily, Lists
							.newArrayList("Requisito de prueba"), 5.0);
			mCertification = certrep.createCertification(mCertification);

			Register register = mExamination.createRegisterWithoutPay(user2);
			registerservice.saveRegister(register);
		}
		return "redirect:/acme/";
	}
}
