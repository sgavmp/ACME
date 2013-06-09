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

import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;
import com.acme.model.user.Admin;
import com.acme.model.user.Company;
import com.acme.model.user.Customer;
import com.acme.model.user.Role;
import com.acme.model.user.User;
import com.acme.model.user.UserType;
import com.acme.repository.CertificationRepository;
import com.acme.repository.ExaminationRepository;
import com.acme.repository.FamilyProfessionalRepository;
import com.acme.repository.UserRepository;
import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class CertificationTest {

	@Autowired
	private CertificationRepository certrep;
	@Autowired
	private FamilyProfessionalRepository famrep;
	@Autowired
	private ExaminationRepository examrep;
	@Autowired
	private UserRepository userrep;

	private static boolean first=true;
	private static Validator validator;

	private User mCompany;
	private FamilyProfessional mProfessionalFamily;

	@Test
	@Ignore
	public void testValidacionNull() {
		// Comprobamos la validación de atributos nulos
		Certification cert = new Certification(null, null, null, null, null, null, null, null, null);
		try {
			certrep.save(cert);
			fail("El certificado con valores nulos no se debe poder guardar");
		} catch (ConstraintViolationException e) {
			// No hacer nada
		}

		Set<ConstraintViolation<Certification>> constraintViolations2 = validator.validate(cert);
		assertEquals("Hay 11 errores de validacion", 11, constraintViolations2.size());
	}

	@Test
	@Ignore
	public void testValidacionNumeros() {
		// Comprobamos la validación de mínimos numéricos
		Certification cert = new Certification("Ingles B2", "Certificado de nivel B2 de idiomas para Inglés expedido por el Instituto de Idiomas de la Universidad de Sevilla", -15.0,
				-30.0, "No caduca", userrep.findOne((long) 1), famrep.findOne((long) 1), Lists.newArrayList("Requisito de prueba"), 5.0);
		try {
			cert=certrep.save(cert);
			fail("El certificado con valores negativos no se debe poder guardar");
		} catch (ConstraintViolationException e) {
			// No hacer nada
		}

		Set<ConstraintViolation<Certification>> constraintViolations3 = validator.validate(cert);
		assertEquals("Haz 2 campos que fallan la validación por tener valores menores que 0", 2, constraintViolations3.size());
	}

	@Test
	public void testCrearCertificado() {
		// Creamos un certificado
		assertEquals("No hay certificados en la base de datos", 0, certrep.count());

		//List<String> requisitos = Lists.newArrayList("El aspirante debera de superar cada apartado del examen");
		List<String> requisitos = Lists.newArrayList();
		User user = userrep.findAll().iterator().next();
		FamilyProfessional family = famrep.findAll().iterator().next();
		Certification cert1 = new Certification("Ingles B1", "Certificado de nivel B1 de idiomas para Inlges expedido por el Instituto de Idiomas de la Universidad de Sevilla", (Double) 15.0,
				(Double) 30.0, "No caduca", user, family, requisitos, 5.0);
		certrep.save(cert1);

		assertEquals("Se ha creado 1 certificado", 1, certrep.count());

		// Comprobamos la creación de otro certificado
		Certification cert2 = new Certification("Ingles C1", "Certificado de nivel C1 de idiomas para Inlgés expedido por el Instituto de Idiomas de la Universidad de Sevilla", 15.0,
				30.0, "No caduca", user, family, Lists.newArrayList("Requisito de prueba"), 5.0);
		certrep.save(cert2);
		assertEquals("El numero de certificados en la base de datos es 2", 2, certrep.count());
	}

	@Test
	@Ignore
	public void testModificarCertificado() {
		Certification cert = new Certification("Inglés B1", "Certificado de nivel B1 de idiomas para Inlges expedido por el Instituto de Idiomas de la Universidad de Sevilla", (Double) 15.0,
				(Double) 30.0, "No caduca", userrep.findOne((long) 1), famrep.findOne((long) 1), Lists.newArrayList("Requisito  de prueba"), 5.0);
		certrep.save(cert);

		// Comprobamos la modificación
		Certification certMod = certrep.findOne(cert.getId());
		assertNotNull("Se ha encotnrado el certificado buscado", certMod);
		assertEquals("El nombre antes de la modificación es \"Ingles B1\"", "Inglés B1", certMod.getName());
		certMod.setName("Francés B1");
		certrep.save(certMod);

		certMod = certrep.findOne(cert.getId());
		assertEquals("El nombre después de la modificación es \"Frandés B1\"", "Francés B1", certMod.getName());
	}

	@Test
	@Ignore
	public void testEliminarCertificado() {
		// Creamos un certificado
		assertEquals("Hay 3 certificados", 3, certrep.count());

		Certification cert = new Certification("Ingles B1", "Certificado de nivel B1 de idiomas para Inlges expedido por el Instituto de Idiomas de la Universidad de Sevilla", (Double) 15.0,
				(Double) 30.0, "No caduca", userrep.findOne((long) 1), famrep.findOne((long) 1), Lists.newArrayList("Requisito de prueba"), 5.0);
		certrep.save(cert);

		assertEquals("Se ha creado 1 certificado", 4, certrep.count());

		// Comprobamos la eliminación
		certrep.delete(cert);
		assertEquals("El numero de certificados en la base de datos es 3", 3, certrep.count());
	}

	@Before
	public void prepareScenario() {
		if (first){
			first=false;
			validator = Validation.buildDefaultValidatorFactory().getValidator();
	
			// Creamos un usuario con rol de company
			Company comp = new Company();
			Customer cust = new Customer();
			Admin admin = new Admin();
			mCompany = new User();
			mCompany.setUsername("Microsfot");
			mCompany.setEmail("pepe@msn.es");
			mCompany.setMobilephone("66666666");
			mCompany.setName("Bill");
			mCompany.setPassword("123456");
			mCompany.addRoleToUser(comp, UserType.ROLE_COMPANY);
			mCompany.addRoleToUser(cust, UserType.ROLE_USER);
			mCompany.addRoleToUser(admin, UserType.ROLE_ADMIN);
			mCompany=userrep.save(mCompany);
	
			// Creamos una familia profesional
			mProfessionalFamily = new FamilyProfessional("Idiomas");
			mProfessionalFamily = famrep.save(mProfessionalFamily);
		}
	}

}
