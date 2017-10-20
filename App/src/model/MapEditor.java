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
	
	public void addContinent(String continentInfo)
	{
		System.out.printf("%d", editorMode);
		System.out.println(continentInfo);
		
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
		
		/*System.out.println("Every continent must have atlest one country.");
		Territory tmpTerritory = null;
		
		while(tmpTerritory != null)
		{
			Territory tmp = addCountry();
			if(tmp != null)
			{
				if(tmp.getContinent().equals(continentName) == false)
				{
					System.out.printf("\nPlease add territory for your new continent %s first\n", continentName);
				}
				else
				{
					tmpTerritory = tmp;
				}
			}
			else
			{
				System.out.println("Please try again: ");
			}
		}
		*/
		
		//addedContinents.add(tmpCont);
		//addedTerritories.add(tmpTerritory);
	}
	
	public void deleteContinent(String continentInfo)
	{
		System.out.printf("%d", editorMode);
		System.out.println(continentInfo);
		/*
		System.out.println("Please enter name of continent to be deleted");
		Scanner in = new Scanner(System.in);
		String continentName = in.next();
		
		for(int i = 0; i < addedContinents.size(); i++)
		{
			if(addedContinents.get(i).getName().equals(continentName))
			{
				addedContinents.remove(i);
				return;
			}
		}
		*/
//		if(mapObj.getDictContinents().containsKey(continentName))
//		{
//			mapObj.deleteContinent(continentName);
//		}
		/*
		else
		{
			System.out.println("No such continent exists");
			return;
		}
		*/
	}



	public void newCountry(String countryInfo)
	{
		System.out.printf("%d", editorMode);
		System.out.println(countryInfo);
//		Territory tmpTerritory = addCountry();
//		
//		if(tmpTerritory != null)
//		{
//			if(mapObj.getDictContinents().containsKey(tmpTerritory.getContinent()))
//			{
//				addedTerritories.add(tmpTerritory);
//				return;
//			}
//			else
//			{
//				System.out.printf("\nNo continent found for territory with name %s\n. "
//						+ "Please add this continent first", tmpTerritory.getContinent());
//			}
//		}
	}
	
	
	//private Territory addCountry()
	{
		/*
		System.out.println("Enter Territory Info. Format = <name>, <x>, <y>, <continent>, <adjacent territory, ...>");
		Scanner in = new Scanner(System.in);
		String territoryInfo = in.next();
		*/
		//Territory tmpTerritory = new Territory(territoryInfo);
		/*
		if(tmpTerritory.getName() == null)
			return null;
		
		for(int i = 0; i < addedTerritories.size(); i++)
		{
			if(addedTerritories.get(i).getName().equals(tmpTerritory.getName()))
			{
				System.out.println("A territory with this name already exists");
				return null;
			}
		}
		
		return tmpTerritory;
		*/
	}
	
	public void delCountry(String countryInfo)
	{
		System.out.printf("%d", editorMode);
		System.out.println(countryInfo);
//		System.out.println("Please enter name of territory to be deleted");
//		Scanner in = new Scanner(System.in);
//		String territoryName = in.next();
//		
//		deleteCountry(territoryName);
	}
	
	private void deleteCountry(String tmpTerritory)
	{
		for(int i = 0; i < addedTerritories.size(); i++)
		{
			if(addedTerritories.get(i).getName().equals(tmpTerritory))
			{
				addedTerritories.remove(i);
				return;
			}
		}
		
		if(mapObj.getDictTerritory().containsKey(tmpTerritory))
		{
			mapObj.deleteTerritory(mapObj.getDictTerritory().get(tmpTerritory));
		}
		else
		{
			System.out.println("No such territory exists");
		}
	}
	
	private Integer displayChoice()
	{
		Integer choice = 0;
		
		while((choice < 1) || (choice > 4))
		System.out.println("1) Add a continent");
		System.out.println("2) Delete a continent");
		System.out.println("3) Add a country");
		System.out.println("4) Delete a country");
		
		Scanner in = new Scanner(System.in);
		choice = in.nextInt();
		if((choice < 1) || (choice > 4))
		{
			System.out.println("Incorrect Choice, Please try again: ");
		}
		
		return choice;
	}
	
	public String[] getContinentListInMapEditor(){
		String[] mapTitles = new String[] { "Atlantis", "DiMul", "Europe", "Old Yorkshire", "Polygons", "Twin Volcano",
				"USA", "World" };
		return mapTitles;
	}
	
	
	public String[] getCountryListInMapEditor(){
		String[] mapTitles = new String[] { "Atlantis", "DiMul", "Europe", "Old Yorkshire", "Polygons", "Twin Volcano",
				"USA", "World" };
		return mapTitles;
	}
	
	public int finishAndValidate(){
		System.out.println("Finished Editing");
		return 0;
	}
    
}

