package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * @author Team20
 *
 */
public class GameConfig {
	private Integer numPlayers = null;
	private Player players[];
	private Maps mapObj;
	private ArrayList<Card> gameCards;
	Integer currentPlayer = 0;
	
	/**
	 * @param numPlayers
	 * @param players
	 * @param mapObj
	 * @param gameCards
	 */
	
	public GameConfig(Integer numPlayers, String mapName) {
		super();
		
		this.numPlayers = numPlayers;
		this.mapObj = new Maps(mapName, 0);
		//setContinentsTerritoryList();
		initMap();
		setNumPlayers();
		setupCards();
	}

	private GenFun genFunObj = new GenFun();
	
	/**
	 * 
	 */
	public void nextPlayerTurn()
	{
		currentPlayer++;
		if(currentPlayer >= players.length)
		{
			currentPlayer = 0;
		}
	}
	
	/**
	 * @return current player
	 */
	public Player getCurrentPlayer()
	{
		return players[currentPlayer];
	}
	
	/**
	 * @return
	 */
	private Integer setNumPlayers()
	{
		if((numPlayers < 2) || (numPlayers > 6))
			return -1;
		
		setupPlayers();
		for (String territory : mapObj.getDictTerritory().keySet())
		{
			System.out.println((mapObj.getDictTerritory()).get(territory).getOwner());
		}
		return 0;
	}
	
	/**
	 * @return players
	 */
	public Player[] getPlayers()
	{
		return players;
	}
	
	/**
	 * @param mapName
	 * @return mapObj
	 */
	public void initMap()
	{
		if(this.mapObj.readMap().equals("true") == false)
		{
			System.out.println("Map initialization failure");
			return;
		}
		
		if(this.mapObj.validateMap().equals("true") == false)
		{
			System.out.println("Map validation failure");
			return;
		}
	}
	
	private void setupPlayers()
	{
		this.players = new Player[numPlayers];

		for(int i = 0; i < numPlayers; i++)
		{
			Player playerObj = new Player("Player" + Integer.toString(i), i);
			playerObj.setArmies(getInitArmy());
			players[i] = playerObj;
		}
		initTerritory();
		
		for (String territory : mapObj.getDictTerritory().keySet())
		{
			players[mapObj.getDictTerritory().get(territory).getOwner()].setTerritories(mapObj.getDictTerritory().get(territory));
		}
		
		for(int i = 0; i < numPlayers; i++)
		{
			players[i].setArmies(players[i].getArmies() - players[i].numOfTerritories());
		}
	}
	
	private void initTerritory()
	{
		Integer perPlayer = mapObj.getDictTerritory().size() / numPlayers;
		Integer remainingTerritoryDist = mapObj.getDictTerritory().size() - perPlayer;
		Integer nextOwnerPlayer = new Integer(0);
		Integer perPlayerDistFlag = -1;
		for (String territory : mapObj.getDictTerritory().keySet())
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
				(mapObj.getDictTerritory()).get(territory).setOwner(nextOwnerPlayer);
				players[nextOwnerPlayer].occupyTerritory();
			}
			else 
			{
				if(players[nextOwnerPlayer].numOfTerritories() < perPlayer)
				{
					(mapObj.getDictTerritory()).get(territory).setOwner(nextOwnerPlayer);
					players[nextOwnerPlayer].occupyTerritory();
				}
			}
		}
	}
	
	/**
	 * @param inPerPlayer
	 */
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
	
	/**
	 * This method initialize how many armies base on the number of players
	 */
	private Integer getInitArmy()
	{

 		if(numPlayers >= 2 && numPlayers <=6 ) {
 			return 5*(10-this.numPlayers);
 		} else {
 			return 0;		// should throw an exception
 		}

	}
	
	public void calcReinforcementArmy()
	{
		Iterator ite = mapObj.getDictContinents().entrySet().iterator();
	    while (ite.hasNext()) 
	    {
	        Map.Entry pair = (Map.Entry)ite.next();
	        Continent tmpContinent = mapObj.getDictContinents().get(pair.getKey());

	        /* Calculates army reward for each continent and add to players */
	        if(tmpContinent.checkForOwnership() == 0)
	        {
	        	players[tmpContinent.getOwnerId()].setContinentArmyReward(tmpContinent.getArmyReward());
	        }
	        
	        //System.out.println(pair.getKey() + " = " + pair.getValue());
	    }
		
		for(int i = 0; i < players.length; i++)
		{
			players[i].calcReinforcementArmies();
		}
	}
	/**
	 * @return the gameCards
	 */
	public ArrayList<Card> getGameCards() {
		return gameCards;
	}

	/**
	 * @return the mapObj
	 */
	public Maps getMapObj() {
		return mapObj;
	}

	/**
	 * @param mapObj the mapObj to set
	 */
	public void setMapObj(Maps mapObj) {
		this.mapObj = mapObj;
	}

	
//// =================================<< Card Methods >>=================================

	/**
	 * Generate 2 WILD cards,
	 * In addition to INFANTRY, CAVALRY, ARTILLARY cards = number of map territories,
	 * generated in a ratio close to 5:2:1 respectively
	 * 
	 */
	private void setupCards() {
		this.gameCards = new ArrayList<Card>();
		int randomNum = 0;
		Random randGenerator = new Random();		
		// 2 WILD cards with no country names
		gameCards.add(new Card(RiskCard.WILD, ""));
		gameCards.add(new Card(RiskCard.WILD, ""));
		Card newCard = null;
		for (String trName : mapObj.getDictTerritory().keySet()) // causes error 
		{
			randomNum = randGenerator.nextInt((16 - 1) + 1) + 1;
//			System.out.println( randomNum );
			if( randomNum % 8 == 0) {		// 8,16
				newCard = new Card(RiskCard.ARTILLERY, trName);
			} else if(randomNum %3 == 0 ) {	// 3,6,9,12
				newCard = new Card(RiskCard.CAVALRY, trName);
			} else {						// 1,2,4,5,7,10,11,13,14,15
				newCard = new Card(RiskCard.INFANTRY, trName);
			} 
//			System.out.println( newCard );
			gameCards.add(newCard);
		}
	}
	
	/**
	 * @param type
	 * @return categ 
	 */
	public ArrayList<Card> getGameCardsOfType(RiskCard type) {
		ArrayList<Card> categ = new ArrayList<Card>();
		
		for(Card card: gameCards) {
			if(card.compareTypeTo(type) == 0) {
				categ.add(card);
			}
		}
		return categ;
	}
	
	/**
	 * Distributes game cards among players according to countries they own
	 * 
	 */
	
	// not yet completed, missing more methods in other classes ?!!!!!
	public void distributeCards() {
		Player p;
		for(int i = 0; i< players.length ; i++) {
			p = players[i];
		}
		
	}
	
	public void fortifyArmies(String srcTerritory, String destTerritory)
	{
		if(mapObj.getDictTerritory().get(srcTerritory).getArmies() > 1)
		{
			mapObj.getDictTerritory().get(srcTerritory).decreaseArmies();
			mapObj.getDictTerritory().get(destTerritory).increaseArmies();
			System.out.printf("\n%s : %d left, %s : %d now", 
			mapObj.getDictTerritory().get(srcTerritory).getName(),
			mapObj.getDictTerritory().get(srcTerritory).getArmies(), 
			mapObj.getDictTerritory().get(destTerritory).getName(),
			mapObj.getDictTerritory().get(destTerritory).getArmies());
		}
	}
	
}
