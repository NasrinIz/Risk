package src.controller;

import javax.swing.SwingWorker;

import src.model.GenFun;

public class GameDriver extends SwingWorker<Integer, Integer>{
	
	public static Integer gamePhase = 0;
	public GenFun genObj = new GenFun();
	private MainController mainController = null;
	
	public GameDriver(MainController inMainController)
	{
		mainController = inMainController;
	}
	
	@Override
	protected Integer doInBackground() throws Exception {
		if(mainController.gamePhase == genObj.GAMEPHASESTARTUP)
			firePropertyChange("gamePhase", genObj.GAMEPHASENONE, genObj.GAMEPHASESTARTUP);
		else if(mainController.gamePhase == genObj.GAMEPHASEREINFORCEMENT)
			firePropertyChange("gamePhase", genObj.GAMEPHASESTARTUP, genObj.GAMEPHASEREINFORCEMENT);
		else if(mainController.gamePhase == genObj.GAMEPHASEFORTIFICATION)
			firePropertyChange("gamePhase", genObj.GAMEPHASEREINFORCEMENT, genObj.GAMEPHASEFORTIFICATION);
		else
			firePropertyChange("gamePhase", gamePhase, genObj.GAMEPHASENONE);
		
		return null;
	}
	
}
