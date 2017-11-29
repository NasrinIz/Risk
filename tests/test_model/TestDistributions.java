package test_model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import src.model.GameConfig;
import src.model.Player;
import src.model.Territory;

public class TestDistributions {
	
	private static GameConfig gameConf;
	private static int numPlayers;
	private static Player[] gameplayers = null;
	private static int gamePhase = 0;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		numPlayers = 3;
		gameConf = new GameConfig(numPlayers, "valid_1", null);
		gameplayers = gameConf.getPlayers();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		gamePhase = gameConf.getGamePhase();
//		System.out.println("current game phase = " + gamePhase);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPlayersValidity() {
		assertEquals(gameplayers.length, 3);
		for(int i = 0; i < numPlayers; i++ ) {
			assertNotNull(gameplayers[i]);
		}
	}
	
	@Test
	public void testPlayersTerritories() {
		int totalTerrs = 0;
		ArrayList<Territory> plTers = null;
		for(int i = 0; i < numPlayers; i++ ) {
			plTers = gameplayers[i].getTerritories();
			assertNotNull(plTers);
			totalTerrs += plTers.size();
		}
		int mapTotalTers = gameConf.getMapObj().getNumTerritories();
		assertEquals(totalTerrs, mapTotalTers);
	}
	
	@Test
	public void testTerrArmiesPhase1() {
		ArrayList<Territory> plTerrs = null;
		int totalArmies = 0;
		for(int i = 0; i < numPlayers; i++ ) {
			plTerrs = gameplayers[i].getTerritories();
			for(Territory t:plTerrs) {
				int numTerrArms = t.getArmies();
				assert(numTerrArms > 0);
				totalArmies += numTerrArms;
			}
		}
		int expected = gameConf.getMapObj().getNumTerritories();

		assertEquals(gamePhase, 1);
		assertEquals(totalArmies, expected);
	}
	
	@Test
	public void testPlayerArmies() {
		ArrayList<Territory> plTerrs = null;
		int totalArmies = 0;
		for(int i = 0; i < numPlayers; i++ ) {
			int plArmies = 0;
			plArmies = gameplayers[i].getArmies();
			totalArmies += plArmies;
		}
		assertEquals(97, totalArmies);    // (8 Territories) how 72 for 2 players ?  how 97 for 3 players? 
	}

}
