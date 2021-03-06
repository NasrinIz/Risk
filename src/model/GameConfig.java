package src.model;

import src.view.MainWindow;

import java.io.Serializable;
import java.util.*;

/**
 * @author Team20
 *         This class is used to instantiate everything, and starts the flow of new game.
 */
public class GameConfig extends Observable implements Serializable{
	private static final long serialVersionUID = -6463387911877296159L;
	private final Integer BEGINNINGCARDDISTRIBUTION = 4;
    private final Integer MAXCARDS = 44;

    private Integer numPlayers = null;
    private Player players[];
    private Maps mapObj;
    private ArrayList<Card> gameCards = new ArrayList<Card>();
    private Integer currentPlayer = 0;


    private Integer gamePhase = 0;
    private GenericFunctions genericFunctionsObj = new GenericFunctions();
    private MainWindow mainWindow;

    public String gamePhaseStr = "Phase: StartUP\nAll players will place \narmies one by one in \nround robin fashion";
    public Boolean gamePhaseChanged = false;
    public String gameWinner;
    
    
    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public boolean maxTurnsReached = false;
    
    /**
     * This is the constructor to class GameConfig and initializes class variables.
     *
     * @param numPlayers Number of players playing the game
     * @param mapName    Map Name to be loaded for game.
     * @param mainWindow Reference to main Window
     */
    public GameConfig(Integer numPlayers, String mapName, MainWindow mainWindow) {
        super();
        initCards();
        this.numPlayers = numPlayers;
        this.mapObj = new Maps(mapName, 0);
        initMap();
        setNumPlayers();
        gamePhase = genericFunctionsObj.GAMEPHASESTARTUP;
        this.mainWindow = mainWindow;
    }

    /**
     * The second constructor of Game Config for tournament
     * @param numPlayers Number of players playing the game
     * @param mapName    Map Name to be loaded for game.
     * @param mainWindow Reference to main Window
     */
    public GameConfig(ArrayList<String> playerTypes, String mapName, MainWindow mainWindow) {
    	super();
    	initCards();
    	this.numPlayers = playerTypes.size();
    	this.mapObj = new Maps(mapName, 0);
    	initMap();
    	setNumPlayers(playerTypes);
    	gamePhase = genericFunctionsObj.GAMEPHASESTARTUP;
        this.mainWindow = mainWindow;
    }

    /**
     * @param numPlayers Number of players playing the game
     */
    public void setNumPlayers(Integer numPlayers) {
        this.numPlayers = numPlayers;
    }

    /**
     * Sets the player objects from player class for all players into Gameconfig
     *
     * @param inPlayers Player objects
     */
    public void setPlayers(Player inPlayers[]) {
        this.players = inPlayers;
    }

    /**
     * @return the current gamePhase.
     */
    public Integer getGamePhase() {
        return gamePhase;
    }

    /**
     * It shows the turn of next player
     */
    public void nextPlayerTurn() {
        currentPlayer++;
        if (currentPlayer >= players.length) {
            currentPlayer = 0;
        }
    }

    /**
     * @return current player
     */
    public Player getCurrentPlayer() {
        return players[currentPlayer];
    }

    /**
     * It calls the function to setup players.
     */
    private Integer setNumPlayers() {
        if ((numPlayers < 2) || (numPlayers > 6))
            return -1;

        setupPlayers(null);
        return 0;
    }

    /**
     * It calls the function to setup players.
     */
    private Integer setNumPlayers(ArrayList<String> playerTypes) {
        if ((numPlayers < 2) || (numPlayers > 6))
            return -1;
        
        if(numPlayers != playerTypes.size()) {
        	System.out.println("Number of players does not match with number of strategies selected");
        	return -1;
        }
        
        setupPlayers(playerTypes);
        return 0;
    }
    
    /**
     * @return Returns the player objects
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * This function is used to initialize Map Object.
     */
    private void initMap() {
        if (!this.mapObj.readMap().equals("true")) {
            System.out.println("Map initialization failure");
            return;
        }

        if (!this.mapObj.validateMap().equals("true")) {
            System.out.println("Map validation failure");
        }
    }

