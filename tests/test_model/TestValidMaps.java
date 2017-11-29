package test_model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import src.model.Continent;
import src.model.GameConfig;
import src.model.Maps;
import src.model.Territory;

public class TestValidMaps {

	private static Map<String, Continent> dictContinents;
	private static Map<String, Territory> dictTerritory;
	private static GameConfig gameConfig;
	private Maps objMap = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gameConfig = new GameConfig(3,"testing_maps//valid_1", null);

		dictContinents = new HashMap<String, Continent>(2, 2);
		dictTerritory = new HashMap<String, Territory>(2, 2);

		String contStrA = "A1,120,120,A,A2,A4,B3\r\n" + 
				"A2,180,120,A,A1,A3,B1\r\n" + 
				"A3,180,180,A,A2,A4,A5,B2\r\n" + 
				"A4,120,180,A,A1,A3,A5,B3\r\n" + 
				"A5,150,240,A,A3,A4";
		
		String[] terrA = contStrA.split("\\n");

		ArrayList<Territory> contA_terr = new ArrayList<Territory>();

		for (int i = 0; i < terrA.length; i++) {
			Territory newTer =  new Territory(terrA[i]);
			contA_terr.add( newTer );
			dictTerritory.put(newTer.getName(), newTer);
		}

		String contStrB = "B1,300,120,B,A2,B2,B3\r\n" + 
				"B2,300,180,B,A3,B1,B3\r\n" + 
				"B3,360,150,B,B1,B2,A1,A4";
		String[] terrB = contStrB.split("\\n");

		ArrayList<Territory> contB_terr = new ArrayList<Territory>();

		for (int i = 0; i < terrB.length; i++) {
			Territory newTer =  new Territory(terrB[i]);
			contB_terr.add( newTer );
			dictTerritory.put(newTer.getName(), newTer);
		}
		
		Continent contA = new Continent("A", 5);
		Continent contB = new Continent("B", 3);

		dictContinents.put(contA.getName(), contA);
		dictContinents.put(contB.getName(), contB);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		gameConfig = null;
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

    /**
     * Checks the map
     */
	@Test
	public void readMap() {
		assertNotNull(dictContinents);
		assertNotNull(dictTerritory);
		assertEquals(gameConfig.getMapObj().getDictContinents().keySet(), dictContinents.keySet());
		assertEquals(gameConfig.getMapObj().getDictTerritory().keySet(), dictTerritory.keySet());
		assertEquals(gameConfig.getMapObj().getNumContinents(), new Integer(2));
		assertEquals(gameConfig.getMapObj().getNumTerritories(), new Integer(8));
		assertEquals(gameConfig.getMapObj().getMapAuthor(), "Samer Ayoub");
		assertEquals(gameConfig.getMapObj().getDictContinents().get("A").getTerritories().size(), 5);
		assertEquals(gameConfig.getMapObj().getDictContinents().get("B").getTerritories().size(), 3);
	}

    /**
     * Checks if the map is valid
     */
	@Test
	public void validateMap() {
		assertEquals(gameConfig.getMapObj().validateMap(), "true" );
	}

    /**
     * Checks if the map is connected
     */
	@Test
	public void testDisconnectedMap() {
		String path = String.format("Resources//Maps//%s.map", "vj_test_Disconnected");
		objMap = new Maps(path, 0);
		objMap.readMap();
		assertEquals(objMap.validateMap(), "The map is not a connected graph");
	}

    /**
     * Checks if the continents are connected
     */
	@Test
	public void testInconsistentAdjacency() {
		String path = String.format("Resources//Maps//%s.map", "UnconnectedContinent");
		objMap = new Maps(path, 0);
		objMap.readMap();
		String rt = objMap.validateMap();
		assertEquals(rt, "\nAll continents are not connected in themselves");
	}
}
