package model;

import java.util.Vector;

public class Continent {

	private String name;
	private Vector<Integer> territories;
	private int value;
	Territory territory;

	Continent(String name, Vector<Integer> territories, int value) {
		this.name = name;
		this.value = value;
		this.territories = territories;
	}

	public String getName() {
		return name;
	}

	public Vector<Integer> getTerritories() {
		return territories;
	}

	public int getValue() {
		return value;
	}

	public boolean isContinentCaptured(Player p) {
		return true;
	}
}
