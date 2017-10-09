package test_model;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Continent;
import model.Maps;
import model.Territory;

public class TestMaps {

	private static String mapLocation;
	private static Map<String, Continent> dictContinents;
	private static Map<String, Territory> dictTerritory;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mapLocation = "resources//maps//testing_maps//valid_1.map";

		dictContinents = new HashMap<String, Continent>(2, 2);
		dictTerritory = new HashMap<String, Territory>(2, 2);

		String contStrA = "A1,120,120,A2,A4\nA2,180,120,A1,A3\nA3,180,180,A2,A4,A5\nA4,120,180,A1,A3,A5\nA5,150,240,A3,A4";
		String[] terrA = contStrA.split("\\n");

		Territory[] contA_terr = new Territory[5];

		for (int i = 0; i < contA_terr.length; i++) {
			contA_terr[i] = new Territory(terrA[i]);
			dictTerritory.put(contA_terr[i].getName(), contA_terr[i]);
		}

		String contStrB = "B1,300,120,A2,B2,B3\nB2,300,180,A3,B1,B3\nB3,360,150,B1,B2,A1,A4";
		String[] terrB = contStrB.split("\\n");

		Territory[] contB_terr = new Territory[3];

		for (int i = 0; i < contB_terr.length; i++) {
			contB_terr[i] = new Territory(terrB[i]);
			dictTerritory.put(contB_terr[i].getName(), contB_terr[i]);
		}
		
		Continent contA = new Continent("A", contA_terr, 5);
		Continent contB = new Continent("B", contB_terr, 3);

		dictContinents.put(contA.getName(), contA);
		dictContinents.put(contB.getName(), contB);
		
		System.out.println( contA );
		System.out.println( contB );
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void readMap() {
		Maps map = new Maps(mapLocation);
		map.readMap();
		
		assertEquals(map.getDictContinents().keySet(), dictContinents.keySet());
		assertEquals(map.getDictTerritory().keySet(), dictTerritory.keySet());
		assertEquals(map.getNumContinents(), new Integer(2));
		assertEquals(map.getNumTerritories(), new Integer(8));
		assertEquals(map.getMapAuthor(), "Samer Ayoub");
		// To Do:
		// override equals in Territory and Continent and use it to assert equality
	}

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}

}
