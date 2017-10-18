package test_model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.GameConfig;
import model.Maps;
import model.RiskCard;

public class TestGameConfig {

	private static GameConfig gameConfig;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String mapName = "valid_1";
		gameConfig = new GameConfig(3, mapName);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGenerateCards() {
		
		assertNotNull(gameConfig.getGameCards());
		assertEquals( gameConfig.getGameCards().size(), 10 );
		assertEquals( gameConfig.getGameCardsOfType(RiskCard.WILD).size(), 2);
		assertEquals( gameConfig.getGameCardsOfType(RiskCard.INFANTRY).size() + 
					  gameConfig.getGameCardsOfType(RiskCard.CAVALRY).size() +
					  gameConfig.getGameCardsOfType(RiskCard.ARTILLERY).size(), 8);
	}

}
