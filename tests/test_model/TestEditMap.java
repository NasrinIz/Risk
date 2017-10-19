package test_model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.MapEditor;

public class TestEditMap {
	
	private static MapEditor mapEditor;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		mapEditor = new MapEditor("testing_maps//valid_1");
		
	}

	@After
	public void tearDown() throws Exception {
		mapEditor = null;
	}

	@Test
	public void testRemoveTerritory() {

		mapEditor.removeTerritory("A3");
		// check Neighbors of A3 adjacency list are updated correctly
		ArrayList<String> A2_adj = mapEditor.getMapObj().getDictTerritory().get("A2").getAdjacentCountries();
		String[] expectds_A2 = {"A1", "B1", "A4", "A5", "B2"};
		assertArrayEquals(expectds_A2, A2_adj.toArray());
		
		// check A3 is removed from territory dict
		assertNull(mapEditor.getMapObj().getDictTerritory().get("A3"));
		
//		// check A3 is removed from Continent A
//		assertNull(mapEditor.getMapObj().getDictContinents().get("A").getTerritories().contains(o));
		
		
	}

}
