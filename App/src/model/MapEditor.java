package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Team20
 *
 */
public class MapEditor {
	final int CHOICENONE = 0;
	final int CHOICEADDCONT = 1;
	final int CHOICEDELCONT = 2;
	final int CHOICEADDCOUNT = 3;
	final int CHOICEDELCOUNT = 4;
	
	GenFun genFunObj = new GenFun();
	Integer editorMode = genFunObj.EDITORMODENONE;
	Maps mapObj = null;
	List<Territory> addedTerritories = new ArrayList<Territory>();
	List<Continent> addedContinents = new ArrayList<Continent>();
	Integer choice = 0;
	String mapName = null;
	
	/**
	 * @param inMapLocation
	 */
	public MapEditor(Integer inEditorMode, String inMapName) {
		mapName = inMapName;
		editorMode = inEditorMode;
		
		if(editorMode == genFunObj.EDITORMODEEDIT)
		{
			editMap();
		}
		else if(editorMode == genFunObj.EDITORMODECREATE)
		{
			createMap();
		}
		
	}
	
	private void editMap()
	{
		//mapName = mapName.substring(0, mapName.length() - 4);
		mapObj = new Maps(mapName, 1);
		if(mapObj.readMap().equals("true") == false)
		{
			System.out.println("Input Map Not correct. Opening to edit for correct");
		}
		System.out.println("Map Opened");
	}
	
	private void createMap()
	{
		mapObj = new Maps(mapName, 1);
	}
	
	private void displayMap()
	{
		Iterator ite = mapObj.getDictContinents().entrySet().iterator();
		while(ite.hasNext())
		{
			Map.Entry pair = (Map.Entry)ite.next();
			Continent tmpContinent = mapObj.getDictContinents().get(pair.getKey());
			System.out.printf("\n%s, %d", tmpContinent.getName(), tmpContinent.getArmyReward());
		}
		System.out.println();
		ite = mapObj.getDictTerritory().entrySet().iterator();
		while(ite.hasNext())
		{
			Map.Entry pair = (Map.Entry)ite.next();
			Territory tmpTerritory = mapObj.getDictTerritory().get(pair.getKey());
			System.out.printf("\n%s, %d, %d, %s", tmpTerritory.getName(), tmpTerritory.getX(), 
					tmpTerritory.getY(), tmpTerritory.getContinent());
			for(int i = 0; i < tmpTerritory.getAdjacentCountries().size(); i++)
			{
				System.out.printf(", %s,", tmpTerritory.getAdjacentCountries().get(i));
			}
		}
	}
	
	public void addContinent(String continentInfo)
	{
		List<String> continentInfoList = genFunObj.genCommaSepStrToArrayList(continentInfo); 
		String continentName = continentInfoList.get(0);
		Integer continentAward = Integer.parseInt(continentInfoList.get(1));
		if(editorMode == genFunObj.EDITORMODEEDIT)
		{
			Iterator ite = mapObj.getDictContinents().entrySet().iterator();
			while(ite.hasNext())
			{
				Map.Entry pair = (Map.Entry)ite.next();
				if(pair.getKey().equals(continentName))
				{
					System.out.println("A continent with this name already exists. Updating the army reward values.");
					mapObj.getDictContinents().get(pair.getKey()).setArmyReward(continentAward);
				}
			}
		}
		
		Continent tmpCont = new Continent(continentName, continentAward);
		mapObj.getDictContinents().put(continentName, tmpCont);
		displayMap();
	}
	
	public void deleteContinent(String continentInfo)
	{
		List<String> continentInfoList = genFunObj.genCommaSepStrToArrayList(continentInfo); 
		String continentName = continentInfo;

		if(mapObj.getDictContinents().containsKey(continentName))
		{
			mapObj.deleteContinent(continentName);
		}
		else
		{
			System.out.println("No such continent exists");
			return;
		}
		displayMap();
	}



	public void newCountry(String countryInfo)
	{
		Territory tmpTerritory = new Territory(countryInfo);
		
		if(tmpTerritory != null)
		{
			if(mapObj.getDictContinents().containsKey(tmpTerritory.getContinent()))
			{
				mapObj.getDictTerritory().put(tmpTerritory.getName(), tmpTerritory);
				List<Territory> continentTerritories = mapObj.getDictContinents().get(tmpTerritory.getContinent()).getTerritories();
				continentTerritories.add(tmpTerritory);
				return;
			}
			else
			{
				System.out.printf("\nNo continent found for territory with name %s. "
						+ "Please add this continent first\n", tmpTerritory.getContinent());
			}
		}
		displayMap();
	}
	
	
	public void delCountry(String countryInfo)
	{	
		if(mapObj.getDictTerritory().containsKey(countryInfo))
		{
			mapObj.deleteTerritory(mapObj.getDictTerritory().get(countryInfo));
		}
		else
		{
			System.out.println("No such territory exists");
		}
		displayMap();
	}
	
	public String[] getContinentListInMapEditor(){
		String[] mapTitles = new String[mapObj.getDictContinents().size()];
		Iterator ite = mapObj.getDictContinents().entrySet().iterator();
		int i = 0;
		while(ite.hasNext())
		{
			Map.Entry pair = (Map.Entry)ite.next();
			mapTitles[i] = (String) pair.getKey();
			i++;
		}
		//String[] mapTitles = new String[] { "Atlantis", "DiMul", "Europe", "Old Yorkshire", "Polygons", "Twin Volcano",
			//	"USA", "World" };
		return mapTitles;
	}
	
	
	public String[] getCountryListInMapEditor(){
		String[] mapTitles = new String[mapObj.getDictTerritory().size()];
		Iterator ite = mapObj.getDictTerritory().entrySet().iterator();
		int i = 0;
		while(ite.hasNext())
		{
			Map.Entry pair = (Map.Entry)ite.next();
			mapTitles[i] = (String) pair.getKey();
			i++;
		}
		//String[] mapTitles = new String[] { "Atlantis", "DiMul", "Europe", "Old Yorkshire", "Polygons", "Twin Volcano",
				//"USA", "World" };
		return mapTitles;
	}
	
	public int finishAndValidate(){
		Integer rt = 0;
		/*
		if(mapObj.readMap() != "true")
		{
			System.out.println("The edited map is not valid");
			rt = -1;
		}
		*/
		
		if(mapObj.validateMap() != "true")
		{
			System.out.println("The edited map is not valid");
			rt = -1;
		}
		return rt;
	}
    
}

