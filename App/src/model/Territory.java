package model;

import java.util.Vector;

import javax.swing.JButton;

public class Territory {
	/***************************/
	private Integer TERRITORYINITSIZE = 2;
	private Integer TERRITORYCAPACITYINCREMENT = 2;
	/***************************/
	private String continent = null;
	private String name = null;
	private Integer X = 0;
	private Integer Y = 0;
	private Vector<String> adjacentCountries = new Vector<String>(TERRITORYINITSIZE, TERRITORYCAPACITYINCREMENT);
	private Vector<JButton> btnTerritories = new Vector<JButton>(2, 2);


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
	
	public Vector<JButton> getBtnTerritories() {
		return btnTerritories;
	}

	private Integer ownerPlayerId = null;
	
	GenFun genFunObj = new GenFun();

	public Territory(String inInfo) {
		String[] tmp = genFunObj.genCommaSepStrToArray(inInfo);
		String tmpAdjacent = inInfo.substring(genFunObj.genOrdinalIndexOf(inInfo, ",", 4));
		SetInfo(tmp[0], tmp[1], tmp[2], tmp[3], tmpAdjacent);
	}

	
	
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
}