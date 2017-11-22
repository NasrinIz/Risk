package src.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by nasrinizadivahedi on 2017-11-21.
 */
public class Random implements Strategy, Serializable {
    private static final long serialVersionUID = -5417659417247726299L;
    GenericFunctions genfunObj;
    
    private Territory reinforceTerritory;
    private Territory fortifyFrom;
    private Territory fortifyTo;
    private Territory attackFrom;
    private Territory attackTo;
    private Integer playerType = 3;
    
    @Override
    public Integer getPlayerType() {
    	return playerType;
    }

    @Override
    public Territory getFortifyTo() {
        return fortifyTo;
    }

    public void setFortifyTo(Territory fortifyTo) {
        this.fortifyTo = fortifyTo;
    }

    @Override
    public Territory getFortifyFrom() {
        return fortifyFrom;
    }

    public void setFortifyFrom(Territory fortifyFrom) {
        this.fortifyFrom = fortifyFrom;
    }

    @Override
    public Territory getAttackFrom() {
        return attackFrom;
    }

    @Override
    public Territory getAttackTo() {
        return attackTo;
    }

    public void setAttackTo(Territory attackTo) {
        this.attackTo = attackTo;
    }

    @Override
    public int getTerritoryForReinforcement(ArrayList<Territory> playerTerritories, Player objPlayer) {
    	
    	int index = genfunObj.genRandomNumber(0, playerTerritories.size());
    	if((index >= 0) && (index < playerTerritories.size())) {
    		reinforceTerritory = playerTerritories.get(index);
    		return 0;
    	}
        return -1;
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
    	int index = genfunObj.genRandomNumber(0, playerTerritories.size());
    	if((index >= 0) && (index < playerTerritories.size())) {
    		int ownerOfFirst = playerTerritories.get(index).getOwner();
    		ArrayList<String> adjacent = playerTerritories.get(index).getAdjacentCountries();
    		for(int ctr = 0; ctr < adjacent.size(); ctr++) {
    			int ownerOfSecond = map.getDictTerritory().get(adjacent.get(ctr)).getOwner();
    			if(ownerOfFirst == ownerOfSecond) {
    				fortifyFrom = playerTerritories.get(index);
    				fortifyTo = map.getDictTerritory().get(adjacent.get(ctr));
    			}
    		}
    		return 0;
    	}
        return -1;
    }

    @Override
    public int getTerritoryForAttack(Territory srcTerritory, Territory dstTerritory, Player objPlayer) {
        return -1;
    }

    @Override
    public int getTerritoryForAttack(Maps map, ArrayList<Territory> playerTerritories, Player objPlayer) {
    	int index = genfunObj.genRandomNumber(0, playerTerritories.size());
    	if((index >= 0) && (index < playerTerritories.size())) {
    		int ownerOfFirst = playerTerritories.get(index).getOwner();
    		ArrayList<String> adjacent = playerTerritories.get(index).getAdjacentCountries();
    		for(int ctr = 0; ctr < adjacent.size(); ctr++) {
    			int ownerOfSecond = map.getDictTerritory().get(adjacent.get(ctr)).getOwner();
    			if(ownerOfFirst != ownerOfSecond) {
    				Territory attackFrom = playerTerritories.get(index);
    				Territory attackTo = map.getDictTerritory().get(adjacent.get(ctr));
    				int attackerDice = genfunObj.genRandomNumber(1, 3);
    				int defendorDice = genfunObj.genRandomNumber(1, 2);
    				int numTimesAttack = genfunObj.genRandomNumber(1, 100);
    				for(int attackTime = 0; attackTime < numTimesAttack; attackTime++) {
    					objPlayer.performAttack(attackerDice, defendorDice, 
    							objPlayer.getGameConfig().getMapObj().getDictContinents(), attackFrom, attackTo);
    				}
    			}
    		}
    		return 0;
    	}
        return -1;
    }
    
    @Override
    public Territory getReinforceTerritory() {
        return reinforceTerritory;
    }

    public void setReinforceTerritory(Territory reinforceTerritory) {
        this.reinforceTerritory = reinforceTerritory;
    }

    public void setAttackFrom(Territory attackFrom) {
        this.attackFrom = attackFrom;
    }
}
