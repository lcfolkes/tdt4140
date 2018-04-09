package tdt4140.gr1823.app.db;

import static org.junit.Assert.*;
import java.sql.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tdt4140.gr1823.app.db.SPManager;

public class SPManagerTest {

public SPManager SP;
	
	@Before
	public void setUp() throws Exception {
		SP = new SPManager();
	}
	
	
	@Test
	public void testSetRecommendedDailyActivity() {
		SP.setRecommendedDailyActivity(10000);
		assertEquals(SP.getRecommendedDailyActivity(), 10000);
	}
	
	@Test
	public void testIsValidPasswordWrongInput() {
		assertFalse(SP.isValidPassword("halla", "heisann"));
	}
	
	@Test
	public void testIsValidPasswordCorrectInput() {
		assertTrue(SP.isValidPassword("helsedirektoratet", "gruppe23"));
	}
	
	
}
