package test_model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
		for(int i = 0; i < objConfig.getGameCards().size(); i++)
		{
			System.out.println(objConfig.getGameCards().get(i).getOwnerId());
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
		
		for(int i = 0; i < tmpPlayers.length; i++)
		{
			tmpPlayers[i] = new Player("ho", i, null);
		}
		
		objConfig.callInitTerritory();
		
		for (String territory : objConfig.getMapObj().getDictTerritory().keySet())
		{
			System.out.println(objConfig.getMapObj().getDictTerritory().get(territory).getOwner());
		}
	}

}
