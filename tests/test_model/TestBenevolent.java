package test_model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import src.model.GameConfig;
import src.model.Player;
import src.model.Territory;
import src.view.MainWindow;

/**
 * This class contains test cases for Benevolent class.
 * @author vaibh
 *
 */
public class TestBenevolent {
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
		playerTypes.add("Benevolent");
		playerTypes.add("Benevolent");
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
	 * Checks if reinforcement works for Benevolent
	 */
	@Test
	public void testReinforce() {
		ArrayList<Territory> playerTerritories = objConfig.getPlayers()[0].getTerritories(); 
		Player objPlayer = objConfig.getPlayers()[0];
		Integer rt = objPlayer.strategy.getTerritoryForReinforcement(playerTerritories, objPlayer);
		assertTrue(rt == 0);
	}

	/**
	 * Checks if fortification works for Benevolent
	 */
	@Test
	public void testFortify() {
		ArrayList<Territory> playerTerritories = objConfig.getPlayers()[0].getTerritories(); 
		Player objPlayer = objConfig.getPlayers()[0];
		Integer rt = objPlayer.strategy.getTerritoryForFortification(objConfig.getMapObj(), playerTerritories, objPlayer);
		assertTrue(rt == 0);
	}
	
	/**
	 * Checks if attack works for Benevolent
	 */
	@Test
	public void testAttack() {
		ArrayList<Territory> playerTerritories = objConfig.getPlayers()[0].getTerritories(); 
		Player objPlayer = objConfig.getPlayers()[0];
		Integer rt = objPlayer.strategy.getTerritoryForAttack(objConfig.getMapObj(), playerTerritories, objPlayer);
		assertTrue((rt == 0) || (rt == -1));
	}
}
