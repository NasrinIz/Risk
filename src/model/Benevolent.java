package src.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by nasrinizadivahedi on 2017-11-21.
 */
public class Benevolent implements Strategy, Serializable {
	private static final long serialVersionUID = 7900114658814948030L;
	GenericFunctions genfunObj;
    private Integer playerType = 2;
    
    @Override
    public Integer getPlayerType() {
    	return playerType;
    }

	@Override
	public int getTerritoryForReinforcement(ArrayList<Territory> playerTerritories, Player objPlayer) {
		int armies = objPlayer.getArmies();
		if(armies == 0) {
    		objPlayer.getGameConfig().nextPlayerOrPhase();
    	}
		while(armies > 0) {
			int min = 99;
			for(int ctr = 0; ctr < playerTerritories.size(); ctr++) {
				if(playerTerritories.get(ctr).getArmies() < min) {
					min = playerTerritories.get(ctr).getArmies();
				}
			}
			
			for(int ctr = 0; ctr < playerTerritories.size(); ctr++) {
				if(playerTerritories.get(ctr).getArmies() == min) {
					if(objPlayer.getGameConfig().getCurrentPlayer().id != objPlayer.id) {
		    			break;
		    		}
					playerTerritories.get(ctr).increaseArmies();
					armies--;
					objPlayer.setArmies(armies);
					if(objPlayer.getGameConfig().getGamePhase() == genfunObj.GAMEPHASESTARTUP) {
		    			objPlayer.getGameConfig().nextPlayerOrPhase();
		    		}
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
		int max = 0;
		Territory temp = null;
		for(int ctr = 0; ctr < playerTerritories.size(); ctr++) {
			if(playerTerritories.get(ctr).getArmies() > max) {
				max = playerTerritories.get(ctr).getArmies();
				temp = playerTerritories.get(ctr);
			}
		}
		
		Territory adja = null;
		ArrayList<String> adjacent = temp.getAdjacentCountries();
		for(int ctr = 0; ctr < adjacent.size(); ctr++) {
			int min = 9999;
			Territory tmpAdja = objPlayer.getGameConfig().getMapObj().getDictTerritory().get(adjacent);
			if(tmpAdja.getArmies() < min) {
				min = tmpAdja.getArmies();
				adja = tmpAdja;
			}
		}
		
		while(temp.getArmies() > adja.getOwner()) {
			adja.increaseArmies();
			adja.decreaseArmies();
		}
		return 0;
	}

	@Override
	public int getTerritoryForAttack(Territory srcTerritory, Territory dstTerritory, Player objPlayer,
			Integer attackerDice, Integer defenderDice) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public int getTerritoryForAttack(Maps map, ArrayList<Territory> playerTerritories, Player objPlayer) {
		return 0;
	}
}