package model;

import java.util.Vector;

public class Player {

	private String name;
	private Vector<Territory> territories;
	private int armies;

	Player(String name) {
		this.name = name;
		this.setTerritories(new Vector<Territory>());
	}

	public String getName() {
		return name;
	}

	public Vector<Territory> getTerritories() {
		return territories;
	}

	public void setTerritories(Vector<Territory> territories) {
		this.territories = territories;
	}
	
	public int getArmies() {
		return armies;
	}

	public int numOfTerritories() {
		return territories.size();
	}

	public void occupyTerritory(Territory territory) {
		territories.add(territory);
	}

	public void loseTerritory(Territory territory) {
		territories.remove(territory);
	}

	public void addArmy(int a) {
		armies += a;
	}

	public void loseArmy(int a) {
		armies -= a;
	}

}
