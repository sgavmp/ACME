//package main;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.acme.model.certification.Certification;
//import com.acme.model.certification.FamilyProfessional;
//import com.acme.model.user.Company;
//import com.acme.model.user.User;
//import com.acme.model.user.UserType;
//import com.acme.repository.CertificationRepository;
//import com.acme.repository.ExaminationRepository;
//import com.acme.repository.UserRepository;
//import com.google.common.collect.Lists;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration("file:src/main/webapp/WEB-INF/acme-servlet.xml")
//public class CertificationControllerTest {
//
//	@Autowired
//	private WebApplicationContext wac;
//
//	@Resource(name = "repositorycert")
//	private CertificationRepository certrep;
//
//	@Resource(name = "repositoryuser")
//	private UserRepository userrep;
//
//	private MockMvc mockMvc;
//
//	private static boolean first=true;
//	
//	@Before
//	public void setup() {
//		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
//				.alwaysExpect(status().isOk()).build();
//		if (first) {
//			first=false;
//			FamilyProfessional fam1 = new FamilyProfessional("Idiomas");
//			certrep.persistFProfessional(fam1);
//	
//			List<String> requisitos = Lists
//					.newArrayList("El aspirante debera de superar cada apartado del examen");
//	
//			// Creamos un usuario con rol de company
//			Company comp = new Company();
//			User mCompany = new User();
//			mCompany.setUsername("Microsfot");
//			mCompany.setEmail("pepe@msn.es");
//			mCompany.setMobilephone("66666666");
//			mCompany.setName("Bill");
//			mCompany.setPassword("asdf87sd8f7asd");
//			mCompany.addRoleToUser(comp, UserType.COMPANY);
//			userrep.persistUser(mCompany);
//	
//			Certification cert1 = new Certification(
//					"Ingles B1",
//					"Certificado de nivel B1 de idiomas para Inlges expedido por el Instituto de Idiomas de la Universidad de Sevilla",
//					(Double) 15.0, (Double) 30.0, "No caduca", mCompany, fam1,
//					requisitos, 5.0);
//			certrep.persistCertification(cert1);
//		}
//	}
//
//	@Test
//	public void general() throws Exception {
//		this.mockMvc.perform(get("/").accept(MediaType.TEXT_HTML))
//				.andDo(print()).andExpect(status().isOk());
//
//	}
//
//	@Test
//	public void allCertification() throws Exception {
//		this.mockMvc.perform(get("/certification").accept(MediaType.TEXT_HTML))
//				.andDo(print()).andExpect(status().isOk());
//
//	}
//
//	@Test
//	public void oneCertification() throws Exception {
//		this.mockMvc
//				.perform(
//						get("/certification/edit/id/1").accept(
//								MediaType.TEXT_HTML)).andDo(print())
//				.andExpect(status().isOk());
//
//	}
//
//	@Test
//	public void noCertification() throws Exception {
//		this.mockMvc
//				.perform(
//						get("/certification/edit/id/45")).andExpect(status().isMovedTemporarily());
//
//	}
//}
