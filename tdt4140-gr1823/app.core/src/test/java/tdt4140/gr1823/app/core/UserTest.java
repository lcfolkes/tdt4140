package tdt4140.gr1823.app.core;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule; //Sprint 3
import org.junit.Test;
import org.junit.rules.ExpectedException; //Sprint 3

import junit.framework.*;


public class UserTest extends TestCase{
	
	//Attributtes
	protected User u1;
	protected String username;
	protected String password;
	protected String name;
	protected LocalDate dateOfBirth;
	protected Gender gender;
	protected int share;

	
	
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
		username = "ola@gmail.com";
		password = "Password1";
		name = "Ola Nordmann";
		dateOfBirth = LocalDate.of(1994, 10, 14);
		gender = Gender.MALE;
		share = 1;
		try {
			u1 = new User("ola@gmail.com","Password1","Ola Nordmann",LocalDate.of(1994, 10, 14), Gender.MALE, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Testing getters and setters (including encapsulation helping methods)
	
	@Test
	public void testUsername() throws Exception {
		assertTrue(u1.isValidUsername(username)); //Valid email/username
		assertFalse(u1.isValidUsername("ola@nordmann@gmail.com")); //Checks two atts
		assertFalse(u1.isValidUsername("ola@gmail.nordmann")); //Checks incorrect countrycode
		u1.setUsername("ole@gmail.com");
		assertEquals("ole@gmail.com", u1.getUsername());//tests getter
		assertTrue(u1.isValidUsername(u1.getUsername()));//test isValidUsername
	}
	
	@Test
	public void testPassword() throws Exception {
		//a valid password must me atleast 8 characherts long and contain 1 uppercase and 1 lowercase letter 1 number.
		assertEquals(password, u1.getPassword());
		assertEquals(true, u1.isValidPassword(password));

		assertEquals(false, u1.isValidPassword("password"));
		assertEquals(false, u1.isValidPassword("PASSWORD"));
		assertEquals(false, u1.isValidPassword("Pass1"));
		
		u1.setPassword("Password2");
		assertEquals("Password2", u1.getPassword());
		assertEquals(true, u1.isValidPassword("Password2"));
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
	public void testGetB_Date() {
		assertEquals(dateOfBirth, u1.getb_Date()); 
	}

	@Test
	public void testGetGender() {
		assertEquals(Gender.MALE, u1.getGender());
	}
	
	@Test
	public void testSharing() {
		assertEquals(1, u1.getSharing());
		u1.setSharing(0);
		assertEquals(0, u1.getSharing());		
	}
	
	
	// #SPRINT 3 - UPDATE 
	// Implements testing to check if the throw new exceptions works if the share value, username or password is set to an illegal value.
	@Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testIllegalValueSetSharing() {
    	  Throwable e = null;

    	  try {
    	    u1.setSharing(2);
    	  } catch (Throwable ex) {
    	    e = ex;
    	  }

    	  assertTrue(e instanceof IllegalArgumentException);
    	}
    
    @Test
    public void testIllegalValueSetPassword() {
  	  Throwable e = null;

  	  try {
  	    u1.setPassword("zxca.<&5126§'!");
  	  } catch (Throwable ex) {
  	    e = ex;
  	  }

  	  assertTrue(e instanceof IllegalArgumentException);
  	}
    
    @Test
    public void testIllegalValueSetUsername() {
  	  Throwable e = null;

  	  try {
  	    u1.setUsername("<.,'1§1%&/PerPål");
  	  } catch (Throwable ex) {
  	    e = ex;
  	  }

  	  assertTrue(e instanceof IllegalArgumentException);
  	}
    
    
    
}









