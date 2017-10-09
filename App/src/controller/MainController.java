package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.GameConfig;
import model.Maps;
import view.MainWindow;
import view.StarterWindow;

public class MainController {
	private StarterWindow starterView;
	private MainWindow mainWindow;
	private GameConfig gameConfig;
	private Maps objMap = null;
	
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
			starterView.addRadioCreateMapActionListener(new CreateMapListener());
			starterView.addSubmitButtontActionListener(new SubmitButtonListener());
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
	
	private class CreateMapListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		
		}
	}
	
	private class SubmitButtonListener implements ActionListener{
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
			
			// To Do debug error ??!!!
//			gameConfig = new GameConfig();   //To Do ... GameConfig needs a fields constructor
//			gameConfig.generateCards();
//			System.out.println(gameConfig.getGameCards());
		}
	}
	
	
}
