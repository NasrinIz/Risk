package model;

import java.util.Vector;

public class Continent {

	private String name;
	private Territory territories[];
	private int armyReward;

	Continent(String inName, Territory inTerritories[], int inArmyReward) {
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
}
