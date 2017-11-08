package src.model;

import src.view.MainWindow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

/**
 * @author Team20
 *         This class is used to instantiate everything, and starts the flow of new game.
 */
public class GameConfig extends Observable {
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

    public MainWindow getMainWindow() {
        return mainWindow;
    }

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
    private void nextPlayerTurn() {
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

        setupPlayers();
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
    private void setupPlayers() {
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

            Player playerObj = new Player("Player" + Integer.toString(i), i, playerCards, this);
            playerObj.setArmies(getInitArmy());
            players[i] = playerObj;
        }

        initTerritory();

        for (String territory : mapObj.getDictTerritory().keySet()) {
            players[mapObj.getDictTerritory().get(territory).getOwner()].setTerritories(mapObj.getDictTerritory().get(territory));
        }

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
                if (nextOwnerPlayer == numPlayers) {
                    nextOwnerPlayer = 0;
                }
                (mapObj.getDictTerritory()).get(territory).setOwner(nextOwnerPlayer);
                players[nextOwnerPlayer].occupyTerritory();
            } else {
                if (players[nextOwnerPlayer].numOfTerritories() < perPlayer) {
                    (mapObj.getDictTerritory()).get(territory).setOwner(nextOwnerPlayer);
                    players[nextOwnerPlayer].occupyTerritory();
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
     */
    public void fortifyArmies(String srcTerritory, String destTerritory) {
        ArrayList<Territory> tmp = getCurrentPlayer().getTerritories();
        int srcFound = 0;
        int destFound = 0;
        int adjacencyFlag = 0;

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

        getCurrentPlayer().fortifyArmy(mapObj, srcTerritory, destTerritory);
    }

    /**
     * This functions increment the gamePhase according to previous phase.
     */
    private void incrementGamePhase() {
        if (gamePhase == genericFunctionsObj.GAMEPHASESTARTUP) {
            calcReinforcementArmy();
            gamePhase = genericFunctionsObj.GAMEPHASEREINFORCEMENT;
            System.out.println("Startup Phase ends, Reinforcement Phase Begins");
        } else if (gamePhase == genericFunctionsObj.GAMEPHASEREINFORCEMENT) {
            gamePhase = genericFunctionsObj.GAMEPHASEFORTIFICATION;
            System.out.println("Reinforcement Phase ends, Fortification Phase Begins");
        } else if (gamePhase == genericFunctionsObj.GAMEPHASEFORTIFICATION) {
            gamePhase = genericFunctionsObj.GAMEPHASEATTACK;
            System.out.println("Fortification Phase ends, Attack Phase Begins");
        } else if (gamePhase == genericFunctionsObj.GAMEPHASEATTACK) {
            calcReinforcementArmy();
            gamePhase = genericFunctionsObj.GAMEPHASEREINFORCEMENT;
            System.out.println("Attack Phase ends, Reinforcement Phase Begins");
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
            if (players[ctr].numOfTerritories() == mapObj.getNumTerritories()) {
                gamePhaseStr = "Phase: None\nPlayer " + Integer.toString(ctr) + " \nwins the game";
                System.out.println("Player " + Integer.toString(ctr) + " wins the game.");
                gamePhase = genericFunctionsObj.GAMEPHASENONE;
            }
        }

        setChanged();
        notifyObservers(this);
    }

    /**
     * This methods starts attack, the number of dice for attacker and defender are chosen
     * @param attackerDice number of dice for attacker
     * @param defenderDice number of dice for defender
     */
    public void attackTerritory(Integer attackerDice, Integer defenderDice,
                                Map<String, Continent> dictContinents) {
        this.getCurrentPlayer().attackTerritory(attackerDice, defenderDice, dictContinents);
        gamePhaseStr = "Phase: Attack\nPlayer " + this.getCurrentPlayer().getPlayerId().toString() + " \nperforms an attack";
        setChanged();
        notifyObservers(this);
    }

}
