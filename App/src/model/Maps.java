package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

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

	private String mapLocation;
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

	/**
	 * @param inMapLocation
	 */
	public Maps(String inMapLocation) {
		mapLocation = inMapLocation;
		if (readMap() != 0) {
			// TBD error
		}
		if (validateMap() != 0) {
			// TBD error
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
			return rtVal;
		}
		if (inVal.contains("image=")) {
			mapImage = genFunObj.genStringGetValueAfterEquals(inVal);
			return rtVal;
		}
		if (inVal.contains("wrap=")) {
			mapWrap = genFunObj.genStringGetValueAfterEquals(inVal);
			return rtVal;
		}
		if (inVal.contains("scroll=")) {
			mapScroll = genFunObj.genStringGetValueAfterEquals(inVal);
			return rtVal;
		}
		if (inVal.contains("warn=")) {
			mapWarning = genFunObj.genStringGetValueAfterEquals(inVal);
			return rtVal;
		}
		rtVal = -1;
		return rtVal;
	}

	public Integer readMap() {
		String mapTxtLoc = String.format("Resources//Maps//%s.map", mapLocation);
		String mapImgLoc = String.format("Resources//Maps//%s.bmp", mapLocation);
		
		BufferedReader mapData = genFunObj.genOpenFileToBufferedReader(mapTxtLoc);
		String line;
		String[] tmpArr;
		Integer count = new Integer(0);

		/* Property Flags - 1 = Found, 2 = Completed, 0 = Not Found */
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
					Continent tmpContinentObj = new Continent(tmpArr[0], null, genFunObj.genStrToInt(tmpArr[1]));
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
				System.out.println("Map is missing [MAP] section");
				return -1;
			}
			if (continentsProperty != 2) {
				System.out.println("Map is missing [Continents] section");
				return -1;
			}
			if (territoriesProperty != 2) {
				System.out.println("Map is missing [Territories] section");
				return -1;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public Integer validateMap() {
		if ((mapAuthor == null) && (mapWarning == null)) {
			return -1;
		}

		for (String territory : this.dictTerritory.keySet()) {

		}

		return 0;
	}


}
