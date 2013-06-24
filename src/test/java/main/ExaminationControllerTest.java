package main;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.acme.exception.DateIncorrectException;
import com.acme.exception.UserNoExistException;
import com.acme.model.Office;
import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;
import com.acme.model.examination.Examination;
import com.acme.model.geography.City;
import com.acme.model.geography.Country;
import com.acme.model.geography.State;
import com.acme.model.user.Admin;
import com.acme.model.user.Company;
import com.acme.model.user.Customer;
import com.acme.model.user.User;
import com.acme.model.user.UserType;
import com.acme.repository.CertificationRepository;
import com.acme.repository.CountryRepository;
import com.acme.repository.ExaminationRepository;
import com.acme.repository.FamilyProfessionalRepository;
import com.acme.repository.UserRepository;
import com.acme.services.CertificationService;
import com.acme.services.ExaminationService;
import com.acme.services.UserService;
import com.google.common.collect.Lists;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/acme-servlet.xml")
public class ExaminationControllerTest {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
    private CertificationService certrep;
	@Autowired
    private FamilyProfessionalRepository famrep;
    @Autowired
    private UserService userService;
	@Autowired
    private ExaminationService examinationrep;
	@Autowired
    private CountryRepository countryrep;

	private MockMvc mockMvc;

	private static Certification certificate;
    private static User mCompany;
    private static FamilyProfessional mProfessionalFamily;
	private static Examination examination;

	private static boolean first = true;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
				.alwaysExpect(status().isOk()).build();
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


            // Creamos un usuario con rol de company, customer y admin
            Company comp = new Company();
            Customer cust = new Customer();
            Admin admin = new Admin();
            mCompany = new User();
            mCompany.setUsername("Appol");
            mCompany.setEmail("jobs@manzanilla.es");
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

            certificate = new Certification(
                    "Ingles B1",
                    "Certificado de nivel B1 de idiomas para Inlges expedido por el Instituto de Idiomas de la Universidad de Sevilla",
                    (Double) 15.0, (Double) 30.0, "No caduca", userService
                    .getUserById(mCompany.getId()), famrep.findOne(mProfessionalFamily.getId()),
                    Lists.newArrayList("Requisito de prueba"), 5.0);
			certificate = certrep.createCertification(certificate);
			// Creamos un certificado
	        examination = new Examination();
	        try {
	            examination.setDateRealization(new Date(1379949576000L));
	            examination.setDateLimitRegister(new Date(1374592776000L));
	            examination.setTimeRealization(new Date(1379949576000L));
	        } catch (DateIncorrectException e) {
	            fail(e.getMessage());
	        }
	        examination.setMinCustomer(10);
	        examination.setMaxCustomer(100);
	        examination.setCertification(certificate);
	        examination=examinationrep.saveExamination(examination);
		}
	}

	@Test
	public void allExamination() throws Exception {
		ModelAndView view = this.mockMvc.perform(get("/examination/").accept(MediaType.TEXT_HTML))
				.andDo(print()).andExpect(status().isOk()).andReturn().getModelAndView();
		assertEquals("El controlador redirige a la vista /examination/listExamination",view.getViewName(),"/examination/listExamination");
		assertEquals("En el modelo se encuentra la variable isNew con valor true",view.getModel().get("isNew"),true);
	}

	@Test
	public void oneExamination() throws Exception {
		ModelAndView view = this.mockMvc
				.perform(
						get("/admin/examination/edit/id/" + examination.getId())
						.accept(
								MediaType.TEXT_HTML)).andDo(print())
				.andExpect(status().isOk()).andReturn().getModelAndView();
		assertEquals("El controlador redirige a la vista /examination/listExamination",view.getViewName(),"/examination/listExamination");
		assertEquals("En el modelo se encuentra la examination",view.getModel().get("exam"),examination);
	}
	
}
