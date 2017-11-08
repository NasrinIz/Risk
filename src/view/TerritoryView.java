package src.view;

import src.model.GameConfig;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * this src.view is used to display the territories as buttons.
 *
 * @author Team20
 */

public class TerritoryView extends JPanel {

    private JButton terrNameBtn;

    /**
     * fields constructor
     *
     * @param ownerId number of armies placed on territory
     * @param terrPos coordinates of the position of the territory
     * @param color color of territory
     */
    public TerritoryView(Integer ownerId, Point terrPos, Color color) {
        this.setBackground(color);
        this.setLayout(new FlowLayout());
        terrNameBtn = new JButton(ownerId.toString());
        terrNameBtn.setPreferredSize(new Dimension(50, 30));
        this.add(terrNameBtn);
        this.setBounds(terrPos.x, terrPos.y, 55, 35);
        this.setVisible(true);
    }

    /**
     * @param territoryBtnListener listen for territories
     */
    public void addTerritoryBtnListener(ActionListener territoryBtnListener) {
        terrNameBtn.addActionListener(territoryBtnListener);
    }

    /**
     * Update button names based on captured countries
     */
    void updateButtonName(String playerId) {
        this.terrNameBtn.setText(playerId);
    }
}
