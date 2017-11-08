package src.model;

import java.util.ArrayList;
import java.util.Map;

/**
 * This class is used to instantiate players, and different attributes.
 * Each player has a list of territories and cards that are owned by him.
 *
 * @author Team20
 */
public class Player {

    private String name;

    private ArrayList<Territory> territories = new ArrayList<Territory>();
    private ArrayList<Card> gameCards;
    private Integer numCards = 0;

    private int armies;
    private Integer numTerritories = 0;
    Integer id = null;

    private boolean isTurnCompleted = false;
    private Integer continentArmyReward = 0;

    private Integer cardsArmyReward = 0;

    private Integer currentCardReward = 15;
    private Integer countCardExchange = 0;

    GenericFunctions genFunObj = new GenericFunctions();

    public Territory srcAttackTerritory;
    public Territory dstAttackTerritory;

    private GameConfig gameConfigObj;

    public ArrayList<Card> getPlayerCards()
    {
    	return gameCards;
    }
    /**
     * This function is used to set current player's turn status.
     *
     * @param status The new status.
     */
    void setTurnStatus(boolean status) {
        isTurnCompleted = status;
    }

    /**
     * This function is used to get current player's turn status.
     *
     * @return The current status.
     */
    boolean getTurnStatus() {
        return isTurnCompleted;
    }

    /**
     * This is the constructor to the class Player and is used to initialize the local class variables.
     *
     * @param name    The name of the player
     * @param inId    The player's unique ID
     * @param inCards The list of cards that this player owns
     * @param gameConfig The reference to gameconfig object
     */
    public Player(String name, Integer inId, ArrayList<Card> inCards, GameConfig gameConfig) {
        this.name = name;
        this.id = inId;
        this.gameCards = inCards;
        this.gameConfigObj = gameConfig;
    }

    /**
     * this method get the player id
     * @return Returns the player's ID
     */
    public Integer getPlayerId() {
        return id;
    }

    /**
     * this method get the numbers of cards
     * @return Returns the number of cards that the player owns
     */
    public Integer getNumCard() {
        return this.numCards;
    }

    /**
     * this method get the name of players
     * @return Returns the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return Returns the player's territories
     */
    public ArrayList<Territory> getTerritories() {
        return territories;
    }

    /**
     * This function is used to set player's owned territories.
     *
     * @param territory name that player owns
     */
    public void setTerritories(Territory territory) {
        this.territories.add(territory);
    }

    /**
     * this method get the number of territories
     * @return Returns the number of territories that player owns
     */
    public int numOfTerritories() {
        return numTerritories;
    }

    /**
     * This function increases the number of territories that player owns
     */
    public void increaseNumTerritories() {
        numTerritories++;
    }

    /**
     * This function is called after player occupyies a territory to
     * increase the numbers of territories.
     */
    void occupyTerritory() {
        numTerritories++;
    }

    /**
     * This function is called when player looses one of his territories
     *
     * @param territory Information about the territory that player lost
     */
    public void loseTerritory(Territory territory) {
        //
    }

    /**
     * This function is used to add armies to player's owned armies.
     *
     * @param newArmies Number of armies to add.
     */
    public void addArmy(int newArmies) {
        armies += newArmies;
    }

    /**
     * This function is called when player looses number of armies he had.
     *
     * @param lostArmies Number of armies player lost
     */
    public void loseArmy(int lostArmies) {
        armies -= lostArmies;
    }

    /**
     * This function is used to set number of armies that player owns
     *
     * @param inArmies New number of armies of player.
     */
    public void setArmies(int inArmies) {
        armies = inArmies;
    }

    /**
     * This function is called to retrieve number of armies that player owns
     *
     * @return armies Number of armies that player owns
     */
    public int getArmies() {
        return armies;
    }

    /**
     * This function retrieves information about the territories owned by player
     */
    public void getTerritoriesInfo() {
        // to do
    }

    /**
     * This function is called to place an army on territories owned by player.
     *
     * @param territoryName The territory name, on which army is to be placed.
     */
    public void placeArmiesOnTerritory(String territoryName) {
        for (Territory territory : territories) {
            if (territory.getName().equals(territoryName)) {
                if (armies > 0) {
                    territory.increaseArmies();
                    armies--;
                }
            }
        }
    }

    /**
     * This function is used to calculate the new armies that player receives when Reinforcement phase begins
     */
    public void calcReinforcementArmies() {
        Integer newArmies = numTerritories / 3;
        armies += newArmies + continentArmyReward;

        System.out.printf("\nPlayer %d received %d armies. (%d from territories, %d from continent).", 
        		this.getPlayerId(), armies, newArmies, continentArmyReward);
    }

