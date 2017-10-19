package test_model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Continent;
import model.GameConfig;
import model.Territory;

public class TestContinent {

	private static GameConfig gameConfig;
	private static Continent testCont;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		gameConfig =  new GameConfig(4, "testing_maps//valid_1");
		testCont = gameConfig.getMapObj().getDictContinents().get("B");
		String contStrB = "B1,300,120,B,A2,B2,B3\r\n" + 
				"B2,300,180,B,A3,B1,B3\r\n" + 
				"B3,360,150,B,B1,B2,A1,A4";
		String[] terrB = contStrB.split("\\n");

		ArrayList<Territory> contB_terr = new ArrayList<Territory>();

		for (int i = 0; i < terrB.length; i++) {
			Territory newTer =  new Territory(terrB[i]);
			contB_terr.add( newTer );
		}
	}

	@After
	public void tearDown() throws Exception {
		gameConfig = null;
	}

	@Test
	public void testContinentInfo() {
		
		assertEquals(testCont.getArmyReward(), 3);
		assertEquals(testCont.getName(), "B");
		ArrayList<String> actuals = new ArrayList<String>(Arrays.asList("B1","B2","B3"));
		for(Territory t: testCont.getTerritories()) {
			assertTrue(actuals.contains(t.getName()));
		}
	}
	
	@Test 
	public void testRemoveTerritory() {
		// won't work until equals is overridden in Territory
		
//		Territory terB1 = new Territory("B1,300,120,B,A2,B2,B3");
//		assertTrue(testCont.getTerritories().contains(terB1));
//		testCont.removeTerritory(terB1);
//		assertFalse(testCont.getTerritories().contains(terB1));
	}
}
