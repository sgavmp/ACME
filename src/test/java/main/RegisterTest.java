package main;

import com.acme.exception.DateIncorrectException;
import com.acme.exception.ExaminationNoExistException;
import com.acme.exception.RegisterNoExistException;
import com.acme.exception.UserNoExistException;
import com.acme.model.Office;
import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;
import com.acme.model.exam.Exam;
import com.acme.model.exam.ExamType;
import com.acme.model.examination.AnswerExam;
import com.acme.model.examination.Examination;
import com.acme.model.examination.OpenAnswerExam;
import com.acme.model.examination.Register;
import com.acme.model.geography.City;
import com.acme.model.geography.Country;
import com.acme.model.geography.Language;
import com.acme.model.geography.State;
import com.acme.model.user.*;
import com.acme.repository.*;
import com.acme.services.CertificationService;
import com.acme.services.ExaminationService;
import com.acme.services.RegisterService;
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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/acme-servlet.xml")
public class RegisterTest {

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
    @Autowired
    private LanguageRepository langrep;
    @Autowired
    private RegisterService registerService;


    private static boolean first = true;

    private static User mCustomer;
    private static FamilyProfessional mProfessionalFamily;
    private static Certification mCertification;
    private static Office mOffice;
    private static Exam mExam;
    private static Examination mExamination;

    @Test
    public void testCrearExaminacion() {
        int numRegis = registerService.getAllRegisters().size();
        // Creamos un registro

        Register register = new Register(mExamination, mCustomer);

        registerService.saveRegister(register);

        assertEquals("Se ha creado 1 registro", numRegis + 1, registerService.getAllRegisters().size());

    }


    @Test
    public void testModificarCertificado() {
        Register register = new Register(mExamination, mCustomer);
        register.setComment("comentario1");
        registerService.saveRegister(register);

        Register regMod = null;
        try {
            regMod = registerService.getRegisterById(register.getId());
        }catch(Exception e){
            e.printStackTrace();
            fail();
        }
        assertNotNull("Se ha encontrado el registro", regMod);
        String newComment = "comment2";
        regMod.setComment(newComment);
        registerService.saveRegister(regMod);

        try {
            regMod = registerService.getRegisterById(register.getId());
        } catch (RegisterNoExistException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            fail();
        }
        assertEquals("El comentario del registro se ha modificado", newComment, regMod.getComment());


    }

    @Test
    public void testEliminarExaminacion() {
        // Creamos un certificado
        Register register = new Register(mExamination, mCustomer);
        register.setComment("a eliminar");
        registerService.saveRegister(register);

        // Comprobamos la eliminación
        int numReg = registerService.getAllRegisters().size();
        registerService.removeRegister(register.getId());
        assertEquals("Hay 1 registro menos en la base de datos", numReg-1, registerService.getAllRegisters().size());

    }

    @Before
    public void prepareScenario() {
        if (first) {
            first = false;

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
            Customer cust = new Customer();
            mCustomer = new User();
            mCustomer.setUsername("jonny");
            mCustomer.setEmail("doe@people.es");
            mCustomer.setMobilephone("66666666");
            mCustomer.setName("John");
            mCustomer.setSurname("Doe");
            mCustomer.setPassword("123456");
            mCustomer.setPhone("34234234");
            mCustomer.addRoleToUser(cust, UserType.ROLE_CUSTOMER);
            mCustomer.setCity(jerez);
            mCustomer.setState(c);
            mCustomer.setCountry(c1);
            mCustomer = userService.createUser(mCustomer);


            try {
                mCertification = new Certification(
                        "Ingles B1",
                        "Certificado de nivel B1 de idiomas para Inlges expedido por el Instituto de Idiomas de la Universidad de Sevilla",
                        (Double) 15.0, (Double) 30.0, "No caduca", userService
                        .getUserById(mCustomer.getId()), famrep.findOne(mProfessionalFamily.getId()),
                        Lists.newArrayList("Requisito de prueba"), 5.0);
            } catch (UserNoExistException e) {
                fail(e.getMessage());
            }
            mCertification = certrep.createCertification(mCertification);

            Language lang = new Language("Spanish");
            langrep.save(lang);
            mExam = new Exam(ExamType.OPEN_ANSWER, lang);
            mExam.createQuestion("Pregunta A", 5.5);
            mExam.createQuestion("Pregunta B", 4.5);
            mExam.setCertification(mCertification);
            mExam = examrep.save(mExam);

            mExamination = new Examination();
            try {
                mExamination.setDateRealization(new Date(1379949576000L));
                mExamination.setDateLimitRegister(new Date(1374592776000L));
                mExamination.setTimeRealization(new Date(1379949576000L));
            } catch (DateIncorrectException e) {
                fail(e.getMessage());
            }
            mExamination.setMinCustomer(10);
            mExamination.setMaxCustomer(100);
            mExamination.setCertification(mCertification);
            mExamination.setRealizationPlace(mOffice);
            mExamination.setExam(mExam);
            mExamination.setReviewer(mCustomer);
            examinationrep.saveExamination(mExamination);
        }
    }
}
