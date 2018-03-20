package tdt4140.gr1823.app.db;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tdt4140.gr1823.app.db.UserManager;

public class TestUserManager {
   
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
   
}