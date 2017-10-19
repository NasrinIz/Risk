package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import view.ErrorInfoView;
import view.MainWindow;

/**
 * @author Team20
 *
 */
public class Maps {
	/*************************/
	private Integer MAP_INIT_SIZE = 2;
	private Integer MAP_CAPACITY_INCREMENT = 2;

	private Integer TERRITORY_INIT_SIZE = 2;
	private Integer TERRITORY_CAPACITY_INCREMENT = 2;
	/*************************/

	public String mapLocation;
	private String mapName;
	/* Basic Map Properties */
	/* Remove */
	private String mapImage = ""; // with extension
	private String mapWrap = ""; // yes or no
	private String mapScroll = ""; // horizontal, vertical, none
	/* */

	private String mapAuthor = null; // first and last name
	private String mapWarning = null; // yes or no

	/* Map Continents and Army Bonuses for each */
	// Map<String, Integer> dictContinents = new HashMap<String,
	// Integer>(MAP_INIT_SIZE, MAP_CAPACITY_INCREMENT);
	private Map<String, Continent> dictContinents = new HashMap<String, Continent>(MAP_INIT_SIZE,
			MAP_CAPACITY_INCREMENT);
	private Map<String, Territory> dictTerritory = new HashMap<String, Territory>(TERRITORY_INIT_SIZE,
			TERRITORY_CAPACITY_INCREMENT);

	GenFun genFunObj = new GenFun();
	private ErrorInfoView errorInfoView;
	/**
	 * @param inMapLocation
	 */
	public Maps(String inMapLocation, Integer Mode) {
		mapLocation = inMapLocation;
		
		if(Mode == 0)	// non editor mode
		{
			errorInfoView = new ErrorInfoView();
			String readMapMsg = readMap();
			String validateMapMsg = validateMap();
			
			if (readMapMsg != "true") {
				// TBD error
				System.out.println(readMapMsg);
				errorInfoView.showErrorInfo(readMapMsg);
			}
			if (validateMapMsg != "true") {
				// TBD error
				System.out.println(validateMapMsg);
				errorInfoView.showErrorInfo(validateMapMsg);
			}
		}
		else if(Mode == 1) // Editor Mode
		{
			// do nothing
		}
	}

	public void setMapName(String inMapName) {
		mapName = inMapName;
	}

	/**
	 * @return mapName
	 */
	public String getMapName() {
		return mapName;
	}

	/**
	 * @return size of dictContinents
	 */
	public Integer getNumContinents() {
		return dictContinents.size();
	}

	/**
	 * @return size of dictContinents
	 */
	public Integer getNumTerritories() {
		return dictTerritory.size();
	}

	public void setDictContinents(Map<String, Continent> dictContinents) {
		this.dictContinents = dictContinents;
	}

	public void setDictTerritory(Map<String, Territory> dictTerritory) {
		this.dictTerritory = dictTerritory;
	}

	/**
	 * @return the dictContinents
	 */
	public Map<String, Continent> getDictContinents() {
		return dictContinents;
	}

	/**
	 * @return the dictTerritory
	 */
	public Map<String, Territory> getDictTerritory() {
		return dictTerritory;
	}

	/**
	 * @return the mapAuthor
	 */
	public String getMapAuthor() {
		return mapAuthor;
	}

	/**
	 * @param mapAuthor
	 *            the mapAuthor to set
	 */
	public void setMapAuthor(String mapAuthor) {
		this.mapAuthor = mapAuthor;
	}