    public void removeTerritory(String territory)
    {
    	for(int ctr = 0; ctr < this.territories.size(); ctr++)
    	{
    		Territory tmp = this.territories.get(ctr);
    		if(tmp.getName().equals(territory))
    		{
    			this.territories.remove(ctr);
    			this.numTerritories --;
    		}
    	}
    }
    
    /**
     * This function is called to initiate the attack from attacker country
     * to defendor country.
     *
     * @param attackerDice   Number of dices, attacker chooses to roll
     * @param defendorDice   Number of dices, defendor chooses to roll
     * @param dictContinents Attacker Country
     * @return Returns whether the attack was successfull or not
     */
    public Integer attackTerritory(Integer attackerDice, Integer defendorDice,
                                   Map<String, Continent> dictContinents) {
        Integer rt = 0;
        Integer adjacencyFlag = -1;
        Integer isCaptured;
        Territory srcTerritory = srcAttackTerritory;
        Territory targetTerritory = dstAttackTerritory;
        if ((srcTerritory == null) || (targetTerritory == null)) {
            System.out.println("Please select both the source and target territories for attack");
            return -1;
        }
        if (!this.territories.contains(srcTerritory)) {
            System.out.println("The territory selected does not belong to the current player");
            return -1;
        }

        ArrayList<String> adjacents = srcTerritory.getAdjacentCountries();
        for (int ctr = 0; ctr < adjacents.size(); ctr++) {
            if (adjacents.get(ctr).equals(targetTerritory.getName()) == true) {
                adjacencyFlag = 0;
                break;
            }
        }

        if (adjacencyFlag == -1) {
            System.out.println("Attack failed, attacking country is not adjacent to defending country");
            return -1;
        }

        if ((srcTerritory.getArmies() + 1) < attackerDice) {
            System.out.println("Attacker's " + srcTerritory.getArmies().toString() + " armies on " +
                    srcTerritory.getName() + " not enough to roll " + attackerDice.toString() + " dice");
            return -1;
        }

        if ((targetTerritory.getArmies()) < defendorDice) {
            System.out.println("Defender's " + targetTerritory.getArmies().toString() + " armies on " +
                    targetTerritory.getName() + " not enough to roll " + defendorDice.toString() + " dice");
            return -1;
        }

        System.out.println("Player " + srcTerritory.getOwner().toString() +
                " attacks player " + targetTerritory.getOwner().toString() + "'s Territory");

        isCaptured = rollDiceToAttack(attackerDice, defendorDice, srcTerritory, targetTerritory);

        if (isCaptured == 0) // Attacker captured the territory
        {
            System.out.println("Player " + srcTerritory.getOwner().toString() +
                    " captured player " + targetTerritory.getOwner().toString() + "'s Territory");
            Player players[] = gameConfigObj.getPlayers();
            players[targetTerritory.getOwner()].removeTerritory(targetTerritory.getName());
            targetTerritory.setOwner(id);
            targetTerritory.increaseArmies();
            srcTerritory.decreaseArmies();
            this.territories.add(targetTerritory);
        }

        for (String continent : dictContinents.keySet()) {
            if (continent.equals(targetTerritory.getContinent())) {
                if (dictContinents.get(continent).isContinentCaptured(srcTerritory.getOwner()) == true) {
                    System.out.println("Player " + srcTerritory.getOwner().toString() + " captured continent " + continent);
                    System.out.println("Player will be awarded " + dictContinents.get(continent).getArmyReward() +
                            " additional armies");
                }
            }
        }

        srcAttackTerritory = null;
        return rt;
    }

