package tdt4140.gr1823.app.core;

import static org.junit.Assert.*;
import java.sql.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ServiceProviderTest {

public ServiceProvider SP;
	
	@Before
	public void setUp() throws Exception {
		SP = new ServiceProvider();
	}
	
	
	@Test
	public void testSetRecommendedDailyActivity() {
		SP.setRecommendedDailyActivity(6969);
		assertEquals(SP.getRecommendedDailyActivity(), 6969);
	}
	
	@Test
	public void testGetRecommendedDailyActivity() {
		SP.setRecommendedDailyActivity(1234);		
		assertEquals(SP.getRecommendedDailyActivity(), 1234);
	}

}
