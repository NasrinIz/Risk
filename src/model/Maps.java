package src.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import src.view.ErrorInfoView;

/**
 * This class is used to instantiate maps, which reads the map files into memory.
 * And then, it validates every map.
 *
 * @author Team20
 */
public class Maps implements Serializable{
	private static final long serialVersionUID = -4600316782346456820L;
	/*************************/
    private Integer MAP_INIT_SIZE = 2;
    private Integer MAP_CAPACITY_INCREMENT = 2;

    private Integer TERRITORY_INIT_SIZE = 2;
    private Integer TERRITORY_CAPACITY_INCREMENT = 2;

    /**
     * This data member is used to store the map path
     */
    public String mapLocation;
    private String mapName;
    /* Basic Map Properties */
    private String mapImage = ""; // with extension
    private String mapWrap = ""; // yes or no
    private String mapScroll = ""; // horizontal, vertical, none

    private String mapAuthor = null; // first and last name
    private String mapWarning = null; // yes or no

    /**
     * This is the error string
     */
    public String errorStr = null;

    /* Map Continents and Army Bonuses for each */
    private Map<String, Continent> dictContinents = new HashMap<String, Continent>(MAP_INIT_SIZE,
            MAP_CAPACITY_INCREMENT);
    private Map<String, Territory> dictTerritory = new HashMap<String, Territory>(TERRITORY_INIT_SIZE,
            TERRITORY_CAPACITY_INCREMENT);

    GenericFunctions genericFunctionsObj = new GenericFunctions();
    private ErrorInfoView errorInfoView;

    /**
     * This is the constructor to Maps class, and is used to set the map location.
     *
     * @param inMapLocation The map location on secondary drive
     * @param Mode          The mode indicates, whether the map is for editor or new game.
     */
    public Maps(String inMapLocation, Integer Mode) {
        mapLocation = inMapLocation;
    }

    /**
     * This function is used to set the map name.
     *
     * @param inMapName This is the map name.
     */
    public void setMapName(String inMapName) {
        mapName = inMapName;
    }

    /**
     * This function is used to retrieve the map name.
     *
     * @return mapName The current name of the map.
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * This function returns the number of continents in map
     *
     * @return Number of continents in map
     */
    public Integer getNumContinents() {
        return dictContinents.size();
    }

    /**
     * @return This function returns the number of territories in map.
     */
    public Integer getNumTerritories() {
        return dictTerritory.size();
    }

    /**
     * This function sets the continents of this map with the input param.
     *
     * @param dictContinents The new hashmap of continents for map,
     */
    public void setDictContinents(Map<String, Continent> dictContinents) {
        this.dictContinents = dictContinents;
    }

    /**
     * This function sets the territories of this map with the input param.
     *
     * @param dictTerritory The new hashmap of territories for map.
     */
    public void setDictTerritory(Map<String, Territory> dictTerritory) {
        this.dictTerritory = dictTerritory;
    }

    /**
     * @return The list and objects of current continents in map
     */
    public Map<String, Continent> getDictContinents() {
        return dictContinents;
    }

    /**
     * @return The list and objects of current territories in map
     */
    public Map<String, Territory> getDictTerritory() {
        return dictTerritory;
    }

    /**
     * @return Returns the name of map author
     */
    public String getMapAuthor() {
        return mapAuthor;
    }

    /**
     * This function is used to set the name of author of map
     *
     * @param mapAuthor The new map author.
     */
    public void setMapAuthor(String mapAuthor) {
        this.mapAuthor = mapAuthor;
    }

    /**
     * This function is used to check whether all the properties of map object are filled or not.
     *
     * @param inVal The string containing map properties.
     * @return rtVal Indicates whether all the properties were found or not.
     */
    private Integer checkPropertyMap(String inVal) {
        Integer rtVal = 0; // 0 = Success, -1 = Fail

        if (inVal.contains("author=")) {
            mapAuthor = genericFunctionsObj.genStringGetValueAfterEquals(inVal);
            if (mapAuthor != null)
                return rtVal;
        }

        if (inVal.contains("image=")) {
            return rtVal;
        }

        if (inVal.contains("wrap=")) {
            mapWrap = genericFunctionsObj.genStringGetValueAfterEquals(inVal);
            if (mapWrap != null)
                return rtVal;
        }
        if (inVal.contains("scroll=")) {
            mapScroll = genericFunctionsObj.genStringGetValueAfterEquals(inVal);
            if (mapScroll != null)
                return rtVal;
        }
        if (inVal.contains("warn=")) {
            mapWarning = genericFunctionsObj.genStringGetValueAfterEquals(inVal);
            if (mapWarning != null)
                return rtVal;
        }
        rtVal = -1;
        return rtVal;
    }

