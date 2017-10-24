package src;

import javax.swing.SwingUtilities;

import src.controller.MainController;
import src.view.StarterWindow;
/**
 * This is the main class that is used to instantiate the src.controller, from where
 * the flow of the game starts. The player can then either start new game, or use
 * map editor.
 * @author Team20
 * 
 */
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