    /**
     * It initializes the players with some default properties
     */
    private void setupPlayers(ArrayList<String> playerTypes) {
        this.players = new Player[numPlayers];
        ArrayList<Card> playerCards;
        Integer cardId = 0;

        for (int i = 0; i < numPlayers; i++) {
            playerCards = new ArrayList<Card>();
            while (playerCards.size() < 4) {
                while (true) {
                    cardId = genericFunctionsObj.genRandomNumber(0, 41);
                    if (gameCards.get(cardId).getOwnerId() == null) {
                        break;
                    }
                }
                gameCards.get(cardId).setOwnerId(i);
                playerCards.add(gameCards.get(cardId));
            }
            if(playerTypes == null) {
            	Player playerObj = new Player("Player" + Integer.toString(i), i, playerCards, this, new Human());
	            playerObj.setArmies(getInitArmy());
	            players[i] = playerObj;
            }
            else {
            	if(playerTypes.get(i) == null) {
            		return;
            	}
            	else if(playerTypes.get(i).equals("Human")) {
            		Player playerObj = new Player("Player" + Integer.toString(i), i, playerCards, this, new Human());
            		playerObj.setArmies(getInitArmy());
		            players[i] = playerObj;
            	}
            	else if(playerTypes.get(i).equals("Aggressive")) {
            		Player playerObj = new Player("Player" + Integer.toString(i), i, playerCards, this, new Aggressive());
            		playerObj.setArmies(getInitArmy());
		            players[i] = playerObj;
            	}
            	else if(playerTypes.get(i).equals("Benevolent")) {
            		Player playerObj = new Player("Player" + Integer.toString(i), i, playerCards, this, new Benevolent());
            		playerObj.setArmies(getInitArmy());
		            players[i] = playerObj;
            	}
            	else if(playerTypes.get(i).equals("Cheater")) {
            		Player playerObj = new Player("Player" + Integer.toString(i), i, playerCards, this, new Cheater());
            		playerObj.setArmies(getInitArmy());
		            players[i] = playerObj;
            	}
            	else if(playerTypes.get(i).equals("Random")) {
            		Player playerObj = new Player("Player" + Integer.toString(i), i, playerCards, this, new Random());
            		playerObj.setArmies(getInitArmy());
		            players[i] = playerObj;
            	}
            }
       }

        initTerritory();
        /*
        for (String territory : mapObj.getDictTerritory().keySet()) {
            players[mapObj.getDictTerritory().get(territory).getOwner()].setTerritories(mapObj.getDictTerritory().get(territory));
        }
	*/
        for (int i = 0; i < numPlayers; i++) {
            players[i].setArmies(players[i].getArmies() - players[i].numOfTerritories());
        }
    }

    /**
     * This function calls for the initialization of cards
     */
    public void callInitcards() {
        initCards();
    }

    /**
     * This function initializes the cards
     */
    private void initCards() {
        Integer infantryCards = 0;
        Integer cavalryCards = 0;
        Integer artilleryCards = 0;
        Integer wildCards = 0;
        Integer cardtype = 0;

        for (int i = 0; i < this.MAXCARDS; i++) {
            while (true) {
                cardtype = genericFunctionsObj.genRandomNumber(1, 4);

                if (infantryCards == 14) {
                    cardtype = genericFunctionsObj.genRandomNumber(2, 4);
                } else if (wildCards == 2) {
                    cardtype = genericFunctionsObj.genRandomNumber(1, 3);
                } else if ((infantryCards == 14) && (cavalryCards == 14)) {
                    cardtype = genericFunctionsObj.genRandomNumber(3, 4);
                } else if ((artilleryCards == 14) && (wildCards == 2)) {
                    cardtype = genericFunctionsObj.genRandomNumber(1, 2);
                } else if ((infantryCards == 14) && (cavalryCards == 14) && (wildCards == 2)) {
                    cardtype = 3;
                    break;
                } else if ((artilleryCards == 14) && (cavalryCards == 14) && (wildCards == 2)) {
                    cardtype = 1;
                    break;
                } else if ((infantryCards == 14) && (artilleryCards == 14) && (wildCards == 2)) {
                    cardtype = 2;
                    break;
                } else if ((cardtype == 1) && (infantryCards < 14)) {
                    break;
                } else if ((cardtype == 2) && (cavalryCards < 14)) {
                    break;
                } else if ((cardtype == 3) && (artilleryCards < 14)) {
                    break;
                } else if ((cardtype == 4) && (wildCards < 2)) {
                    break;
                }
            }

            Card tmpCard = new Card(i, cardtype);
            this.gameCards.add(tmpCard);
        }
    }

