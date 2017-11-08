package src.model;

import java.util.ArrayList;

// import javax.swing.JButton;
 
/**
 * This class is used to instantiate different territories along with information
 * such as stationed armies and adjecent countries to each country.
 * @author Team20
 *
 */
public class Territory 
{

	/***************************/
	private String continent = null;
	private String name = null;
	private Integer X = 0;
	private Integer Y = 0;
	private Integer stationedArmies = 1;
	private ArrayList<String> adjacentCountries = new ArrayList<String>();
	
	private Integer ownerPlayerId = null;
	GenericFunctions genericFunctionsObj = new GenericFunctions();

//	private Vector<JButton> btnTerritories = new Vector<JButton>(2, 2);

	/**
	 * This is the constructor to Territory class, and initializes local class variables
	 * @param continent The continent in which territory exists.
	 * @param name The name of territory
	 * @param x The x co-ordinate of territory
	 * @param y The y co-ordinate of territory
	 * @param adjacentCountries The list of adjacent territories to current territory
	 */
	public Territory(String continent, String name, Integer x, Integer y, ArrayList<String> adjacentCountries) 
	{
		super();
		this.continent = continent;
		this.name = name;
		X = x;
		Y = y;
		this.adjacentCountries = adjacentCountries;
		this.stationedArmies = 1;
	}
	
	/**
	 * Increase number of armies on territory
	 */
	public void increaseArmies()
	{
		stationedArmies++;
		System.out.println("Player " + this.getOwner().toString() + " gets one army on " + this.name);
	}
	
	/**
	 * Decrease number of armies on territory
	 */
	public void decreaseArmies()
	{
		stationedArmies--;
		System.out.println("Player " + this.getOwner().toString() + " lost one army on " + this.name);
	}
	
	/**
	 * 
	 * @return Returns number of armies on territory
	 */
	public Integer getArmies()
	{
		return stationedArmies;
	}
	
	/**
	 * The overloaded constructor that receives territory information in string,
	 * and calls function to parse the string and save information in data memebers.
	 * @param inInfo The string containing all the information about the territory
	 */
	public Territory(String inInfo) 
	{
		String[] tmp = genericFunctionsObj.genCommaSepStrToArray(inInfo);
		if(tmp.length < 5)
		{
			System.out.println("Invalid Territory Info Skipped Addition");
			return;
		}
		String tmpAdjacent = inInfo.substring(genericFunctionsObj.genOrdinalIndexOf(inInfo, ",", 4));
		SetInfo(tmp[0], tmp[1], tmp[2], tmp[3], tmpAdjacent);
		
	}
	
	/** 
	 * @return Returns the name of territory
	 */
	public String getName() 
	{
		return name;
	}
	
    /**
     * @return Returns the name of the continent in which territory exists
     */
	public String getContinent() 
	{
		return continent;
	}
	
    /**
     * @return Returns x co-ordinate of the territory
     */
	public Integer getX() 
	{
		return X;
	}
	
	/**
	 * @return Returns y co-ordinate of the territory
	 */
	public Integer getY() 
	{
		return Y;
	}
	
	/**
	 * @return Returns list of adjacent territories to the current territory
	 */
	public ArrayList<String> getAdjacentCountries() 
	{
		return adjacentCountries;
	}
	
    /**
     * The function used to set the information of territory into the data structures
     * @param inName The name of the territory
     * @param inX The x co-ordinate of the territory
     * @param inY The y co-ordinate of the territory
     * @param inContinent The name of the continent in which territory exists
     * @param inAdjacent The string containing information about adjacent territories
     */
	private void SetInfo(String inName, String inX, String inY, String inContinent, String inAdjacent)
	{
		name = inName;
		X = genericFunctionsObj.genStrToInt(inX);
		Y = genericFunctionsObj.genStrToInt(inY);
		continent = inContinent;
		adjacentCountries = genericFunctionsObj.genCommaSepStrToArrayList(inAdjacent);
		return;
	}
	
	/**
	 * Sets the current owner of the territory
	 * @param inId The new owner
	 */
	void setOwner(Integer inId)
	{
		ownerPlayerId = inId;
	}
	
	/** 
	 * @return Returns the current owner ID
	 */
	public Integer getOwner()
	{
		return ownerPlayerId;
	}
	
	/**
	 * @return This function returns the territory information as a single string
	 */
	public String toString() 
	{
		String info = "Country Name: " + name + "\n\n"
					+ "Continent Name : " + continent + "\n\n"
					+ "Coordinates: " + "(" + X + "," + Y + ")\n\n"
					+ "Number of Armies:" + stationedArmies + "\n\n"
					+ "Adjacent Countries:\n";
		
		for(String adjTerr: adjacentCountries) 
		{
			info += adjTerr + "\n";
		}
		
		return info.substring(0,info.length());
	}
}