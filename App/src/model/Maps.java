package model;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;


public class Maps {
	/*************************/
	private Integer MAPINITSIZE = 2;
	private Integer MAPCAPACITYINCREMENT = 2;
	
	private Integer TERRITORYINITSIZE = 2;
	private Integer TERRITORYCAPACITYINCREMENT = 2;
	/*************************/
	
	private String mapLocation = "";
	private String mapName = "";
	/* Basic Map Properties */
	/* Remove */
	private String mapImage = "";			// with extension
	private String mapWrap = "";			// yes or no
	private String mapScroll = "";			// horizontal, vertical, none
	/* */
	
	private String mapAuthor = null;				// first and last name
	private String mapWarning = null;			// yes or no
	
	/* Map Continents and Army Bonuses for each */
	// Map<String, Integer> dictContinents = new HashMap<String, Integer>(MAPINITSIZE, MAPCAPACITYINCREMENT);
	Map<String, Continent> dictContinents = new HashMap<String, Continent>(MAPINITSIZE, MAPCAPACITYINCREMENT);
	public Map<String, Territory> dictTerritory = new HashMap<String, Territory>(TERRITORYINITSIZE, TERRITORYCAPACITYINCREMENT);
	
	GenFun genFunObj = new GenFun();
	
	public Maps(String inMapLocation) {
		mapLocation = inMapLocation;
		if(readMap() != 0)
		{
			// TBD error
		}
		if(validateMap() != 0)
		{
			// TBD error
		}
	}
	
	public void setMapName(String inMapName)
	{
		mapName = inMapName;
	}
	
	public String getMapName()
	{
		return mapName;
	}
	
	public Integer getNumContinents()
	{
		return dictContinents.size();
	}
	
	public Integer getNumTerritories()
	{
		return dictTerritory.size();
	}
	
	private Integer checkPropertyMap(String inVal) {
		Integer rtVal = 0;		// 0 = Success, -1 = Fail

		if(inVal.contains("author=")) {
			mapAuthor = genFunObj.genStringGetValueAfterEquals(inVal);
			return rtVal;
		}
		if(inVal.contains("image=")) {
			mapImage = genFunObj.genStringGetValueAfterEquals(inVal);
			return rtVal;
		}
		if(inVal.contains("wrap=")) {
			mapWrap = genFunObj.genStringGetValueAfterEquals(inVal);
			return rtVal;
		}
		if(inVal.contains("scroll=")) {
			mapScroll = genFunObj.genStringGetValueAfterEquals(inVal);
			return rtVal;
		}
		if(inVal.contains("warn=")) {
			mapWarning = genFunObj.genStringGetValueAfterEquals(inVal);
			return rtVal;
		}
		rtVal = -1;
		return rtVal;
	}
	
	private Integer readMap() {
		 BufferedReader mapData = genFunObj.genOpenFileToBufferedReader(mapLocation);
		 String line;
		 String[] tmpArr;
		 Integer count = new Integer(0);
		 
		 /* Property Flags - 1 = Found, 2 = Completed, 0 = Not Found */
		 Integer mapProperty = new Integer(0);
		 Integer continentsProperty = new Integer(0);
		 Integer territoriesProperty = new Integer(0);
		 
		 try {
			 while((line = mapData.readLine()) != null) {
				 
				 if((line.equalsIgnoreCase("[MAP]") == true)) {					 
					 if(continentsProperty == 1) {
						 continentsProperty = 2;
					 }
					 if(territoriesProperty == 1) {
						 territoriesProperty = 2;
					 }
					 mapProperty = 1;
					 continue;
				 }
				 
				 if((line.equalsIgnoreCase("[Continents]") == true)) {
					 if((mapProperty != 2) && (mapProperty != 0)) {
						 break;
					 }
					 if(territoriesProperty == 1) {
						 territoriesProperty = 2;
					 }
					 continentsProperty = 1;
					 continue;
				 }
				 
				 if((line.equalsIgnoreCase("[Territories]") == true)) {
					 if((mapProperty != 2) && (mapProperty != 0)) {
						 break;
					 }
					 if(continentsProperty == 1) {
						 continentsProperty = 2;
					 }					 
					 territoriesProperty = 1;
					 continue;
				 }
				 
				 if(mapProperty == 1) {
					 if(checkPropertyMap(line) == 0) {
						 count++;
					 }
					 else {
						 if(count < 5)
							 continue;
						 else
							 mapProperty = 2;
					 }
				 }
				 
				 if(continentsProperty == 1) {
					 tmpArr = genFunObj.genStringGetSplitArrayEquals(line);
					 if(tmpArr.length < 2) {
						 continue;
					 }
					 Continent tmpContinentObj = new Continent(tmpArr[0], null, genFunObj.genStrToInt(tmpArr[1]));
					 dictContinents.put(tmpArr[0], tmpContinentObj);
				 }
				 
				 if(territoriesProperty == 1) {
					 if(line.equalsIgnoreCase("")) {
						 continue;
					 }
					 Territory obj = new Territory(line);
					 dictTerritory.put(obj.name, obj);
				 }				
			 }
			 
			 if(continentsProperty == 2) {
				 territoriesProperty = 2;
			 }
			 if(territoriesProperty == 2) {
				 continentsProperty = 2;
			 }
			 
			 if(mapProperty != 2)
			 {
				 System.out.println("Map is missing [MAP] section");
				 return -1;
			 }
			 if(continentsProperty != 2)
			 {
				 System.out.println("Map is missing [Continents] section");
				 return -1;
			 }
			 if(territoriesProperty != 2)
			 {
				 System.out.println("Map is missing [Territories] section");
				 return -1;
			 }
		 }
		 catch (IOException e) {
			 e.printStackTrace();
		 }
		 
		 return 0;
	}
	
	private Integer validateMap()
	{
		if((mapAuthor == null) && (mapWarning == null))
		{
			return -1;
		}
		
		for (String territory : this.dictTerritory.keySet())
		{

		}
		
		return 0;
	}
}