    /**
     * This function is called to actually roll the dice for attacker and defender
     *
     * @param redDice   Number of red dice to roll
     * @param whiteDice Number of white dice to roll
     * @param attacker  Attacker Country
     * @param defendor  Defender Country
     * @return Returns whether the country was captured or not
     */
    private Integer rollDiceToAttack(Integer redDice, Integer whiteDice, Territory attacker, Territory defendor) {
        Integer rDiceOne = genFunObj.genRandomNumber(1, 6);
        Integer rDiceTwo = genFunObj.genRandomNumber(1, 6);
        Integer rDiceThree = genFunObj.genRandomNumber(1, 6);
        Integer wDiceOne = genFunObj.genRandomNumber(1, 6);
        Integer wDiceTwo = genFunObj.genRandomNumber(1, 6);
        Integer max = getMaxRedDice(rDiceOne, rDiceTwo, rDiceThree);
        Integer maxTwo = getSecondMaxRedDice(rDiceOne, rDiceTwo, rDiceThree);

        if (wDiceTwo > wDiceOne) {
            wDiceOne = wDiceOne + wDiceTwo;
            wDiceTwo = wDiceOne - wDiceTwo;
            wDiceOne = wDiceOne - wDiceTwo;
        }

        System.out.println("Attacker rolls " + redDice.toString() + " dice.");
        System.out.println("Defender rolls " + whiteDice.toString() + " dice.");
        switch (redDice) {
            case 1:
                System.out.println("Player " + attacker.getOwner().toString() + " rolled " + max.toString());
                System.out.println("Player " + defendor.getOwner().toString() + " rolled " + wDiceOne.toString());
                if (max > wDiceOne) {
                    System.out.println("Attacker's " + max.toString() + " against Defender's " + wDiceOne.toString() +
                            ", Attacker Wins");
                    defendor.decreaseArmies();
                } else {
                    System.out.println("Defender's " + wDiceOne.toString() + " against Attacker's " + max.toString() +
                            ", Defendor Wins");
                    attacker.decreaseArmies();
                }
                break;
            case 2:
            case 3:
                if (redDice == 2) {
                    System.out.println("Player " + attacker.getOwner().toString() + " rolled " + max.toString());
                    System.out.println("Player " + attacker.getOwner().toString() + " rolled " + maxTwo.toString());
                } else if (redDice == 3) {
                    System.out.println("Player " + attacker.getOwner().toString() + " rolled " + rDiceOne.toString());
                    System.out.println("Player " + attacker.getOwner().toString() + " rolled " + rDiceTwo.toString());
                    System.out.println("Player " + attacker.getOwner().toString() + " rolled " + rDiceThree.toString());
                }

                if (whiteDice == 2) {
                    System.out.println("Player " + defendor.getOwner().toString() + " rolled " + wDiceOne.toString());
                    System.out.println("Player " + defendor.getOwner().toString() + " rolled " + wDiceTwo.toString());
                } else {
                    System.out.println("Player " + defendor.getOwner().toString() + " rolled " + wDiceOne.toString());
                }

                if (max > wDiceOne) {
                    System.out.println("Attacker's " + max.toString() + " against Defender's " + wDiceOne.toString() +
                            ", Attacker Wins");
                    defendor.decreaseArmies();
                } else {
                    System.out.println("Defender's " + wDiceOne.toString() + " against Attacker's " + max.toString() +
                            ", Defendor Wins");
                    attacker.decreaseArmies();
                }

                if (whiteDice == 2) {
                    if (maxTwo > wDiceTwo) {
                        System.out.println("Attacker's " + maxTwo.toString() + " against Defender's " + wDiceTwo.toString() +
                                ", Attacker Wins");
                        defendor.decreaseArmies();
                    } else {
                        System.out.println("Defender's " + wDiceTwo.toString() + " against Attacker's " + maxTwo.toString() +
                                ", Defendor Wins");
                        attacker.decreaseArmies();
                    }
                }
        }

        if (defendor.getArmies() == 0) {
            return 0;
        }

        return -1;
    }

    /**
     * This function is called to receive the highest roll of all 3 dices
     *
     * @param r1 Red dice number 1
     * @param r2 Red dice number 2
     * @param r3 Red dice number 3
     * @return Returns the highest dice roll
     */
    private Integer getMaxRedDice(Integer r1, Integer r2, Integer r3) {
        if ((r1 >= r2) && (r1 >= r3)) {
            return r1;
        } else if ((r2 >= r1) && (r2 >= r3)) {
            return r2;
        } else if ((r3 >= r1) && (r3 >= r2)) {
            return r3;
        }

        return 0;
    }

    /**
     * This function is called to receive the second highest roll of all 3 dices
     *
     * @param r1 Red dice number 1
     * @param r2 Red dice number 2
     * @param r3 Red dice number 3
     * @return Returns the second highest dice roll
     */
    private Integer getSecondMaxRedDice(Integer r1, Integer r2, Integer r3) {
        if (((r1 >= r2) && (r1 <= r3)) ||
                ((r1 >= r3) && (r1 <= r2))) {
            return r1;
        } else if (((r2 >= r1) && (r2 <= r3)) ||
                ((r2 >= r3) && (r2 <= r1))) {
            return r2;
        } else if (((r3 >= r1) && (r3 <= r2)) ||
                ((r3 >= r2) && (r3 <= r1))) {
            return r3;
        }

        return 0;
    }

