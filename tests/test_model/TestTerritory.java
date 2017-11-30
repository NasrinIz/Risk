package test_model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import src.model.Territory;

/**
 * This class contains test cases for territory class
 * @author vaibh
 *
 */
public class TestTerritory {

	/**
	 * Overridden method runs once for all test cases
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Overridden method runs after all test cases
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Overridden method runs before each test case
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Overridden method runs after end of each test case
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Tests the constructor with String field
	 */
	@Test
	public void testConstructor() {
		String inInfo = "India,612,249,Asia,China,Siam,Afghanistan,Middle East";
		Territory t1 = new Territory(inInfo);
		assertEquals(t1.getName(), "India");
		assertEquals(t1.getX(), new Integer(612));
		assertEquals(t1.getY(), new Integer(249));
		String [] adjTer = {"China", "Siam", "Afghanistan", "Middle East"};
		int i = 0;
		for(String t: t1.getAdjacentCountries()) {
			assertEquals(t, adjTer[i++]);
		}
	}

}