    /**
     * This function calls init territories for map.
     */
    public void callInitTerritory() {
        initTerritory();
    }

    /**
     * This function initializes the territories for map.
     */
    private void initTerritory() {
        Integer perPlayer = mapObj.getDictTerritory().size() / numPlayers;
        Integer remainingTerritoryDist = mapObj.getDictTerritory().size() - perPlayer;
        Integer nextOwnerPlayer = new Integer(0);
        Integer perPlayerDistFlag = -1;
        for (String territory : mapObj.getDictTerritory().keySet()) {
            if (perPlayerDistFlag != 0) {
                nextOwnerPlayer = (genericFunctionsObj.genRandomNumber(0, numPlayers - 1));
            }

            while ((players[nextOwnerPlayer].numOfTerritories() + remainingTerritoryDist) == (perPlayer + remainingTerritoryDist)) {
                nextOwnerPlayer = (genericFunctionsObj.genRandomNumber(0, numPlayers - 1));
                perPlayerDistFlag = checkForDistCompletion(perPlayer);
                if (perPlayerDistFlag == 0) {
                    nextOwnerPlayer = -1;
                    break;
                }
            }

            if (perPlayerDistFlag == 0) {
                nextOwnerPlayer++;
                if (nextOwnerPlayer >= numPlayers) {
                    nextOwnerPlayer = 0;
                }
                (mapObj.getDictTerritory()).get(territory).setOwner(nextOwnerPlayer);
                players[nextOwnerPlayer].setTerritories((mapObj.getDictTerritory()).get(territory));
            } else {
                if (players[nextOwnerPlayer].numOfTerritories() < perPlayer) {
                    (mapObj.getDictTerritory()).get(territory).setOwner(nextOwnerPlayer);
                    players[nextOwnerPlayer].setTerritories((mapObj.getDictTerritory()).get(territory));
                }
            }
        }
    }

    /**
     * @param inPerPlayer Checks if the territory distribution is complete for all players
     *                    according to inPerPlayer distribution number.
     */
    private Integer checkForDistCompletion(Integer inPerPlayer) {
        for (int i = 0; i < numPlayers; i++) {
            if (players[i].numOfTerritories() < inPerPlayer) {
                return -1;
            }
        }
        return 0;
    }

    /**
     * This method initialize how many armies base on the number of players
     */
    private Integer getInitArmy() {

        if (numPlayers >= 2 && numPlayers <= 6) {
            return 5 * (10 - this.numPlayers);
        } else {
            return 0;        // should throw an exception
        }

    }

    /**
     * It calculate reinforcement army
     */
    public void calcReinforcementArmy() {
        Iterator ite = mapObj.getDictContinents().entrySet().iterator();
        while (ite.hasNext()) {
            Map.Entry pair = (Map.Entry) ite.next();
            Continent tmpContinent = mapObj.getDictContinents().get(pair.getKey());

	        /* Calculates army reward for each continent and add to players */
            if (tmpContinent.checkForOwnership() == 0) {
                players[tmpContinent.getOwnerId()].setContinentArmyReward(tmpContinent.getArmyReward());
            }
        }
        
        this.getCurrentPlayer().calcReinforcementArmies();
    }

