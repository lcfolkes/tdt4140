package tdt4140.gr1823.app.db;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tdt4140.gr1823.app.core.Gender;
import tdt4140.gr1823.app.core.User;
import tdt4140.gr1823.app.db.UserManager;

public class UserManagerTest {
   
   private UserManager userManager;

   @BeforeClass
   public static void setUpBeforeClass() throws Exception {
   }

   @AfterClass
   public static void tearDownAfterClass() throws Exception {
   }

   @After
   public void tearDown() throws Exception {
       userManager = null;
   }

   
   @Before
   public void setUp() {
       userManager = new UserManager();
   }
   
   
   //Teting get number of users
   @Test
   public void testGetNumberOfUsers() {
       int numUsers = 0;
       try {
           numUsers = userManager.getNumberOfUsers();
       } catch (SQLException e) {
           e.printStackTrace();
       }
       assertNotNull(numUsers);
   
   }
   
   // SPRINT 3 - UPDATE
   
   @Test
   public void testGetShareValue() throws Exception {
	   User testUser = new User("test_testUser@gmail.com", "Password1", "testUser10", LocalDate.of(2010, 06, 20), Gender.MALE, 1);
	   userManager.addUser(testUser);
	   assertEquals(userManager.getShareValue(testUser.getUsername()), true);
	   userManager.setShareValue(testUser.getUsername(), 0);
	   assertEquals(userManager.getShareValue(testUser.getUsername()), false);
	   userManager.deleteUser(testUser);
   }
   
   
}