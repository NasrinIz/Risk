package test_model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import src.model.Card;
import src.model.Player;

public class TestPlayer {

	Player objPlayer = null;
	ArrayList<Card> gameCards = null;
	
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
		/* Assuming player owns 12 territories */
		objPlayer = new Player("HOKA", 0, null, null);
		for(int i = 0; i < 10; i++)
		{
			objPlayer.increaseNumTerritories();
		}
		
		/* Assuming player owns a continents with reward value of 10*/
		objPlayer.setContinentArmyReward(10);
	}

	@After
	public void tearDown() throws Exception {
		// do nothing
	}

	@Test
	public void testReinforcementCalc() {
		objPlayer.calcReinforcementArmies();
		assertTrue(objPlayer.getArmies() == 22);
	}
}
