package src.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by nasrinizadivahedi on 2017-11-21.
 */
public class Human implements Strategy, Serializable {
    private static final long serialVersionUID = -5417659417247726299L;
    GenericFunctions genfunObj = new GenericFunctions();

    private Integer playerType = 0;
    
    @Override
    public Integer getPlayerType() {
    	return playerType;
    }

    @Override
    public int getTerritoryForReinforcement(ArrayList<Territory> playerTerritories, Player objPlayer) {
        return -1;
    }

    @Override
    public int getTerritoryForReinforcement(Territory territory, Player objPlayer) {
    	if(territory == null) {
    		System.out.println("No territory selected for reinforcement");
    		return -1;
    	}
    	if(objPlayer.getArmies() > 0) {
    		objPlayer.setArmies(objPlayer.getArmies() - 1);
    		territory.increaseArmies();
    		if(objPlayer.getGameConfig().getGamePhase() == (genfunObj.GAMEPHASESTARTUP)) {
    			objPlayer.getGameConfig().nextPlayerOrPhase();
    		}
    		
    		if(objPlayer.getGameConfig().getGamePhase() == (genfunObj.GAMEPHASESTARTUP)) {
    			if(objPlayer.getArmies() <= 0) {
    				objPlayer.getGameConfig().nextPlayerOrPhase();
    			}
    		}
    	}
    	else {
    		objPlayer.getGameConfig().nextPlayerOrPhase();
    	}
        return 0;
    }

    @Override
    public int getTerritoryForFortification(Territory srcTerritory, Territory dstTerritory, Player objPlayer) {
    	if((srcTerritory.getOwner() == dstTerritory.getOwner()) &&
    			(srcTerritory.getOwner() == objPlayer.id) &&
    			(objPlayer.id == dstTerritory.getOwner())) {
    		if(srcTerritory.getArmies() > 1) {
	    		srcTerritory.decreaseArmies();
	    		dstTerritory.increaseArmies();
		        System.out.printf("\n%s : %d left, %s : %d now", srcTerritory.getName(), srcTerritory.getArmies(),
		        		dstTerritory.getName(), dstTerritory.getArmies());
    		}
    	}
        return 0;
    }

    @Override
    public int getTerritoryForFortification(Maps map, ArrayList<Territory> playerTerritories, Player objPlayer) {
        return -1;
    }

    @Override
    public int getTerritoryForAttack(Territory srcTerritory, Territory dstTerritory, Player objPlayer,
    		Integer attackerDice, Integer defenderDice) {
        objPlayer.performAttack(attackerDice, defenderDice, 
        		objPlayer.getGameConfig().getMapObj().getDictContinents(), srcTerritory, dstTerritory);
        return 0;
    }

    @Override
    public int getTerritoryForAttack(Maps map, ArrayList<Territory> playerTerritories, Player objPlayer) {
        return -1;
    }
}
