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
import javax.swing.JTextField;

import model.Maps;

public class StarterWindow extends JFrame {
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItemNewGame;
	private JLabel lblNumPlayers;
	private JLabel lblMapSelect;
	private JLabel lblMapLoad;
	private JComboBox<Integer> playerNum;
	private JRadioButton radioLoadMap;
	private JRadioButton radioSelectMap;
	private JRadioButton radioCreateMap;
	private JComboBox<String> mapList;
	private JTextField loadMapField;
	private JButton submitButton;
	private JRadioButton editMapRadioBtn;
	private JTextField adjacentCountriesField;
	private JLabel adjacentCountriesLbl;
	private JLabel addCountryLbl;
	private JTextField addCountryField;
	private JLabel addContinentLbl;
	private JTextField addContinentField;
	private JButton finishAddingContinentsBtn;
	private JLabel editCountryLbl;
	private JComboBox<String> editCountryList;
	private JLabel editContinentLbl;
	private JComboBox<String> editContinentList;
	private JButton addContinentButton;
	private JButton addCountryButton;
	private JButton editMapButton;
	private JLabel addCountryToContinentLbl;
	private JComboBox <String> continentList;

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
		setSize(new Dimension(WIDTH, HEIGHT));
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		this.setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
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

	/**
	 * @param listenForMenuItemNewGame
	 */
	public void addMenuItemNewGameActionListener(ActionListener listenForMenuItemNewGame) {
		menuItemNewGame.addActionListener(listenForMenuItemNewGame);
	}

	/**
	 * @param listenForLoadMap
	 */
	public void addRadioLoadMapActionListener(ActionListener listenForLoadMap) {
		radioLoadMap.addActionListener(listenForLoadMap);
	}

	/**
	 * @param listenForSelectMap
	 */
	public void addRadioSelectMapActionListener(ActionListener listenForSelectMap) {
		radioSelectMap.addActionListener(listenForSelectMap);
	}

	/**
	 * @param listenForCreateMap
	 */
	public void addRadioCreateMapActionListener(ActionListener listenForCreateMap) {
		radioCreateMap.addActionListener(listenForCreateMap);
	}

	/**
	 * @param listenForMapList
	 */
	public void addMapListActionListener(ActionListener listenForMapList) {
		mapList.addActionListener(listenForMapList);
	}

	/**
	 * @param listenForSubmitButton
	 */
	public void addSubmitButtontActionListener(ActionListener listenForSubmitButton) {
		submitButton.addActionListener(listenForSubmitButton);
	}

	/**
	 * @param listenForSubmitButton
	 */
	public void finishAddingContinentBtnActionListener(ActionListener listenForContinentBtn) {
		finishAddingContinentsBtn.addActionListener(listenForContinentBtn);
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
		playerNum.setBounds(220, 40, 200, 20);

		submitButton = new JButton("Start New Game");
		submitButton.setBounds(10, 500, 400, 30);

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
				"USA", "World", "valid_1" };

		mapList = new JComboBox<>(mapTitles);
		mapList.setBounds(220, 120, 200, 20);

		editMapRadioBtn = new JRadioButton("Edit the selected Map");
		editMapRadioBtn.setBounds(430, 120, 200, 20);

		if (editMapRadioBtn != null) {
			editMapRadioBtn.setSelected(false);
		}

		this.removeEditMapForm();
		this.removeLoadMapForm();
		this.removeCreateMapForm();
		this.removeAddCountryForm();

