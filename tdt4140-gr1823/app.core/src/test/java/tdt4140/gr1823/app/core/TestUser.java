package tdt4140.gr1823.app.core;
import static org.junit.Assert.fail;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.*;


public class TestUser extends TestCase{
	
	//Attributtes
	protected User u1;
	protected int ID;
	protected String name;
	protected LocalDate dateOfBirth;
	protected Gender gender;
	protected String email;
	protected String username;
	protected String password;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		u1 = null;
	}

	
	@Before
	protected void setUp() {
/*		name = "Ola Nordmann";
		dateOfBirth = LocalDate.of(1994, 10, 14);
		gender = Gender.MALE;
		email = "ola@gmail.com";
		username = "ola";*/
		password = "Olanorge47";
		u1 = new User(3, "Ola Nordmann", LocalDate.of(1994, 10, 14), Gender.MALE, "ola@gmail.com", "ola");
	}
	
	//Testing getters and setters (including encapsulation helping methods)
	@Test
	public void testgetID() {
		assertEquals(3, u1.getID());
	}
	
	@Test
	public void testGetName() {
		assertEquals("Ola Nordmann", u1.getName());
	}
	
	@Test
	public void testGetAge() {
		assertEquals(23, u1.getAge()); //Ola is 23
	}

	@Test
	public void testGetGender() {
		assertEquals(Gender.MALE, u1.getGender());
	}	
	@Test
	public void testIsValidEmail() {
		assertEquals(true, u1.isValidEmail("ola@gmail.com")); //Valid email
		assertEquals(false, u1.isValidEmail("ola@nordmann@gmail.com")); //Checks two atts
		assertEquals(false, u1.isValidEmail("ola@gmail.nordmann")); //Checks incorrect countrycode
	}
	@Test
	public void testSetEmail() throws Exception {
		u1.setEmail("ole@gmail.com");
		assertEquals("ole@gmail.com", u1.getEmail());
	}
	@Test
	public void testGetEmail() {
		assertEquals("ola@gmail.com", u1.getEmail());
	}
	@Test
	public void testGetUsername() {
		assertEquals("ola", u1.getUsername());
	}
	@Test
	public void testisValidUsername() {
		assertTrue(u1.isValidUsername(u1.getUsername()));
	}
	@Test
	public void testIsValidPassword() {
		User u1 = new User(3, name, dateOfBirth, gender, email, username);
		assertEquals(true, u1.isValidPassword(password));
	}
	@Test
	public void testSetAndGetPassword() throws Exception {
		u1.setPassword(password);
		assertEquals(password, u1.getPassword());
	}
	
	//Testing ID
	
	
	
	//Testing advanced constructor for creating user for database
	@Test
	public void testAdvancedConstructor() throws Exception {
		User u2 = new User("Ole Nordmann", LocalDate.of(1994, 10, 14), Gender.MALE, "ola@gmail.com", "ola", password);
	}
	
	

}
