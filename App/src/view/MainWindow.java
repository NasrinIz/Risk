package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import model.Maps;

public class MainWindow extends JFrame {
	private JScrollPane mapPane;
	private BorderLayout borderLayout;
	public Maps objMap;

	public MainWindow() {
		setSize(getMaximumSize());
		this.initWindow();
		this.initContentPane();
	}

	private void initWindow() {
		setTitle("Risk");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 489);
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

		for (String territoryName : objMap.getDictTerritory().keySet()) {
			System.out.println();
			x = (objMap.getDictTerritory()).get(territoryName).getX();
			y = (objMap.getDictTerritory()).get(territoryName).getY();
			JButton btnTemp = new JButton(territoryName);
			btnTemp.setBounds(x - 4, y - 4, 10, 10);
			mapPane.add(btnTemp);
			mapPane.setComponentZOrder(btnTemp, 0);
			// (objMap.dictTerritory).get(territoryName).getBtnTerritories().add(btnTemp);
		}

		this.getContentPane().add(mapPane);
		this.getContentPane().repaint();

	}
}
