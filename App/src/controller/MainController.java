package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;

import model.GameConfig;
import model.Maps;
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
			 //TODO Auto-generated method stub
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
			addTerritoryListeners();
		}
	}

	private class territoryListener implements ActionListener {
		String countryName;
		territoryListener(String countryName) {
			this.countryName = countryName;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			 //TODO Auto-generated method stub
			String info = (gameConfig.getMapObj().getDictTerritory().get(countryName)).toString();
			mainWindow.getInfoView().showTerritoryInfo(info);
		}
	}

	public ArrayList<String> getTerritoryList(Maps mapObj) {
		territories = new ArrayList<String>();
		for (String territoryName : mapObj.getDictTerritory().keySet()) {
			territoryName = (mapObj.getDictTerritory()).get(territoryName).getName();
			territories.add(territoryName);
		}
		return territories;
	}

	public ArrayList<String> getContinentList(Maps mapObj) {
		continents = new ArrayList<String>();
		for (String continentName : mapObj.getDictTerritory().keySet()) {
			continentName = (mapObj.getDictTerritory()).get(continentName).getContinent();
			continents.add(continentName);
		}
		return continents;
	}
	
	public void addTerritoryListeners() {
		Iterator<Entry<String, TerritoryView>> it = mainWindow.getDictTerrViews().entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String, TerritoryView> pair = (Entry<String, TerritoryView>)it.next();
	        TerritoryView tv = pair.getValue();
	        tv.addTerritoryBtnListener(new territoryListener(pair.getKey()));
	        it.remove(); // avoids a ConcurrentModificationException
	    }

	}

}
