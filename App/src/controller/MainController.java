package controller;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private String[] territories = null;
	private String[] continents = null;
	private Integer i = 0;
	private Integer j = 0 ;
	
	public MainController(StarterWindow starterView){
		this.starterView = starterView;
		this.starterView.addMenuItemNewGameActionListener(new NewGameListener());
	}

	private class NewGameListener implements ActionListener{
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
	
	private class loadMapListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			starterView.showLoadMapForm();
		
		}
	}
	
	private class selectMapListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			starterView.showSelectMapForm();
			starterView.addEditMapRadioBtnListener(new editMapListener());
		}
	}
	
	private class createMapListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			starterView.showCreateMapForm();
		
		}
	}
	
	private class editMapListener implements ActionListener{
		@SuppressWarnings("null")
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			Integer playerNum = starterView.getPlayerNumbers();
			String selectedMap = starterView.getSelectedMap();
			
			gameConfig = new GameConfig(playerNum, selectedMap);
			
			mainWindow = new MainWindow();
			mapObj = gameConfig.getMapObj();
			

			String[] mapTitles = new String[] { "Atlantis", "DiMul", "Europe", "Old Yorkshire", "Polygons", "Twin Volcano",
					"USA", "World", "valid_1" };
			
			starterView.showEditMapForm(mapTitles,mapTitles);
		}
	}
	
	private class submitButtonListener implements ActionListener{
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
	
	private class territoryListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("hi hi hi");
			// TODO Auto-generated method stub
			infoView = new InfoView();
			infoView.showTerritoryInfo();
		}
	}
	
}
