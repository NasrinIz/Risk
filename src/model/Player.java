package src.model;

import java.util.ArrayList;
import java.util.Map;

/**
 * This class is used to instantiate players, and different attributes.
 * Each player has a list of territories and cards that are owned by him.
 * @author Team20
 *
 */
public class Player 
{

	private String name;
	
	/* Its empty, dont use */
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
	
	/**
	 * This function is used to set current player's turn status.
	 * @param status The new status.
	 */
    void setTurnStatus(boolean status)
	{
		isTurnCompleted = status;
	}
	
	/**
	 * This function is used to get current player's turn status.
	 * @return The current status.
	 */
    boolean getTurnStatus()
	{
		return isTurnCompleted;
	}
	/**
	 * This is the constructor to the class Player and is used to initialize the local class variables.
	 * @param name The name of the player
	 * @param inId The player's unique ID
	 * @param inCards The list of cards that this player owns
	 */
	public Player(String name, Integer inId, ArrayList<Card> inCards) 
	{
		this.name = name;
		this.id = inId;
		this.gameCards = inCards;
	}

	/**
	 * @return Returns the player's ID
	 */
	public Integer getPlayerId()
	{
		return id;
	}
	
	/**
	 * @return Returns the number of cards that the player owns
	 */
	public Integer getNumCard()
	{
		return this.numCards;
	}
	
	/**
	 * @return Returns the player's name
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * @return Returns the player's territories
	 */
	public ArrayList<Territory> getTerritories() 
	{
		return territories;
	}

	/**
	 * This function is used to set player's owned territories.
	 * @param territory name that player owns
	 */
	public void setTerritories(Territory  territory) 
	{
		this.territories.add(territory);
	}
	
	/**
	 * 
	 * @return Returns the number of territories that player owns
	 */
	public int numOfTerritories() 
	{
		return numTerritories;
	}
	
	/**
	 * This function increases the number of territories that player owns
	 */
	public void increaseNumTerritories()
	{
		numTerritories++;
	}

	/**
	 * This function is called after player occupyies a territory to
	 * increase the numbers of territories.
	 */
    void occupyTerritory()
	{
		numTerritories++;
	}

	/**
	 * This function is called when player looses one of his territories
	 * @param territory Information about the territory that player lost
	 */
	public void loseTerritory(Territory territory) 
	{
		//
	}

	/**
	 * This function is used to add armies to player's owned armies.
	 * @param newArmies Number of armies to add.
	 */
	public void addArmy(int newArmies) 
	{
		armies += newArmies;
	}

	/**
	 * This function is called when player looses number of armies he had.
	 * @param lostArmies Number of armies player lost
	 */
	public void loseArmy(int lostArmies) 
	{
		armies -= lostArmies;
	}
	
	/**
	 * This function is used to set number of armies that player owns
	 * @param inArmies New number of armies of player.
	 */
    void setArmies(int inArmies)
	{
		armies = inArmies;
	}
	
	/**
	 * This function is called to retrieve number of armies that player owns
	 * @return armies Number of armies that player owns
	 */
    int getArmies()
	{
		return armies;
	}

	/** 
	 * This function retrieves information about the territories owned by player
	 */
	public void getTerritoriesInfo()
	{
		// to do
	}
	
