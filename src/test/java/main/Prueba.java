//package main;
//
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.Resource;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
//import javax.persistence.Transient;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.acme.model.IntervalDate;
//import com.acme.model.MethodPay;
//import com.acme.model.Office;
//import com.acme.model.Pay;
//import com.acme.model.certification.Certification;
//import com.acme.model.certification.FamilyProfessional;
//import com.acme.model.exam.Exam;
//import com.acme.model.exam.ExamType;
//import com.acme.model.exam.Option;
//import com.acme.model.exam.Question;
//import com.acme.model.exam.TestQuestion;
//import com.acme.model.examination.AnswerExam;
//import com.acme.model.examination.Examination;
//import com.acme.model.examination.PreRegister;
//import com.acme.model.examination.Register;
//import com.acme.model.examination.TestAnswerExam;
//import com.acme.model.geography.City;
//import com.acme.model.geography.Country;
//import com.acme.model.geography.Language;
//import com.acme.model.geography.State;
//import com.acme.model.user.Company;
//import com.acme.model.user.Customer;
//import com.acme.model.user.Reviewer;
//import com.acme.model.user.Role;
//import com.acme.model.user.User;
//import com.acme.model.user.UserType;
//import com.acme.repository.AuxiliarRepository;
//import com.acme.repository.CertificationRepository;
//import com.acme.repository.ExaminationRepository;
//import com.acme.repository.UserRepository;
//import com.google.common.collect.Lists;
//
//
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
//public class Prueba {
//	
//	@Resource(name = "repositoryaux")
//	private AuxiliarRepository auxrep;
//	@Resource(name = "repositorycert")
//	private CertificationRepository certrep;
//	@Resource(name = "repositoryexa")
//	private ExaminationRepository examrep;
//	@Resource(name = "repositoryuser")
//	private UserRepository userrep;
//	
//	private static boolean iniciado=false;
//	
//	public void setRep(AuxiliarRepository rep) {
//		this.auxrep = rep;
//	}
//	
//	@Test
//	public void testRepositorioAuxiliar(){
//		//Probamos metodos de consultas
//		List<Country> paises = auxrep.getAllCountry();
//		Assert.assertEquals("Comprobamos que se han guardado 3 paises",3, paises.size());
//		List<Office> oficinas = auxrep.getAllOffice();
//		Assert.assertEquals("Comprobamos que se han guardado 3 oficinas",3, oficinas.size());
//		List<Language> lenguas = auxrep.getAllLanguage();
//		Assert.assertEquals("Comprobamos que se han guardado 3 lenguas",3, lenguas.size());
//		List<MethodPay> metodosPago = auxrep.getAllMethodPay();
//		Assert.assertEquals("Comprobamos que se han guardado 4 metodos de pago",4, metodosPago.size());
//		for (Country c:paises){
//			List<Office> oficinasUnPais = auxrep.getAllOfficeFromCountry(c);
//			Assert.assertEquals("Comprobamos que solo hay una oficina en cada pais",1, oficinasUnPais.size());
//			for (State s:c.getStates()){
//				List<Office> oficinasProvincia = auxrep.getAllOfficeFromState(s);
//				Assert.assertEquals("Comprobamos que solo hay una oficina en cada provincia",1, oficinasProvincia.size());
//				for (City city:s.getCities()){
//					List<Office> oficinasCiudad = auxrep.getAllOfficeFromCity(city);
//					if (!city.getName().equals("Cadiz"))
//						Assert.assertEquals("Comprobamos que solo hay una oficina en cada ciudad",1, oficinasCiudad.size());
//					else
//						Assert.assertEquals("Comprobamos que no hay oficina en cadiz",0, oficinasCiudad.size());
//				}
//			}
//		}
//		//Probamos metodos de borrado
//		auxrep.removeLanguage(lenguas.get(0));
//		lenguas = auxrep.getAllLanguage();
//		Assert.assertEquals("Comprobamos que solo quedan 2 lenguas",2, lenguas.size());
//		auxrep.removeOffice(oficinas.get(2));
//		oficinas = auxrep.getAllOffice();
//		Assert.assertEquals("Comprobamos que solo quedan 2 oficinas",2, oficinas.size());
//		auxrep.removeCountry(paises.get(2));
//		paises = auxrep.getAllCountry();
//		Assert.assertEquals("Comprobamos que ahora solo hay 2 paises",2, paises.size());
//		auxrep.removeMethodPay(metodosPago.get(0));
//		metodosPago = auxrep.getAllMethodPay();
//		Assert.assertEquals("Comprobamos que solo quedan 3 metodos de pago",3, metodosPago.size());
//		
//		//Probamos la recuperacion por id y la modificacion
//		Language l = auxrep.getLanguageById((long) 2);
//		Country c = auxrep.getCountryById((long) 2);
//		Office o = auxrep.getOfficeById((long) 2);
//		MethodPay m = auxrep.getMethodPayById((long) 2);
//		
//		l.setName("Modificado");
//		c.setName("Modificado");
//		o.setAddress("Modificado");
//		m.setName("Modificado");
//		
//		auxrep.updateLanguage(l);
//		auxrep.updateCountry(c);
//		auxrep.updateOffice(o);
//		auxrep.updateMethodPay(m);
//		
//		//Comprobamos que se han guardado correctamente
//		Assert.assertEquals("El language almacenado en la base de datos es el mismo al que tenemos en local", l, auxrep.getLanguageById((long) 2));
//		Assert.assertEquals("El pais almacenado en la base de datos es el mismo al que tenemos en local", c, auxrep.getCountryById((long) 2));
//		Assert.assertEquals("La oficina almacenado en la base de datos es el mismo al que tenemos en local", o, auxrep.getOfficeById((long) 2));
//		Assert.assertEquals("El metodo de pago almacenado en la base de datos es el mismo al que tenemos en local", m, auxrep.getMethodPayById((long) 2));
//	}
//	
//	@Test
//	public void testUserRepository(){
//		//Comprobamos con las consultas el numero de usuarios y el numero de usuarios de cada tipo
//		Assert.assertEquals("Hay 3 usuarios en la base de datos", 3, userrep.getAllUser().size());
//		Assert.assertEquals("Hay 1 usuario de tipo Customer", 1, userrep.getAllRole(UserType.CUSTOMER).size());
//		Assert.assertEquals("Hay 1 usuario de tipo Reviewer", 1, userrep.getAllRole(UserType.REVIEWER).size());
//		Assert.assertEquals("Hay 1 usuario de tipo Company", 1, userrep.getAllRole(UserType.COMPANY).size());
//		
//		//Comprobamos que se modifican los usuarios
//		User u = userrep.getUserById((long) 3);
//		u.setUsername("Modificado");
//		userrep.updateUser(u);
//		Assert.assertEquals("El usuario almacenado en la base de datos es el mismo al que tenemos en local", u, userrep.getUserById((long) 3));
//		
//		//Probamos a borrar un usuario
//		userrep.removeUser(u);
//		
//		//Comprobamos que el numero de usuario es de 2(uno menos que antes)
//		Assert.assertEquals("Hay 2 usuarios en la base de datos", 2, userrep.getAllUser().size());
//		Assert.assertEquals("Hay 1 usuario de tipo Company", 0, userrep.getAllRole(UserType.COMPANY).size());
//	}
//	
//	@Test
//	public void testCertificationRepository(){
//		//Conseguimos un certificado guardado en la base de datos
//		Certification cert = certrep.getCertificationById((long) 1);
//		Assert.assertNotNull("Se ha encotnrado el certificado buscado",cert);
//		//El numero de certificados en la base de datos es de 1
//		Assert.assertEquals("Hay un certificado guardado en la base de datos",1,certrep.getAllCertifications().size());
//		//Modificamos el certificado y lo guardamos
//		cert.setName("Modificado");
//		certrep.updateCertification(cert);
//		Assert.assertEquals("El certificado en la base de datos es igual que en local", cert,certrep.getCertificationById((long) 1));
//		//Creamos un nuevo certificado
//		Certification cert2 = new Certification();
//		certrep.persistCertification(cert2);
//		Assert.assertEquals("El numero de certificados en la base de datos es 2", 2,certrep.getAllCertifications().size());
//		//Borramos el certificado
//		certrep.removeCertification(cert2);
//		Assert.assertEquals("El numero de certificados en la base de datos es 1", 1,certrep.getAllCertifications().size());
//	}
//	
//	@Test
//	public void testExaminationRepository(){
//		 //Conseguimos el certificado de la base de datos
//		 Certification cert = certrep.getCertificationById((long) 1);
//		 //Conseguimos el usuario de la base de datos para Customer
//		 User usuario = userrep.getUserById((long) 1);
//		 Customer cust=(Customer) usuario.getRole(UserType.CUSTOMER);
//		 //Conseguimos el usuario de la base de datos para Reviewer
//		 User usuario2 = userrep.getUserById((long) 2);
//		 Reviewer rev=(Reviewer) usuario2.getRole(UserType.REVIEWER);
//		 //Conseguimos la oficina para la realizacion de la prueba
//		 Office oficina = auxrep.getOfficeById((long) 1);
//		 //Creamos un preregistro del usuario al certificado
//		 PreRegister pre=cert.createPreRegisterCustomer(new IntervalDate(new Date(2013,2,05,10,0),new Date(2013,2,07,15,0)), cust);
//		 certrep.persistPreRegister(pre);
//		 //Creamos una examination
//		 Examination e=cert.createNewExaminationForCertification(new Date(2013,2,05,10,0), new Date(2013,2,04,10,0), 30, 15, oficina, rev);
//		 examrep.persistExamination(e);
//		 Assert.assertEquals("El numero de Examination en la base de datos es de 1", 1, examrep.getAllExamination().size());
//		 //Borramos el pre registro realizado y creamos un registro a la examination creada
//		 certrep.removePreRegister(pre);
//		 Register reg=e.createRegisterWithoutPay(cust);
//		 //Guardamos el registro
//		 examrep.persistRegister(reg);
//		 Assert.assertEquals("El numero de Register en la base de datos es de 1", 1, examrep.getAllRegisters().size());
//		 //Realizamos el pago del registro y lo guardamos en la base de datos
//		 reg.payRegister(new Pay(24.45,new Date(),"Pago registro",null));
//		 examrep.updateRegister(reg);
//		 Assert.assertEquals("El registro en la base de datos es igual al registro en local", reg, examrep.getAllRegisters().get(0));
//	}
//	
//	@Before
//	public void setUp() {
//		if (!this.iniciado)
//		{
//			iniciado=true;
//			// Generamos paises de prueba
//			Country spain = new Country();
//			Country france = new Country();
//			Country italy = new Country();
//			spain.setName("España");
//			france.setName("Francia");
//			italy.setName("Italia");
//			// Generamos provincia de prueba
//			State cadiz = spain.createState("Cadiz");
//			State paris = france.createState("Paris");
//			State rome = italy.createState("Rome");
//			// Generamos ciudades de prueba
//			City jerez = cadiz.createCity("Jerez");
//			City cadizCity = cadiz.createCity("Cadiz");
//			City romeCity = rome.createCity("Rome");
//			City parisCity = paris.createCity("Paris");
//			// Guardamos en la base de datos los paises
//			auxrep.persistCountry(spain);
//			auxrep.persistCountry(france);
//			auxrep.persistCountry(italy);
//	
//			
//			// Creamos varias oficinas y las guardamos en la base de datos
//			auxrep.peristOffice(new Office("Calle Griega", 41013, "Manolo",
//					"954234765", "954987654", jerez));
//			auxrep.peristOffice(new Office("Calle Paco", 41013, "Manolo", "954234765",
//					"954987654", parisCity));
//			auxrep.peristOffice(new Office("Calle Peca", 41013, "Manolo", "954234765",
//					"954987654", romeCity));
//			
//			//Creamos varios idiomas y los guardamos en la base de datos
//			auxrep.persistLanguage(new Language("Español"));
//			auxrep.persistLanguage(new Language("Ingles"));
//			auxrep.persistLanguage(new Language("Frances"));
//			
//			//Creamos varios metodos de pago y los guardamos en la base de datos
//			auxrep.persistMethodPay(new MethodPay("Efectivo"));
//			auxrep.persistMethodPay(new MethodPay("PayPal"));
//			auxrep.persistMethodPay(new MethodPay("Tarjeta"));
//			auxrep.persistMethodPay(new MethodPay("Transferencia"));
//			
//			// Creamos un certificado
//			 FamilyProfessional fam1 = new FamilyProfessional("Idiomas");
//			 List<String> requisitos = Lists.newArrayList("El aspirante debera de superar cada apartado del examen");
//			 Certification c1 = new Certification(
//			 "Ingles B1",
//			 "Certificado de nivel B1 de idiomas para Inlges expedido por el Instituto de Idiomas de la Universidad de Sevilla",
//			 (Double) 15.0, (Double) 30.0, "No caduca",null , fam1,
//			 requisitos, 5.0);
//			// Creamos un examen
//			 Exam exam1 = c1.addExamToCertification(ExamType.MULTITEST, null);
//			 TestQuestion q = exam1.createTestQuestion("I....studying", (Double) 10.0);
//			 q.createOption("'m", true);
//			 q.createOption("am", true);
//			 q.createOption("are", false);
//			 q.createOption("is", false);
//			 certrep.persistFProfessional(fam1);
//			 certrep.persistCertification(c1);
//			 
//			 //Creamos un usuario con rol de customer
//			 User usuario =  new User();
//			 usuario.setUsername("paco");
//			 usuario.setEmail("prueba@prueba.es");
//			 usuario.setMobilephone("32423");
//			 usuario.setName("prueba");
//			 usuario.setPassword("asda");
//			 usuario.addRoleToUser(new Customer(), UserType.CUSTOMER);
//			 userrep.persistUser(usuario);
//			 
//			//Creamos un usuario con rol de reviewer
//			 User usuario2 =  new User();
//			 usuario2.setUsername("pepe");
//			 usuario2.setEmail("pepe@prueba.es");
//			 usuario2.setMobilephone("342523");
//			 usuario2.setName("pepe");
//			 usuario2.setPassword("asfsd asdfsdf");
//			 usuario2.addRoleToUser(new Reviewer(), UserType.REVIEWER);
//			 userrep.persistUser(usuario2);
//			 
//			 //Creamos un usuario con rol de company
//			 User usuario3 = new User();
//			 usuario3.setUsername("Microsfot");
//			 usuario3.setEmail("pepe@msn.es");
//			 usuario3.setMobilephone("66666666");
//			 usuario3.setName("Bill");
//			 usuario3.setPassword("asdf87sd8f7asd");
//			 usuario3.addRoleToUser(new Company(), UserType.COMPANY);
//			 userrep.persistUser(usuario3);
//		}
//	}
//}
