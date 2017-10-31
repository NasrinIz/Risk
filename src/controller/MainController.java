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
public class MainController 
{

	private StarterWindow starterView;
	private MainWindow mainWindow;
	private GameConfig gameConfig;
	private GenericFunctions genericFunctionsObj = new GenericFunctions();
	private MapEditor mapEditor;
	private String previousCountryName = null;
	/*
	 * 0 New game 1 Edit or create
	 */
	private Integer applicationMode = 0;

	/**
	 * This is the constructor to the controller.
	 * @param starterView reference to the view to register appropriate action listeners.
	 */
	public MainController(StarterWindow starterView) 
	{
		this.starterView = starterView;
		this.starterView.addMenuItemNewGameActionListener(new NewGameListener());
	}

	/**
	 * This is the inner class, to define action listener to the menu option "New Game"
	 * @author Team20
	 */
	private class NewGameListener implements ActionListener 
	{
		/**
		 * This function over-rides the actionPerformed function of ActionListerner class
		 * and registers listeners for starterview
		 * @param e Action Event received by user interaction
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			starterView.showStarterForm();
			starterView.addRadioLoadMapActionListener(new loadMapListener());
			starterView.addRadioSelectMapActionListener(new selectMapListener());
			starterView.addRadioCreateMapActionListener(new createMapListener());
			starterView.addSubmitButtontActionListener(new submitButtonListener());
		}
	}

	/**
	 * This is the inner class, to define action listener to the option "Load Map"
	 * @author Team20
	 */
	private class loadMapListener implements ActionListener 
	{
		/**
		 * This function over-rides the actionPerformed function of ActionListerner class
		 * and registers listeners for starter view's load map form.
		 * @param e Action Event received by user interaction
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			applicationMode = 0;
			starterView.showLoadMapForm();
		}
	}

	/**
	 * This is the inner class, to define action listener to the option "Select Map"
	 * @author Team20
	 */
	private class selectMapListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			applicationMode = 0;
			starterView.showSelectMapForm();
			starterView.addEditMapRadioBtnListener(new editMapListener());
		}
	}

	/**
	 * This is the inner class, to define action listener to the option "Create Map"
	 * @author Team20
	 */
	private class createMapListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			applicationMode = 1;
			starterView.showCreateMapForm();
			System.out.println(starterView.getMapSaveLocation());
			mapEditor = new MapEditor(2, starterView.getMapSaveLocation());
			starterView.finishAddingContinentBtnActionListener(new finishAddContinentListener());
			starterView.addContinentBtnActionListener(new addContinentListener());
		}
	}

	/**
	 * This is the inner class, to define action listener to the option "Edit Map"
	 * @author Team20
	 */
	private class editMapListener implements ActionListener 
	{
		@SuppressWarnings("null")
		@Override
		public void actionPerformed(ActionEvent e) 
		{
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

	/**
	 * This is the inner class, to define action listener to the option "Finish Add Continent Button"
	 * @author Team20
	 */
	private class finishAddContinentListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
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

	/**
	 * This is the inner class, to define action listener to the option "Add Continent Button"
	 * @author Team20
	 */
	private class addContinentListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String continentInfo = starterView.getContinentValues();
			mapEditor.addContinent(continentInfo);
		}
	}

	/**
	 * This is the inner class, to define action listener to the option "Add Country Button"
	 * @author Team20
	 */
	private class addCountryListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String countryInfo = starterView.getCountryValues();
			mapEditor.newCountry(countryInfo);
		}
	}

	/**
	 * This is the inner class, to define action listener to the option "Remove Country Button"
	 * @author Team20
	 */
	private class removeCountryListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String countryInfo = starterView.getCountryValue();
			mapEditor.delCountry(countryInfo);
		}
	}

	/**
	 * This is the inner class, to define action listener to the option "Remove Continent Button"
	 * @author Team20
	 */
	private class removeContinentListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String continentInfo = starterView.getContinentValue();
			mapEditor.deleteContinent(continentInfo);
		}
	}

	/**
	 * This is the inner class, to define action listener to the option "Submit Button"
	 * @author Team20
	 */
	private class submitButtonListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub

			if (applicationMode == 1) 
			{
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

			String error = gameConfig.getMapObj().validateMap();

			if (!error.equals("true")) 
			{
				mainWindow.getErrorInfoView().showErrorInfo(error);
				mainWindow.removeCountryButtons();
			}

		}
	}

	/**
	 * This is the inner class, to define action listener to the territory buttons
	 * @author Team20
	 */
	private class territoryListener implements ActionListener 
	{
		String countryName;

		territoryListener(String countryName) 
		{
			this.countryName = countryName;
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			Integer currentPlayerId = gameConfig.getCurrentPlayer().getPlayerId();
			Integer territoryOwner = (gameConfig.getMapObj().getDictTerritory().get(countryName).getOwner());
			Integer gamePhase = gameConfig.getGamePhase();
			if ((gamePhase == genericFunctionsObj.GAMEPHASESTARTUP) 
					|| (gamePhase == genericFunctionsObj.GAMEPHASEREINFORCEMENT)
					|| (gamePhase == genericFunctionsObj.GAMEPHASEFORTIFICATION)) 
			{
				if (Objects.equals(currentPlayerId, territoryOwner)) 
				{
					if ((gamePhase == genericFunctionsObj.GAMEPHASESTARTUP) 
							|| (gamePhase == genericFunctionsObj.GAMEPHASEREINFORCEMENT)) 
					{
						gameConfig.getCurrentPlayer().placeArmiesOnTerritory(countryName);
						gameConfig.nextPlayerOrPhase();
					}

					if ((previousCountryName != null) 
							&& (gamePhase == genericFunctionsObj.GAMEPHASEFORTIFICATION)) 
					{
						if(!previousCountryName.equals(countryName)) 
						{
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

	/**
	 * This function calls for the addition of all the territory listeners in the territory hashmap
	 */
	private void addTerritoryListeners() 
	{
		Iterator<Entry<String, TerritoryView>> it = mainWindow.getDictTerrViews().entrySet().iterator();
		while (it.hasNext()) 
		{
			Map.Entry<String, TerritoryView> pair = it.next();
			TerritoryView tv = pair.getValue();
			tv.addTerritoryBtnListener(new territoryListener(pair.getKey()));
			it.remove(); // avoids a ConcurrentModificationException
		}

	}

}
