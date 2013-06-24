package main;

import com.acme.exception.CertificationNoExistException;
import com.acme.exception.DateIncorrectException;
import com.acme.exception.ExaminationNoExistException;
import com.acme.exception.UserNoExistException;
import com.acme.model.Office;
import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;
import com.acme.model.exam.Exam;
import com.acme.model.exam.ExamType;
import com.acme.model.exam.Question;
import com.acme.model.examination.Examination;
import com.acme.model.geography.City;
import com.acme.model.geography.Country;
import com.acme.model.geography.Language;
import com.acme.model.geography.State;
import com.acme.model.user.*;
import com.acme.repository.*;
import com.acme.services.CertificationService;
import com.acme.services.ExaminationService;
import com.acme.services.UserService;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/acme-servlet.xml")
public class ExaminationTest {

    @Autowired
    private CertificationService certrep;
    @Autowired
    private FamilyProfessionalRepository famrep;
    @Autowired
    private UserService userService;
    @Autowired
    private CountryRepository countryrep;

    @Autowired
    private ExamRepository examrep;
    @Autowired
    private ExaminationService examinationrep;
    @Autowired
    private OfficeRepository officerep;


    private static boolean first = true;
    private static Validator validator;

    private static User mCompany;
    private static FamilyProfessional mProfessionalFamily;
    private static Certification mCertification;
    private static Office mOffice;

    @Test
    public void testValidacionNull() {
        // Comprobamos la validación de atributos nulos
        Examination examination = new Examination();

        examination.setMinCustomer(69);
        examination.setMaxCustomer(666);
        examination.setCertification(null);
        examination.setRealizationPlace(mOffice);
        examination.setReviewer(mCompany);

        try {
            examinationrep.saveExamination(examination);
            fail("La examinacón con valores nulos no se debe poder guardar");
        } catch (Exception e) {
            //No hacer nada
        }
        Set<ConstraintViolation<Examination>> constraintViolations2 = validator.validate(examination);
        assertEquals("Hay 3 errores de validacion", 3, constraintViolations2.size());
    }

    @Test
    public void testValidacionFechas() {
        // Comprobamos la validación de restricciones de fechas
        Examination examination = new Examination();
        try {
            examination.setDateRealization(new Date(1311434376000L));
            examination.setDateLimitRegister(new Date(1300893576000L));
            examination.setTimeRealization(new Date(1379949576000L));
        } catch (DateIncorrectException e) {
            fail(e.getMessage());
        }
        examination.setMinCustomer(6);
        examination.setMaxCustomer(17);
        examination.setCertification(mCertification);
        examination.setRealizationPlace(mOffice);
        examination.setReviewer(mCompany);
        try{
            examinationrep.saveExamination(examination);
            fail("La fecha de registro y realización no puede ser del pasado.");
        }catch (Exception e){
            Assert.isTrue(true, "No permite guardar con fecha anterior a la actual");
        }
    }

    @Test
    public void testCrearExaminacion() {
        int numExacion = examinationrep.getAllExamination().size();
        // Creamos una examinación
        Examination examination = new Examination();
        try {
            examination.setDateRealization(new Date(1379949576000L));
            examination.setDateLimitRegister(new Date(1374592776000L));
            examination.setTimeRealization(new Date(1379949576000L));
        } catch (DateIncorrectException e) {
            fail(e.getMessage());
        }
        examination.setMinCustomer(10);
        examination.setMaxCustomer(100);
        examination.setCertification(mCertification);
        examination.setRealizationPlace(mOffice);
        examination.setReviewer(mCompany);
        examinationrep.saveExamination(examination);

        assertEquals("Se ha creado 1 examinación", numExacion+1, examinationrep.getAllExamination().size());

        // Comprobamos la creación de otro certificado
        Examination examination2 = new Examination();
        try {
            examination2.setDateRealization(new Date(1379949576000L));
            examination2.setDateLimitRegister(new Date(1374592776000L));
            examination2.setTimeRealization(new Date(1379949576000L));
        } catch (DateIncorrectException e) {
            fail(e.getMessage());
        }
        examination2.setMinCustomer(10);
        examination2.setMaxCustomer(100);
        examination2.setCertification(mCertification);
        examination2.setRealizationPlace(mOffice);
        examination2.setReviewer(mCompany);
        examinationrep.saveExamination(examination2);

        assertEquals("Se ha creado otra examinación", numExacion+2, examinationrep.getAllExamination().size());

    }

