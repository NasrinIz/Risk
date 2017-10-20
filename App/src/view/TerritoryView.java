package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * this view is used to display the territories as buttons.
 * @author Team20
 *
 */

public class TerritoryView extends JPanel {

	JLabel armiesNumLbl;
	Point terrPos;
	Color color;
	JButton terrNameBtn;
	String terrName;
	/**
	 * fields constructor
	 * 
	 * @param terrName
	 *            name of territory
	 * @param armiesNum
	 *            number of armies placed on territory
	 * @param terrPos
	 *            coordinates of the position of the territory
	 */
	public TerritoryView(String terrName, Integer ownerId, Point terrPos, Color color) {
		this.terrName = terrName;
		this.setBackground(color);
		this.setLayout(new FlowLayout());
		terrNameBtn = new JButton(ownerId.toString());
		terrNameBtn.setPreferredSize(new Dimension(50, 30));
		this.terrPos = terrPos;
		this.add(terrNameBtn);
		this.setBounds(terrPos.x, terrPos.y, 55, 35);
		this.setVisible(true);
	}

	/**
	 * @param territoryBtnListener
	 */
	public void addTerritoryBtnListener(ActionListener territoryBtnListener) {
		terrNameBtn.addActionListener(territoryBtnListener);
	}

	/**
	 * @return the terrName
	 */
	public String getTerrName() {
		return terrName;
	}

	/**
	 * @param terrName the terrName to set
	 */
	public void setTerrName(String terrName) {
		this.terrName = terrName;
	}

}
