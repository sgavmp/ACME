package main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import sun.misc.FpUtils;

import com.acme.exception.UserNoExistException;
import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;
import com.acme.model.geography.City;
import com.acme.model.geography.Country;
import com.acme.model.geography.State;
import com.acme.model.user.Admin;
import com.acme.model.user.Company;
import com.acme.model.user.Customer;
import com.acme.model.user.Role;
import com.acme.model.user.User;
import com.acme.model.user.UserType;
import com.acme.repository.CertificationRepository;
import com.acme.repository.CountryRepository;
import com.acme.repository.ExaminationRepository;
import com.acme.repository.FamilyProfessionalRepository;
import com.acme.services.UserService;
import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/acme-servlet.xml")
public class CertificationTest {

	@Autowired
	private CertificationRepository certrep;
	@Autowired
	private FamilyProfessionalRepository famrep;
	@Autowired
	private ExaminationRepository examrep;
	@Autowired
	private UserService userService;
	@Autowired
	private CountryRepository countryrep;

	private static boolean first = true;
	private static Validator validator;

	private static User mCompany;
	private static FamilyProfessional mProfessionalFamily;

	@Test
	public void testValidacionNull() {
		// Comprobamos la validación de atributos nulos
		Certification cert = new Certification(null, null, null, null, null,
				null, null, null, null);
		try {
			cert=certrep.save(cert);
			fail("El certificado con valores nulos no se debe poder guardar");
		} catch (Exception e) {
			//No hacer nada
		}

		Set<ConstraintViolation<Certification>> constraintViolations2 = validator
				.validate(cert);
		assertEquals("Hay 11 errores de validacion", 11,
				constraintViolations2.size());
	}

	@Test
	public void testValidacionNumeros() {
		// Comprobamos la validación de mínimos numéricos
		Certification cert = null;
		try {
			cert = new Certification(
					"Ingles B2",
					"Certificado de nivel B2 de idiomas para Inglés expedido por el Instituto de Idiomas de la Universidad de Sevilla",
					-15.0, -30.0, "No caduca", userService.getUserById(mCompany.getId()), famrep
							.findOne(mProfessionalFamily.getId()), Lists
							.newArrayList("Requisito de prueba"), 5.0);
			cert = certrep.save(cert);
		} catch (Exception e1) {
			//No hacer nada
			Assert.isTrue(true);
		}
		Set<ConstraintViolation<Certification>> constraintViolations3 = validator
				.validate(cert);
		assertEquals(
				"Hay 3 campos que fallan la validación por tener valores menores que 0",
				2, constraintViolations3.size());
	}

	@Test
	public void testCrearCertificado() {
		// Creamos un certificado
		
		long numCert=certrep.count();

		List<String> requisitos = Lists
				.newArrayList("El aspirante debera de superar cada apartado del examen");
		Certification cert1 = null;
		try {
			cert1 = new Certification(
					"Ingles B1",
					"Certificado de nivel B1 de idiomas para Inlges expedido por el Instituto de Idiomas de la Universidad de Sevilla",
					(Double) 15.0, (Double) 30.0, "No caduca", userService
							.getUserById(mCompany.getId()), famrep.findOne(mProfessionalFamily.getId()),
					requisitos, 5.0);
		} catch (UserNoExistException e) {
			e.printStackTrace();
		}
		certrep.save(cert1);

		assertEquals("Se ha creado 1 certificado", numCert+1, certrep.count());

		// Comprobamos la creación de otro certificado
		Certification cert2 = null;
		try {
			cert2 = new Certification(
					"Ingles C1",
					"Certificado de nivel C1 de idiomas para Inlgés expedido por el Instituto de Idiomas de la Universidad de Sevilla",
					15.0, 30.0, "No caduca", userService.getUserById(mCompany.getId()), famrep
							.findOne(mProfessionalFamily.getId()), Lists
							.newArrayList("Requisito de prueba"), 5.0);
		} catch (UserNoExistException e) {
			e.printStackTrace();
		}
		certrep.save(cert2);
		assertEquals("El numero de certificados en la base de datos es 2", numCert+2,
				certrep.count());
	}

