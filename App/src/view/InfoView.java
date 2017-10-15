package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
//import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import model.Maps;

/**
 * @author Team20
 */
public class InfoView extends JPanel {
	private JLabel terrNameLbl;
	private JLabel terrNumArmiesLbl;
	private JLabel terrAdjacentCountriesLbl;

	public InfoView() {
		this.showTerritoryPanel();
	}

	/**
	 * Show territory information panel
	 */
	public void showTerritoryPanel() {
		this.setBackground(Color.GRAY);
		this.setLayout(new FlowLayout());
		terrNameLbl = new JLabel("Territory Name: ");
		terrNumArmiesLbl = new JLabel("Number of Armies: ");
		terrAdjacentCountriesLbl = new JLabel("Adjacent countries: ");
		this.setBounds(1024, 0, 175, 768);
		this.add(terrNameLbl);
		this.add(terrNumArmiesLbl);
		this.add(terrAdjacentCountriesLbl);
		TitledBorder border = new TitledBorder("Information Panel");
	    border.setTitleJustification(TitledBorder.CENTER);
	    border.setTitlePosition(TitledBorder.TOP);
		this.setBorder(border);
		this.setVisible(true);
	}

	public void showTerritoryInfo() {

	}

}
