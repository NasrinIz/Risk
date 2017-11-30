package test_model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import src.model.GameConfig;
import src.model.GenericFunctions;
import src.model.Maps;
import src.model.Player;
import src.model.Territory;

public class TestGeneric {
	private GenericFunctions genFunObj;
	
	/**
	 * Over-ridden method for initial setup ran once before all test cases
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Overridden method for tear down after all test cases ends.
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Overridden method to be ran for each test case
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		genFunObj = new GenericFunctions();
	}

	/**
	 * Overridden method runs after each test case.
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Checks if cards are generated correctly
	 */
	@Test
	public void testgenStrToInt() {
		String a = "45";
		Integer b = genFunObj.genStrToInt(a);
		assertTrue(b == 45);
	}

	/**
	 * Checks if the territories are assigned correctly
	 */
	@Test
	public void testgenCommaSepStrToVector() {
		String a = "a,b,c,d";
		Vector<String> b = genFunObj.genCommaSepStrToVector(a);
		assertTrue(b.get(3).equals("d"));
	}
	
	@Test
	public void testgenCommaSepStrToArrayList() {
		String a = "a,b,c,d";
		ArrayList<String> b = genFunObj.genCommaSepStrToArrayList(a);
		assertTrue(b.get(3).equals("d"));
	}
	
	@Test
	public void testgenCommaSepStrToArray() {
		String a = "a,b,c,d";
		String[] b = genFunObj.genCommaSepStrToArray(a);
		assertTrue(b[3].equals("d"));
	}
	
	@Test
	public void testgenStringGetValueAfterEquals() {
		String a = "a=4";
		String b = genFunObj.genStringGetValueAfterEquals(a);
		assertTrue(b.equals("4"));
	}
	
	@Test
	public void testgenStringGetSplitArrayEquals() {
		String a = "a=4";
		String[] b = genFunObj.genStringGetSplitArrayEquals(a);
		assertTrue(b[1].equals("4") && b[0].equals("a"));
	}
	
	@Test
	public void testgenOrdinalIndexOf() {
		String a = "aaabaabab";
		int b = genFunObj.genOrdinalIndexOf(a, "a", 4);
		assertTrue(b == 5);
	}
	
	@Test
	public void testgenRandomNumber() {
		int b = genFunObj.genRandomNumber(1, 4);
		assertTrue((b <= 4) && (b >= 1));
	}
	
	@Test
	public void testtoString() {
		String a[] = {"a","b"};
		String b = genFunObj.toString(a);
		assertTrue(b.equals("a\nb\n"));
	}
}