	@Test
	public void testModificarCertificado() {
		Certification cert = null;
		try {
			cert = new Certification(
					"Inglés B1",
					"Certificado de nivel B1 de idiomas para Inlges expedido por el Instituto de Idiomas de la Universidad de Sevilla",
					(Double) 15.0, (Double) 30.0, "No caduca", userService
							.getUserById(mCompany.getId()), famrep.findOne(mProfessionalFamily.getId()), Lists
							.newArrayList("Requisito de prueba"), 5.0);
		} catch (UserNoExistException e) {
			e.printStackTrace();
		}
		certrep.save(cert);

		// Comprobamos la modificación
		Certification certMod = certrep.findOne(cert.getId());
		assertNotNull("Se ha encotnrado el certificado buscado", certMod);
		assertEquals("El nombre antes de la modificación es \"Ingles B1\"",
				"Inglés B1", certMod.getName());
		certMod.setName("Francés B1");
		certrep.save(certMod);

		certMod = certrep.findOne(cert.getId());
		assertEquals("El nombre después de la modificación es \"Frandés B1\"",
				"Francés B1", certMod.getName());
	}

	@Test
	public void testEliminarCertificado() {
		// Creamos un certificado
		
		long numCert=certrep.count();

		Certification cert = null;
		try {
			cert = new Certification(
					"Ingles B1",
					"Certificado de nivel B1 de idiomas para Inlges expedido por el Instituto de Idiomas de la Universidad de Sevilla",
					(Double) 15.0, (Double) 30.0, "No caduca", userService
							.getUserById(mCompany.getId()), famrep.findOne(mProfessionalFamily.getId()), Lists
							.newArrayList("Requisito de prueba"), 5.0);
		} catch (UserNoExistException e) {
			e.printStackTrace();
		}
		certrep.save(cert);

		assertEquals("Se ha creado 1 certificado", numCert+1, certrep.count());

		// Comprobamos la eliminación
		certrep.delete(cert);
		assertEquals("El numero de certificados en la base de datos es 3", numCert,
				certrep.count());
	}

	@Before
	public void prepareScenario() {
		if (first) {
			first = false;
			validator = Validation.buildDefaultValidatorFactory()
					.getValidator();

			// Creamos una familia profesional
			mProfessionalFamily = new FamilyProfessional("Idomas");
			mProfessionalFamily = famrep.save(mProfessionalFamily);

			// Creamos una serie de paises
			Country c1 = new Country();
			c1.setName("España");
			State c = new State();
			c.setName("Cadiz");
			c.createCity("Cadiz");
			City jerez = new City();
			jerez.setName("Jerez");
			c.getCities().add(jerez);
			State c2 = new State();
			c2.setName("Sevilla");
			c2.createCity("Sevilla");
			c1.getStates().add(c);
			c1.getStates().add(c2);
			Country c3 = new Country();
			c3.setName("Francia");
			State p = new State();
			p.setName("Paris");
			p.createCity("Paris");
			c3.getStates().add(p);
			countryrep.save(c1);
			countryrep.save(c3);

			// Creamos un usuario con rol de company, customer y admin
			Company comp = new Company();
			Customer cust = new Customer();
			Admin admin = new Admin();
			mCompany = new User();
			mCompany.setUsername("Microsfot");
			mCompany.setEmail("pepe@msn.es");
			mCompany.setMobilephone("66666666");
			mCompany.setName("Bill");
			mCompany.setSurname("Alonso");
			mCompany.setPassword("123456");
			mCompany.setPhone("34234234");
			mCompany.addRoleToUser(comp, UserType.ROLE_COMPANY);
			mCompany.addRoleToUser(cust, UserType.ROLE_CUSTOMER);
			mCompany.addRoleToUser(admin, UserType.ROLE_ADMIN);
			mCompany.setCity(jerez);
			mCompany.setState(c);
			mCompany.setCountry(c1);
			mCompany = userService.createUser(mCompany);
		}
	}

}
