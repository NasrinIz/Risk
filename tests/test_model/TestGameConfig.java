package test_model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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


public class TestGameConfig {

	private GameConfig objConfig;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		objConfig = new GameConfig(2, "world", null);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGenerateCards() {
		objConfig.callInitcards();
		Integer playerCards[] = new Integer[2];
		for(int ctr = 0; ctr < playerCards.length; ctr++)
		{
			playerCards[ctr] = 0;
		}
		for(int i = 0; i < objConfig.getGameCards().size(); i++)
		{
			if(objConfig.getGameCards().get(i).getOwnerId() != null)
			{
				playerCards[objConfig.getGameCards().get(i).getOwnerId()]++;
			}
		}
		for(int ctr = 0; ctr < playerCards.length; ctr++)
		{
			assertTrue(((Integer)playerCards[ctr] == 4));
		}
	}
	
	@Test
	public void randomTerritoryAssignment() {
		String path = String.format("Resources//Maps//%s.map", "World");
		objConfig.setNumPlayers(2);
		Maps mapObj = new Maps(path, 0);
		objConfig.setMapObj(mapObj);
		objConfig.getMapObj().readMap();
		
		Player tmpPlayers[] = new Player[2];
		Integer playerTerr[] = new Integer[2];
		playerTerr[0] = 0;
		playerTerr[1] = 0;
		for(int i = 0; i < 2; i++)
		{
			tmpPlayers[i] = new Player("ho", i, null, null);
		}
		
		objConfig.callInitTerritory();
		
		Maps objMap = objConfig.getMapObj();
		Map<String, Territory> dictTerr = objMap.getDictTerritory();
		for (String territory : dictTerr.keySet())
		{
			Integer owner = dictTerr.get(territory).getOwner();
			playerTerr[owner] = playerTerr[owner] + 1;
		}
		
		for(int ctr = 0; ctr < 2; ctr++) {
			assertTrue(playerTerr[ctr] == 21);
		}
	}

}
