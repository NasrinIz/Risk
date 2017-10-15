package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.GameConfig;
import view.InfoView;
import view.MainWindow;
import view.StarterWindow;

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
			// TODO Auto-generated method stub
			infoView = new InfoView();
			infoView.showTerritoryInfo(null);
		}
	}
	
}
