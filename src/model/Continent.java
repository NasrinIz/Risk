package src.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Team20
 * This is class Continent that is used to instantiate the continents of map, including
 * the list of territories for each continent among other properties.
 */
public class Continent 
{

	private String name;
	private ArrayList<Territory> territories = new ArrayList<>();
	private int armyReward;
	private Integer ownerPlayerId = null;
	
	/**
	 * This is the constructor to class Continent
	 * @param inName continent name
	 * @param inArmyReward army reward
	 */
	public Continent(String inName, int inArmyReward) 
	{
		this.name = inName;
		this.armyReward = inArmyReward;
		this.territories = new ArrayList<>();
	}
    
	/**
	 * @return The owner of the continent
	 */
	Integer checkForOwnership()
	{
		Integer ownerId = null;
		for (Territory territory : territories) 
		{
			if (ownerId == null) 
			{
				ownerId = territory.getOwner();
			} 
			else 
			{
				if (!Objects.equals(territory.getOwner(), ownerId)) 
				{
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
	 * @return The name of the continent
	 */
	public String getName() 
	{
		return name;
	}
    
	/**
	 * @return Returns the territories inside this continent
	 */
	public ArrayList<Territory> getTerritories() 
	{
		return territories;
	}
	
	/**
	 * This function is used to add a territory to this continent
	 * @param obj territory object
	 */
	void addToTerritoryList(Territory obj)
	{
		this.territories.add(obj);
	}
	
    /**
     * @return This method returns armyReward for the continent capture
     */
	int getArmyReward() 
	{
		return armyReward;
	}
	
	/**
	 * Sets the army reward for the capture of this continent
	 * @param inArmyReward reward of armies
	 */
	void setArmyReward(Integer inArmyReward)
	{
		this.armyReward = inArmyReward;
	}

	/**
	 * This method checks if a continent is captured or not.
	 * @param p player
	 * @return true
	 */
//	public boolean isContinentCaptured(Player p) {
//		return true;
//	}
	
	/** 
	 * @return This function returns the continent information in a string
	 */
	public String toString() 
	{
		StringBuilder info = new StringBuilder("Name:	" + name + "\n"
				+ "Territories:	");
		for(Territory ter: territories) 
		{
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