    /**
     * @return the gameCards
     */
    public ArrayList<Card> getGameCards() {
        return gameCards;
    }

    /**
     * @return the mapObj
     */
    public Maps getMapObj() {
        return mapObj;
    }

    /**
     * @param mapObj the mapObj to set
     */
    public void setMapObj(Maps mapObj) {
        this.mapObj = mapObj;
    }

    /**
     * User can fortify armies from one territory to the others
     *
     * @param srcTerritory  fortify from source
     * @param destTerritory fortify to destiny
     * @param numArmies		Number of armies to be moved
     */
    public void fortifyArmies(String srcTerritory, String destTerritory, Integer numArmies) {
        ArrayList<Territory> tmp = getCurrentPlayer().getTerritories();
        int srcFound = 0;
        int destFound = 0;
        int adjacencyFlag = 0;
        if(getCurrentPlayer().strategy.getPlayerType() == 0)
        {
	        for (int ctr = 0; ctr < tmp.size(); ctr++) {
	            if (tmp.get(ctr).getName().equals(srcTerritory) == true) {
	                srcFound = 1;
	                for (String adjacent : tmp.get(ctr).getAdjacentCountries()) {
	                    if (adjacent.equals(destTerritory)) {
	                        adjacencyFlag = 1;
	                    }
	                }
	            }
	            if (tmp.get(ctr).getName().equals(destTerritory) == true) {
	                destFound = 1;
	            }
	        }
	
	        if ((srcFound == 0) || (destFound == 0)) {
	            return;
	        }

	        if (adjacencyFlag == 0) {
	            System.out.println("Player can only fortify armies from territory adjacent to target territory");
	            return;
	        }
        }

        getCurrentPlayer().fortifyArmy(mapObj, srcTerritory, destTerritory, numArmies);
    }

    /**
     * This functions increment the gamePhase according to previous phase.
     */
    private void incrementGamePhase() {
        if (gamePhase == genericFunctionsObj.GAMEPHASESTARTUP) {   
        	if(maxTurnsReached) {
        		return;
        	}
    		gamePhase = genericFunctionsObj.GAMEPHASEREINFORCEMENT;
    		System.out.println("Startup Phase ends, Reinforcement Phase Begins");
    		calcReinforcementArmy();
        } else if (gamePhase == genericFunctionsObj.GAMEPHASEREINFORCEMENT) {
            gamePhase = genericFunctionsObj.GAMEPHASEFORTIFICATION;
            System.out.println("Reinforcement Phase ends, Fortification Phase Begins");
        } else if (gamePhase == genericFunctionsObj.GAMEPHASEFORTIFICATION) {
            gamePhase = genericFunctionsObj.GAMEPHASEATTACK;
            System.out.println("Fortification Phase ends, Attack Phase Begins");
        } else if (gamePhase == genericFunctionsObj.GAMEPHASEATTACK) {
        	gamePhaseChanged = true;
    		gamePhase = genericFunctionsObj.GAMEPHASEREINFORCEMENT;
            System.out.println("Attack Phase ends, Reinforcement Phase Begins");
            calcReinforcementArmy();
        }
    }

