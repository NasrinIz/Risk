package application;


import javax.swing.SwingUtilities;

import controller.GameDriver;
import controller.MainController;
import view.StarterWindow;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					StarterWindow frame = new StarterWindow();
					MainController mainController = new MainController(frame);
					//GameDriver gameDriver = new GameDriver(mainController);
					//gameDriver.addPropertyChangeListener(mainController);
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
}
