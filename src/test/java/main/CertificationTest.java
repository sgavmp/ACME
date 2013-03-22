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

import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;
import com.acme.model.user.Company;
import com.acme.model.user.User;
import com.acme.model.user.UserType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.acme.repository.AuxiliarRepository;
import com.acme.repository.CertificationRepository;
import com.acme.repository.ExaminationRepository;
import com.acme.repository.UserRepository;

import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CertificationTest {

	@Resource(name = "repositoryaux")
	private AuxiliarRepository auxrep;
	@Resource(name = "repositorycert")
	private CertificationRepository certrep;
	@Resource(name = "repositoryexa")
	private ExaminationRepository examrep;
	@Resource(name = "repositoryuser")
	private UserRepository userrep;

	private Validator validator;

	private Company mCompany;

	@Test
	public void test() {
		// Creamos un certificado
		FamilyProfessional fam1 = new FamilyProfessional("Idiomas");
		certrep.persistFProfessional(fam1);

		List<String> requisitos = Lists.newArrayList("El aspirante debera de superar cada apartado del examen");

		Certification cert1 = new Certification("Ingles B1", "Certificado de nivel B1 de idiomas para Inlges expedido por el Instituto de Idiomas de la Universidad de Sevilla", (Double) 15.0,
				(Double) 30.0, "No caduca", mCompany, fam1, requisitos, 5.0);

		certrep.persistCertification(cert1);

		assertEquals("El numero de certificados en la base de datos es 1", 1, certrep.getAllCertifications().size());

		// Comprobamos la validación de atributos nulos
		Certification cert2 = new Certification(null, null, null, null, null, null, null, null, null);
		try {
			certrep.persistCertification(cert2);
			fail("El certificado con valores nulos no se debe poder guardar");
		} catch (ConstraintViolationException e) {
			// No hacer nada
		}

		Set<ConstraintViolation<Certification>> constraintViolations2 = validator.validate(cert2);
		assertEquals("Haz 8 campos que fallan la validación por tener valores nulos", 8, constraintViolations2.size());

		// Comprobamos la validación de mínimos numéricos
		Certification cert3 = new Certification("Ingles B2", "Certificado de nivel B2 de idiomas para Inlges expedido por el Instituto de Idiomas de la Universidad de Sevilla", -15.0,
				-30.0, "No caduca", mCompany, fam1, requisitos, 5.0);
		try {
			certrep.persistCertification(cert3);
			fail("El certificado con valores negativos no se debe poder guardar");
		} catch (ConstraintViolationException e) {
			// No hacer nada
		}

		Set<ConstraintViolation<Certification>> constraintViolations3 = validator.validate(cert3);
		assertEquals("Haz 2 campos que fallan la validación por tener valores menores que 0", 2, constraintViolations3.size());

		// Comprobamos la modificación
		Certification certMod = certrep.getCertificationById(1);
		assertNotNull("Se ha encotnrado el certificado buscado", certMod);
		assertEquals("El nombre antes de la modificación es \"Ingles B1\"", "Ingles B1", certMod.getName());
		certMod.setName("Francés B1");
		certrep.updateCertification(certMod);

		certMod = certrep.getCertificationById(1);
		assertEquals("El nombre después de la modificación es \"Frandés B1\"", "Francés B1", certMod.getName());

		// Comprobamos la creación de otro certificado
		Certification cert4 = new Certification("Ingles C1", "Certificado de nivel C1 de idiomas para Inlgés expedido por el Instituto de Idiomas de la Universidad de Sevilla", 15.0,
				30.0, "No caduca", mCompany, fam1, requisitos, 5.0);
		certrep.persistCertification(cert4);
		assertEquals("El numero de certificados en la base de datos es 2", 2, certrep.getAllCertifications().size());

		// Comprobamos la eliminación
		certrep.removeCertification(cert4);
		assertEquals("El numero de certificados en la base de datos es 1", 1, certrep.getAllCertifications().size());
	}

	@Before
	public void prepareScenario() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();

		// Creamos un usuario con rol de company
		mCompany = new Company();
		User usuario3 = new User();
		usuario3.setUsername("Microsfot");
		usuario3.setEmail("pepe@msn.es");
		usuario3.setMobilephone("66666666");
		usuario3.setName("Bill");
		usuario3.setPassword("asdf87sd8f7asd");
		usuario3.addRoleToUser(mCompany, UserType.COMPANY);
		userrep.persistUser(usuario3);
	}

}
