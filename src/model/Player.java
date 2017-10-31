package src.model;

import java.util.ArrayList;

/**
 * This class is used to instantiate players, and different attributes.
 * Each player has a list of territories and cards that are owned by him.
 * @author Team20
 *
 */
public class Player {

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
	
	/**
	 * This function is used to set current player's turn status.
	 * @param status The new status.
	 */
	public void setTurnStatus(boolean status)
	{
		isTurnCompleted = status;
	}
	
	/**
	 * This function is used to get current player's turn status.
	 * @return The current status.
	 */
	public boolean getTurnStatus()
	{
		return isTurnCompleted;
	}
	/**
	 * This is the constructor to the class Player and is used to initialize the local class variables.
	 * @param name The name of the player
	 * @param inId The player's unique ID
	 * @param inCards The list of cards that this player owns
	 */
	public Player(String name, Integer inId, ArrayList<Card> inCards) {
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
	 * @param territory name that player owns
	 */
	public void setTerritories(Territory  territory) {
		this.territories.add(territory);
	}
	
	/**
	 * 
	 * @return Returns the number of territories that player owns
	 */
	public int numOfTerritories() {
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
	public void occupyTerritory() {
		numTerritories++;
	}

	/**
	 * This function is called when player looses one of his territories
	 * @param territory Information about the territory that player lost
	 */
	public void loseTerritory(Territory territory) {
		//
	}

	/**
	 * This function is used to add armies to player's owned armies.
	 * @param newArmies Number of armies to add.
	 */
	public void addArmy(int newArmies) {
		armies += newArmies;
	}

	/**
	 * This function is called when player looses number of armies he had.
	 * @param lostArmies Number of armies player lost
	 */
	public void loseArmy(int lostArmies) {
		armies -= lostArmies;
	}
	
	/**
	 * This function is used to set number of armies that player owns
	 * @param inArmies New number of armies of player.
	 */
	public void setArmies(int inArmies) {
		armies = inArmies;
	}
	
	/**
	 * This function is called to retrieve number of armies that player owns
	 * @return armies Number of armies that player owns
	 */
	public int getArmies() {
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
		for(int i=0; i < territories.size(); i++)
		{
			if(territories.get(i).getName().equals(territoryName) == true)
			{
				if(armies > 0)
				{
					territories.get(i).increaseArmies();
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
	public void exchangeCards()
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
}
