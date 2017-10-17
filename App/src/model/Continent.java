package model;

/**
 * @author Team20
 * This is class Continent that maintain name,territories,armyReward
 */
public class Continent {

	private String name;
	private Territory territories[];
	private int armyReward;

	/**
	 * @param inName
	 * @param inTerritories
	 * @param inArmyReward
	 */
	public Continent(String inName, Territory inTerritories[], int inArmyReward) {
		this.name = inName;
		this.armyReward = inArmyReward;
		this.territories = inTerritories;
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
	public Territory[] getTerritories() {
		return territories;
	}
	
    /**
     * This method get armyReward
     * @return armyReward
     */
	public int getArmyReward() {
		return armyReward;
	}

	/**
	 * This method checks if a continent is captured or not. 
	 * @param p
	 * @return true
	 */
	public boolean isContinentCaptured(Player p) {
		return true;
	}
	
	public String toString() {
		String info = "Name:	" + name + "\n"
					+ "Territories:	";
		for(int i = 0; i < territories.length ; i++) {
			info += territories[i].getName() + " ";
		}
		
		info += "\nArmy reward:	" + armyReward + "\n";
				
		return info;	
	}
	
}
