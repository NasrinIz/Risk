package src.view;


import src.model.GameConfig;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * This view shows the information of players
 */
public class PlayerInformationView extends JPanel implements Observer {
    private JTextArea InfoTextArea;

    public PlayerInformationView() {
        this.showInfoPanel();
    }

    /**
     * Show error information
     */
    public void showInfoPanel() {
        this.setBackground(Color.BLUE);
        this.setLayout(new FlowLayout());
        this.setBounds(1024, 268, 255, 100);
        InfoTextArea = new JTextArea();
        InfoTextArea.setRows(3);
        InfoTextArea.setBounds(1024, 268, 200, 100);
        this.add(InfoTextArea);

        TitledBorder border = new TitledBorder("Phase View");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        this.setBorder(border);
        this.setVisible(true);
    }

    /**
     * @param info error to show in panel
     */
    private void showPlayerInformationView(String info) {
        InfoTextArea.setText(info);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.showPlayerInformationView(((GameConfig) o).gamePhaseStr);
    }
}
