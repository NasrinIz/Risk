package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.GameConfig;
import model.Maps;
import model.Territory;
import view.InfoView;
import view.MainWindow;
import view.StarterWindow;
import view.TerritoryView;

public class MainController {
	private StarterWindow starterView;
	private MainWindow mainWindow;
	private GameConfig gameConfig;
	private Maps objMap = null;
	private TerritoryView territoryView;
	private InfoView infoView;
	
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
		
		}
	}
	
	private class selectMapListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			starterView.showSelectMapForm();
		}
	}
	
	private class createMapListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		
		}
	}
	
	private class submitButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			Integer playerNum = starterView.getPlayerNumbers();
			String selectedMap = starterView.getSelectedMap();
			
			String mapTxtLoc = String.format("Resources//Maps//%s.map", selectedMap);
			String mapImgLoc = String.format("Resources//Maps//%s.bmp", selectedMap);
			
			objMap = new Maps(mapTxtLoc);
			objMap.setMapName(selectedMap);
			
			mainWindow = new MainWindow();
			mainWindow.addCountryButtons(objMap);
			mainWindow.setVisible(true);
			starterView.setVisible(false);
			
			gameConfig = new GameConfig();   //To Do ... GameConfig needs a fields constructor
			gameConfig.setMapObj(objMap);	// move later to constructor
			gameConfig.generateCards();
			//System.out.println(gameConfig.getGameCards());
			
		}
	}
	
	private class territoryListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			infoView = new InfoView();
			infoView.showTerritoryInfo(null);
		}
	}
}