	/**
	 * This function is called to place an army on territories owned by player.
	 * @param territoryName The territory name, on which army is to be placed.
	 */
	public void placeArmiesOnTerritory(String territoryName)
	{
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
	public void calcReinforcementArmies()
	{
		Integer newArmies = numTerritories / 3;
		exchangeCards();
		
		newArmies += continentArmyReward;
		newArmies += cardsArmyReward;
		armies += newArmies;
		
		System.out.printf("\nPlayer %d received %d armies.", this.getPlayerId(), armies);
	}

	/**
	 * This function is called to initiate the attack from attacker country
	 * to defendor country.
	 * @param attackerDice Number of dices, attacker chooses to roll
	 * @param defendorDice Number of dices, defendor chooses to roll
	 * @param srcTerritory Attacker Country
	 * @param targetTerritory Defendor Country
	 * @return Returns whether the attack was successfull or not
	 */
	public Integer attackTerritory(Integer attackerDice, Integer defendorDice, 
			Territory srcTerritory, Territory targetTerritory, Map<String, Continent> dictContinents)
	{
		Integer rt = 0;
		Integer adjacencyFlag = -1;
		Integer isCaptured; 
				
		if(this.territories.contains(srcTerritory) != true)
		{
			return -1;
		}
		
		ArrayList<String> adjacents = srcTerritory.getAdjacentCountries();
		for(int ctr = 0; ctr < adjacents.size(); ctr++)
		{
			if(adjacents.get(ctr).equals(targetTerritory.getName()) == true)
			{
				adjacencyFlag = 0;
				break;
			}
		}
		
		if(adjacencyFlag == -1)
		{
			return -1;
		}
		
		if(srcTerritory.getArmies() < 2)
		{
			return -1;
		}
		
		isCaptured = rollDiceToAttack(attackerDice, defendorDice, srcTerritory, targetTerritory);
		
		if(isCaptured == 0) // Attacker captured the territory
		{
			targetTerritory.setOwner(id);
			targetTerritory.increaseArmies();
			srcTerritory.decreaseArmies();
		}
		
		for(String continent : dictContinents.keySet())
		{
			if(dictContinents.get(continent).isContinentCaptured(srcTerritory.getOwner()) == true)
			{
				System.out.println("Player " + srcTerritory.getOwner().toString() + " captured " + srcTerritory.getName());
				System.out.println("Player will be awarded " + dictContinents.get(continent).getArmyReward());
			}
		}
		// vj push to view
		
		return rt;
	}
	
	/**
	 * This function is called to actually roll the dice for attacker and defender
	 * @param redDice Number of red dice to roll
	 * @param whiteDice Number of white dice to roll
	 * @param attacker Attacker Country
	 * @param defendor Defender Country
	 * @return Returns whether the country was captured or not
	 */
	private Integer rollDiceToAttack(Integer redDice, Integer whiteDice, Territory attacker, Territory defendor)
	{
		Integer rDiceOne = genFunObj.genRandomNumber(1, 6);
		Integer rDiceTwo = genFunObj.genRandomNumber(1, 6);
		Integer rDiceThree = genFunObj.genRandomNumber(1, 6);
		Integer wDiceOne = genFunObj.genRandomNumber(1, 6);
		Integer wDiceTwo = genFunObj.genRandomNumber(1, 6);
		Integer max = getMaxRedDice(rDiceOne, rDiceTwo, rDiceThree);
		Integer maxTwo = getSecondMaxRedDice(rDiceOne, rDiceTwo, rDiceThree);
		
		switch(redDice)
		{
		case 1:
			if(max > wDiceOne)
			{
				defendor.decreaseArmies();
			}
			else
			{
				attacker.decreaseArmies();
			}
			break;
		case 2:
		case 3:
			if(max > wDiceOne)
			{
				defendor.decreaseArmies();
			}
			else
			{
				attacker.decreaseArmies();
			}
			if(maxTwo > wDiceTwo)
			{
				defendor.decreaseArmies();
			}
			else
			{
				defendor.decreaseArmies();
			}
		}
		
		if(defendor.getArmies() == 0)
		{
			return 0;
		}
		
		return -1;
	}
	
	/**
	 * This function is called to receive the highest roll of all 3 dices
	 * @param r1 Red dice number 1
	 * @param r2 Red dice number 2
	 * @param r3 Red dice number 3
	 * @return Returns the highest dice roll
	 */
	private Integer getMaxRedDice(Integer r1, Integer r2, Integer r3)
	{
		if((r1 >= r2) && (r1 >= r3))
		{
			return r1;
		}
		else if((r2 >= r1) && (r2 >= r3))
		{
			return r2;
		}
		else if((r3 >= r1) && (r3 >= r2))
		{
			return r3;
		}
		
		return 0;
	}
	
	/**
	 * This function is called to receive the second highest roll of all 3 dices
	 * @param r1 Red dice number 1
	 * @param r2 Red dice number 2
	 * @param r3 Red dice number 3
	 * @return Returns the second highest dice roll
	 */
	private Integer getSecondMaxRedDice(Integer r1, Integer r2, Integer r3)
	{
		if(((r1 >= r2) && (r1 <= r3)) ||
				((r1 >= r3) && (r1 <= r2)))
		{
			return r1;
		}
		else if(((r2 >= r1) && (r2 <= r3)) ||
				((r2 >= r3) && (r2 <= r1)))
		{
			return r2;
		}
		else if(((r3 >= r1) && (r3 <= r2)) ||
				((r3 >= r2) && (r3 <= r1)))
		{
			return r3;
		}
		
		return 0;
	}
	
	/**
	 * This function is used to set the number of armies player received
	 * from owned continents
	 * @param inArmy Number of armies player received
	 */
	public void setContinentArmyReward(Integer inArmy)
	{
		continentArmyReward = inArmy;
	}
	
	/**
	 * This function is used to check, how many armies player gets when exchanging cards
	 */
	private void checkReward()
	{
		if(this.countCardExchange > 6)
		{
			this.currentCardReward = 15 + (5 * (this.countCardExchange - 6));
		}
	}
	
	/**
	 * This function is used to exchange cards
	 */
    private void exchangeCards()
	{
		ArrayList<Card> cards = this.gameCards;
		Integer rewardedArmy = 0;
		Integer infantryCards = 0;
		Integer cavalryCards = 0;
		Integer artilleryCards = 0;
		Integer wildCards = 0;
		
		for(int i = 0; i < cards.size(); i++)
		{
			if(cards.get(i).cardType == 1)
			{
				infantryCards++;
			}
			else if(cards.get(i).cardType == 2)
			{
				cavalryCards++;
			}
			else if(cards.get(i).cardType == 3)
			{
				artilleryCards++;
			}
			else if(cards.get(i).cardType == 4)
			{
				wildCards++;
			}
		}
		
		while((infantryCards > 0) && (cavalryCards > 0) && (artilleryCards > 0))
		{
			infantryCards -= 1;
			cavalryCards -= 1;
			artilleryCards -= 1;
			
			Integer infantryRemoved = -1;
			Integer cavalryRemoved = -1;
			Integer artilleryRemoved = -1;
			int i = 0;
			while((infantryRemoved != 0) && (cavalryRemoved != 0) && (artilleryRemoved != 0))
			{
				if(cards.get(i).cardType == 1)
				{
					if(infantryRemoved == 0)
					{
						infantryRemoved++;
						cards.get(i).setOwnerId(null);
						cards.remove(i);
					}
					else
					{
						continue;
					}
				}
				else if(cards.get(i).cardType == 2)
				{
					if(cavalryRemoved == 0)
					{
						cavalryRemoved++;
						cards.get(i).setOwnerId(null);
						cards.remove(i);
					}
					else
					{
						continue;
					}
				}
				else if(cards.get(i).cardType == 3)
				{
					if(artilleryRemoved == 0)
					{
						artilleryRemoved++;
						cards.get(i).setOwnerId(null);
						cards.remove(i);
					}
					else
					{
						continue;
					}
				}
			}
			this.cardsArmyReward += this.currentCardReward;
			this.countCardExchange += 1;
			checkReward();
			System.out.printf("\nPlayer %d exchanged 1 of each", this.getPlayerId());
			return;
		}
		
		if(infantryCards > 2)
		{
			rewardedArmy += this.cardsArmyReward;
			infantryCards -= 3;
			int removed = 0;
			for(int i = 0; i < cards.size(); i++)
			{
				if(removed == 3)
				{
					break;
				}
				if(cards.get(i).cardType != 1)
				{
					continue;
				}
				cards.get(i).setOwnerId(null);
				cards.remove(i);
				removed += 1;
			}
			this.cardsArmyReward += this.currentCardReward;
			this.countCardExchange += 1;
			checkReward();
			System.out.printf("\nPlayer %d exchanged 3 of a kind", this.getPlayerId());
			return;
		}
		
		if(cavalryCards > 2)
		{
			rewardedArmy += this.cardsArmyReward;
			cavalryCards -= 3;
			int removed = 0;
			for(int i = 0; i < cards.size(); i++)
			{
				if(removed == 3)
				{
					break;
				}
				if(cards.get(i).cardType != 2)
				{
					continue;
				}
				cards.get(i).setOwnerId(null);
				cards.remove(i);
				removed += 1;
			}
			this.cardsArmyReward += this.currentCardReward;
			this.countCardExchange += 1;
			checkReward();
			System.out.printf("\nPlayer %d exchanged 3 of a kind", this.getPlayerId());
			return;
		}
		
		if(artilleryCards > 2)
		{
			rewardedArmy += this.cardsArmyReward;
			artilleryCards -= 3;
			int removed = 0;
			for(int i = 0; i < cards.size(); i++)
			{
				if(removed == 3)
				{
					break;
				}
				if(cards.get(i).cardType != 3)
				{
					continue;
				}
				cards.get(i).setOwnerId(null);
				cards.remove(i);
				removed += 1;
			}
			this.cardsArmyReward += this.currentCardReward;
			this.countCardExchange += 1;
			checkReward();
			System.out.printf("\nPlayer %d exchanged 3 of a kind", this.getPlayerId());
			return;
		}

		for(int i = 0; i < wildCards; i++)
		{
			Integer remainingCards = infantryCards + cavalryCards + artilleryCards;
			
			if(remainingCards > 1)
			{
				cards.get(0).setOwnerId(null);
				cards.get(1).setOwnerId(null);
				cards.remove(0);
				cards.remove(1);
				for(int j = 0; j < cards.size(); j++)
				{
					if(cards.get(j).cardType == 4)
					{
						cards.get(j).setOwnerId(null);
						cards.remove(j);
						break;
					}
				}
				this.cardsArmyReward += this.currentCardReward;
				this.countCardExchange += 1;
				checkReward();
				System.out.printf("\nPlayer %d exchanged with one wild card", this.getPlayerId());
				return;
			}
			
			if((wildCards == 2) && (remainingCards == 1))
			{
				cards.get(0).setOwnerId(null);
				cards.remove(0);
				int removed = 0;
				for(int j = 0; j < cards.size(); j++)
				{
					if(removed > 1)
					{
						break;
					}
					if(cards.get(j).cardType == 4)
					{
						cards.get(j).setOwnerId(null);
						cards.remove(j);
						removed++;
					}
				}
				this.cardsArmyReward += this.currentCardReward;
				this.countCardExchange += 1;
				checkReward();
				System.out.printf("\nPlayer %d exchanged with 2 wild cards", this.getPlayerId());
				return;
			}
		}
	}

	void fortifyArmy(Maps mapObj, String srcTerritory, String destTerritory){
        if(mapObj.getDictTerritory().get(srcTerritory).getArmies() > 1)
        {
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
