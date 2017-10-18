package model;

import java.util.ArrayList;

// import javax.swing.JButton;
 
/**
 * 
 * @author Team20
 *
 */
public class Territory {

	/***************************/
	private String continent = null;
	private String name = null;
	private Integer X = 0;
	private Integer Y = 0;
	private ArrayList<String> adjacentCountries = new ArrayList<String>();

//	private Vector<JButton> btnTerritories = new Vector<JButton>(2, 2);

	/**
	 * @param continent
	 * @param name
	 * @param x
	 * @param y
	 * @param adjacentCountries
	 */
	public Territory(String continent, String name, Integer x, Integer y, ArrayList<String> adjacentCountries) {
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
	
	/** 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
    /**
     * @return continent
     */
	public String getContinent() {
		return continent;
	}
	
    /**
     * @return X
     */
	public Integer getX() {
		return X;
	}
	
	/**
	 * @return Y
	 */
	public Integer getY() {
		return Y;
	}
	
	/**
	 * @return adajacentCountries
	 */
	public ArrayList<String> getAdjacentCountries() {
		return adjacentCountries;
	}
	
//	public Vector<JButton> getBtnTerritories() {
//		return btnTerritories;
//	}
	
    /**
     * @param inName
     * @param inX
     * @param inY
     * @param inContinent
     * @param inAdjacent
     */
	public void SetInfo(String inName, String inX, String inY, String inContinent, String inAdjacent) {
		name = inName;
		X = genFunObj.genStrToInt(inX);
		Y = genFunObj.genStrToInt(inY);
		continent = inContinent;
		adjacentCountries = genFunObj.genCommaSepStrToArrayList(inAdjacent);
		return;
	}
	
	/**
	 * @param inId
	 */
	public void setOwner(Integer inId)
	{
		ownerPlayerId = inId;
	}
	
	/**
	 * @return ownerPlayerId
	 */
	public Integer getOwner()
	{
		return ownerPlayerId;
	}
	
	public String toString() {
		String info = "Country Name: " + name + "\n\n"
					+ "Continent Name : " + continent + "\n\n"
					+ "Coordinates: " + "(" + X + "," + Y + ")\n\n"
					+ "Adjacent Countries:\n";
		
		for(String adjTerr: adjacentCountries) {
			info += adjTerr + "\n";
		}
		
		return info.substring(0,info.length());
	}
}