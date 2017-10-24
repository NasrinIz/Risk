package test_model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import src.model.Maps;

public class TestInvalidMaps {

	private String disconnectedMapName = null;
	private String inconsistentAdjacencyMapName = null;
	private Maps objMap = null; 
	
	TestInvalidMaps(String inDisconnectedMapName, String inInconsistentAdjacencyMapName)
	{
		this.disconnectedMapName = inDisconnectedMapName;
		this.inconsistentAdjacencyMapName = inInconsistentAdjacencyMapName;
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// do nothing
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// do nothing
	}

	@Before
	public void setUp() throws Exception {
		objMap = null;
	}

	@After
	public void tearDown() throws Exception {
		// do nothing
	}

	@Test
	public void testDisconnectedMap() {
		String path = String.format("Resources//Maps//%s.map", disconnectedMapName);
		objMap = new Maps(path, 0);
		//gameConfig = new GameConfig(3, "testing_maps//invalid_1");
		objMap.readMap();
		assertEquals(objMap.validateMap(), "The map is not a connected graph");
	}
	
	@Test
	public void testInconsistentAdjacency() {
		String path = String.format("Resources//Maps//%s.map", inconsistentAdjacencyMapName);
		objMap = new Maps(path, 0);
		objMap.readMap();
		String rt = objMap.validateMap();
		rt = rt.substring(0, 23);
		assertEquals(rt.substring(0, 23), "\nPlease check adjacency");
	}

}