    @Test
    public void testModificarCertificado() {
        Examination examination = new Examination();
        try {
            examination.setDateRealization(new Date(1379949576000L));
            examination.setDateLimitRegister(new Date(1374592776000L));
            examination.setTimeRealization(new Date(1379949576000L));
        } catch (DateIncorrectException e) {
            fail(e.getMessage());
        }
        examination.setMinCustomer(10);
        examination.setMaxCustomer(100);
        examination.setCertification(mCertification);
        examination.setRealizationPlace(mOffice);
        examination.setReviewer(mCompany);
        examinationrep.saveExamination(examination);

        // Comprobamos la modificación
        Examination examinationMod = null;
        try {
             examinationMod = examinationrep.getExaminationById(examination.getId());
        } catch (ExaminationNoExistException e) {
            e.printStackTrace();
        }
        assertNotNull("Se ha encontrado la examinación buscada", examinationMod);
        Date oldDate = examinationMod.getDateRealization();
        Date newDate = new Date(1395587976000L);

        try {
            examinationMod.setDateRealization(newDate);
        } catch (DateIncorrectException e) {
            fail();
        }
        examinationrep.saveExamination(examinationMod);

        try {
            examinationMod = examinationrep.getExaminationById(examination.getId());
        } catch (ExaminationNoExistException e) {
            fail();
        }
        
        assertFalse("La fecha de realización se ha actualizado", oldDate.equals(examinationMod.getDateRealization()));

    }

    @Test
    public void testEliminarExaminacion() {
        // Creamos un certificado
        Examination examination = new Examination();
        try {
            examination.setDateRealization(new Date(1379949576000L));
            examination.setDateLimitRegister(new Date(1374592776000L));
            examination.setTimeRealization(new Date(1379949576000L));
        } catch (DateIncorrectException e) {
            fail(e.getMessage());
        }
        examination.setMinCustomer(10);
        examination.setMaxCustomer(100);
        examination.setCertification(mCertification);
        examination.setRealizationPlace(mOffice);
        examination.setReviewer(mCompany);
        examinationrep.saveExamination(examination);

        // Comprobamos la eliminación
        int numEx = examinationrep.getAllExamination().size();
        examinationrep.removeExamination(examination.getId());
        assertEquals("Hay 1 examinación menos en la base de datos", numEx-1, examinationrep.getAllExamination().size());

    }

    @Before
    public void prepareScenario() throws Exception {
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

            mOffice = new Office("Google Plex", 21700, "Larry", "666", "777", jerez);
            mOffice = officerep.save(mOffice);


            // Creamos un usuario con rol de company, customer y admin
            Company comp = new Company();
            Customer cust = new Customer();
            Admin admin = new Admin();
            mCompany = new User();
            mCompany.setUsername("Appolo");
            mCompany.setEmail("jobs2@manzanilla.es");
            mCompany.setMobilephone("66666666");
            mCompany.setName("Estiv");
            mCompany.setSurname("Jos");
            mCompany.setPassword("123456");
            mCompany.setPhone("34234234");
            mCompany.addRoleToUser(comp, UserType.ROLE_COMPANY);
            mCompany.addRoleToUser(cust, UserType.ROLE_CUSTOMER);
            //mCompany.addRoleToUser(admin, UserType.ROLE_ADMIN);
            mCompany.setCity(jerez);
            mCompany.setState(c);
            mCompany.setCountry(c1);
            mCompany = userService.createUser(mCompany);


            try {
                mCertification = new Certification(
                        "Ingles B1",
                        "Certificado de nivel B1 de idiomas para Inlges expedido por el Instituto de Idiomas de la Universidad de Sevilla",
                        (Double) 15.0, (Double) 30.0, "No caduca", userService
                        .getUserById(mCompany.getId()), famrep.findOne(mProfessionalFamily.getId()),
                        Lists.newArrayList("Requisito de prueba"), 5.0);
            } catch (UserNoExistException e) {
                fail(e.getMessage());
            }
            mCertification = certrep.createCertification(mCertification);

        }
    }
}
