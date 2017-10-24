package src.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import src.model.GameConfig;
import src.model.GenericFunctions;
import src.model.MapEditor;
import src.model.Player;
import src.view.InfoView;
import src.view.MainWindow;
import src.view.StarterWindow;
import src.view.TerritoryView;

/**
 * As our architecture is MVC, this is the main src.controller, that adds action
 * listeners too all the GUI components, defined and declared in src.view.
 * This src.controller is also connected to the src.model package, so that it can
 * initiate the execution of appropriate business logic on the basis of specific events.
 * @author Team20
 *
 */
public class MainController {

	private StarterWindow starterView;
	private MainWindow mainWindow;
	private GameConfig gameConfig;
	private Integer gamePhase = 0;
	private GenericFunctions genericFunctionsObj = new GenericFunctions();
	private MapEditor mapEditor;
	private String previousCountryName = null;
	/*
	 * 0 New game 1 Edit or create
	 */
	private Integer applicationMode = 0;

	Integer getGamePhase() {
		return gamePhase;
	}

	public MainController(StarterWindow starterView) {
		this.starterView = starterView;
		this.starterView.addMenuItemNewGameActionListener(new NewGameListener());
	}

	/**
	 * 
	 * @author Team20
	 *
	 */
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
			applicationMode = 0;
			starterView.showLoadMapForm();

		}
	}

	private class selectMapListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			applicationMode = 0;
			starterView.showSelectMapForm();
			starterView.addEditMapRadioBtnListener(new editMapListener());
		}
	}

	private class createMapListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			applicationMode = 1;
			starterView.showCreateMapForm();
			System.out.println(starterView.getMapSaveLocation());
			mapEditor = new MapEditor(2, starterView.getMapSaveLocation());
			starterView.finishAddingContinentBtnActionListener(new finishAddContinentListener());
			starterView.addContinentBtnActionListener(new addContinentListener());
		}
	}

	private class editMapListener implements ActionListener {
		@SuppressWarnings("null")
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			applicationMode = 1;
			mapEditor = new MapEditor(1, starterView.getSelectedMap());

			// Integer playerNum = starterView.getPlayerNumbers();
			// String selectedMap = starterView.getSelectedMap();
			//
			// gameConfig = new GameConfig(playerNum, selectedMap);
			// mapObj = gameConfig.getMapObj();
			//
			// String[] territoryList = getTerritoryList(mapObj).toArray(new
			// String[getTerritoryList(mapObj).size()]);
			// String[] continentList = getContinentList(mapObj).toArray(new
			// String[getContinentList(mapObj).size()]);

			starterView.showEditMapForm(mapEditor.getCountryListInMapEditor(), mapEditor.getContinentListInMapEditor());

			starterView.finishAddingContinentBtnActionListener(new finishAddContinentListener());
			starterView.addContinentBtnActionListener(new addContinentListener());
			starterView.removeCountryBtnActionListener(new removeCountryListener());
			starterView.removeContinentBtnActionListener(new removeContinentListener());
			// starterView.addContinentAgainActionListener(new
			// addContinentAgainListener());
		}
	}

	private class finishAddContinentListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//
			// Integer playerNum = starterView.getPlayerNumbers();
			// String selectedMap = starterView.getSelectedMap();
			//
			// gameConfig = new GameConfig(playerNum, selectedMap);
			// mapObj = gameConfig.getMapObj();
			//
			// String[] continentList = getContinentList(mapObj).toArray(new
			// String[getContinentList(mapObj).size()]);

			starterView.showAddCountryForm(mapEditor.getContinentListInMapEditor());
			starterView.addCountryBtnActionListener(new addCountryListener());
		}
	}

	private class addContinentListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String continentInfo = starterView.getContinentValues();
			mapEditor.addContinent(continentInfo);
		}
	}

	private class addCountryListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String countryInfo = starterView.getCountryValues();
			mapEditor.newCountry(countryInfo);

		}
	}

	private class removeCountryListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String countryInfo = starterView.getCountryValue();
			mapEditor.delCountry(countryInfo);
		}
	}

	private class removeContinentListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String continentInfo = starterView.getContinentValue();
			mapEditor.deleteContinent(continentInfo);
		}
	}

	private class passBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Player tmpPlayers[] = gameConfig.getPlayers();
			int i;
			previousCountryName = null;
			gameConfig.getCurrentPlayer().setTurnStatus(true);
			for (i = 0; i < tmpPlayers.length; i++) {
				if (!tmpPlayers[i].getTurnStatus()) {
					break;
				}
			}

			if (i >= tmpPlayers.length) {
				incrementGamePhase();
				for (i = 0; i < tmpPlayers.length; i++) {
					tmpPlayers[i].setTurnStatus(false);
				}
			}

			gameConfig.nextPlayerTurn();
		}
	}

	private class submitButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			if (applicationMode == 1) {
				mapEditor.finishAndValidate(starterView.saveMapPathField.getText());
				return;
			}

			Integer playerNum = starterView.getPlayerNumbers();
			String selectedMap = starterView.getSelectedMap();

			gameConfig = new GameConfig(playerNum, selectedMap);

			mainWindow = new MainWindow();
			mainWindow.addCountryButtons(gameConfig.getMapObj());
			mainWindow.setVisible(true);
			starterView.setVisible(false);
			addTerritoryListeners();
			InfoView infoView = mainWindow.getInfoView();
			infoView.passBtnActionListener(new passBtnListener());
			gamePhase = genericFunctionsObj.GAMEPHASESTARTUP;

			String error = gameConfig.getMapObj().validateMap();

			if (!error.equals("true")) {
				mainWindow.getErrorInfoView().showErrorInfo(error);
				mainWindow.removeCountryButtons();
			}

		}
	}

	private void incrementGamePhase() {
		if (gamePhase == genericFunctionsObj.GAMEPHASESTARTUP) {
			gameConfig.calcReinforcementArmy();
			gamePhase = genericFunctionsObj.GAMEPHASEREINFORCEMENT;
			System.out.println("Startup Phase ends, Reinforcement Phase Begins");
		} else if (gamePhase == genericFunctionsObj.GAMEPHASEREINFORCEMENT) {
			gamePhase = genericFunctionsObj.GAMEPHASEFORTIFICATION;
			System.out.println("Reinforcement Phase ends, Fortification Phase Begins");
		} else if (gamePhase == genericFunctionsObj.GAMEPHASEFORTIFICATION) {
			gameConfig.calcReinforcementArmy();
			gamePhase = genericFunctionsObj.GAMEPHASEREINFORCEMENT;
			System.out.println("Fortification Phase ends, Reinforcement Phase Begins");
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

			if ((gamePhase == genericFunctionsObj.GAMEPHASESTARTUP) || (gamePhase == genericFunctionsObj.GAMEPHASEREINFORCEMENT)
					|| (gamePhase == genericFunctionsObj.GAMEPHASEFORTIFICATION)) {
				if (Objects.equals(currentPlayerId, territoryOwner)) {
					if ((gamePhase == genericFunctionsObj.GAMEPHASESTARTUP) || (gamePhase == genericFunctionsObj.GAMEPHASEREINFORCEMENT)) {
						gameConfig.getCurrentPlayer().placeArmiesOnTerritory(countryName);
					}

					if ((previousCountryName != null) && (gamePhase == genericFunctionsObj.GAMEPHASEFORTIFICATION)) {
						if(!previousCountryName.equals(countryName)) {
							gameConfig.fortifyArmies(previousCountryName, countryName);
						}
					}
					else if((previousCountryName == null) && (gamePhase == genericFunctionsObj.GAMEPHASEFORTIFICATION))
					{
						previousCountryName = countryName;
					}

					String info = (gameConfig.getMapObj().getDictTerritory().get(countryName)).toString();
					mainWindow.getInfoView().showTerritoryInfo(info);
				}
			}
		}
	}

	private void addTerritoryListeners() {
		Iterator<Entry<String, TerritoryView>> it = mainWindow.getDictTerrViews().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, TerritoryView> pair = it.next();
			TerritoryView tv = pair.getValue();
			tv.addTerritoryBtnListener(new territoryListener(pair.getKey()));
			it.remove(); // avoids a ConcurrentModificationException
		}

	}

}
