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
	public int getTerritoryForReinforcement(ArrayList<Territory> playerTerritories) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTerritoryForReinforcement(Territory territory) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTerritoryForFortification(Territory srcTerritory, Territory dstTerritory) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTerritoryForFortification(Maps map, ArrayList<Territory> playerTerritories) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTerritoryForAttack(Territory srcTerritory, Territory dstTerritory) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTerritoryForAttack(Maps map, ArrayList<Territory> playerTerritories) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Territory getFortifyTo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Territory getFortifyFrom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Territory getAttackFrom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Territory getAttackTo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Territory getReinforceTerritory() {
		// TODO Auto-generated method stub
		return null;
	}

}