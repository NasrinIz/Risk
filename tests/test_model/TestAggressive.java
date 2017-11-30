package test_model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import src.model.GameConfig;
import src.model.Maps;
import src.model.Player;
import src.model.Territory;
import src.view.MainWindow;

/**
 * This class contains test cases for Aggressive Class
 * @author vaibh
 *
 */
public class TestAggressive {
	private GameConfig objConfig;
	
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
		ArrayList<String> playerTypes = new ArrayList<String>();
		playerTypes.add("Aggressive");
		playerTypes.add("Aggressive");
		String mapName = "World";
		MainWindow mainWindow = new MainWindow();
		
		objConfig = new GameConfig(playerTypes, mapName, mainWindow);
	}

	/**
	 * Overridden method runs after each test case.
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Checks reinforcement for Aggressive player
	 */
	@Test
	public void testReinforce() {
		ArrayList<Territory> playerTerritories = objConfig.getPlayers()[0].getTerritories(); 
		Player objPlayer = objConfig.getPlayers()[0];
		Integer rt = objPlayer.strategy.getTerritoryForReinforcement(playerTerritories, objPlayer);
		assertTrue(rt == 0);
	}

	/**
	 * Checks fortification for Aggressive player
	 */
	@Test
	public void testFortify() {
		ArrayList<Territory> playerTerritories = objConfig.getPlayers()[0].getTerritories(); 
		Player objPlayer = objConfig.getPlayers()[0];
		Integer rt = objPlayer.strategy.getTerritoryForFortification(objConfig.getMapObj(), playerTerritories, objPlayer);
		assertTrue(rt == 0);
	}
	
	/**
	 * Checks attack for Aggressive player
	 */
	@Test
	public void testAttack() {
		ArrayList<Territory> playerTerritories = objConfig.getPlayers()[0].getTerritories(); 
		Player objPlayer = objConfig.getPlayers()[0];
		Integer rt = objPlayer.strategy.getTerritoryForAttack(objConfig.getMapObj(), playerTerritories, objPlayer);
		assertTrue((rt == 0) || (rt == -1));
	}
}
