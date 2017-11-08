package src.view;


import src.model.GameConfig;
import src.model.Player;
import src.model.Territory;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * This view shows the percentage of territories of players
 */
public class PlayerDominationView extends JPanel implements Observer {
    private JTextArea InfoTextArea;

    /**
     * Show error information
     */
    public void showInfoPanel() {
        this.setBackground(Color.GREEN);
        this.setLayout(new FlowLayout());
        this.setBounds(1024, 368, 255, 117);
        InfoTextArea = new JTextArea();
        InfoTextArea.setRows(2);
        InfoTextArea.setBounds(1024, 368, 200, 117);
        this.add(InfoTextArea);

        TitledBorder border = new TitledBorder("Player Domination View");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        this.setBorder(border);
        this.setVisible(true);
    }

    /**
     * @param percentage Number of territories of players
     */
    private void showPlayerDominationView(String percentage) {

        InfoTextArea.setText(percentage);
    }

    @Override
    public void update(Observable o, Object arg) {
        String playerName;
        String dominationValue = "";
        for (Player player : ((GameConfig) o).getPlayers()) {
            playerName = player.getName();
            Integer percentage = (player.numOfTerritories() * 100) / ((GameConfig) o).getMapObj().getNumTerritories();
            Integer star = (percentage * 10) / 100;

            String starValue = "";
            for (Integer i = 0; i < star; i++) {
                starValue = "*" + starValue;
            }

            dominationValue = playerName + ':' + starValue + '\n' + dominationValue;
        }

        this.showPlayerDominationView(dominationValue);
        GameConfig gameConfig = ((GameConfig) o);

        if (gameConfig.getCurrentPlayer().dstAttackTerritory != null && gameConfig.getGamePhase() == 4) {
            String target = gameConfig.getCurrentPlayer().dstAttackTerritory.getName();
            Map<String, TerritoryView> dictViews = gameConfig.getMainWindow().getDictTerrViews();
            String targetOwner = gameConfig.getMapObj().getDictTerritory().get(target).getOwner().toString();

            if (target != null && dictViews != null) {
                TerritoryView targetValue = dictViews.get(target);
                if (targetValue != null) {
                    targetValue.updateButtonName(targetOwner);
                }
            }
        }
    }
}
