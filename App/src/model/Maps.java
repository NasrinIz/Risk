package model;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;


public class Maps {
	/*************************/
	Integer MAPINITSIZE = 2;
	Integer MAPCAPACITYINCREMENT = 2;
	
	Integer TERRITORYINITSIZE = 2;
	Integer TERRITORYCAPACITYINCREMENT = 2;
	/*************************/
	
	String mapLocation = "";

	/* Basic Map Properties */
	String mapImage = "";			// with extension
	String mapWrap = "";			// yes or no
	String mapScroll = "";			// horizontal, vertical, none
	String mapAuthor = "";				// first and last name
	String mapWarning = "";			// yes or no
	
	/* Map Continents and Army Bonuses for each */
	Map<String, Integer> dictContinents = new HashMap<String, Integer>(MAPINITSIZE, MAPCAPACITYINCREMENT);
	
	/* Map Territories */
	//Vector<Territory> territories = new Vector<Territory>(TERRITORYINITSIZE, TERRITORYCAPACITYINCREMENT);
	public Map<String, Territory> dictTerritory = new HashMap<String, Territory>(TERRITORYINITSIZE, TERRITORYCAPACITYINCREMENT);
	
	GenFun genFunObj = new GenFun();
	public Maps(String inMapLocation) {
		mapLocation = inMapLocation;
		readMap();
	}
	Integer checkPropertyMap(String inVal) {
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
	
	void readMap() {
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
					 dictContinents.put(tmpArr[0], genFunObj.genStrToInt(tmpArr[1]));
				 }
				 
				 if(territoriesProperty == 1) {
					 if(line.equalsIgnoreCase("")) {
						 continue;
					 }
					 Territory obj = new Territory(line);
					 dictTerritory.put(obj.Name, obj);
				 }				
			 }
			 
			 if(continentsProperty == 2) {
				 territoriesProperty = 2;
			 }
			 if(territoriesProperty == 2) {
				 continentsProperty = 2;
			 }
			 
			 if((mapProperty != 2) || (continentsProperty != 2) || (territoriesProperty != 2)) {
				 System.out.println("Please check map");
			 }
		 }
		 catch (IOException e) {
			 e.printStackTrace();
		 }
	}
}
