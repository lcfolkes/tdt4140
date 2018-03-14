package tdt4140.gr1823.app.core;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class ActivityManagerTest {
	
	//Attributtes
	protected ActivityManager A_Manager;
	protected DailyActivity activity;
	protected User user;
	
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Before
	public void setUp() {
		A_Manager  = new ActivityManager();
				try {
					user = new User("Andreas", LocalDate.of(1995, 06, 20), Gender.MALE, "andreas@gmail.com", "brukernavn", "Passord1");
					DailyActivity activity =  new DailyActivity(user, 10000, LocalDate.of(2018, 02, 20));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetNationalAverage() throws SQLException {
		Double a = (double) A_Manager.getNationalAverage();
		Assert.assertTrue(a instanceof Double);
	}
	
	@Test
	public void testFilter() throws SQLException {
		Double a = (double) A_Manager.filter("10", "60", "MALE");
		Assert.assertTrue(a instanceof Double);
		Double b = (double) A_Manager.filter("10", "60", "FEMALE");
		Assert.assertTrue(a instanceof Double);
		Double c = (double) A_Manager.filter("", "60", "MALE");
		Assert.assertTrue(a instanceof Double);
		Double d = (double) A_Manager.filter("", "60", "FEMALE");
		Assert.assertTrue(a instanceof Double);
		Double e = (double) A_Manager.filter("10", "", "MALE");
		Assert.assertTrue(a instanceof Double);
		Double f = (double) A_Manager.filter("10", "", "FEMALE");
		Assert.assertTrue(a instanceof Double);
		Double g = (double) A_Manager.filter("", "", "MALE");
		Assert.assertTrue(a instanceof Double);
		Double h = (double) A_Manager.filter("", "", "FEMALE");
		Assert.assertTrue(a instanceof Double);
		Double i = (double) A_Manager.filter("", "", "");
		Assert.assertTrue(a instanceof Double);
	}
	
	@Test
	public void testGetElementInArray() throws SQLException {
		A_Manager.myCon.connect();
		ArrayList<ArrayList<String>>ret = A_Manager.myCon.retrieve("SELECT AVG(Steps) FROM DailySteps");
		A_Manager.myCon.disconnect();
		String element = ActivityManager.getElementInArray(ret);
		Double a = (double) Double.parseDouble(element);
		Assert.assertTrue(a instanceof Double);
	}
	
	@Test
	public void testAddActivity() throws SQLException {
		//A_Manager.addActivity(activity);
	}
	
	@Test
	public void testConvertAgeToDate() {
		int age = 20;
		LocalDate ld = A_Manager.convertAgeToDate(age);
		LocalDate today = LocalDate.now();
		Assert.assertEquals(LocalDate.of(today.getYear() -20, today.getMonthValue(), today.getDayOfMonth()),ld);
	}
	
	//The following two steps will only run when this step instance exists in the database
	@Test
	public void testGetDailyActivity() throws SQLException{
		Double a = A_Manager.getDailyActivity(37, LocalDate.of(2018, 03, 12));
		Assert.assertTrue(a instanceof Double);
	}
	
	//since this will only run successfully when 37 has value for todays date I comment it out (Amanda)
	/*@Test
	public void testGetTodaySteps() throws SQLException {
		Double a = A_Manager.getTodaySteps(37);
		Assert.assertTrue(a instanceof Double);
	}*/
	
	
}
