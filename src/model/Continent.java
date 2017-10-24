package src.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Team20
 * This is class Continent that is used to instantiate the continents of map, including
 * the list of territories for each continent among other properties.
 */
public class Continent {

	private String name;
	private ArrayList<Territory> territories = new ArrayList<>();
	private int armyReward;
	private Integer ownerPlayerId = null;
	
	/**
	 * @param inName continent name
	 * @param inArmyReward army reward
	 */
	public Continent(String inName, int inArmyReward) {
		this.name = inName;
		this.armyReward = inArmyReward;
		this.territories = new ArrayList<>();
	}
    
	/**
	 * check who is the owner of continent
	 */
	Integer checkForOwnership()
	{
		Integer ownerId = null;
		for (Territory territory : territories) {
			if (ownerId == null) {
				ownerId = territory.getOwner();
			} else {
				if (!Objects.equals(territory.getOwner(), ownerId)) {
					return -1;
				}
			}
		}
		if(ownerPlayerId == null)
		{
			ownerPlayerId = ownerId;
		}
		else
		{
			ownerPlayerId = ownerId;
		}
		return 0;
	}
	
	/**
	 * @return owner player id
	 */
	Integer getOwnerId()
	{
		return ownerPlayerId;
	}
	
	/**
	 * This method get the name 
	 * @return name
	 */
	public String getName() {
		return name;
	}
    
	/**
	 * This method get territories
	 * @return territories
	 */
	public ArrayList<Territory> getTerritories() {
		return territories;
	}
	
	/**
	 * @param obj territory object
	 */
	void addToTerritoryList(Territory obj)
	{
		this.territories.add(obj);
	}
	
    /**
     * This method get armyReward
     * @return armyReward
     */
	int getArmyReward() {
		return armyReward;
	}
	
	/**
	 * @param inArmyReward reward of armies
	 */
	void setArmyReward(Integer inArmyReward)
	{
		this.armyReward = inArmyReward;
	}

	/**
//	 * This method checks if a continent is captured or not.
//	 * @param p player
//	 * @return true
//	 */
//	public boolean isContinentCaptured(Player p) {
//		return true;
//	}
	
	public String toString() {
		StringBuilder info = new StringBuilder("Name:	" + name + "\n"
				+ "Territories:	");
		for(Territory ter: territories) {
			info.append(ter.getName()).append(" ");
		}
		
		info.append("\nArmy reward:	").append(armyReward).append("\n");
				
		return info.toString();
	}

//	/**
//	 * @param ter territory
//	 */
//	public void removeTerritory(Territory ter) {
//
//		this.territories.remove(ter);
//	}
	
}
