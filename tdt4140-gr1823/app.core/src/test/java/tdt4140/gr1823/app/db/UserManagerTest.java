package tdt4140.gr1823.app.db;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tdt4140.gr1823.app.core.Gender;
import tdt4140.gr1823.app.core.SPUser;
import tdt4140.gr1823.app.core.User;
import tdt4140.gr1823.app.db.UserManager;

public class UserManagerTest {
   
   private static UserManager userManager;
   protected static DBManager DB;

   @BeforeClass
   public static void setUpBeforeClass() throws Exception {
	   DB = new DBManager();
	   userManager = new UserManager();
   }

   @AfterClass
   public static void tearDownAfterClass() throws Exception {
   }

//   @After
//   public void tearDown() throws Exception {
//       userManager = null;
//   }

   
//   @Before
//   public void setUp() {
//   }
//   
   
   //Teting get number of users
   @Test
   public void testGetNumberOfUsers() {
       int numUsers = 0;
       try {
           numUsers = userManager.getNumberOfUsers("testPerson");
       } catch (SQLException e) {
           e.printStackTrace();
       }
       assertNotNull(numUsers);
   
   }
   
   // SPRINT 3 - UPDATE
   
   @Test
   public void testGetShareValue() throws Exception {
	   assertTrue(userManager.getShareValue("testUser5@gmail.com", "testPerson"));
	   userManager.setShareValue("testUser5@gmail.com", 0, "testPerson");
	   assertFalse(userManager.getShareValue("testUser5@gmail.com", "testPerson"));
	   userManager.setShareValue("testUser5@gmail.com", 1, "testPerson");
   }
   
   
   @Test
	public void testAddUser() throws Exception {
	   	// Check how many registered users there are in the testPerson-table
		ArrayList<ArrayList<String>> matriseTest1 = DB.retrieve("SELECT COUNT(*) FROM testPerson;");
		ArrayList<String> radTest1 = matriseTest1.get(0);
		int count = Integer.parseInt((radTest1.get(0)));
		
		// Create and add an user into the testPerson-table.
		User testUser1 = new User("test_testUser_test@gmail.com", "Password1", "testUser10", LocalDate.of(2010, 06, 20), Gender.MALE, 1);
		userManager.addUser(testUser1, "testPerson");
		
		// Checking the count after adding the person. 
		ArrayList<ArrayList<String>> matriseTest2 = DB.retrieve("SELECT COUNT(*) FROM testPerson;");
		ArrayList<String> radTest2 = matriseTest2.get(0);
		int count2 = Integer.parseInt((radTest2.get(0)));
		
		// Verify that the count increased by one. 
		assertEquals(count, count2-1);
		
		// Deleting the user so that the testPerson table returns to original state. 
		userManager.deleteUser(testUser1, "testPerson");
		
   }
   
   
   @Test
	public void testDeleteUser() throws Exception {
	   	// Check how many registered users there are in the testPerson-table
		ArrayList<ArrayList<String>> matriseTest1 = DB.retrieve("SELECT COUNT(*) FROM testPerson;");
		ArrayList<String> radTest1 = matriseTest1.get(0);
		int count = Integer.parseInt((radTest1.get(0)));
		
		// Create and add an user into the testPerson-table.
		User testUser1 = new User("test_testUser_test@gmail.com", "Password1", "testUser10", LocalDate.of(2010, 06, 20), Gender.MALE, 1);
		userManager.addUser(testUser1, "testPerson");
		
		// Checking the count after adding the person. 
		ArrayList<ArrayList<String>> matriseTest2 = DB.retrieve("SELECT COUNT(*) FROM testPerson;");
		ArrayList<String> radTest2 = matriseTest2.get(0);
		int count2 = Integer.parseInt((radTest2.get(0)));
		
		// Verify that the count increased by one. 
		assertEquals(count, count2-1);
		
		// Deleting the user from the testUser-table.
		userManager.deleteUser(testUser1, "testPerson");
		
		// Checking the count after the delete.
		ArrayList<ArrayList<String>> matriseTest3 = DB.retrieve("SELECT COUNT(*) FROM testPerson;");
		ArrayList<String> radTest3 = matriseTest3.get(0);
		int count3 = Integer.parseInt((radTest3.get(0)));
		
		// Verify that the count increased when we deleted the user. 
		assertEquals(count2-1, count3);
		
   }  
}