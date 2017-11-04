package src.view;


import src.model.GameConfig;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
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
        this.setBounds(1024, 368, 255, 50);
        InfoTextArea = new JTextArea();
        InfoTextArea.setRows(2);
        InfoTextArea.setBounds(1024, 368, 200, 50);
        this.add(InfoTextArea);

        TitledBorder border = new TitledBorder("Player Domination View");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        this.setBorder(border);
        this.setVisible(true);
    }

    /**
     * @param playerTerritories Number of territories of players
     * @param totalTerritories  Total numbers of territories
     */
    public void showPlayerDominationView(Integer playerTerritories, Integer totalTerritories) {
        double percentage = Math.floor(((playerTerritories * 100) / totalTerritories));
        InfoTextArea.setText(percentage + "%");
    }

    @Override
    public void update(Observable o, Object arg) {
        this.showPlayerDominationView(((GameConfig) o).getCurrentPlayer().numOfTerritories(), ((GameConfig) o).getMapObj().getNumTerritories());
    }
}