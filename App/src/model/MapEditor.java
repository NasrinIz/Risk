package model;

public class MapEditor {
	Maps mapObj = null;
	
	public MapEditor(String inMapLocation) {
		createMap(inMapLocation);
	}
	
	private void createMap(String inMapLocation)
	{
		mapObj = new Maps(inMapLocation);
	}
	
	private void loadEditor()
	{
		dispMapInfo(mapObj);
		dispEditMenu();
	}
	
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
}
