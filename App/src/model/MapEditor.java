package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Team20
 *
 */
public class MapEditor {
	Maps mapObj = null;
	Integer editChoice = null;
	
	/**
	 * @param inMapLocation
	 */
	public MapEditor(String inMapLocation) {
		createMap(inMapLocation);
	}
	
	/**
	 * @param inMapLocation
	 */
	private void createMap(String inMapLocation)
	{
		mapObj = new Maps(inMapLocation);
	}
	
	private void loadEditor()
	{
		dispMapInfo(mapObj);
		dispEditMenu();
	}
	
	/**
	 * @param inMapObj
	 */
	private void dispMapInfo(Maps inMapObj)
	{
		System.out.printf("Current Map Info:");
		System.out.printf("Name of map = %s", inMapObj.getMapName());
		System.out.printf("Continents = %d", inMapObj.getNumContinents());
		System.out.printf("Territories = %d", inMapObj.getNumTerritories());
	}
	
	
	private void dispEditMenu()
	{
		System.out.printf("What would you like to do:");
		System.out.printf("1) Add a territory");
		System.out.printf("2) Add a continent");
	}
	
	/**
	 *  removes a passed territory node from the graph of the map
	 *  @param territory
	 */
	public void  removeTerritory(String terName) {
		Territory ter = mapObj.getDictTerritory().get(terName);

		// connect all common neighbors and remove ter from their adjacency lists
		ArrayList<String> terrAdj = ter.getAdjacentCountries();

		for(String neigName: terrAdj) {
			
			Territory Neighbor = mapObj.getDictTerritory().get(neigName);

			ArrayList<String> neighAdj = Neighbor.getAdjacentCountries();
			
			union(terrAdj, neighAdj);
			
			neighAdj.remove(neigName);	// the neighbor itself was added to its own adjacency list so remove it 
			neighAdj.remove(terName);	// remove terName from it's neighbor adjacency list

		}
		
		mapObj.getDictTerritory().remove(terName);	// remove terName from Territory Dictionary
		Continent terCont = mapObj.getDictContinents().get(ter.getContinent()); 
		terCont.removeTerritory(ter);	// remove terName from its continent Dictionary
		ter = null;		// delete Territory object

	}
	/**
	 * helper method used in removeTerritory
	 * @param terrAdj
	 * @param neighAdj
	 * @return union of territory adjacency list and a neighbor adjacency list 
	 */
    private void union(ArrayList<String> terrAdj, ArrayList<String> neighAdj) {
        
    	for(String t: terrAdj) {
    		if(!neighAdj.contains(t)) {
    			neighAdj.add(t);
    		}
    	}
    }

	/**
	 * @return the mapObj
	 */
	public Maps getMapObj() {
		return mapObj;
	}
    
    
}

