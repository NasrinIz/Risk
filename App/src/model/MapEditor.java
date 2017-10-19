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
	
	/**
	 * @param inMapLocation
	 */
	public MapEditor(Integer inEditorMode) {
		editorMode = inEditorMode;
		if(editorMode == genFunObj.EDITORMODEEDIT)
		{
			System.out.println("Please enter path of map to edit: ");
			Scanner scanIn = new Scanner(System.in);
			String path = scanIn.next();
			editMap(path);
		}
		else if(editorMode == genFunObj.EDITORMODECREATE)
		{
			System.out.println("Please enter path for new Map: ");
			Scanner scanIn = new Scanner(System.in);
			String path = scanIn.next();
			createMap(path);
		}
	}
	
	private void editMap(String inPath)
	{
		mapObj = new Maps(inPath, 1);
		if(mapObj.readMap().equals("true") == false)
		{
			System.out.println("Input Map Not correct. Opening to edit for correct");
		}
		System.out.println("Map Opened");
		checkChoice(displayChoice());
	}
	
	private void createMap(String inPath)
	{
		mapObj = new Maps(null, 1);
		checkChoice(displayChoice());
	}
	
	private void checkChoice(Integer choice)
	{
		this.choice = choice;
		
		if(choice == null)
			System.out.println("System state not correct");
		
		switch(choice)
		{
		case 1:
			addContinent();
			break;
		case 2:
			deleteContinent();
			break;
		case 3:
			newCountry();
			break;
		case 4:
			delCountry();
			break;
		case 5:
			quitAndValidate();
			break;
		}
		
	}
	
	private void quitAndValidate()
	{
		mapObj.addContinent(addedContinents);
		mapObj.addCountry(addedTerritories);
		
		
	}
	
	private void addContinent()
	{
		System.out.println("Enter continent name: ");
		Scanner in = new Scanner(System.in);
		String continentName = in.next();
		
		System.out.println("Enter continent award armies: ");
		Integer continentAward = in.nextInt();
	
		for(int i = 0; i < addedContinents.size(); i++)
		{
			if(addedContinents.get(i).getName().equals(continentName))
			{
				System.out.println("A continent with this name already exists");
				return;
			}
		}
		
		Iterator ite = mapObj.getDictContinents().entrySet().iterator();
		while(ite.hasNext())
		{
			Map.Entry pair = (Map.Entry)ite.next();
			if(pair.getKey().equals(continentName))
			{
				System.out.println("A continent with this name already exists");
				return;
			}
		}
		
		Continent tmpCont = new Continent(continentName, continentAward);
		
		System.out.println("Every continent must have atlest one country.");
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
		
		addedContinents.add(tmpCont);
		addedTerritories.add(tmpTerritory);
	}
	
	private void deleteContinent()
	{
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
		
		if(mapObj.getDictContinents().containsKey(continentName))
		{
			mapObj.deleteContinent(continentName);
		}
		else
		{
			System.out.println("No such continent exists");
			return;
		}
	}

	private void newCountry()
	{
		Territory tmpTerritory = addCountry();
		
		if(tmpTerritory != null)
		{
			if(mapObj.getDictContinents().containsKey(tmpTerritory.getContinent()))
			{
				addedTerritories.add(tmpTerritory);
				return;
			}
			else
			{
				System.out.printf("\nNo continent found for territory with name %s\n. "
						+ "Please add this continent first", tmpTerritory.getContinent());
			}
		}
	}
	
	private Territory addCountry()
	{
		System.out.println("Enter Territory Info. Format = <name>, <x>, <y>, <continent>, <adjacent territory, ...>");
		Scanner in = new Scanner(System.in);
		String territoryInfo = in.next();
		Territory tmpTerritory = new Territory(territoryInfo);
		
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
	}
	
	private void delCountry()
	{
		System.out.println("Please enter name of territory to be deleted");
		Scanner in = new Scanner(System.in);
		String territoryName = in.next();
		
		deleteCountry(territoryName);
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
    
}

