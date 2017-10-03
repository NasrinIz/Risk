package model;

public class GameConfig {
	private Integer numPlayers = null;
	private Player players[];
	private Continent []continents;
	private Maps mapObj;
	private Card []cards;
	private GenFun genFunObj = new GenFun();
	
	public Integer setNumPlayers(int inNumPlayers)
	{
		if((inNumPlayers < 2) || (inNumPlayers > 6))
			return -1;
		
		numPlayers = new Integer(inNumPlayers);
		setupPlayers();
		for (String territory : mapObj.dictTerritory.keySet())
		{
			System.out.println((mapObj.dictTerritory).get(territory).getOwner());
		}
		return 0;
	}
	
	public Maps createMap(String mapLocation)
	{
		mapObj = new Maps(mapLocation);
		return mapObj;
	}
	
	private void setupPlayers()
	{
		players = new Player[numPlayers];
		for(int i = 0; i < numPlayers; i++)
		{
			Player playerObj = new Player("Player" + Integer.toString(i));
			playerObj.setArmies(getInitArmy());
			players[i] = playerObj;
		}
		initTerritory();
	}
	
	private void initTerritory()
	{
		Integer perPlayer = mapObj.dictTerritory.size() / numPlayers;
		Integer remainingTerritoryDist = mapObj.dictTerritory.size() - perPlayer;
		Integer nextOwnerPlayer = new Integer(0);
		Integer perPlayerDistFlag = -1;
		for (String territory : mapObj.dictTerritory.keySet())
		{
			if(perPlayerDistFlag != 0)
			{
				nextOwnerPlayer = (genFunObj.genRandomNumber(0, numPlayers - 1));
			}
			
			while((players[nextOwnerPlayer].numOfTerritories() + remainingTerritoryDist) == (perPlayer + remainingTerritoryDist))
			{
				nextOwnerPlayer = (genFunObj.genRandomNumber(0, numPlayers - 1));
				perPlayerDistFlag = checkForDistCompletion(perPlayer);
				if(perPlayerDistFlag == 0)
				{
					nextOwnerPlayer = -1;
					break;
				}
			}

			if(perPlayerDistFlag == 0)
			{
				nextOwnerPlayer++;
				(mapObj.dictTerritory).get(territory).setOwner(nextOwnerPlayer);
				players[nextOwnerPlayer].occupyTerritory();
			}
			else 
			{
				if(players[nextOwnerPlayer].numOfTerritories() < perPlayer)
				{
					(mapObj.dictTerritory).get(territory).setOwner(nextOwnerPlayer);
					players[nextOwnerPlayer].occupyTerritory();
				}
			}
		}
	}
	
	private Integer checkForDistCompletion(Integer inPerPlayer)
	{
		for(int i=0; i<numPlayers; i++)
		{
			if(players[i].numOfTerritories() < inPerPlayer)
			{
				return -1;
			}
		}
		return 0;
	}
	
	/* This function will distribute the initial number of armies to each player */
	private Integer getInitArmy()
	{
		if(numPlayers == 2)
		{
			return 40;
		}
		else if(numPlayers == 3)
		{
			return 35;
		}
		else if(numPlayers == 4)
		{
			return 30;
		}
		else if(numPlayers == 5)
		{
			return 25;
		}
		else
		{
			return 20;
		}
	}
}
