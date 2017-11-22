package src.model;

import java.util.ArrayList;

/**
 * Created by nasrinizadivahedi on 2017-11-21.
 */
public interface Strategy {
    public int getTerritoryForReinforcement(ArrayList<Territory> playerTerritories);
    public int getTerritoryForReinforcement(Territory territory);
    
    public int getTerritoryForFortification(Territory srcTerritory, Territory dstTerritory);
    public int getTerritoryForFortification(Maps map, ArrayList<Territory> playerTerritories);
    
    public int getTerritoryForAttack(Territory srcTerritory, Territory dstTerritory);
    public int getTerritoryForAttack(Maps map, ArrayList<Territory> playerTerritories);
    
    public Integer getPlayerType();
    
    public Territory getFortifyTo();
    public Territory getFortifyFrom();
    
    public Territory getAttackFrom();
    public Territory getAttackTo();
    
    public Territory getReinforceTerritory();
}
