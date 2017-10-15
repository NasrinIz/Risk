package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TerritoryView extends JPanel {

	JLabel armiesNumLbl;
	Point terrPos;
	Color color;
	JButton terrNameBtn;

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
	public TerritoryView(String terrName, String armiesNum, Point terrPos, Color color) {
		this.setBackground(color);
		this.setLayout(new FlowLayout());
		terrNameBtn = new JButton(armiesNum + " " +terrName);
		terrNameBtn.setPreferredSize(new Dimension(40, 10));
		this.terrPos = terrPos;
		this.add(terrNameBtn);
		this.setBounds(terrPos.x, terrPos.y, 45, 40);
		this.setVisible(true);
	}

	/**
	 * @param aListener
	 */
	public void addListener(ActionListener aListener) {
		this.addListener(aListener);
		System.out.println("this listener");
	}

	/**
	 * @param territoryBtnListener
	 */
	public void addTerritoryBtnListener(ActionListener territoryBtnListener) {
		terrNameBtn.addActionListener(territoryBtnListener);
	}

}
