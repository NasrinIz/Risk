package src.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by nasrinizadivahedi on 2017-11-21.
 */
public class Cheater implements Strategy, Serializable {
    private static final long serialVersionUID = -5417659417247726299L;
    GenericFunctions genfunObj;

    private Integer playerType = 4;
    
    @Override
    public Integer getPlayerType() {
    	return playerType;
    }

    @Override
    public int getTerritoryForReinforcement(ArrayList<Territory> playerTerritories, Player objPlayer) {
        for(int ctr = 0; ctr < playerTerritories.size(); ctr++) {
        	int num = playerTerritories.get(ctr).getArmies();
        	if(num == 0) {
        		objPlayer.getGameConfig().nextPlayerTurn();
        		objPlayer.getGameConfig().nextPlayerOrPhase();
        	}
        	for(int ctr2 = 0; ctr2 < num; ctr++) {
        		if(objPlayer.getGameConfig().getCurrentPlayer().id != objPlayer.id) {
        			break;
        		}
        		playerTerritories.get(ctr).increaseArmies();
        		if(objPlayer.getGameConfig().getGamePhase() == genfunObj.GAMEPHASESTARTUP) {
        			objPlayer.getGameConfig().nextPlayerOrPhase();
        		}
        	}
        }
        return 0;
    }

    @Override
    public int getTerritoryForReinforcement(Territory territory, Player objPlayer) {
        return -1;
    }

    @Override
    public int getTerritoryForFortification(Territory srcTerritory, Territory dstTerritory, Player objPlayer) {
        return -1;
    }

    @Override
    public int getTerritoryForFortification(Maps map, ArrayList<Territory> playerTerritories, Player objPlayer) {
    	for(int ctr = 0; ctr < playerTerritories.size(); ctr++) {
        	int num = playerTerritories.get(ctr).getArmies();
        	ArrayList<String> adjacent = playerTerritories.get(ctr).getAdjacentCountries();
        	
        	for(int ctr2 = 0; ctr2 < adjacent.size(); ctr++) {
        		Territory temp = objPlayer.getGameConfig().getMapObj().getDictTerritory().get(adjacent.get(ctr2));
        		if(temp.getOwner() != objPlayer.id) {
        			for(int ctr3 = 0; ctr3 < num; ctr3++) {
        				playerTerritories.get(ctr).increaseArmies();
        			}
        		}
        	}
        }
        return 0;
    }

    @Override
    public int getTerritoryForAttack(Territory srcTerritory, Territory dstTerritory, Player objPlayer,
    		Integer attackerDice, Integer defenderDice) {
        return -1;
    }

    @Override
    public int getTerritoryForAttack(Maps map, ArrayList<Territory> playerTerritories, Player objPlayer) {
    	Map<String, Continent> dictContinents = objPlayer.getGameConfig().getMapObj().getDictContinents();
        for(int ctr = 0; ctr < playerTerritories.size(); ctr++) {
        	Territory srcTerritory = playerTerritories.get(ctr);
        	ArrayList<String> adjacent = playerTerritories.get(ctr).getAdjacentCountries();
        	for(int ctr2 = 0; ctr2 < adjacent.size(); ctr++) {
        		Territory temp = objPlayer.getGameConfig().getMapObj().getDictTerritory().get(adjacent.get(ctr2));
        		if(temp.getOwner() != objPlayer.id) {
        	        System.out.println("Player " + srcTerritory.getOwner().toString() +
        	                " captured player " + temp.getOwner().toString() + "'s Territory");
    	            Player players[] = objPlayer.getGameConfig().getPlayers();
    	            players[temp.getOwner()].removeTerritory(temp.getName());
    	            temp.setOwner(objPlayer.id);
    	            temp.increaseArmies();
    	            srcTerritory.decreaseArmies();
    	            objPlayer.getTerritories().add(temp);

        	        for (String continent : dictContinents.keySet()) {
        	            if (continent.equals(temp.getContinent())) {
        	                if (dictContinents.get(continent).isContinentCaptured(srcTerritory.getOwner())) {
        	                    System.out.println("Player " + srcTerritory.getOwner().toString() + " captured continent " + continent);
        	                    System.out.println("Player will be awarded " + dictContinents.get(continent).getArmyReward() +
        	                            " additional armies");
        	                }
        	            }
        	        }
        		}
        	}
        }
        return 0;
    }
}
