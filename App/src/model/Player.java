package model;

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
	
	public void setTurnStatus(boolean status)
	{
		isTurnCompleted = status;
	}
	public boolean getTurnStatus()
	{
		return isTurnCompleted;
	}
	/**
	 * @param name
	 */
	public Player(String name, Integer inId, ArrayList<Card> inCards) {
		this.name = name;
		this.id = inId;
		this.gameCards = inCards;
	}

	public Integer getPlayerId()
	{
		return id;
	}
	
	public Integer getNumCard()
	{
		return this.numCards;
	}
	
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return territories
	 */
	public ArrayList<Territory> getTerritories() {
		return territories;
	}

	/**
	 * @param territory
	 */
	public void setTerritories(Territory  territory) {
		this.territories.add(territory);
	}
	
	/**
	 * @return numTerritories
	 */
	public int numOfTerritories() {
		return numTerritories;
	}
	public void increaseNumTerritories()
	{
		numTerritories++;
	}

	/**
	 * after occupying each territories,add one to the numbers of them  
	 */
	public void occupyTerritory() {
		numTerritories++;
	}

	public void loseTerritory(Territory territory) {
		//
	}

	/**
	 * @param a
	 */
	public void addArmy(int a) {
		armies += a;
	}

	/**
	 * @param a
	 */
	public void loseArmy(int a) {
		armies -= a;
	}
	
	/**
	 * @param inArmies
	 */
	public void setArmies(int inArmies) {
		armies = inArmies;
	}
	
	/**
	 * @return armies
	 */
	public int getArmies() {
		return armies;
	}

	public void getTerritoriesInfo()
	{
		// to do
	}
	
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
	
	public void calcReinforcementArmies()
	{
		Integer newArmies = numTerritories / 3;
		exchangeCards();
		
		newArmies += continentArmyReward;
		newArmies += cardsArmyReward;
		armies += newArmies;
		
		System.out.printf("\nPlayer %d received %d armies.", this.getPlayerId(), armies);
	}
	
	public void setContinentArmyReward(Integer inArmy)
	{
		continentArmyReward = inArmy;
	}
	
	private void checkReward()
	{
		if(this.countCardExchange > 6)
		{
			this.currentCardReward = 15 + (5 * (this.countCardExchange - 6));
		}
	}
	
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
			}
		}
	}
}