    /**
     * This function is used to set the number of armies player received
     * from owned continents
     *
     * @param inArmy Number of armies player received
     */
    public void setContinentArmyReward(Integer inArmy) {
        continentArmyReward = inArmy;
    }

    /**
     * This function is used to check, how many armies player gets when exchanging cards
     */
    private void checkReward() {
        if (this.countCardExchange == 1) {
            this.currentCardReward = 4;
        } else if (this.countCardExchange == 2) {
            this.currentCardReward = 6;
        } else if (this.countCardExchange == 3) {
            this.currentCardReward = 8;
        } else if (this.countCardExchange == 4) {
            this.currentCardReward = 10;
        } else if (this.countCardExchange == 5) {
            this.currentCardReward = 12;
        } else {
            this.currentCardReward = 15 + (5 * (this.countCardExchange - 6));
        }
    }

    /**
     * This function is used to exchange cards.
     * @param infantry Number of infantry cards.
     * @param cavalry Number of cavalry cards.
     * @param artillery Number of artillery cards.
     * @param wild Number of wild cards.
     */
    public void exchangeCards(Integer infantry, Integer cavalry, Integer artillery, Integer wild) {


        System.out.println(infantry);
        System.out.println(cavalry);
        System.out.println(artillery);
        System.out.println(wild);

        boolean exchangeSuccess = false;
        int infantryRem = 0;
        int cavalryRem = 0;
        int artilleryRem = 0;
        int wildRem = 0;

        if ((infantry == 3) || (cavalry == 3) || (artillery == 3) || (wild == 3) ||
                ((infantry == 1) && (cavalry == 1) && (artillery == 1)) ||
                ((infantry == 1) && (cavalry == 1) && (wild == 1)) ||
                ((infantry == 1) && (wild == 1) && (artillery == 1)) ||
                ((wild == 1) && (cavalry == 1) && (artillery == 1))) {
            exchangeSuccess = true;
        }

        if (exchangeSuccess) {
            for (int ctr = 0; ctr < this.gameCards.size(); ctr++) {
                if ((infantryRem < infantry) &&
                        (this.gameCards.get(ctr).cardType == 1)) {
                    this.gameCards.get(ctr).setOwnerId(null);
                    this.gameCards.remove(ctr);
                    infantryRem++;
                }
            }
            for (int ctr = 0; ctr < this.gameCards.size(); ctr++) {
                if ((cavalryRem < cavalry) &&
                        (this.gameCards.get(ctr).cardType == 2)) {
                    this.gameCards.get(ctr).setOwnerId(null);
                    this.gameCards.remove(ctr);
                    ctr--;
                    cavalryRem++;
                }
            }
            for (int ctr = 0; ctr < this.gameCards.size(); ctr++) {
                if ((artilleryRem < artillery) &&
                        (this.gameCards.get(ctr).cardType == 3)) {
                    this.gameCards.get(ctr).setOwnerId(null);
                    this.gameCards.remove(ctr);
                    ctr--;
                    artilleryRem++;
                }
            }
            for (int ctr = 0; ctr < this.gameCards.size(); ctr++) {
                if ((wildRem < wild) &&
                        (this.gameCards.get(ctr).cardType == 4)) {
                    this.gameCards.get(ctr).setOwnerId(null);
                    this.gameCards.remove(ctr);
                    ctr--;
                    wildRem++;
                }
            }
            this.countCardExchange += 1;
            checkReward();
            this.cardsArmyReward += this.currentCardReward;
            this.armies += this.cardsArmyReward;
            System.out.printf("\nPlayer %d exchanged 3 cards for %d armies", this.getPlayerId(), this.cardsArmyReward);
        }
        else {
        	System.out.println("The selected cards cannot be exchanged");
        }

    }

    void fortifyArmy(Maps mapObj, String srcTerritory, String destTerritory) {
        if (mapObj.getDictTerritory().get(srcTerritory).getArmies() > 1) {
            mapObj.getDictTerritory().get(srcTerritory).decreaseArmies();
            mapObj.getDictTerritory().get(destTerritory).increaseArmies();
            System.out.printf("\n%s : %d left, %s : %d now",
                    mapObj.getDictTerritory().get(srcTerritory).getName(),
                    mapObj.getDictTerritory().get(srcTerritory).getArmies(),
                    mapObj.getDictTerritory().get(destTerritory).getName(),
                    mapObj.getDictTerritory().get(destTerritory).getArmies());
        }
    }
}