    /**
     * This function is used to read the map, and store it into the memory using data structures.
     *
     * @return The error message if any, while reading the map.
     */
    public String readMap() {
        String tmpExtChecker = mapLocation.substring(mapLocation.length() - 4, mapLocation.length());
        String mapTxtLoc = null;
        if (tmpExtChecker.equals(".map") == false) {
            mapTxtLoc = String.format("Resources//Maps//%s.map", mapLocation);
            String mapImgLoc = String.format("Resources//Maps//%s.bmp", mapLocation);
            mapLocation = mapTxtLoc;
        } else {
            mapTxtLoc = mapLocation;
        }

        BufferedReader mapData = genericFunctionsObj.genOpenFileToBufferedReader(mapTxtLoc);
        String line;
        String[] tmpArr;
        Integer count = new Integer(0);

		/* Property Flags 	- 1 = Found, 2 = Completed, 0 = Not Found */
        Integer mapProperty = new Integer(0);
        Integer continentsProperty = new Integer(0);
        Integer territoriesProperty = new Integer(0);

        try {
            while ((line = mapData.readLine()) != null) {
            	line = line.toLowerCase();
                if ((line.equalsIgnoreCase("[MAP]"))) {
                    if (continentsProperty == 1) {
                        continentsProperty = 2;
                    }
                    if (territoriesProperty == 1) {
                        territoriesProperty = 2;
                    }
                    mapProperty = 1;
                    continue;
                }

                if ((line.equalsIgnoreCase("[Continents]"))) {
                    if ((mapProperty != 2) && (mapProperty != 0)) {
                        break;
                    }
                    if (territoriesProperty == 1) {
                        territoriesProperty = 2;
                    }
                    continentsProperty = 1;
                    continue;
                }

                if ((line.equalsIgnoreCase("[Territories]"))) {
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
                    tmpArr = genericFunctionsObj.genStringGetSplitArrayEquals(line);
                    if (tmpArr.length < 2) {
                        continue;
                    }
                    Continent tmpContinentObj = new Continent(tmpArr[0], genericFunctionsObj.genStrToInt(tmpArr[1]));
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
                //return "Map is missing [MAP] section";
            }
            if (continentsProperty != 2) {
                //return "Map is missing [Continents] section";
            }
            if (territoriesProperty != 2) {
                //return "Map is missing [Territories] section";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        mapFillContinentTerritories();

        return "true";
    }

    /**
     * This function is used to fill the territories for each continent in the map object
     */
    private void mapFillContinentTerritories() {
        for (String continent : this.getDictContinents().keySet()) {
            String continentName = this.getDictContinents().get(continent).getName();
            for (String territory : this.getDictTerritory().keySet()) {
                String territoryContinentName = this.getDictTerritory().get(territory).getContinent();
                if (territoryContinentName.equals(continentName) == true) {
                    this.getDictContinents().get(continent).addToTerritoryList(this.getDictTerritory().get(territory));
                }
            }
        }
    }

    /**
     * This function is used to validate the map adjacency.
     *
     * @return Any error string, if any, while validating the map adjacency.
     */
    public String validateMap() {
        if ((mapAuthor == null) && (mapWarning == null)) {
            //  return "The map author or map warning property is not valid";
        }

        for (String territory : this.dictTerritory.keySet()) {
            Territory tmpTerritoryObj = this.dictTerritory.get(territory);
            if (tmpTerritoryObj.getName() == null) {
                return "\nThe territory %s does not have a name" + territory;
            }
            if (tmpTerritoryObj.getContinent() == null) {
                return "\nThe territory %s does not have a continent" + territory;
            }
            if (tmpTerritoryObj.getX() == 0) {
                return "\nThe territory %s does not have defined X Axis" + territory;
            } else if (tmpTerritoryObj.getY() == 0) {
                return "\nThe territory %s does not have defined Y Axis" + territory;
            }

            if (dictContinents.get((tmpTerritoryObj.getContinent())) == null) {
                return "\nThe territory %s is in continent %s. This continent either does not exist or isnt defined properly" + territory + tmpTerritoryObj.getContinent();
            }

            /*
            ArrayList<String> adjacent = tmpTerritoryObj.getAdjacentCountries();
            for (int i = 0; i < adjacent.size(); i++) {
                Integer matchFlag = 0;
                String adjacentCountry = adjacent.get(i);
                if (this.dictTerritory.get(adjacentCountry) != null) {
                    ArrayList<String> checkList = (this.dictTerritory.get(adjacentCountry)).getAdjacentCountries();
                    for (int j = 0; j < checkList.size(); j++) {
                        if (this.dictTerritory.get(territory).getName().equals(checkList.get(j))) {
                            matchFlag = 1;
                            break;
                        }
                    }
                    if (matchFlag == 0) {
                        return "\nPlease check adjacency for territory %s" + adjacentCountry;
                    }
                } else {
                    return "\nPlease mention adjacent territories for %s" + adjacentCountry;
                }
            }
            */

        }

        if(ContinentConnectivity() != "true") {
        	return "Continent Connection Error";
        }
        
        if (mapConnectivity() != "true") {
            return "The map is not a connected graph";
        }

        return "true";
    }

    /**
     * This function checks whether the continent is a connected graph or not.
     *
     * @return Any error message, while checking for continent connectivity.
     */
    public String ContinentConnectivity() {
        for(String continent : this.dictContinents.keySet()) {
        	HashMap<Territory, Integer> TerritoryVisitFlags = new HashMap<Territory, Integer>();
        	Continent obj = this.dictContinents.get(continent);
        	ArrayList<Territory> territories = obj.getTerritories();
        	Territory tmp = null;
        	for(int ctr = 0; ctr < territories.size(); ctr++) {
        		Territory tmpTerritoryObj = territories.get(ctr);
        		if(tmp == null) {
        			tmp = tmpTerritoryObj;
        		}
        		if (tmpTerritoryObj.getName() != null) {
                    TerritoryVisitFlags.put(tmpTerritoryObj, 0);
                }
        	}
        	
        	TerritoryVisitFlags = validateContinentConn(TerritoryVisitFlags, tmp, continent);
        	for(int ctr = 0; ctr < territories.size(); ctr++) {
        		if (TerritoryVisitFlags.get(territories.get(ctr)) != 1) {
                    return "All continents are not connected in themselves";
                }
            }
        }

        
        return "true";
    }

    /**
     * The recursive function to travel across each territory in continent.
     *
     * @param TerritoryVisitFlags The flag indicating whether the territory was previously visited or not
     * @param territory           The object of territory being checked.
     * @return The current territory visited.
     */
    private HashMap<Territory, Integer> validateContinentConn(HashMap<Territory, Integer> TerritoryVisitFlags, 
    		Territory territory, String continentName) {
        try {
            if ((TerritoryVisitFlags == null) || (territory == null) || (TerritoryVisitFlags.size() == 0)) {
                return null;
            }

            int val = TerritoryVisitFlags.get(territory);

            if (val == 0) {
                TerritoryVisitFlags.put(territory, 1);
            }

            for (String adjTerritory : territory.getAdjacentCountries()) {
            	if(dictTerritory.get(adjTerritory).getContinent().equals(continentName) == true)
            	{
	                if (TerritoryVisitFlags.get(dictTerritory.get(adjTerritory)) == 0) {
	                	TerritoryVisitFlags = validateContinentConn(TerritoryVisitFlags, 
	                			dictTerritory.get(adjTerritory), continentName);
	                }
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(TerritoryVisitFlags);
            System.out.println(territory);
        }
        return TerritoryVisitFlags;
    }
    
    
    /**
     * This function checks whether the map is a connected graph or not.
     *
     * @return Any error message, while checking for map connectivity.
     */
    public String mapConnectivity() {
        HashMap<Territory, Integer> TerritoryVisitFlags = new HashMap<Territory, Integer>();
        Territory tmp = null;
        for (String territory : this.dictTerritory.keySet()) {
            Territory tmpTerritoryObj = this.dictTerritory.get(territory);
            if (tmp == null) {
                tmp = tmpTerritoryObj;
            }
            if (tmpTerritoryObj.getName() != null) {
                TerritoryVisitFlags.put(tmpTerritoryObj, 0);
            }
        }

        TerritoryVisitFlags = validateConnectivity(TerritoryVisitFlags, tmp);

        for (String territory : this.dictTerritory.keySet()) {
            if (TerritoryVisitFlags.get(this.dictTerritory.get(territory)) != 1) {
                return "The map is not a connected graph";
            }
        }


        return "true";
    }

    /**
     * The recursive function to travel across each territory in map.
     *
     * @param TerritoryVisitFlags The flag indicating whether the territory was previously visited or not
     * @param territory           The object of territory being checked.
     * @return The current territory visited.
     */
    private HashMap<Territory, Integer> validateConnectivity(HashMap<Territory, Integer> TerritoryVisitFlags, Territory territory) {
        try {
            if ((TerritoryVisitFlags == null) || (territory == null) || (TerritoryVisitFlags.size() == 0)) {
                return null;
            }

            int val = TerritoryVisitFlags.get(territory);

            if (val == 0) {
                TerritoryVisitFlags.put(territory, 1);
            }

            for (String adjTerritory : territory.getAdjacentCountries()) {
                if (TerritoryVisitFlags.get(dictTerritory.get(adjTerritory)) == 0) {
                    TerritoryVisitFlags = validateConnectivity(TerritoryVisitFlags, dictTerritory.get(adjTerritory));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(TerritoryVisitFlags);
            System.out.println(territory);
        }
        return TerritoryVisitFlags;
    }

    /**
     * This function is used to add a continent to hashmap of existing continents.
     *
     * @param tmpContinent The information of new continents to be added.
     */
    public void addContinent(List<Continent> tmpContinent) {
        for (int i = 0; i < tmpContinent.size(); i++) {
            this.getDictContinents().put(tmpContinent.get(i).getName(), tmpContinent.get(i));
        }
    }

    /**
     * This functions is used to add new territories to hashmap of existing territories.
     *
     * @param tmpTerritory The information of new territories to be added.
     */
    public void addCountry(List<Territory> tmpTerritory) {
        for (int i = 0; i < tmpTerritory.size(); i++) {
            this.getDictTerritory().put(tmpTerritory.get(i).getName(), tmpTerritory.get(i));
        }
    }

    /**
     * This function is used to delete a continent from existing continents.
     *
     * @param tmpContinent The name of continent to be deleted.
     */
    public void deleteContinent(String tmpContinent) {
        Integer rt = -1;

        if (this.getDictContinents().containsKey(tmpContinent) == false) {
            System.out.println("No such continent exists");
        }
        List<Territory> territories = this.getDictContinents().get(tmpContinent).getTerritories();

        for (int i = 0; i < territories.size(); i++) {
        	for(String terr : this.dictTerritory.keySet()) {
        		for(int ctr = 0; ctr < this.dictTerritory.get(terr).getAdjacentCountries().size(); ctr++) {
        			if(this.dictTerritory.get(terr).getAdjacentCountries().get(ctr).equals(territories.get(i).getName())) {
        				this.dictTerritory.get(terr).getAdjacentCountries().remove(ctr);
        			}
        		}
        	}
            this.dictTerritory.remove(territories.get(i).getName());
        }

        this.getDictContinents().remove(tmpContinent);
    }

    /**
     * This function is used to delete a territory from existing territories.
     *
     * @param tmpTerritory The name of territory to be deleted.
     */
    public void deleteTerritory(Territory tmpTerritory) {
		/* From Continent */
        Iterator ite = this.getDictContinents().entrySet().iterator();
        Integer foundFlag = -1;

        while (ite.hasNext()) {
            Map.Entry pair = (Map.Entry) ite.next();
            List<Territory> territoriesInContinent = this.getDictContinents().get(pair.getKey()).getTerritories();
            for (int i = 0; i < territoriesInContinent.size(); i++) {
                if (territoriesInContinent.get(i).getName().equals(tmpTerritory.getName())) {
                    territoriesInContinent.remove(i);
                    foundFlag = 1;
                    break;
                }
            }
        }
		
		/* From Territory */
        ite = this.getDictTerritory().entrySet().iterator();
        while (ite.hasNext()) {
            Map.Entry pair = (Map.Entry) ite.next();
            if (pair.getKey().equals(tmpTerritory.getName())) {
                this.getDictTerritory().remove(tmpTerritory.getName());
                break;
            }
        }

        System.out.println("Territory Deleted");
    }

    /**
     * This function is used to write the edited map to a file.
     *
     * @param inPath The new path of map file.
     */
    public void writeMapToFile(String inPath) {
        File logFile = new File(inPath);
        PrintWriter out = null;
        try {
            logFile.createNewFile();
            out = new PrintWriter(inPath);

            out.printf("\n[Continents]\n");
            Iterator ite = this.getDictContinents().entrySet().iterator();
            while (ite.hasNext()) {
                Map.Entry pair = (Map.Entry) ite.next();
                out.printf("%s = %d\n", this.getDictContinents().get(pair.getKey()).getName(),
                        this.getDictContinents().get(pair.getKey()).getArmyReward());

            }

            out.printf("\n[Territories]\n");

            ite = this.getDictTerritory().entrySet().iterator();
            while (ite.hasNext()) {
                Map.Entry pair = (Map.Entry) ite.next();
                out.printf("%s,%d,%d,%s", this.getDictTerritory().get(pair.getKey()).getName(),
                        this.getDictTerritory().get(pair.getKey()).getX(),
                        this.getDictTerritory().get(pair.getKey()).getY(),
                        this.getDictTerritory().get(pair.getKey()).getContinent());
                for (int i = 0; i < this.getDictTerritory().get(pair.getKey()).getAdjacentCountries().size(); i++) {
                    out.printf(",%s", this.getDictTerritory().get(pair.getKey()).getAdjacentCountries().get(i));
                }

                out.printf("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}