package view;
import javax.swing.JButton;
import javax.swing.JFrame;

import model.Maps;
import model.Territory;

public class InfoView extends JFrame{
	
	JButton attackButton;
	JButton fortifyButton;

	/**
	 * Information View constructor
	 */
	public InfoView() {

	}
	
	/**
	 * Show territory information
	 */
	public void showTerritoryInfo(Territory objTerritory) {
		System.out.println("Hello");
	}
}