    /**
     * This function passes the turn to next player in a round robin manner
     * And, also changes the game phase based on different conditions
     */
    public void nextPlayerOrPhase() {

        Player tmpPlayers[] = getPlayers();
        int i;
        getCurrentPlayer().setTurnStatus(true);
        for (i = 0; i < tmpPlayers.length; i++) {
            if (!tmpPlayers[i].getTurnStatus()) {
                break;
            }
        }

        if (gamePhase == genericFunctionsObj.GAMEPHASESTARTUP) {
            Integer gamePhaseFlag = 0; // 0 Change True, -1 Change False
            for (i = 0; i < tmpPlayers.length; i++) {
                if (tmpPlayers[i].getArmies() != 0) {
                    gamePhaseFlag = -1;
                    break;
                }
            }

            if (gamePhaseFlag == 0) {
                nextPlayerTurn();
                incrementGamePhase();
                gamePhaseStr = "Phase: Reinforcement\nPlayer " + this.getCurrentPlayer().getPlayerId().toString() + " will \nre-inforce his armies now";
            } else {
                nextPlayerTurn();
            }

        } else if (gamePhase == genericFunctionsObj.GAMEPHASEREINFORCEMENT) {
            if (getCurrentPlayer().getArmies() <= 0) {
                incrementGamePhase();
                gamePhaseStr = "Phase: Fortification\nPlayer " + this.getCurrentPlayer().getPlayerId().toString() + " will \nfortify his armies now";
            }

        } else if (gamePhase == genericFunctionsObj.GAMEPHASEFORTIFICATION) {
            incrementGamePhase();
            gamePhaseStr = "Phase: Attack\nPlayer " + this.getCurrentPlayer().getPlayerId().toString() + " can \nperform attack now";
        } else if (gamePhase == genericFunctionsObj.GAMEPHASEATTACK) {
            nextPlayerTurn();
            incrementGamePhase();
            gamePhaseStr = "Phase: Reinforcement\nPlayer " + this.getCurrentPlayer().getPlayerId().toString() + " will \nre-inforce his armies now";
        }

        for (int ctr = 0; ctr < players.length; ctr++) {
        	//System.out.println("HIHIHI " + players[ctr].numOfTerritories() + ":" + mapObj.getNumTerritories() + "HIHIHI");
            if (players[ctr].numOfTerritories() >= mapObj.getNumTerritories()) {
                gamePhaseStr = "Phase: None\nPlayer " + Integer.toString(ctr) + " \nwins the game";
                System.out.println("Player " + Integer.toString(ctr) + " wins the game.");
                gamePhase = genericFunctionsObj.GAMEPHASENONE;
                if(players[ctr].strategy.getPlayerType() == 0) {
                	this.gameWinner = "Human";
                }
                else if(players[ctr].strategy.getPlayerType() == 1) {
                	this.gameWinner = "Aggressive";
                }
                else if(players[ctr].strategy.getPlayerType() == 2) {
                	this.gameWinner = "Benevolent";
                }
                else if(players[ctr].strategy.getPlayerType() == 3) {
                	this.gameWinner = "Random";
                }
                else if(players[ctr].strategy.getPlayerType() == 4) {
                	this.gameWinner = "Cheater";
                }
            }
        }

        setChanged();
        notifyObservers(this);
    }

    /**
     * This methods starts attack, the number of dice for attacker and defender are chosen
     * @param attackerDice number of dice for attacker
     * @param defenderDice number of dice for defender
     * @param dictContinents dictionary of continents
     */
    public void attackTerritory(Integer attackerDice, Integer defenderDice,
                                Map<String, Continent> dictContinents) {
        this.getCurrentPlayer().attackTerritory(attackerDice, defenderDice, dictContinents);
        gamePhaseStr = "Phase: Attack\nPlayer " + this.getCurrentPlayer().getPlayerId().toString() + " \nperforms an attack";
        setChanged();
        notifyObservers(this);
    }
    
    /**
     * This function is used to move armies to a territory.
     * @param numArmies number of armies
     */
    public void playerMoveArmies(Integer numArmies, String srcTerritory, String destTerritory) {
    	this.fortifyArmies(srcTerritory, destTerritory, numArmies);
    }

    
    /**
     * This function is used to display the game result
     * @param result game result
     */
    public void gameResult(String result) {
    	System.out.println("----------------------------------------------------------");
    	System.out.println("GAME RESULT: " + result);
    	System.out.println("----------------------------------------------------------");
    	System.out.println("Territory End Status: ");
    	for( String territory : this.getMapObj().getDictTerritory().keySet()) {
    		Territory tmp = this.getMapObj().getDictTerritory().get(territory);
    		System.out.println("Territory: " + tmp.getName() + " owned by " + 
    		tmp.getOwner() + " has " + tmp.getArmies() + " armies.");
    	}
    }
}
