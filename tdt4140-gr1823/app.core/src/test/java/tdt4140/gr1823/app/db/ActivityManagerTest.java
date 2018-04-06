package tdt4140.gr1823.app.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue; //Sprint 3

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

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
	protected User testUser;	//Sprint 3
	protected UserManager UserManager; //Sprint 3
	protected static DBManager DB_Manager;
	
	
	//SPRINT 3 - UPDATE (start)
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DB_Manager = new DBManager();
		DB_Manager.execute("CREATE TABLE testPerson LIKE Person;");
		DB_Manager.execute("CREATE TABLE testDailySteps LIKE DailySteps;");
		testUser1 = new User("testUser1@gmail.com", "Password1", "testUser1", LocalDate.of(2000, 06, 20), Gender.MALE, 1);
		testUser2 = new User("testUser2@gmail.com", "Password1", "testUser2", LocalDate.of(1940, 06, 20), Gender.MALE, 1);
		testUser3 = new User("testUser3@gmail.com", "Password1", "testUser3", LocalDate.of(1960, 06, 20), Gender.FEMALE, 1);
		testUser4 = new User("testUser4@gmail.com", "Password1", "testUser4", LocalDate.of(1980, 06, 20), Gender.MALE, 1);
		testUser5 = new User("testUser5@gmail.com", "Password1", "testUser5", LocalDate.of(2010, 06, 20), Gender.FEMALE, 1);
		DB_Manager.execute("INSERT INTO testPerson VALUES ('"+ testUser1.getUsername() +"', '"+ testUser1.getPassword()+"', '"+ testUser1.getName() +"', '"+ testUser1.getb_Date() +"', '"+ testUser1.getGender() +"', "+ testUser1.getSharing()+");");
		DB_Manager.execute("INSERT INTO testPerson VALUES ('"+ testUser2.getUsername() +"', '"+ testUser2.getPassword()+"', '"+ testUser2.getName() +"', '"+ testUser2.getb_Date() +"', '"+ testUser2.getGender() +"', "+ testUser2.getSharing()+");");
		DB_Manager.execute("INSERT INTO testPerson VALUES ('"+ testUser3.getUsername() +"', '"+ testUser3.getPassword()+"', '"+ testUser3.getName() +"', '"+ testUser3.getb_Date() +"', '"+ testUser3.getGender() +"', "+ testUser3.getSharing()+");");
		DB_Manager.execute("INSERT INTO testPerson VALUES ('"+ testUser4.getUsername() +"', '"+ testUser4.getPassword()+"', '"+ testUser4.getName() +"', '"+ testUser4.getb_Date() +"', '"+ testUser4.getGender() +"', "+ testUser4.getSharing()+");");
		DB_Manager.execute("INSERT INTO testPerson VALUES ('"+ testUser5.getUsername() +"', '"+ testUser5.getPassword()+"', '"+ testUser5.getName() +"', '"+ testUser5.getb_Date() +"', '"+ testUser5.getGender() +"', "+ testUser5.getSharing()+");");
		
		DB_Manager.execute("INSERT INTO testDailySteps VALUES ('"+ testUser1.getUsername() +"','"+ LocalDate.now()+"', "+ 1000 +","+ 1 +");");
		DB_Manager.execute("INSERT INTO testDailySteps VALUES ('"+ testUser2.getUsername() +"','"+ LocalDate.now()+"', "+ 4500 +","+ 1 +");");
		DB_Manager.execute("INSERT INTO testDailySteps VALUES ('"+ testUser3.getUsername() +"','"+ LocalDate.now()+"', "+ 8000 +","+ 1 +");");
		DB_Manager.execute("INSERT INTO testDailySteps VALUES ('"+ testUser4.getUsername() +"','"+ LocalDate.now()+"', "+ 12000 +","+ 1 +");");
		DB_Manager.execute("INSERT INTO testDailySteps VALUES ('"+ testUser5.getUsername() +"','"+ LocalDate.now()+"', "+ 15000 +","+ 1 +");");
		
		DB_Manager.execute("ALTER TABLE Person RENAME TO PersonTemp;");
		DB_Manager.execute("ALTER TABLE DailySteps RENAME TO DailyStepsTemp;");
		DB_Manager.execute("ALTER TABLE testDailySteps RENAME TO DailySteps;");
		DB_Manager.execute("ALTER TABLE testPerson RENAME TO Person;");
		
		DB_Manager.execute("CREATE TABLE testGetElement (kolonne1 int, kolonne2 int NOT NULL, PRIMARY KEY (kolonne2));");
		DB_Manager.execute("INSERT INTO testGetElement VALUES (null,'"+2+"');");
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		DBManager DB_Manager = new DBManager();
		DB_Manager.execute("ALTER TABLE DailySteps RENAME TO testDailySteps;");
		DB_Manager.execute("ALTER TABLE Person RENAME TO testPerson;");
		DB_Manager.execute("ALTER TABLE DailyStepsTemp RENAME TO DailySteps;");
		DB_Manager.execute("ALTER TABLE PersonTemp RENAME TO Person;");
		
		DB_Manager.execute("DELETE FROM testDailySteps WHERE Username = '"+"testUser1@gmail.com"+"';");
		DB_Manager.execute("DELETE FROM testDailySteps WHERE Username = '"+"testUser2@gmail.com"+"';");
		DB_Manager.execute("DELETE FROM testDailySteps WHERE Username = '"+"testUser3@gmail.com"+"';");
		DB_Manager.execute("DELETE FROM testDailySteps WHERE Username = '"+"testUser4@gmail.com"+"';");
		DB_Manager.execute("DELETE FROM testDailySteps WHERE Username = '"+"testUser5@gmail.com"+"';");
		
		DB_Manager.execute("DELETE FROM testPerson WHERE Username = '"+"testUser1@gmail.com"+"';");
		DB_Manager.execute("DELETE FROM testPerson WHERE Username = '"+"testUser2@gmail.com"+"';");
		DB_Manager.execute("DELETE FROM testPerson WHERE Username = '"+"testUser3@gmail.com"+"';");
		DB_Manager.execute("DELETE FROM testPerson WHERE Username = '"+"testUser4@gmail.com"+"';");
		DB_Manager.execute("DELETE FROM testPerson WHERE Username = '"+"testUser5@gmail.com"+"';");

		DB_Manager.execute("DROP TABLE testGetElement;");
		DB_Manager.execute("DROP TABLE testPerson;");
		DB_Manager.execute("DROP TABLE testDailySteps;");
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
	
	
	
	@After
	public void tearDown() throws Exception {
	}
	
	
	
	@Test
	public void testGetNationalAverage() throws SQLException {
		Double a = (double) A_Manager.getNationalAverage();
		Assert.assertTrue(a instanceof Double);
	}
	
	
	@Test //Updated during Sprint 3
	public void testGetElementInArray() throws SQLException {
		A_Manager.myCon.connect();
		ArrayList<ArrayList<String>>ret = A_Manager.myCon.retrieve("SELECT AVG(Steps) FROM DailySteps");
		A_Manager.myCon.disconnect();
		String element = ActivityManager.getElementInArray(ret);
		Double a = (double) Double.parseDouble(element);
		Assert.assertTrue(a instanceof Double);
		
		ArrayList<ArrayList<String>> test = A_Manager.myCon.retrieve("SELECT kolonne1 FROM testGetElement");
		Assert.assertEquals(ActivityManager.getElementInArray(test), "0");
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
	
	
	// #SPRINT 3 UPDATE (below this mark)
	// Implemented by Tor and Anders

	@Test //UPDATED from AMANDAs original.
	public void testGetDailyActivity() throws SQLException{
		assertEquals(A_Manager.getDailyActivity(testUser2.getUsername(), LocalDate.now()), 4500, 1);
	}
	
	
	@Test
	public void testGetTodaySteps() throws Exception {
		Assert.assertEquals(A_Manager.getTodaySteps(testUser1.getUsername()), 1000, 1);
	}

	
	@Test
	public void testFilterByAge() throws Exception {
		assertEquals(8000, A_Manager.filter("", "20", ""), 1);
		assertEquals(8166, A_Manager.filter("20", "", ""), 1);
		assertEquals(6250, A_Manager.filter("50", "80", ""), 1);	
	}
	
	@Test
	public void testFilterByGenderAge() throws Exception {
		assertEquals(8100, A_Manager.filter("", "", ""), 1);
		assertEquals(5833, A_Manager.filter("", "", "MALE"), 1);
		assertEquals(11500, A_Manager.filter("", "", "FEMALE"), 1);	
		assertEquals(12000, A_Manager.filter("20", "50", "MALE"), 1);	
	}
	
	
}






