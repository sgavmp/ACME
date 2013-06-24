package main;

import com.acme.exception.UserNoExistException;
import com.acme.model.geography.City;
import com.acme.model.geography.Country;
import com.acme.model.geography.State;
import com.acme.model.user.*;
import com.acme.repository.CountryRepository;
import com.acme.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.springframework.util.Assert.isTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/acme-servlet.xml")
public class UserTest {

    @Autowired
    private UserService userService;
    @Autowired
    private CountryRepository countryrep;

    private static boolean first = true;
    private static Validator validator;

    private static City mCity;
    private static State mState;
    private static Country mCountry;


    @Test
    public void testCrearUsuario() throws Exception {

        int numUser = userService.getAllUsers().size();

        // Creamos un certificado
        Company comp = new Company();
        User user = new User();
        user.setUsername("Guguel");
        user.setEmail("page@gugle.es");
        user.setMobilephone("66666666");
        user.setName("Larris");
        user.setSurname("Peis");
        user.setPassword("123456");
        user.setPhone("34234234");
        user.addRoleToUser(comp, UserType.ROLE_COMPANY);
        user.setCity(mCity);
        user.setState(mState);
        user.setCountry(mCountry);

        userService.createUser(user);
        assertEquals("Se ha creado 1 usuario", numUser + 1, userService.getAllUsers().size());


    }

    @Test
    public void testValidacionNull() {
        // Comprobamos la validación de atributos nulos
        Admin admin = new Admin();
        User user = new User();
        user.setUsername(null);
        user.setEmail(null);
        user.setMobilephone(null);
        user.setName(null);
        user.setSurname(null);
        user.setPassword(null);
        user.setPhone(null);
        user.addRoleToUser(admin, UserType.ROLE_ADMIN);
        user.setCity(null);
        user.setState(null);
        user.setCountry(null);

        try {
            userService.createUser(user);
            fail();
        } catch (Exception e) {
            isTrue(true, "No se puede guardar un usuario con atributos nulos");
        }

        Set<ConstraintViolation<User>> constraintViolations1 = validator.validate(user);
        assertEquals("Hay 15 errores de validacion por atributos nulos", 15, constraintViolations1.size());


        Company comp = new Company();
        User user2 = new User();
        user2.setUsername("");
        user2.setEmail("");
        user2.setMobilephone("");
        user2.setName("");
        user2.setSurname("");
        user2.setPassword("");
        user2.setPhone("");
        user2.addRoleToUser(comp, UserType.ROLE_COMPANY);
        user2.setCity(mCity);
        user2.setState(mState);
        user2.setCountry(mCountry);

        try {
            userService.createUser(user2);
            fail();
        } catch (Exception e) {
            isTrue(true, "No se puede guardar un usuario con atributos vacíos");
        }

        Set<ConstraintViolation<User>> constraintViolations2 = validator.validate(user);
        assertEquals("Hay 15 errores de validacion por atributos nulos", 15, constraintViolations2.size());

    }


    @Test
    public void testModificarUsuario() throws Exception {
        // Creamos un certificado
        Customer customer = new Customer();
        User user = new User();
        user.setUsername("flintstone");
        user.setEmail("stone@flint.es");
        user.setMobilephone("66666666");
        user.setName("Paco");
        user.setSurname("Picapiedras");
        user.setPassword("123456");
        user.setPhone("34234234");
        user.addRoleToUser(customer, UserType.ROLE_CUSTOMER);
        user.setCity(mCity);
        user.setState(mState);
        user.setCountry(mCountry);
        userService.createUser(user);

        // Comprobamos la modificación
        User userMod = null;
        try {
            userMod = userService.getUserById(user.getId());
        } catch (UserNoExistException e) {
            e.printStackTrace();
            fail();
        }
        String newName = "Pedro";
        userMod.setName(newName);
        userService.updateUser(userMod);

        try {
            userMod = userService.getUserById(user.getId());
        } catch (UserNoExistException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals("El nombre después de la modificación es Pedro", newName, userMod.getName());

    }

    @Test
    public void testEliminarCertificado() throws Exception {
        // Creamos un certificado
        Customer customer = new Customer();
        User user = new User();
        user.setUsername("tontin");
        user.setEmail("tontin@schtroumpfs.es");
        user.setMobilephone("66666666");
        user.setName("Tontín");
        user.setSurname("Smurf");
        user.setPassword("123456");
        user.setPhone("34234234");
        user.addRoleToUser(customer, UserType.ROLE_CUSTOMER);
        user.setCity(mCity);
        user.setState(mState);
        user.setCountry(mCountry);
        userService.createUser(user);

        // Comprobamos la eliminación
        int numUser = userService.getAllUsers().size();
        try {
            userService.removeUser(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertEquals("Se ha eliminado el usuario", numUser - 1, userService.getAllUsers().size());

    }

    @Before
    public void prepareScenario() {
        if (first) {
            first = false;
            validator = Validation.buildDefaultValidatorFactory().getValidator();

            // Creamos una serie de paises
            mCountry = new Country();
            mCountry.setName("España");

            mState = new State();
            mState.setName("Sevilla");

            mCity = new City();
            mCity.setName("Sevilla");
            mState.getCities().add(mCity);

            mCountry.getStates().add(mState);
            mCountry = countryrep.save(mCountry);
        }
    }

}
