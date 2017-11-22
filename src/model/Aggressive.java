package src.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by nasrinizadivahedi on 2017-11-21.
 */
public class Aggressive implements Strategy, Serializable {
    private static final long serialVersionUID = -5417659417247726299L;
    GenericFunctions genfunObj;
    private Integer playerType = 1;

    @Override
    public int getTerritoryForReinforcement(ArrayList<Territory> playerTerritories, Player objPlayer) {
    	int max = 0;
    	Territory temp = null;
    	for(int ctr = 0; ctr < playerTerritories.size(); ctr++) {
    		if(playerTerritories.get(ctr).getArmies() > max) {
    			max = playerTerritories.get(ctr).getArmies();
    			temp = playerTerritories.get(ctr);
    		}
    	}
    	
    	int num = objPlayer.getArmies();
    	for(int ctr2 = 0; ctr2 < num; ctr2++) {
    		temp.increaseArmies();
    		objPlayer.setArmies(objPlayer.getArmies() - 1);
    	}
    	
        return 0;
    }

    @Override
    public int getTerritoryForFortification(Territory srcTerritory, Territory dstTerritory, Player objPlayer) {
        return -1;
    }

    @Override
    public int getTerritoryForFortification(Maps map, ArrayList<Territory> playerTerritories, Player objPlayer) {
    	int max = 0;
    	Territory temp = null;
    	for(int ctr = 0; ctr < playerTerritories.size(); ctr++) {
    		if(playerTerritories.get(ctr).getArmies() > max) {
    			max = playerTerritories.get(ctr).getArmies();
    			temp = playerTerritories.get(ctr);
    		}
    	}
    	
    	Territory nextCountry = null;
    	
    	ArrayList<String> adjacent = temp.getAdjacentCountries();
    	for(int ctr = 0; ctr < adjacent.size(); ctr++) {
    		Territory adja = map.getDictTerritory().get(adjacent.get(ctr));
    		if(adja.getOwner() == objPlayer.id) {
    			ArrayList<String> adjacent2 = adja.getAdjacentCountries();
    			for(int ctr2 = 0; ctr2 < adjacent2.size(); ctr2++) {
    				Territory adja2 = map.getDictTerritory().get(adjacent2.get(ctr2));
    				if(adja2.getOwner() != objPlayer.id) {
    					nextCountry = adja;
    				}
    			}
    		}
    	}
    	
    	int num = nextCountry.getArmies();
    	for(int ctr2 = 0; ctr2 < num; ctr2++) {
    		nextCountry.increaseArmies();
    		temp.decreaseArmies();
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
    	int max = 0;
    	Territory temp = null;
    	for(int ctr = 0; ctr < playerTerritories.size(); ctr++) {
    		if(playerTerritories.get(ctr).getArmies() > max) {
    			max = playerTerritories.get(ctr).getArmies();
    			temp = playerTerritories.get(ctr);
    		}
    	}
    	
    	Territory target = null;
    	ArrayList<String> adjacent = temp.getAdjacentCountries();
    	
    	for(int ctr = 0; ctr < playerTerritories.size(); ctr++) {
    		Territory adja = objPlayer.getGameConfig().getMapObj().getDictTerritory().get(adjacent.get(ctr));
    		if(adja.getOwner() != temp.getOwner()) {
    			target = adja;
    			while((adja.getOwner() != temp.getOwner()) && (temp.getArmies() > 1)) {
    				Integer attackerDice = 0;
    				Integer defenderDice = 0;
    				
    				if(temp.getArmies() >= 3) {
    					attackerDice = 3;
    				}
    				else {
    					attackerDice = 2;
    				}
    				
    				if(adja.getArmies() >= 2) {
    					defenderDice = 2;
    				}
    				else {
    					defenderDice = 1;
    				}
    				
    				objPlayer.performAttack(attackerDice, defenderDice, 
    						objPlayer.getGameConfig().getMapObj().getDictContinents(), temp, target);
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
	public Integer getPlayerType() {
		return playerType;
	}
}