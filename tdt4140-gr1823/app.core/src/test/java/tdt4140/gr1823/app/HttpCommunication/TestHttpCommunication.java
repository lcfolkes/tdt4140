package tdt4140.gr1823.app.HttpCommunication;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class TestHttpCommunication {
	
	private HttpCommunication testCommunication;
	@Before
	public void setUp() throws Exception {
		
		testCommunication = new HttpCommunication();
		
	}

	@Test //Test getRecommendedDailyActivity();
	public void test() throws IOException {
		int recommendedActivity = testCommunication.getRecommendedDailyActivity();
		assertTrue(Integer.valueOf(recommendedActivity) instanceof Integer);
	}

}
