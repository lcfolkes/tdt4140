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

//	protected static DBManager DB_Manager;
//	protected static User testUser1;
//	protected static User testUser2;
	protected HttpCommunication http;
	
	

	
	@Before
	public void setUp() throws Exception {
		http = new HttpCommunication();		
		
	}
	

	@Test
	public void testGetShareValue() throws IOException, SQLException {
		//Her sjekker vi at testUser1@gmail.com har sharevalue 1.
		assertTrue(http.getShareValue("testUser1@gmail.com", "testPerson"));
		}
	
	@Test
	public void testSetShareValue() throws SQLException, IOException {
		http.setShareValue("testUser2@gmail.com", 0, "testPerson");
		assertFalse(http.getShareValue("testUser2@gmail.com", "testPerson"));
		http.setShareValue("testUser2@gmail.com", 1, "testPerson");
	}

//	@Test
//	public void testGetRecommendedDailyActivity() throws IOException {
//		assertEquals(10000, http.getRecommendedDailyActivity("testRecommendedDailyActivity"));
//	}
//
//	
//	@Test
//	public void testGetDailyActivity() throws IOException {
//		assertEquals(10000, http.getDailyActivity("andersbgandrud@gmail.com", LocalDate.of(2018, 04, 07)), 1);
//	}

	// Work in progress
//	@Test
//	public void testGetNationalAverage() throws IOException {
//		assertEquals(1000, http.getNationalAverage(), 1);
//	}

}
	


	

