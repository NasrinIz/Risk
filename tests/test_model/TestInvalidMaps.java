package test_model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.GameConfig;

public class TestInvalidMaps {

	private static GameConfig gameConfig;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		gameConfig = null;
	}

	@Test
	public void testDisconnectedMap() {
		gameConfig = new GameConfig(3, "testing_maps//invalid_1");
		assertEquals(gameConfig.getMapObj().validateMap(), "The map is not a connected graph");
	}
	
	public void testInconsistentAdjacency() {
		gameConfig = new GameConfig(3, "testing_maps//invalid_2");
		assertEquals(gameConfig.getMapObj().validateMap(), new Integer(-1));
	}

}
