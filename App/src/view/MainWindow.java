package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import model.Maps;

public class MainWindow extends JFrame {
	
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	
	private JScrollPane mapPane;
	private BorderLayout borderLayout;
	public Maps objMap;

	public MainWindow() {
		super("Risky Conquest");		
		this.initWindow();
		this.initContentPane();
	}

	private void initWindow() {

		// Size the frame.
	    setSize( new Dimension(WIDTH, HEIGHT) );
	    setMinimumSize( new Dimension(WIDTH, HEIGHT) );
	    setMaximumSize( new Dimension(WIDTH, HEIGHT) );    
		this.setResizable(false);
		
//		setSize(getMaximumSize());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 567, 489);
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
	}

	private void initContentPane() {
		borderLayout = new BorderLayout();
		this.setLayout(borderLayout);
	}

	public void addCountryButtons(Maps objMap) {
		Integer x = new Integer(0);
		Integer y = new Integer(0);
		mapPane = new JScrollPane();
		mapPane.setBounds(0, 0, 1024, 768);
		mapPane.setMinimumSize(new Dimension(1200, 900));
		mapPane.setWheelScrollingEnabled(true);

		for (String territoryName : objMap.getDictTerritory().keySet()) {
			System.out.println();
			x = (objMap.getDictTerritory()).get(territoryName).getX();
			y = (objMap.getDictTerritory()).get(territoryName).getY();
			
			JButton btnTemp = new JButton(territoryName);
			btnTemp.setBounds(x - 4, y - 4, 10, 10);
			mapPane.add(btnTemp);
			mapPane.setComponentZOrder(btnTemp, 0);
			
			// comment the previous 4 lines and uncomment the next 3
//			TerritoryView terrPanel = new TerritoryView(territoryName, "3", new Point(x,y), Color.WHITE );
//			mapPane.add(terrPanel);
//			mapPane.setComponentZOrder(terrPanel, 0);
			
			// (objMap.dictTerritory).get(territoryName).getBtnTerritories().add(btnTemp);
		}

		this.getContentPane().add(mapPane);
		this.getContentPane().repaint();

	}
}
