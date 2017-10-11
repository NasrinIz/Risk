package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;

public class StarterWindow extends JFrame {
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItemNewGame;
	private JLabel lblNumPlayers;
	private JLabel lblMapSelect;
	private JComboBox<Integer> playerNum;
	private JRadioButton radioLoadMap;
	private JRadioButton radioSelectMap;
	private JRadioButton radioCreateMap;
	private JComboBox<String> mapList;
	private JButton submitButton;

	ButtonGroup group = new ButtonGroup();

	private BorderLayout borderLayout;

	public StarterWindow() {
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		menuItemNewGame = new JMenuItem("New Game");
		setSize(getMaximumSize());
		this.initWindow();
		this.initMenuBar();
		this.initContentPane();
	}

	private void initWindow() {
		// Size the frame.
	    setSize( new Dimension(WIDTH, HEIGHT) );
	    setMinimumSize( new Dimension(WIDTH, HEIGHT) );
	    setMaximumSize( new Dimension(WIDTH, HEIGHT) );    
		this.setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 489);
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
	}

	private void initContentPane() {
		borderLayout = new BorderLayout();
		getContentPane().add(menuBar);
		this.setLayout(borderLayout);
	}

	private void initMenuBar() {
		setJMenuBar(menuBar);
		menuBar.add(menu);
		menu.add(menuItemNewGame);
	}

	public void addMenuItemNewGameActionListener(ActionListener listenForMenuItemNewGame) {
		menuItemNewGame.addActionListener(listenForMenuItemNewGame);
	}

	public void addRadioLoadMapActionListener(ActionListener listenForLoadMap) {
		radioLoadMap.addActionListener(listenForLoadMap);
	}

	public void addRadioSelectMapActionListener(ActionListener listenForSelectMap) {
		radioSelectMap.addActionListener(listenForSelectMap);
	}

	public void addRadioCreateMapActionListener(ActionListener listenForCreateMap) {
		radioCreateMap.addActionListener(listenForCreateMap);
	}

	public void addMapListActionListener(ActionListener listenForMapList) {
		mapList.addActionListener(listenForMapList);
	}

	public void addSubmitButtontActionListener(ActionListener listenForSubmitButton) {
		submitButton.addActionListener(listenForSubmitButton);
	}

	public void showStarterForm() {

		radioLoadMap = new JRadioButton("Load map");
		radioLoadMap.setBounds(10, 80, 200, 30);
		
		radioSelectMap = new JRadioButton("Select map");
		radioSelectMap.setBounds(220, 80, 200, 30);
		
		radioCreateMap = new JRadioButton("Create map");
		radioCreateMap.setBounds(430, 80, 200, 30);

		lblNumPlayers = new JLabel("Number of Human Players: ");
		lblNumPlayers.setBounds(10, 40, 200, 20);

		Integer[] playerNums = new Integer[] { 2, 3, 4, 5, 6 };

		playerNum = new JComboBox<>(playerNums);
		playerNum.setBounds(200, 40, 200, 20);

		submitButton = new JButton("Submit");
		submitButton.setBounds(400, 150, 100, 30);
		//submitButton.setEnabled(false);
		group = new ButtonGroup();
		group.add(radioLoadMap);
		group.add(radioSelectMap);
		group.add(radioCreateMap);

		this.getContentPane().add(lblNumPlayers);
		this.getContentPane().add(playerNum);
		this.getContentPane().add(radioLoadMap);
		this.getContentPane().add(radioSelectMap);
		this.getContentPane().add(radioCreateMap);
		this.getContentPane().add(submitButton);
		this.getContentPane().repaint();
	}

	public void showSelectMapForm() {
		lblMapSelect = new JLabel("Select a map: ");
		lblMapSelect.setBounds(10, 120, 200, 20);

		String[] mapTitles = new String[] { "Atlantis", "DiMul", "Europe", "Old Yorkshire", "Polygons", "Twin Volcano",
				"USA", "World", "valid_1"};

		mapList = new JComboBox<>(mapTitles);
		mapList.setBounds(120, 120, 200, 20);

		this.getContentPane().add(lblMapSelect);
		this.getContentPane().add(mapList);
		this.getContentPane().repaint();
	}

	public Integer getPlayerNumbers() {
		Integer numPlayers = (Integer)playerNum.getSelectedItem();
		return numPlayers;
	}

	public String getSelectedMap() {
		String selectedMap = String.valueOf(mapList.getSelectedItem());
		return selectedMap;
	}

}
