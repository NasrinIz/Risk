package src.model;

/**
 * Created by nasrinizadivahedi on 2017-11-21.
 */
public interface Strategy {
    public int getTerritoryForReinforcement();
    public int getTerritoryForReinforcement(Territory territory);
    public int getTerritoryForFortification(Territory srcTerritory, Territory dstTerritory);
    public int getTerritoryForFortification(Maps map, Territory srcTerritory, Territory dstTerritory);
    public int getTerritoryForAttack(Territory srcTerritory, Territory dstTerritory);
    public int getTerritoryForAttack(Maps map, Territory srcTerritory, Territory dstTerritory);

}
