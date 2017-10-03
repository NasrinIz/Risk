package model;

import java.util.Vector;

public class Player {

	private String name;
	private Territory territories[];
	private int armies;
	private Integer numTerritories = new Integer(0);

	Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Territory[] getTerritories() {
		return territories;
	}

	public void setTerritories(Territory territories[]) {
		this.territories = territories;
	}
	
	public int numOfTerritories() {
		return numTerritories;
	}

	public void occupyTerritory() {
		numTerritories++;
	}

	public void loseTerritory(Territory territory) {
		//
	}

	public void addArmy(int a) {
		armies += a;
	}

	public void loseArmy(int a) {
		armies -= a;
	}
	
	public void setArmies(int inArmies) {
		armies = inArmies;
	}
	
	public int getArmies() {
		return armies;
	}

}
