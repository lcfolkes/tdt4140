package tdt4140.gr1823.app.core;

import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;

public class DailyActivityTest extends TestCase {
		
	//Attributtes
	protected DailyActivity da1;
	protected User user1;
	protected User user2;
	protected LocalDate date;
	protected int steps;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		user1 = null;
	}

	
	@Before
	protected void setUp() {
		date = LocalDate.of(2018, 03, 04);
		try {
			user1 = new User("Ola Nordmann", LocalDate.of(1995, 11, 11), Gender.MALE, "ola@gmail.com", "brukernavn", "Passord1");
			user2 = new User ("Kari Nordmann", LocalDate.of(1996, 05, 11), Gender.FEMALE, "kari@gmail.com", "brukernavn", "Passord2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		steps = 10000;
		da1 = new DailyActivity(user1, steps, date);
	}
	
	//Testing getters and setters (including encapsulation helping methods)
	@Test
	public void testGetSteps() {
		assertEquals(10000, da1.getSteps());
	}
	
	@Test
	public void testGetDate() {
		assertEquals(LocalDate.of(2018, 03, 04), da1.getDate());
	}

	@Test
	public void testGetUser() {
		assertEquals(user1, da1.getUser());
	}	
	
	@Test
	public void testSetSteps() {
		assertEquals(10000, da1.getSteps());
		da1.setSteps(3000);
		assertEquals(3000, da1.getSteps());
	}
	
	@Test
	public void testSetDate() {
		assertEquals(LocalDate.of(2018, 03, 04), da1.getDate());
		da1.setDate(LocalDate.of(2017, 11, 06));
		assertEquals(LocalDate.of(2017, 11, 06), da1.getDate());
	}

	@Test
	public void testSetUser() {
		assertEquals(user1, da1.getUser());
		da1.setUser(user2);
		assertEquals(user2, da1.getUser());
	}	
	
}

