package test_model;

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
		gameCards = new ArrayList<Card>();
		for(int i = 0; i < 11; i++)
		{
			Card tmpCard = null;
			if(i < 8)
			{
				tmpCard = new Card(i, 1);
			}
			else if(i < 11)
			{
				tmpCard = new Card(i, 2);
			}
			else if(i < 21)
			{
				tmpCard = new Card(i, 3);
			}
			else if(i < 23)
			{
				tmpCard = new Card(i, 4);
			}
			gameCards.add(tmpCard);
		}
		
		/* Assuming player captured 10 territories */
		objPlayer = new Player("HOKA", 0, gameCards);
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
	}
}