		this.getContentPane().add(editMapRadioBtn);
		this.getContentPane().add(lblMapSelect);
		this.getContentPane().add(mapList);
		this.getContentPane().repaint();
	}

	public void showLoadMapForm() {
		lblMapLoad = new JLabel("Load a map: ");
		lblMapLoad.setBounds(10, 120, 200, 20);

		loadMapField = new JTextField();
		loadMapField.setBounds(220, 120, 200, 20);

		if (editMapRadioBtn != null) {
			editMapRadioBtn.setSelected(false);
		}

		this.removeSelectMapForm();
		this.removeCreateMapForm();
		this.removeEditMapForm();
		this.removeAddCountryForm();

		this.getContentPane().add(lblMapLoad);
		this.getContentPane().add(loadMapField);
		this.getContentPane().repaint();
	}

	public void showCreateMapForm() {

		addContinentLbl = new JLabel("Add Continent: ");
		addContinentLbl.setBounds(10, 150, 200, 20);

		addContinentField = new JTextField();
		addContinentField.setBounds(220, 150, 200, 20);

		addContinentButton = new JButton("Add continent");
		addContinentButton.setBounds(10, 200, 200, 30);

		finishAddingContinentsBtn = new JButton("Finish adding continent");
		finishAddingContinentsBtn.setBounds(10, 250, 200, 30);

		if (editMapRadioBtn != null) {
			editMapRadioBtn.setSelected(false);
		}

		this.removeEditMapForm();
		this.removeSelectMapForm();
		this.removeLoadMapForm();
		this.removeAddCountryForm();

		this.getContentPane().add(addContinentLbl);
		this.getContentPane().add(addContinentField);
		this.getContentPane().add(addContinentButton);
		this.getContentPane().add(finishAddingContinentsBtn);
		this.getContentPane().repaint();
	}

	public void showAddCountryForm(String[] continents) {

		addCountryLbl = new JLabel("Add Country: ");
		addCountryLbl.setBounds(10, 150, 200, 20);

		addCountryField = new JTextField();
		addCountryField.setBounds(220, 150, 200, 20);

		adjacentCountriesLbl = new JLabel("Adjacent Countries: ");
		adjacentCountriesLbl.setBounds(10, 200, 200, 20);

		adjacentCountriesField = new JTextField();
		adjacentCountriesField.setBounds(220, 200, 200, 20);

		addCountryToContinentLbl = new JLabel("Add Country to continent: ");
		addCountryToContinentLbl.setBounds(10, 250, 200, 20);

		continentList = new JComboBox<String>(continents);
		continentList.setBounds(220, 250, 200, 20);

		addCountryButton = new JButton("Add Country");
		addCountryButton.setBounds(220, 300, 200, 30);

		this.removeCreateMapForm();

		this.getContentPane().add(addCountryLbl);
		this.getContentPane().add(addCountryField);
		this.getContentPane().add(adjacentCountriesLbl);
		this.getContentPane().add(adjacentCountriesField);
		this.getContentPane().add(addCountryButton);
		this.getContentPane().add(addCountryToContinentLbl);
		this.getContentPane().add(continentList);
		this.getContentPane().repaint();
	}

	public void showEditMapForm(String[] countries, String[] Continents) {
		editCountryLbl = new JLabel("Edit country: ");
		editCountryLbl.setBounds(10, 400, 200, 20);

		editCountryList = new JComboBox<>(countries);
		editCountryList.setBounds(220, 400, 200, 20);

		editContinentLbl = new JLabel("Edit continent: ");
		editContinentLbl.setBounds(420, 400, 200, 20);

		editContinentList = new JComboBox<>(Continents);
		editContinentList.setBounds(620, 400, 200, 20);

		editMapButton = new JButton("Remove from Map");
		editMapButton.setBounds(10, 450, 200, 30);

		this.removeLoadMapForm();
		this.showCreateMapForm();

		this.getContentPane().add(editCountryLbl);
		this.getContentPane().add(editCountryList);
		this.getContentPane().add(editContinentLbl);
		this.getContentPane().add(editContinentList);
		this.getContentPane().add(editMapButton);
		this.getContentPane().repaint();
	}

	/**
	 * @param listenForEditMapBtn
	 */
	public void addEditMapRadioBtnListener(ActionListener listenForEditMapRadioBtn) {
		editMapRadioBtn.addActionListener(listenForEditMapRadioBtn);
	}

	/**
	 * @return numPlayers
	 */
	public Integer getPlayerNumbers() {
		Integer numPlayers = (Integer) playerNum.getSelectedItem();
		return numPlayers;
	}

	/**
	 * @return selectedMap
	 */
	public String getSelectedMap() {
		String selectedMap = String.valueOf(mapList.getSelectedItem());
		return selectedMap;
	}

	public void removeCreateMapForm() {
		if (addContinentLbl != null && addContinentField != null && addContinentButton != null
				&& finishAddingContinentsBtn != null) {
			this.getContentPane().remove(addContinentLbl);
			this.getContentPane().remove(addContinentField);
			this.getContentPane().remove(addContinentButton);
			this.getContentPane().remove(finishAddingContinentsBtn);
			this.getContentPane().repaint();
		}
	}

	public void removeAddCountryForm() {
		if (addCountryLbl != null && addCountryField != null && adjacentCountriesLbl != null
				&& adjacentCountriesField != null &&  addCountryButton != null && addCountryToContinentLbl != null
				&& continentList != null) {
			this.getContentPane().add(addCountryLbl);
			this.getContentPane().add(addCountryField);
			this.getContentPane().add(adjacentCountriesLbl);
			this.getContentPane().add(adjacentCountriesField);
			this.getContentPane().add(addCountryButton);
			this.getContentPane().add(addCountryToContinentLbl);
			this.getContentPane().add(continentList);
			this.getContentPane().repaint();
		}
	}

	public void removeEditMapForm() {
		if (editCountryLbl != null && editCountryList != null && editContinentLbl != null && editContinentList != null
				&& editMapButton != null) {
			this.getContentPane().remove(editCountryLbl);
			this.getContentPane().remove(editCountryList);
			this.getContentPane().remove(editContinentLbl);
			this.getContentPane().remove(editContinentList);
			this.getContentPane().remove(editMapButton);
			this.removeCreateMapForm();
			this.getContentPane().repaint();
		}
	}

	public void removeSelectMapForm() {
		if (editMapRadioBtn != null && lblMapSelect != null && mapList != null) {
			this.getContentPane().remove(editMapRadioBtn);
			this.getContentPane().remove(lblMapSelect);
			this.getContentPane().remove(mapList);
			this.getContentPane().repaint();
		}
	}

	public void removeLoadMapForm() {
		if (lblMapLoad != null && loadMapField != null) {
			this.getContentPane().remove(lblMapLoad);
			this.getContentPane().remove(loadMapField);
			this.getContentPane().repaint();
		}
	}
}
