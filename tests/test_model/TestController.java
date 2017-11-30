package test_model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import src.controller.MainController;
import src.model.GameConfig;
import src.model.GenericFunctions;
import src.view.AttackView;
import src.view.MainWindow;
import src.view.PlayerDominationView;
import src.view.PlayerInformationView;
import src.view.StarterWindow;

/**
 * This class contains test cases for mainController
 * @author vaibh
 *
 */
public class TestController {
	private GameConfig gameConfig;
	ArrayList<String> strategies;
	GenericFunctions genericFunctionsObj;
	String mapArray[];
	Integer numGames;
	Integer drawTurns;
	Integer numMaps;
    HashMap<String, ArrayList<HashMap<String,String>>> winners;
    StarterWindow starterView = new StarterWindow();
    MainWindow mainWindow = new MainWindow();
    MainController ctrl = new MainController(starterView);
	
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
        strategies = new ArrayList<String>();
        strategies.add("Random");
        strategies.add("Cheater");
        strategies.add("Aggressive");
        strategies.add("Benevolent");
        genericFunctionsObj = new GenericFunctions();
        mapArray = genericFunctionsObj.genCommaSepStrToArray("World,Atlantis");
        numGames = 4;
        drawTurns = 4;
        numMaps = mapArray.length;
        winners = new HashMap<String, ArrayList<HashMap<String,String>>>();
        ctrl.mainWindow = this.mainWindow;
        ctrl.starterView = this.starterView;
        ctrl.drawTurns = 4;
        ctrl.numGames = 4;
	}

	/**
	 * Overridden method runs after each test case.
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void TestDriver() {
		for(int ctr = 0; ctr < numMaps; ctr++) {
    		ArrayList<HashMap<String, String>> tmpL = new ArrayList<HashMap<String, String>>();
    		winners.put(mapArray[ctr], tmpL);

    		for(int ctr2 = 0; ctr2 < numGames; ctr2++) {
    			HashMap<String,String> tmp = new HashMap<String, String>();
                tmp.put("Game: " + Integer.toString(ctr2 + 1), "");
                winners.get(mapArray[ctr]).add(tmp);
    		}
    	}
		ctrl.mainWindow.attackView = new AttackView();
		ctrl.mainWindow.playerDominationView = new PlayerDominationView();
		ctrl.mainWindow.playerInformationView = new PlayerInformationView();
		ctrl.winners = winners;
		for (int ctr = 0; ctr < numMaps; ctr++) {
            for (int ctr2 = 0; ctr2 < numGames; ctr2++) {
                gameConfig = new GameConfig(strategies, mapArray[ctr], mainWindow);
                ctrl.gameConfig = gameConfig;
                ctrl.currentMapPlayed = mapArray[ctr];
                ctrl.currentGameNumber = "Game: " + Integer.toString(ctr2 + 1);
                String error = gameConfig.getMapObj().validateMap();
                gameConfig.gameResult(ctrl.ai_driver(ctr, ctr2));
            }
        }

        System.out.println();
        System.out.println();
        System.out.println("RESULTS: ");
        System.out.println();
        System.out.println();
        System.out.println("***************************************");
        for(String mapL : winners.keySet()) {
        	System.out.println(mapL);
        	System.out.println();
        	ArrayList<HashMap<String, String>> gameList = winners.get(mapL);
        	for(int ctr5 = 0; ctr5 < gameList.size(); ctr5++) {
        		HashMap<String, String> gameInfo = gameList.get(ctr5);
        		Map.Entry<String,String> entry = gameInfo.entrySet().iterator().next();
        		String key = entry.getKey();
        		String value = entry.getValue();
        		System.out.println(key + ":   And the winner is as always " + value);
        	}
        	System.out.println("***************************************");
        }
        System.out.println("");

	}
}
