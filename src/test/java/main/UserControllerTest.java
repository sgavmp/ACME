package main;

import java.util.List;

import javax.annotation.Resource;

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

import com.acme.model.certification.Certification;
import com.acme.model.certification.FamilyProfessional;
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
import com.acme.repository.UserRepository;
import com.acme.services.CertificationService;
import com.acme.services.UserService;
import com.google.common.collect.Lists;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/acme-servlet.xml")
public class UserControllerTest {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private UserService serviceuser;

	@Autowired
	private CountryRepository countryrep;

	private MockMvc mockMvc;

	private static User mCompany;

	private static boolean first = true;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
				.alwaysExpect(status().isOk()).build();
		if (first) {
			first = false;

			// Creamos una serie de paises
			Country c1 = new Country();
			c1.setName("Reino Unido");
			State c = new State();
			c.setName("Inglaterra");
			c.createCity("Roma");
			City bomarzo = new City();
			bomarzo.setName("Londres");
			c.getCities().add(bomarzo);
			c1.getStates().add(c);
			countryrep.save(c1);

			// Creamos un usuario con rol de company, customer y admin
			Company comp = new Company();
			Customer cust = new Customer();
			Admin admin = new Admin();
			mCompany = new User();
			mCompany.setUsername("Microsoft2");
			mCompany.setEmail("peprde@asdmsn.es");
			mCompany.setMobilephone("66666666");
			mCompany.setName("Biasdfll");
			mCompany.setSurname("Aloasdfnso");
			mCompany.setPassword("123456");
			mCompany.setPhone("34234234");
			mCompany.addRoleToUser(comp, UserType.ROLE_COMPANY);
			mCompany.setCity(bomarzo);
			mCompany.setState(c);
			mCompany.setCountry(c1);
			mCompany = serviceuser.createUser(mCompany);

		}
	}

	@Test
	public void allUser() throws Exception {
		String view = this.mockMvc.perform(get("/admin/user/").accept(MediaType.TEXT_HTML))
				.andDo(print()).andExpect(status().isOk()).andReturn().getModelAndView().getViewName();
		assertEquals("El controlador redirige a la vista /user/listUser",view,"/user/listUser");
	}

	@Test
	public void oneUser() throws Exception {
		String view = this.mockMvc
				.perform(
						get("/admin/user/edit/id/" + mCompany.getId())
						.accept(
								MediaType.TEXT_HTML)).andDo(print())
				.andExpect(status().isOk()).andReturn().getModelAndView().getViewName();
		assertEquals("El controlador redirige a la vista /user/OneUser",view,"/user/oneUser");
	}
}