	/**
	 * @param inVal
	 * @return rtVal
	 */
	private Integer checkPropertyMap(String inVal) {
		Integer rtVal = 0; // 0 = Success, -1 = Fail

		if (inVal.contains("author=")) {
			mapAuthor = genFunObj.genStringGetValueAfterEquals(inVal);
			if(mapAuthor != null)
				return rtVal;
		}
		
		if (inVal.contains("image=")) {
			/* vj, we dont load images */
			//mapImage = genFunObj.genStringGetValueAfterEquals(inVal);
			//if(mapImage != null)
				return rtVal;
		}
		
		if (inVal.contains("wrap=")) {
			mapWrap = genFunObj.genStringGetValueAfterEquals(inVal);
			if(mapWrap != null)
				return rtVal;
		}
		if (inVal.contains("scroll=")) {
			mapScroll = genFunObj.genStringGetValueAfterEquals(inVal);
			if(mapScroll != null)
				return rtVal;
		}
		if (inVal.contains("warn=")) {
			mapWarning = genFunObj.genStringGetValueAfterEquals(inVal);
			if(mapWarning != null)
				return rtVal;
		}
		rtVal = -1;
		return rtVal;
	}

	public String readMap() {
		String mapTxtLoc = String.format("Resources//Maps//%s.map", mapLocation);
		String mapImgLoc = String.format("Resources//Maps//%s.bmp", mapLocation);

		BufferedReader mapData = genFunObj.genOpenFileToBufferedReader(mapTxtLoc);
		String line;
		String[] tmpArr;
		Integer count = new Integer(0);

		/* Property Flags 	- 1 = Found, 2 = Completed, 0 = Not Found */
		Integer mapProperty = new Integer(0);
		Integer continentsProperty = new Integer(0);
		Integer territoriesProperty = new Integer(0);

		try {
			while ((line = mapData.readLine()) != null) {

				if ((line.equalsIgnoreCase("[MAP]") == true)) {
					if (continentsProperty == 1) {
						continentsProperty = 2;
					}
					if (territoriesProperty == 1) {
						territoriesProperty = 2;
					}
					mapProperty = 1;
					continue;
				}

				if ((line.equalsIgnoreCase("[Continents]") == true)) {
					if ((mapProperty != 2) && (mapProperty != 0)) {
						break;
					}
					if (territoriesProperty == 1) {
						territoriesProperty = 2;
					}
					continentsProperty = 1;
					continue;
				}

				if ((line.equalsIgnoreCase("[Territories]") == true)) {
					if ((mapProperty != 2) && (mapProperty != 0)) {
						break;
					}
					if (continentsProperty == 1) {
						continentsProperty = 2;
					}
					territoriesProperty = 1;
					continue;
				}

				if (mapProperty == 1) {
					if (checkPropertyMap(line) == 0) {
						count++;
					} else {
						if (count < 5)
							continue;
						else
							mapProperty = 2;
					}
				}

				if (continentsProperty == 1) {
					tmpArr = genFunObj.genStringGetSplitArrayEquals(line);
					if (tmpArr.length < 2) {
						continue;
					}
					Continent tmpContinentObj = new Continent(tmpArr[0], genFunObj.genStrToInt(tmpArr[1]));
					dictContinents.put(tmpArr[0], tmpContinentObj);
				}

				if (territoriesProperty == 1) {
					if (line.equalsIgnoreCase("")) {
						continue;
					}
					Territory obj = new Territory(line);
					String terrName = obj.getName();
					dictTerritory.put(terrName, obj);
				}
			}

			if (continentsProperty == 2) {
				territoriesProperty = 2;
			}
			if (territoriesProperty == 2) {
				continentsProperty = 2;
			}

			if (mapProperty != 2) {
				return "Map is missing [MAP] section";
			}
			if (continentsProperty != 2) {
				return "Map is missing [Continents] section";
			}
			if (territoriesProperty != 2) {
				return "Map is missing [Territories] section";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "true";
	}

	public String validateMap() {
		if ((mapAuthor == null) && (mapWarning == null)) {
		      return "The map author or map warning property is not valid"; 
		}

		for (String territory : this.dictTerritory.keySet()) 
		{
		    Territory tmpTerritoryObj = this.dictTerritory.get(territory); 
		    if(tmpTerritoryObj.getName() == null) 
		    { 
		    	return "\nThe territory %s does not have a name" + territory; 
		    } 
		    if(tmpTerritoryObj.getContinent() == null) 
		    { 
			    return "\nThe territory %s does not have a continent" +  territory; 
		    } 
		    if(tmpTerritoryObj.getX() == 0) 
		    { 
			    return "\nThe territory %s does not have defined X Axis" + territory; 
		    } 
		    else if(tmpTerritoryObj.getY() == 0) 
		    { 
			    return "\nThe territory %s does not have defined Y Axis" + territory; 
		    } 
		   
		    if(dictContinents.get((tmpTerritoryObj.getContinent())) == null) 
		    { 
			    return "\nThe territory %s is in continent %s. This continent either does not exist or isnt defined properly" + territory + tmpTerritoryObj.getContinent();
	 	    } 
		   
		    ArrayList<String> adjacent = tmpTerritoryObj.getAdjacentCountries(); 
		    for(int i = 0; i<adjacent.size(); i++) 
		    { 
			    Integer matchFlag = 0; 
			    String adjacentCountry = adjacent.get(i); 
			    if(this.dictTerritory.get(adjacentCountry) != null) 
			    { 
			    	ArrayList<String> checkList = (this.dictTerritory.get(adjacentCountry)).getAdjacentCountries(); 
			        for(int j = 0; j<checkList.size(); j++) 
			        { 
				        if(this.dictTerritory.get(territory).getName().equals(checkList.get(j))) 
				        { 
					        matchFlag = 1; 
					        break; 
				        } 
			        } 
			        if(matchFlag == 0) 
			        { 
				       return "\nPlease check adjacency for territory %s" + adjacentCountry;  
			        } 
			    } 
				else 
				{ 
				    return "\nPlease mention adjacent territories for %s" + adjacentCountry; 
				} 
			} 
		
		}
		
		if(mapConnectivity() != "true")
		{
			return "The map is not a connected graph";
		}

		return "true";
	}
	
	public String mapConnectivity()
	{
		HashMap<Territory, Integer> TerritoryVisitFlags = new HashMap<Territory, Integer>();
		Territory tmp = null;
		for(String territory : this.dictTerritory.keySet())
		{
			Territory tmpTerritoryObj = this.dictTerritory.get(territory);
			if(tmp == null)
			{
				tmp = tmpTerritoryObj;
			}
		    if(tmpTerritoryObj.getName() != null)
		    {
		    	TerritoryVisitFlags.put(tmpTerritoryObj, 0);
		    }
		}
		
		TerritoryVisitFlags = validateConnectivity(TerritoryVisitFlags, tmp);
		
		for(String territory : this.dictTerritory.keySet())
		{
			if(TerritoryVisitFlags.get(this.dictTerritory.get(territory)) != 1)
			{
				return "The map is not a connected graph";
			}
		}
		
		
		return "true";
	}

	private HashMap<Territory, Integer> validateConnectivity(HashMap<Territory, Integer> TerritoryVisitFlags, Territory territory)
	{
		try
		{
			if((TerritoryVisitFlags == null) || (territory == null) || (TerritoryVisitFlags.size() == 0))
			{
				return null;
			}
			
			int val = TerritoryVisitFlags.get(territory); 
			
			if(val == 0)
			{
				TerritoryVisitFlags.put(territory, 1);
			}
			
			for(String adjTerritory : territory.getAdjacentCountries())
			{
				if(TerritoryVisitFlags.get(dictTerritory.get(adjTerritory)) == 0)
				{
					TerritoryVisitFlags = validateConnectivity(TerritoryVisitFlags, dictTerritory.get(adjTerritory));
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(TerritoryVisitFlags);
			System.out.println(territory);
			//System.out.println(TerritoryVisitFlags.get(territory));
		}
		return TerritoryVisitFlags;
	}
	
	public void addContinent(List<Continent> tmpContinent)
	{
		for(int i = 0; i < tmpContinent.size(); i++)
		{
			this.getDictContinents().put(tmpContinent.get(i).getName(), tmpContinent.get(i));
		}
	}
	
	public void addCountry(List<Territory> tmpTerritory)
	{
		for(int i = 0; i < tmpTerritory.size(); i++)
		{
			this.getDictTerritory().put(tmpTerritory.get(i).getName(), tmpTerritory.get(i));
		}
	}
	
	public void deleteContinent(String tmpContinent)
	{
		Integer rt = -1;
		
		if(this.getDictContinents().containsKey(tmpContinent) == false)
		{
			System.out.println("No such continent exists");
		}
		List<Territory> territories = this.getDictContinents().get(tmpContinent).getTerritories();
		
		for(int i = 0; i < territories.size(); i++)
		{
			Iterator ite = this.getDictTerritory().entrySet().iterator();
			while(ite.hasNext())
			{
				Map.Entry pair = (Map.Entry)ite.next();
				if(this.getDictTerritory().get(pair.getKey()).getName().equals(territories.get(i).getName()))
				{
					this.getDictTerritory().remove(pair.getKey());
				}
			}
		}
		
		this.getDictContinents().remove(tmpContinent);
	}
	
	public void deleteTerritory(Territory tmpTerritory)
	{
		/* From Continent */
		Iterator ite = this.getDictContinents().entrySet().iterator();
		Integer foundFlag = -1;
		
		while(ite.hasNext())
		{
			Map.Entry pair = (Map.Entry)ite.next();
			List<Territory> territoriesInContinent = this.getDictContinents().get(pair.getKey()).getTerritories();
			for(int i = 0; i < territoriesInContinent.size(); i++)
			{
				if(territoriesInContinent.get(i).getName().equals(tmpTerritory.getName()))
				{
					territoriesInContinent.remove(i);
					foundFlag = 1;
					break;
				}
			}
		}
		
		/* From Territory */
		ite = this.getDictTerritory().entrySet().iterator();
		while(ite.hasNext())
		{
			Map.Entry pair = (Map.Entry)ite.next();
			if(pair.getValue().equals(tmpTerritory.getName()))
			{
				List<String> adjacent = this.getDictTerritory().get(pair.getKey()).getAdjacentCountries();
				for(int i = 0; i < adjacent.size(); i++)
				{
					List<String> adjacentOfAdjacent = this.getDictTerritory().get(adjacent.get(i)).getAdjacentCountries();
					for(int j = 0; j < adjacentOfAdjacent.size(); j++)
					{
						if(adjacentOfAdjacent.get(j).equals(tmpTerritory.getName()))
						{
							adjacentOfAdjacent.remove(j);
							break;
						}
					}
				}
				this.getDictTerritory().remove(tmpTerritory.getName());
				break;
			}
		}
		
		System.out.println("Territory Deleted");
	}
	
	private void writeMapToFile(String inPath)
	{
		File logFile = new File(inPath);
		PrintWriter out = null;
		try 
		{
			if(logFile.exists() && !logFile.isDirectory())
			{
				out = new PrintWriter(new FileOutputStream(new File(inPath), true));
			}
			else
			{
				out = new PrintWriter(inPath);
			}

			out.printf("\n[Continents]\n");
			Iterator ite = this.getDictContinents().entrySet().iterator();
			while(ite.hasNext())
			{
				Map.Entry pair = (Map.Entry)ite.next();
				out.printf("%s = %d\n", this.getDictContinents().get(pair.getKey()).getName(),
						this.getDictContinents().get(pair.getKey()).getArmyReward());
				
			}
			
			out.printf("\n[Territories]\n");
			
			ite = this.getDictTerritory().entrySet().iterator();
			while(ite.hasNext())
			{
				Map.Entry pair = (Map.Entry)ite.next();
				out.printf("%s,%d,%d,%s", this.getDictTerritory().get(pair.getKey()).getName(),
						this.getDictTerritory().get(pair.getKey()).getX(),
						this.getDictTerritory().get(pair.getKey()).getY(),
						this.getDictTerritory().get(pair.getKey()).getContinent());
				for(int i = 0; i < this.getDictTerritory().get(pair.getKey()).getAdjacentCountries().size(); i++)
				{
					out.printf("%s", this.getDictTerritory().get(pair.getKey()).getAdjacentCountries().get(i));
				}
				
				out.printf("\n");
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if (out != null) 
			{
				out.close();
	        }
		}
	}
}