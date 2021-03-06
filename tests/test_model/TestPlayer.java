package test_model;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import src.model.Card;
import src.model.GameConfig;
import src.model.Human;
import src.model.Maps;
import src.model.Player;
import src.model.Territory;

/**
 * This class contains test cases for Player class
 * @author vaibh
 */
public class TestPlayer {

	Player objPlayer = null;
	ArrayList<Card> gameCards = null;
	
	/**
	 * Overridden method runs once before all test cases
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// do nothing
	}

	/**
	 * Overridden method runs at end of all test cases
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// do nothing
	}

	/**
	 * Overridden method runs before each test case
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		/* Assuming player owns 12 territories */
		objPlayer = new Player("HOKA", 0, null, null, new Human());
		for(int i = 0; i < 12; i++)
		{
			Territory tmp = new Territory("asd");
			objPlayer.setTerritories(tmp);
		}
		
		/* Assuming player owns a continents with reward value of 10*/
		objPlayer.setContinentArmyReward(10);
	}

	/**
	 * Overridden method runs at end of each test case
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		// do nothing
	}

	/**
	 * This function tests reinforcements
	 */
	@Test
	public void testReinforcementCalc() {
		objPlayer.calcReinforcementArmies();
		assertTrue(objPlayer.getArmies() == 14);
	}
    /**
     * Checks if fortify src and destination and armies are correct
     */
	@Test
	public void testFortify() {
		GameConfig objConfig;
		objConfig = new GameConfig(2, "world", null);
		String path = String.format("Resources//Maps//%s.map", "World");
		objConfig.setNumPlayers(2);
		Maps mapObj = new Maps(path, 0);
		objConfig.setMapObj(mapObj);
		objConfig.getMapObj().readMap();
		int flag = 0;
		Territory srcTerr = null;
		for(String terr : mapObj.getDictTerritory().keySet()) {
			if(flag == 0) {
				Territory tmp = mapObj.getDictTerritory().get(terr);
				tmp.setOwner(0);
				mapObj.getDictTerritory().get(terr).increaseArmies();
				srcTerr = mapObj.getDictTerritory().get(terr);
				flag = 1;
				break;
			}
			else {
				if(mapObj.getDictTerritory().get(terr).getOwner() == 1) {
					mapObj.getDictTerritory().get(terr).increaseArmies();
					break;
				}
			}
		}
		Territory destTerr = mapObj.getDictTerritory().get(srcTerr.getAdjacentCountries().get(0));
		
		Player player = objConfig.getPlayers()[0];
		destTerr.setOwner(0);
		player.fortifyArmy(mapObj, srcTerr.getName(), destTerr.getName(), 5);
		assertTrue(destTerr.getArmies() == 2);
	}

	/**
	 * Checks if attack is done correctly
	 */
	@Test
	public void testAttack() {
		GameConfig objConfig;
		objConfig = new GameConfig(2, "world", null);
		String path = String.format("Resources//Maps//%s.map", "World");
		objConfig.setNumPlayers(2);
		Maps mapObj = new Maps(path, 0);
		objConfig.setMapObj(mapObj);
		objConfig.getMapObj().readMap();
		int flag = 0;
		Player player = objConfig.getPlayers()[0];
		Territory srcTerr = player.getTerritories().get(0);
		srcTerr.increaseArmies();
		srcTerr.increaseArmies();
		srcTerr.increaseArmies();
		srcTerr.increaseArmies();
		
		Territory destTerr = mapObj.getDictTerritory().get(srcTerr.getAdjacentCountries().get(0));
		destTerr.setOwner(1);
		destTerr.increaseArmies();
		destTerr.increaseArmies();
		
		
		destTerr.setOwner(1);
		player.srcAttackTerritory = srcTerr;
		player.dstAttackTerritory = destTerr;
		int rt = player.attackTerritory(1, 1, mapObj.getDictContinents());
		assertTrue(rt == 0);
	}

    /**
     * Checks if cards are exchanged correctly
     */
	@Test
	public void testExchange() {
		GameConfig objConfig = new GameConfig(2, "world", null);;
		objConfig.callInitcards();
		Integer playerCards[] = new Integer[2];
		for(int ctr = 0; ctr < playerCards.length; ctr++)
		{
			playerCards[ctr] = 0;
		}
		int ctr = 0;
		for(int i = 0; i < objConfig.getGameCards().size(); i++)
		{
			if(objConfig.getGameCards().get(i).getOwnerId() != null)
			{
				objConfig.getGameCards().get(i).cardType = ctr + 1;
				ctr++;
				playerCards[objConfig.getGameCards().get(i).getOwnerId()]++;
			}
		}
		
		objConfig.getPlayers()[0].exchangeCards(1, 1, 1, 1);
	}
}
