package model;

import java.util.ArrayList;

/**
 * @author Team20
 *
 */
public class Player {

	private String name;
	
	/* Its empty, dont use */
	private ArrayList<Territory> territories = new ArrayList<Territory>();
	private ArrayList<Card> playerCards;
	private int armies;
	private Integer numTerritories = 0;
	Integer id = null;

	private boolean isTurnCompleted = false;
	
	
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
	Player(String name, Integer inId) {
		this.name = name;
		this.id = inId;
	}

	public Integer getPlayerId()
	{
		return id;
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
	
	
	public void addCard(Card c) {

		playerCards.add(c);
	}
	
	public void removeCard(Card c) {
		playerCards.remove(c);
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
				territories.get(i).increaseArmies();
			}
		}
	}
}
