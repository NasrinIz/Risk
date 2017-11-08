package test_model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import src.model.GameConfig;
import src.model.Maps;
import src.model.Player;


public class TestGameConfig {

	private GameConfig objConfig;
	private Integer numPlayers = null;
	String mapName = null;
	
	TestGameConfig(Integer inNumPlayers, String inMapName)
	{
		this.numPlayers = inNumPlayers;
		this.mapName = inMapName;
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		objConfig = new GameConfig(numPlayers, mapName, null);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGenerateCards() {
		objConfig.callInitcards();
		Integer playerCards[] = new Integer[this.numPlayers];
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
		for(int i = 0; i < tmpPlayers.length; i++)
		{
			tmpPlayers[i] = new Player("ho", i, null, null);
		}
		
		objConfig.callInitTerritory();
		
		for (String territory : objConfig.getMapObj().getDictTerritory().keySet())
		{
			playerTerr[objConfig.getMapObj().getDictTerritory().get(territory).getOwner()]++;
		}
		
		for(int ctr = 0; ctr < 2; ctr++) {
			assertTrue(playerTerr[ctr] == 21);
		}
	}

}
