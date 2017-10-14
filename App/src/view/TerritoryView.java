package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TerritoryView extends JPanel{
	
	JLabel terrNameLbl;
	JLabel armiesNumLbl;
	Point terrPos;
	Color color;

	/**
	 * fields constructor
	 * 
	 * @param terrName	name of territory
	 * @param armiesNum	number of armies placed on territory
	 * @param terrPos	coordinates of the position of the territory 
	 */
	public TerritoryView(String terrName, String armiesNum, Point terrPos, Color color) {
		super();
		this.setBackground(color);
		this.setLayout(new FlowLayout());
		terrNameLbl = new JLabel( terrName );
		armiesNumLbl = new JLabel( armiesNum );
		armiesNumLbl.setForeground(Color.red);
		this.terrPos = terrPos;
		this.add(terrNameLbl);
		this.add(armiesNumLbl);
		this.setBounds(terrPos.x, terrPos.y, 60, 40);
		this.setVisible(true);
	}


	/**
	 * @param aListener
	 */
	public void addListener(ActionListener aListener) {
		this.addListener(aListener);
	}
}
