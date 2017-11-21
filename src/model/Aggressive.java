package src.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by nasrinizadivahedi on 2017-11-21.
 */
public class Aggressive implements Strategy, Serializable {
    private static final long serialVersionUID = -5417659417247726299L;
    private Player player;
    private Maps map;
    private Territory reinforceTerritory;
    private Territory fortifyFrom;
    private Territory fortifyTo;
    private Territory attackFrom;
    private Territory attackTo;


    public Territory getFortifyTo() {
        return fortifyTo;
    }

    public void setFortifyTo(Territory fortifyTo) {
        this.fortifyTo = fortifyTo;
    }

    public Territory getFortifyFrom() {
        return fortifyFrom;
    }

    public void setFortifyFrom(Territory fortifyFrom) {
        this.fortifyFrom = fortifyFrom;
    }

    public Territory getAttackFrom() {
        return attackFrom;
    }

    public Territory getAttackTo() {
        return attackTo;
    }

    public void setAttackTo(Territory attackTo) {
        this.attackTo = attackTo;
    }

    @Override
    public int getTerritoryForReinforcement() {
        return 0;
    }

    @Override
    public int getTerritoryForReinforcement(Territory territory) {
        this.setReinforceTerritory(territory);
        return 0;
    }

    @Override
    public int getTerritoryForFortification(Territory srcTerritory, Territory dstTerritory) {
        setFortifyFrom(srcTerritory);
        setFortifyTo(dstTerritory);
        return 0;
    }

    @Override
    public int getTerritoryForFortification(Maps map, Territory srcTerritory, Territory dstTerritory) {
        return 0;
    }

    @Override
    public int getTerritoryForAttack(Territory srcTerritory, Territory dstTerritory) {
        setAttackFrom(srcTerritory);
        setAttackTo(dstTerritory);
        return 0;
    }

    @Override
    public int getTerritoryForAttack(Maps map, Territory srcTerritory, Territory dstTerritory) {
        return 0;
    }

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
