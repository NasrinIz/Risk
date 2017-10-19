package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;

import model.GameConfig;
import model.GenFun;
import model.Maps;
import model.Player;
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
	/**
	 * reference to mainView
	 */
	private MainWindow mainWindow;
	private InfoView infoView;
	private GameConfig gameConfig;
	private Maps mapObj;
	private ArrayList<String> territories = null;
	private ArrayList<String> continents = null;

	public Integer gamePhase = 0;
	private GenFun genFunObj = new GenFun();
	
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
			starterView.finishAddingContinentBtnActionListener(new finishAddContinentListener());
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
			mapObj = gameConfig.getMapObj();

			String[] territoryList = getTerritoryList(mapObj).toArray(new String[getTerritoryList(mapObj).size()]);
			String[] continentList = getContinentList(mapObj).toArray(new String[getContinentList(mapObj).size()]);

			starterView.showEditMapForm(territoryList, continentList);

			starterView.finishAddingContinentBtnActionListener(new finishAddContinentListener());
		}
	}

	private class finishAddContinentListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			Integer playerNum = starterView.getPlayerNumbers();
			String selectedMap = starterView.getSelectedMap();
			
			gameConfig = new GameConfig(playerNum, selectedMap);
			mapObj = gameConfig.getMapObj();

			String[] continentList = getContinentList(mapObj).toArray(new String[getContinentList(mapObj).size()]);
			starterView.showAddCountryForm(continentList);
		}
	}

	private class passBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Player tmpPlayers[] = gameConfig.getPlayers();
			int i;
			
			for(i = 0; i<tmpPlayers.length; i++)
			{
				if(tmpPlayers[i].getTurnStatus() == false)
					break;
			}
			
			if(i >= tmpPlayers.length)
			{
				incrementGamePhase();
			}
			
			gameConfig.nextPlayerTurn();
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
			infoView =mainWindow.getInfoView();
			infoView.passBtnActionListener(new passBtnListener());
			

			if (gameConfig.getMapObj().readMap() != 0) {
				mainWindow.getErrorInfoView().showErrorInfo("Can not read map");
			}
			
			if (gameConfig.getMapObj().validateMap() != 0) {
				mainWindow.getErrorInfoView().showErrorInfo("This map is not valid");
			}
			
			
			gamePhase = genFunObj.GAMEPHASESTARTUP;
		}
	}

	private void incrementGamePhase()
	{
		if(gamePhase == genFunObj.GAMEPHASESTARTUP)
		{
			gamePhase = genFunObj.GAMEPHASEREINFORCEMENT;
		}
		else if(gamePhase == genFunObj.GAMEPHASEREINFORCEMENT)
		{
			gamePhase = genFunObj.GAMEPHASEFORTIFICATION;
		}
		else if(gamePhase == genFunObj.GAMEPHASEFORTIFICATION)
		{
			gamePhase = genFunObj.GAMEPHASEREINFORCEMENT;
		}
	}

	private class territoryListener implements ActionListener {
		String countryName;

		territoryListener(String countryName) {
			this.countryName = countryName;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Integer currentPlayerId = gameConfig.getCurrentPlayer().getPlayerId();
			Integer territoryOwner = (gameConfig.getMapObj().getDictTerritory().get(countryName).getOwner());
			
			if((gamePhase == genFunObj.GAMEPHASESTARTUP) || (gamePhase == genFunObj.GAMEPHASEREINFORCEMENT) ||
					(gamePhase == genFunObj.GAMEPHASEFORTIFICATION))
			{
				if(currentPlayerId == territoryOwner)
				{
					String info = (gameConfig.getMapObj().getDictTerritory().get(countryName)).toString();
					mainWindow.getInfoView().showTerritoryInfo(info);
					
					if((gamePhase == genFunObj.GAMEPHASESTARTUP) || (gamePhase == genFunObj.GAMEPHASEREINFORCEMENT))
					{
						gameConfig.getCurrentPlayer().placeArmiesOnTerritory(countryName);
					}
				}
			}
			
			
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
			Map.Entry<String, TerritoryView> pair = (Entry<String, TerritoryView>) it.next();
			TerritoryView tv = pair.getValue();
			tv.addTerritoryBtnListener(new territoryListener(pair.getKey()));
			it.remove(); // avoids a ConcurrentModificationException
		}

	}

}
