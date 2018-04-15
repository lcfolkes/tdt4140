package tdt4140.gr1823.app.db;

import static org.junit.Assert.assertEquals;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tdt4140.gr1823.app.core.DailyActivity;
import tdt4140.gr1823.app.core.Gender;
import tdt4140.gr1823.app.core.User;
import tdt4140.gr1823.app.db.ActivityManager;


public class ActivityManagerTest {
	
	//Attributtes
	protected ActivityManager A_Manager;
	protected DailyActivity activity;
	protected DailyActivity testUserDailyActivity; //Sprint 3
	protected User user;
	protected static User testUser1;
	protected static User testUser2;
	protected static User testUser3;
	protected static User testUser4;
	protected static User testUser5;
	protected static User testUser6;
	protected User testUser;	//Sprint 3
	protected UserManager UserManager; //Sprint 3
	protected static DBManager DB_Manager;
	
	
	//SPRINT 3 - UPDATE (start)
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DB_Manager = new DBManager();

		testUser1 = new User("testUser1@gmail.com", "Password1", "testUser1", LocalDate.of(2000, 06, 20), Gender.MALE, 1);
		testUser2 = new User("testUser2@gmail.com", "Password1", "testUser2", LocalDate.of(1940, 06, 20), Gender.MALE, 1);
		testUser3 = new User("testUser3@gmail.com", "Password1", "testUser3", LocalDate.of(1960, 06, 20), Gender.FEMALE, 1);
		testUser4 = new User("testUser4@gmail.com", "Password1", "testUser4", LocalDate.of(1980, 06, 20), Gender.MALE, 1);
		testUser5 = new User("testUser5@gmail.com", "Password1", "testUser5", LocalDate.of(2010, 06, 20), Gender.FEMALE, 1);
		testUser6 = new User("testUser6@gmail.com", "Password1", "testUser6", LocalDate.of(2010, 06, 20), Gender.FEMALE, 1);
		
//		DB_Manager.execute("ALTER TABLE Person RENAME TO PersonTemp;");
//		DB_Manager.execute("ALTER TABLE DailySteps RENAME TO DailyStepsTemp;");
//		TimeUnit.SECONDS.sleep(1);
//		DB_Manager.execute("ALTER TABLE testDailySteps RENAME TO DailySteps;");
//		DB_Manager.execute("ALTER TABLE testPerson RENAME TO Person;");
//		
		DB_Manager.execute("CREATE TABLE testGetElement (kolonne1 int, kolonne2 int NOT NULL, PRIMARY KEY (kolonne2));");
		DB_Manager.execute("INSERT INTO testGetElement VALUES (null,'"+2+"');");
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		DBManager DB_Manager = new DBManager();
//		DB_Manager.execute("ALTER TABLE DailySteps RENAME TO testDailySteps;");
//		DB_Manager.execute("ALTER TABLE Person RENAME TO testPerson;");
//		TimeUnit.SECONDS.sleep(1);
//		DB_Manager.execute("ALTER TABLE DailyStepsTemp RENAME TO DailySteps;");
//		DB_Manager.execute("ALTER TABLE PersonTemp RENAME TO Person;");

		DB_Manager.execute("DROP TABLE testGetElement;");
	}

	//SPRINT 3 - UPDATE (end)
	
	@Before
	public void setUp() throws Exception {
		A_Manager  = new ActivityManager();
		UserManager = new UserManager(); //Sprint 3
		user = new User("andreas@gmail.com","Password1","Andreas", LocalDate.of(1995, 06, 20), Gender.MALE,1); 
		testUser = new User("testActivityManager@gmail.com", "Password1", "testBRUKER", LocalDate.of(1995, 01, 11), Gender.MALE,1); //Sprint 3
		testUserDailyActivity = new DailyActivity(testUser, 12000, LocalDate.now()); //Sprint 3
	}
	
	@Test
	public void testGetNationalAverage() throws SQLException {
		Double a = (double) A_Manager.getNationalAverage("testDailySteps");
		Assert.assertTrue(a instanceof Double);
	}
	
	
	@Test //Updated during Sprint 3
	public void testGetElementInArray() throws SQLException {
		A_Manager.myCon.connect();
		ArrayList<ArrayList<String>>ret = A_Manager.myCon.retrieve("SELECT AVG(Steps) FROM testDailySteps;");
		A_Manager.myCon.disconnect();
		String element = DBManager.getElementInArray(ret);
		Double a = (double) Double.parseDouble(element);
		Assert.assertTrue(a instanceof Double);
		
		ArrayList<ArrayList<String>> test = A_Manager.myCon.retrieve("SELECT kolonne1 FROM testGetElement;");
		Assert.assertEquals(DBManager.getElementInArray(test), "0");
	}
		
	@Test
	public void testConvertAgeToDate() {
		int age = 20;
		LocalDate ld = ActivityManager.convertAgeToDate(age);
		LocalDate today = LocalDate.now();
		Assert.assertEquals(LocalDate.of(today.getYear() -20, today.getMonthValue(), today.getDayOfMonth()),ld);
	}
	
	
	@Test
	public void testGetDailyActivity() throws SQLException{
		assertEquals(A_Manager.getDailyActivity(testUser2.getUsername(), LocalDate.of(2018, 04, 9), "testDailySteps"), 4500, 1);
	}
	
	
	@Test
	public void testGetTodaySteps() throws Exception {
		DailyActivity activity1 = new DailyActivity(testUser6, 40000, LocalDate.now());
		A_Manager.addActivity(activity1, "testDailySteps");
		Assert.assertEquals(A_Manager.getTodaySteps(testUser6.getUsername(), "testDailySteps"), 40000, 1);
		A_Manager.deleteDailyActivity(testUser6.getUsername(), LocalDate.now(), "testDailySteps");
	}

	
	@Test
	public void testFilterByAge() throws Exception {
		assertEquals(8000, A_Manager.filter("", "20", "", "testDailySteps", "testPerson"), 1);
		assertEquals(8166, A_Manager.filter("20", "", "", "testDailySteps", "testPerson"), 1);
		assertEquals(6250, A_Manager.filter("50", "80", "", "testDailySteps", "testPerson"), 1);	
	}
	
	@Test
	public void testFilterByGenderAge() throws Exception {
		assertEquals(8100, A_Manager.filter("", "", "NOT SPECIFIED", "testDailySteps", "testPerson"), 1);
		assertEquals(8100, A_Manager.filter("", "", "NOT SPECIFIED", "testDailySteps", "testPerson"), 1);
		assertEquals(5833, A_Manager.filter("", "", "MALE", "testDailySteps", "testPerson"), 1);
		assertEquals(11500, A_Manager.filter("", "", "FEMALE", "testDailySteps", "testPerson"), 1);	
		assertEquals(15000, A_Manager.filter("", "50", "FEMALE", "testDailySteps", "testPerson"), 1);	
		assertEquals(8250, A_Manager.filter("20", "", "MALE", "testDailySteps", "testPerson"), 1);	
		assertEquals(12000, A_Manager.filter("20", "50", "MALE", "testDailySteps", "testPerson"), 1);	
	}

}






