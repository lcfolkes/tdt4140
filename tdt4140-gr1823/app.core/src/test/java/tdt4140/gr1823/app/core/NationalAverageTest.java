package tdt4140.gr1823.app.core;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NationalAverageTest {

	private NationalAverage nationalAverage;
	
	protected void setUp(NationalAverage nationalAverage) {
		this.nationalAverage = nationalAverage;
	}
	
	@Before
	public void setUp() {
		setUp(new NationalAverage());
	}
	
	@Test
	public void testAverage() {
		NationalAverage test = new NationalAverage();
		
	}
	
}
