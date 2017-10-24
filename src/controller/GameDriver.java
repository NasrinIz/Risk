package src.controller;

import javax.swing.SwingWorker;

import src.model.GenFun;

/**
 * Game driver will go through the phases of the game which are Startup, Reinforcement, Fortification, ...
 */
public class GameDriver extends SwingWorker<Integer, Integer>{

    private GenFun genObj = new GenFun();
	private MainController mainController = null;
	
	public GameDriver(MainController inMainController)
	{
		mainController = inMainController;
	}
	
	@Override
	protected Integer doInBackground() throws Exception {
        Integer gamePhase = 0;
        if(mainController.getGamePhase() == genObj.GAMEPHASESTARTUP)
			firePropertyChange("gamePhase", genObj.GAMEPHASENONE, genObj.GAMEPHASESTARTUP);
		else if(mainController.getGamePhase() == genObj.GAMEPHASEREINFORCEMENT)
			firePropertyChange("gamePhase", genObj.GAMEPHASESTARTUP, genObj.GAMEPHASEREINFORCEMENT);
		else if(mainController.getGamePhase() == genObj.GAMEPHASEFORTIFICATION)
			firePropertyChange("gamePhase", genObj.GAMEPHASEREINFORCEMENT, genObj.GAMEPHASEFORTIFICATION);
		else
			firePropertyChange("gamePhase", gamePhase, genObj.GAMEPHASENONE);
		
		return null;
	}
	
}
