package tdt4140.gr1823.web.server;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tdt4140.gr1823.app.HttpCommunication.HttpCommunication;
import tdt4140.gr1823.app.core.Gender;
import tdt4140.gr1823.app.core.User;
import tdt4140.gr1823.app.db.DBManager;




public class HttpCommunicationTestIT {
	
	protected HttpCommunication http;
	
	
	@Before
	public void setUp() throws Exception {
		http = new HttpCommunication();			
	}
	
	//Verifies that testUser1@gmail.com has sharevalue 1.
	@Test
	public void testGetShareValue() throws IOException, SQLException {
		assertTrue(http.getShareValue("testUser1@gmail.com", "testPerson"));
		}
	//Set shareValue to 0, verify the value has been changed, and set the shareValue back to 1.
	@Test
	public void testSetShareValue() throws SQLException, IOException {
		http.setShareValue("testUser2@gmail.com", 0, "testPerson");
		assertFalse(http.getShareValue("testUser2@gmail.com", "testPerson"));
		http.setShareValue("testUser2@gmail.com", 1, "testPerson");
	}
	
	//Asserts that we are able to get the recommended number of steps from DB.
	@Test
	public void testGetRecommendedDailyActivity() throws IOException {
		assertEquals(10000, http.getRecommendedDailyActivity("testRecommendedDailyActivity"));
	}

	//Asserts that we are able to get the dailyActivity of a user from DB.
	@Test
	public void testGetDailyActivity() throws IOException {
		assertEquals(1000, http.getDailyActivity("testUser1@gmail.com", LocalDate.of(2018, 04, 9), "testDailySteps"), 1);
	}
	
	//Asserts that we are able to get the national average number of steps from DB.
	@Test
	public void testGetNationalAverage() throws IOException {
		assertEquals(8100, http.getNationalAverage("testDailySteps"), 1);
	}

}
	


	

