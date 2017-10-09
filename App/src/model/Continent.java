package model;

public class Continent {

	private String name;
	private Territory territories[];
	private int armyReward;

	public Continent(String inName, Territory inTerritories[], int inArmyReward) {
		this.name = inName;
		this.armyReward = inArmyReward;
		this.territories = inTerritories;
	}

	public String getName() {
		return name;
	}

	public Territory[] getTerritories() {
		return territories;
	}

	public int getArmyReward() {
		return armyReward;
	}

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
