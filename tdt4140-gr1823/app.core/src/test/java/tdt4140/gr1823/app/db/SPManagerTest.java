package tdt4140.gr1823.app.db;

import static org.junit.Assert.*;
import java.sql.*;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tdt4140.gr1823.app.core.SPUser;
import tdt4140.gr1823.app.db.SPManager;

public class SPManagerTest {

protected SPManager SP;
protected DBManager DB;
	
	@Before
	public void setUp() throws Exception {
		SP = new SPManager();
		DB = new DBManager();
	}
	
	
	@Test
	public void testSetRecommendedDailyActivity() {
		SP.setRecommendedDailyActivity(10000);
		assertEquals(SP.getRecommendedDailyActivity(), 10000);
	}

	// SPRINT 3 - UPDATE (starts here)
	
	@Test
	public void testAddUser() throws Exception {
		SPUser testUser = new SPUser("z_testUser@gmail.com", "Password1");
		SP.addUser(testUser);
		ArrayList<ArrayList<String>> matrise = DB.retrieve("SELECT Username FROM User WHERE Username = 'z_testUser@gmail.com';");
		ArrayList<String> rad = matrise.get(0);
		String usernameTest = (rad.get(0));
		assertEquals(usernameTest, "z_testUser@gmail.com");
		DB.execute("DELETE FROM User WHERE Username = 'z_testUser@gmail.com';");
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		ArrayList<ArrayList<String>> matriseTest1 = DB.retrieve("SELECT COUNT(*) FROM User;");
		ArrayList<String> radTest1 = matriseTest1.get(0);
		int count = Integer.parseInt((radTest1.get(0)));
		SPUser testUser = new SPUser("z_testUser@gmail.com", "Password1");
		SP.addUser(testUser);
		ArrayList<ArrayList<String>> matriseTest2 = DB.retrieve("SELECT COUNT(*) FROM User;");
		ArrayList<String> radTest2 = matriseTest2.get(0);
		int count2 = Integer.parseInt((radTest2.get(0)));
		
		assertEquals(count, count2-1);
		
		SP.deleteUser(testUser);
		ArrayList<ArrayList<String>> matriseTest3 = DB.retrieve("SELECT COUNT(*) FROM User;");
		ArrayList<String> radTest3 = matriseTest3.get(0);
		int count3 = Integer.parseInt((radTest3.get(0)));
		
		assertEquals(count2-1, count3);
		
	}
	
}
