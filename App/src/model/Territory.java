package model;

import java.util.Vector;

//import javax.swing.JButton;

public class Territory {
	/***************************/
	private static Integer TERRITORY_INIT_SIZE = 2;
	private static Integer TERRITORY_CAPACITY_INCREMENT = 2;
	/***************************/
	private String continent = null;
	private String name = "";
	private Integer X = 0;
	private Integer Y = 0;
	private Vector<String> adjacentCountries = new Vector<String>(TERRITORY_INIT_SIZE, TERRITORY_CAPACITY_INCREMENT);

//	private Vector<JButton> btnTerritories = new Vector<JButton>(2, 2);

	/**
	 * @param continent
	 * @param name
	 * @param x
	 * @param y
	 * @param adjacentCountries
	 */
	public Territory(String continent, String name, Integer x, Integer y, Vector<String> adjacentCountries) {
		super();
		this.continent = continent;
		this.name = name;
		X = x;
		Y = y;
		this.adjacentCountries = adjacentCountries;
	}
	
	private Integer ownerPlayerId = null;
	GenFun genFunObj = new GenFun();
	
	public Territory(String inInfo) {
		String[] tmp = genFunObj.genCommaSepStrToArray(inInfo);
		String tmpAdjacent = inInfo.substring(genFunObj.genOrdinalIndexOf(inInfo, ",", 4));
		SetInfo(tmp[0], tmp[1], tmp[2], tmp[3], tmpAdjacent);
	}
	
	public String getName() {
		return name;
	}

	public String getContinent() {
		return continent;
	}

	public Integer getX() {
		return X;
	}
	
	public Integer getY() {
		return Y;
	}
	
	public Vector<String> getAdjacentCountries() {
		return adjacentCountries;
	}
	
//	public Vector<JButton> getBtnTerritories() {
//		return btnTerritories;
//	}

	public void SetInfo(String inName, String inX, String inY, String inContinent, String inAdjacent) {
		name = inName;
		X = genFunObj.genStrToInt(inX);
		Y = genFunObj.genStrToInt(inY);
		continent = inContinent;
		adjacentCountries = genFunObj.genCommaSepStrToVector(inAdjacent);
		return;
	}
	
	public void setOwner(Integer inId)
	{
		ownerPlayerId = inId;
	}
	
	public Integer getOwner()
	{
		return ownerPlayerId;
	}
	
	public String toString() {
		String info = "Country 	 Name:	" + name + "\n"
					+ "Continent Name:	" + continent + "\n"
					+ "Coordinates	 :	" + "(" + X + "," + Y + ")\n"
					+ "Adjacent Countries: \n";
		
		for(String adjTerr: adjacentCountries) {
			info += adjTerr + " , ";
		}
		
		return info.substring(0,-3);
	}
}