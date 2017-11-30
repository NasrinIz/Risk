package src.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Human: Player that requires user interaction to make decisions.
 */
public class Human implements Strategy, Serializable {
    private static final long serialVersionUID = -5417659417247726299L;
    GenericFunctions genfunObj = new GenericFunctions();

    private Integer playerType = 0;

    /**
     * @return Player Type
     */
    @Override
    public Integer getPlayerType() {
        return playerType;
    }

    /**
     * @param playerTerritories Player territories
     * @param objPlayer         PLayer object
     * @return -1
     */
    @Override
    public int getTerritoryForReinforcement(ArrayList<Territory> playerTerritories, Player objPlayer) {
        return -1;
    }

    /**
     * @param territory Territory
     * @param objPlayer Player object
     * @return Check
     */
    @Override
    public int getTerritoryForReinforcement(Territory territory, Player objPlayer) {
        if (territory == null) {
            System.out.println("No territory selected for reinforcement");
            return -1;
        }
        if (objPlayer.getArmies() > 0) {
            objPlayer.setArmies(objPlayer.getArmies() - 1);
            territory.increaseArmies();
            if (objPlayer.getGameConfig().getGamePhase() == (genfunObj.GAMEPHASESTARTUP)) {
                objPlayer.getGameConfig().nextPlayerOrPhase();
            }

            if (objPlayer.getGameConfig().getGamePhase() == (genfunObj.GAMEPHASESTARTUP)) {
                if (objPlayer.getArmies() <= 0) {
                    objPlayer.getGameConfig().nextPlayerOrPhase();
                }
            }
        } else {
            objPlayer.getGameConfig().nextPlayerOrPhase();
        }
        return 0;
    }

    /**
     * @param srcTerritory Source Territory
     * @param dstTerritory Destination Territory
     * @param objPlayer    Player object
     * @return 0
     */
    @Override
    public int getTerritoryForFortification(Territory srcTerritory, Territory dstTerritory, Player objPlayer) {
        if ((srcTerritory.getOwner() == dstTerritory.getOwner()) &&
                (srcTerritory.getOwner() == objPlayer.id) &&
                (objPlayer.id == dstTerritory.getOwner())) {
            if (srcTerritory.getArmies() > 1) {
                srcTerritory.decreaseArmies();
                dstTerritory.increaseArmies();
                System.out.printf("\n%s : %d left, %s : %d now", srcTerritory.getName(), srcTerritory.getArmies(),
                        dstTerritory.getName(), dstTerritory.getArmies());
            }
        }
        return 0;
    }

    /**
     * @param map               Map
     * @param playerTerritories PLayer Territories
     * @param objPlayer         Player object
     * @return -1
     */
    @Override
    public int getTerritoryForFortification(Maps map, ArrayList<Territory> playerTerritories, Player objPlayer) {
        return -1;
    }

    /**
     * @param srcTerritory Source Territory
     * @param dstTerritory Destination Territory
     * @param objPlayer    PLayer object
     * @param attackerDice Attacker dice
     * @param defenderDice Defender dice
     * @return 0
     */
    @Override
    public int getTerritoryForAttack(Territory srcTerritory, Territory dstTerritory, Player objPlayer,
                                     Integer attackerDice, Integer defenderDice) {
        objPlayer.performAttack(attackerDice, defenderDice,
                objPlayer.getGameConfig().getMapObj().getDictContinents(), srcTerritory, dstTerritory);
        return 0;
    }

    /**
     * @param map               Map
     * @param playerTerritories Player territories
     * @param objPlayer         PLayer object
     * @return -1
     */
    @Override
    public int getTerritoryForAttack(Maps map, ArrayList<Territory> playerTerritories, Player objPlayer) {
        return -1;
    }
}
