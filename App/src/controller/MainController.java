package controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.GameConfig;
import model.Maps;
import model.Territory;
import view.InfoView;
import view.MainWindow;
import view.StarterWindow;
import view.TerritoryView;

/**
 * @author Team20
 *
 */
public class MainController {

	private StarterWindow starterView;
	private InfoView infoView;
	/**
	 * reference to mainView
	 */
	private MainWindow mainWindow;
	/**
	 * reference to mainModel
	 */
	private GameConfig gameConfig;
	private TerritoryView territoryView;
	private Maps mapObj;
	private String territoryName;
	private String continentName;
	private ArrayList<String> territories = null;
	private ArrayList<String> continents = null;

	public MainController(StarterWindow starterView) {
		this.starterView = starterView;
		this.starterView.addMenuItemNewGameActionListener(new NewGameListener());
	}

	private class NewGameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			starterView.showStarterForm();
			starterView.addRadioLoadMapActionListener(new loadMapListener());
			starterView.addRadioSelectMapActionListener(new selectMapListener());
			starterView.addRadioCreateMapActionListener(new createMapListener());
			starterView.addSubmitButtontActionListener(new submitButtonListener());
		}
	}

	private class loadMapListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			starterView.showLoadMapForm();

		}
	}

	private class selectMapListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			starterView.showSelectMapForm();
			starterView.addEditMapRadioBtnListener(new editMapListener());
		}
	}

	private class createMapListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			starterView.showCreateMapForm();

		}
	}

	private class editMapListener implements ActionListener {
		@SuppressWarnings("null")
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			Integer playerNum = starterView.getPlayerNumbers();
			String selectedMap = starterView.getSelectedMap();

			gameConfig = new GameConfig(playerNum, selectedMap);

			mainWindow = new MainWindow();
			mapObj = gameConfig.getMapObj();

			String[] territoryList = getTerritoryList(mapObj).toArray(new String[getTerritoryList(mapObj).size()]);
			String[] continentList = getContinentList(mapObj).toArray(new String[getContinentList(mapObj).size()]);

			// String[] mapTitles = new String[] { "Atlantis", "DiMul",
			// "Europe", "Old Yorkshire", "Polygons", "Twin Volcano",
			// "USA", "World", "valid_1" };

			starterView.showEditMapForm(territoryList, continentList);
		}
	}

	private class submitButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			Integer playerNum = starterView.getPlayerNumbers();
			String selectedMap = starterView.getSelectedMap();

			gameConfig = new GameConfig(playerNum, selectedMap);

			mainWindow = new MainWindow();
			mainWindow.addCountryButtons(gameConfig.getMapObj());
			mainWindow.setVisible(true);
			starterView.setVisible(false);
		}
	}

	private class territoryListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("hi hi hi");
			// TODO Auto-generated method stub
			infoView = new InfoView();
			infoView.showTerritoryInfo();
		}
	}

	public ArrayList<String> getTerritoryList(Maps mapObj) {
		territories = new ArrayList<String>();
		for (String territoryName : mapObj.getDictTerritory().keySet()) {
			territoryName = (mapObj.getDictTerritory()).get(territoryName).getName();
			System.out.println(territoryName);
			territories.add(territoryName);
		}
		return territories;
	}

	public ArrayList<String> getContinentList(Maps mapObj) {
		continents = new ArrayList<String>();
		for (String continentName : mapObj.getDictTerritory().keySet()) {
			continentName = (mapObj.getDictTerritory()).get(continentName).getContinent();
			System.out.println(continentName);
			continents.add(continentName);
		}
		return continents;
	}

}
