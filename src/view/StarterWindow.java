package src.view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * This src.view is used to display the starter window when the form is given to user
 * to select the options, whether he wants to load the map, edit the map, or create
 * a new map.
 *
 * @author Team20
 */
public class StarterWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItemNewGame;
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
    private JLabel countryCoordinationLbl;
    private JTextField countryCoordinationField;
    private JLabel addContinentLbl;
    private JTextField addContinentField;
    private JLabel saveMapPathLbl;
    public JTextField saveMapPathField;
    private JLabel continentAwardLbl;
    private JTextField continentAwardField;
    private JButton finishAddingContinentsBtn;
    private JLabel editCountryLbl;
    private JTextField editCountryList;
    private JLabel editContinentLbl;
    private JTextField editContinentList;
    private JButton addContinentButton;
    private JButton addCountryButton;
    private JButton removeCountryButton;
    private JButton removeContinentButton;
    private JLabel addCountryToContinentLbl;
    private JTextField continentList;

    /**
     * Initializes starter window
     */
    public StarterWindow() {
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        menuItemNewGame = new JMenuItem("New Game");
        setSize(getMaximumSize());
        this.initWindow();
        this.initMenuBar();
        this.initContentPane();
    }

    /**
     * Initializes window
     */
    private void initWindow() {
        setSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.setResizable(false);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 1424, 768);
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    }

    /**
     * Initializes content pane
     */
    private void initContentPane() {
        BorderLayout borderLayout = new BorderLayout();
        getContentPane().add(menuBar);
        this.setLayout(borderLayout);
    }

    /**
     * Initializes menu bar
     */
    private void initMenuBar() {
        setJMenuBar(menuBar);
        menuBar.add(menu);
        menu.add(menuItemNewGame);
    }

    /**
     * Adds action listener for new game
     *
     * @param listenForMenuItemNewGame Menu item
     */
    public void addMenuItemNewGameActionListener(ActionListener listenForMenuItemNewGame) {
        menuItemNewGame.addActionListener(listenForMenuItemNewGame);
    }

    /**
     * adds action listener for radio map
     *
     * @param listenForLoadMap load map
     */
    public void addRadioLoadMapActionListener(ActionListener listenForLoadMap) {
        radioLoadMap.addActionListener(listenForLoadMap);
    }

    /**
     * adds action listener
     *
     * @param listenForSelectMap select map
     */
    public void addRadioSelectMapActionListener(ActionListener listenForSelectMap) {
        radioSelectMap.addActionListener(listenForSelectMap);
    }

    /**
     * adds action listener
     *
     * @param listenForCreateMap create map
     */
    public void addRadioCreateMapActionListener(ActionListener listenForCreateMap) {
        radioCreateMap.addActionListener(listenForCreateMap);
    }

    /**
     * adds action listener
     *
     * @param listenForSubmitButton submit button
     */
    public void addSubmitButtontActionListener(ActionListener listenForSubmitButton) {
        submitButton.addActionListener(listenForSubmitButton);
    }

    /**
     * adds action listener
     *
     * @param listenForContinentBtn continent button
     */
    public void finishAddingContinentBtnActionListener(ActionListener listenForContinentBtn) {
        finishAddingContinentsBtn.addActionListener(listenForContinentBtn);
    }

    /**
     * adds action listener
     *
     * @param listenForAddContinentBtn add continent button
     */
    public void addContinentBtnActionListener(ActionListener listenForAddContinentBtn) {
        addContinentButton.addActionListener(listenForAddContinentBtn);
    }

    /**
     * adds action listener
     *
     * @param listenForAddCountryBtn add country button
     */
    public void addCountryBtnActionListener(ActionListener listenForAddCountryBtn) {
        addCountryButton.addActionListener(listenForAddCountryBtn);
    }

    /**
     * adds action listener
     *
     * @param listenForRemoveCountryBtn remove country button
     */
    public void removeCountryBtnActionListener(ActionListener listenForRemoveCountryBtn) {
        removeCountryButton.addActionListener(listenForRemoveCountryBtn);
    }

    /**
     * adds action listener
     *
     * @param listenForAddContinentBtn add continent button
     */
    public void removeContinentBtnActionListener(ActionListener listenForAddContinentBtn) {
        removeContinentButton.addActionListener(listenForAddContinentBtn);
    }


    /**
     * Shows starter form
     */
    public void showStarterForm() {

        radioLoadMap = new JRadioButton("Load map");
        radioLoadMap.setBounds(10, 80, 200, 30);

        radioSelectMap = new JRadioButton("Select map");
        radioSelectMap.setBounds(220, 80, 200, 30);

        radioCreateMap = new JRadioButton("Create map");
        radioCreateMap.setBounds(430, 80, 200, 30);

        JLabel lblNumPlayers = new JLabel("Number of Human Players: ");
        lblNumPlayers.setBounds(10, 40, 200, 20);

        Integer[] playerNumbers = new Integer[]{2, 3, 4, 5, 6};

        playerNum = new JComboBox<>(playerNumbers);
        playerNum.setBounds(220, 40, 200, 20);

        submitButton = new JButton("Start New Game");
        submitButton.setBounds(590, 500, 260, 30);

        ButtonGroup group = new ButtonGroup();
        group.add(radioLoadMap);
        group.add(radioSelectMap);
        group.add(radioCreateMap);

        ErrorInfoView errorInfoView = new ErrorInfoView();

        this.getContentPane().add(lblNumPlayers);
        this.getContentPane().add(playerNum);
        this.getContentPane().add(radioLoadMap);
        this.getContentPane().add(radioSelectMap);
        this.getContentPane().add(radioCreateMap);
        this.getContentPane().add(submitButton);
        this.getContentPane().add(errorInfoView);
        this.getContentPane().repaint();
    }


    /**
     * Shows selected map form
     */
    public void showSelectMapForm() {
        lblMapSelect = new JLabel("Select a map: ");
        lblMapSelect.setBounds(10, 120, 200, 20);

        String[] mapTitles = new String[]{"Atlantis", "DiMul", "Europe", "Old Yorkshire", "Polygons", "Twin Volcano",
                "USA", "World", "valid_1", "vj_test"};

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


    /**
     * Shows load map form
     */
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


    /**
     * Show create map form
     */
    public void showCreateMapForm() {

        addContinentLbl = new JLabel("Add Continent: ");
        addContinentLbl.setBounds(10, 150, 200, 20);

        addContinentField = new JTextField();
        addContinentField.setBounds(220, 150, 200, 20);

        continentAwardLbl = new JLabel("Continent award: ");
        continentAwardLbl.setBounds(430, 150, 200, 20);

        continentAwardField = new JTextField();
        continentAwardField.setBounds(640, 150, 200, 20);

        saveMapPathLbl = new JLabel("Map save location: ");
        saveMapPathLbl.setBounds(840, 150, 200, 20);

        saveMapPathField = new JTextField();
        saveMapPathField.setBounds(1050, 150, 200, 20);

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
        this.getContentPane().add(continentAwardLbl);
        this.getContentPane().add(continentAwardField);
        this.getContentPane().add(saveMapPathLbl);
        this.getContentPane().add(saveMapPathField);
        this.getContentPane().add(addContinentButton);
        this.getContentPane().add(finishAddingContinentsBtn);
        this.getContentPane().repaint();
    }

    /**
     * Get continents values
     * @return continent values
     */
    public String getContinentValues() {
        return addContinentField.getText() + "," + continentAwardField.getText();
    }

    /**
     * Get country values
     * @return country field and coordination field continent list and adjacent country fields
     */
    public String getCountryValues() {
        return addCountryField.getText() + "," + countryCoordinationField.getText() + ","
                + continentList.getText() + "," + adjacentCountriesField.getText();
    }

    /**
     * Get country value
     * @return country list
     */
    public String getCountryValue() {
        return editCountryList.getText();
    }

    /**
     * Get continent value
     * @return continent list
     */
    public String getContinentValue() {
        return editContinentList.getText();
    }

    /**
     * Get Map save location
     * @return map path field
     */
    public String getMapSaveLocation() {
        return saveMapPathField.getText();
    }

    /**
     * Add country form
     * @param continentListInMapEditor continent list
     */
    public void showAddCountryForm(String[] continentListInMapEditor) {

        addCountryLbl = new JLabel("Country: ");
        addCountryLbl.setBounds(10, 150, 200, 20);

        addCountryField = new JTextField();
        addCountryField.setBounds(220, 150, 200, 20);

        countryCoordinationLbl = new JLabel("Country coordination(X,Y): ");
        countryCoordinationLbl.setBounds(430, 150, 200, 20);

        countryCoordinationField = new JTextField();
        countryCoordinationField.setBounds(640, 150, 200, 20);

        adjacentCountriesLbl = new JLabel("Adjacent Countries: ");
        adjacentCountriesLbl.setBounds(10, 200, 200, 20);

        adjacentCountriesField = new JTextField();
        adjacentCountriesField.setBounds(220, 200, 200, 20);

        addCountryToContinentLbl = new JLabel("Add Country to continent: ");
        addCountryToContinentLbl.setBounds(10, 250, 200, 20);

        continentList = new JTextField();
        continentList.setBounds(220, 250, 200, 20);

        addCountryButton = new JButton("Add Country");
        addCountryButton.setBounds(220, 300, 200, 30);

        this.removeCreateMapForm();

        this.getContentPane().add(countryCoordinationLbl);
        this.getContentPane().add(countryCoordinationField);
        this.getContentPane().add(addCountryLbl);
        this.getContentPane().add(addCountryField);
        this.getContentPane().add(adjacentCountriesLbl);
        this.getContentPane().add(adjacentCountriesField);
        this.getContentPane().add(addCountryButton);
        this.getContentPane().add(addCountryToContinentLbl);
        this.getContentPane().add(continentList);
        this.getContentPane().repaint();
    }

    /**
     * Show edit map form
     * @param countryListInMapEditor continent list
     * @param continentListInMapEditor continent list
     */
    public void showEditMapForm(String[] countryListInMapEditor, String[] continentListInMapEditor) {
        editCountryLbl = new JLabel("Edit country: ");
        editCountryLbl.setBounds(10, 400, 100, 20);

        editCountryList = new JTextField();
        editCountryList.setBounds(120, 400, 150, 20);

        editContinentLbl = new JLabel("Edit continent: ");
        editContinentLbl.setBounds(290, 400, 100, 20);

        editContinentList = new JTextField();
        editContinentList.setBounds(410, 400, 150, 20);

        removeCountryButton = new JButton("Remove country");
        removeCountryButton.setBounds(590, 450, 130, 30);

        removeContinentButton = new JButton("Remove continent");
        removeContinentButton.setBounds(740, 450, 130, 30);

        this.removeLoadMapForm();
        this.showCreateMapForm();

        this.getContentPane().add(editCountryLbl);
        this.getContentPane().add(editCountryList);
        this.getContentPane().add(editContinentLbl);
        this.getContentPane().add(editContinentList);
        this.getContentPane().add(removeCountryButton);
        this.getContentPane().add(removeContinentButton);
        this.getContentPane().repaint();
    }

    /**
     * adds action listener
     *
     * @param listenForEditMapRadioBtn map edit radio
     */
    public void addEditMapRadioBtnListener(ActionListener listenForEditMapRadioBtn) {
        editMapRadioBtn.addActionListener(listenForEditMapRadioBtn);
    }

    /**
     * get player numbers
     *
     * @return numPlayers
     */
    public Integer getPlayerNumbers() {
        return (Integer) playerNum.getSelectedItem();
    }

    /**
     * get selected map
     *
     * @return selectedMap
     */
    public String getSelectedMap() {
        return String.valueOf(mapList.getSelectedItem());
    }

    /**
     * Remove create map form
     */
    private void removeCreateMapForm() {
        if (addContinentLbl != null && addContinentField != null
                && addContinentButton != null && finishAddingContinentsBtn != null) {
            this.getContentPane().remove(addContinentLbl);
            this.getContentPane().remove(addContinentField);
            this.getContentPane().remove(continentAwardLbl);
            this.getContentPane().remove(continentAwardField);
            this.getContentPane().remove(saveMapPathLbl);
            this.getContentPane().remove(saveMapPathField);
            this.getContentPane().remove(addContinentButton);
            this.getContentPane().remove(finishAddingContinentsBtn);
            this.getContentPane().repaint();
        }
    }

    /**
     * Remove add country form
     */
    private void removeAddCountryForm() {
        if (addCountryLbl != null && addCountryField != null
                && countryCoordinationLbl != null && countryCoordinationField != null
                && adjacentCountriesLbl != null && adjacentCountriesField != null
                && addCountryButton != null && addCountryToContinentLbl != null
                && continentList != null) {
            this.getContentPane().remove(addCountryLbl);
            this.getContentPane().remove(addCountryField);
            this.getContentPane().remove(countryCoordinationLbl);
            this.getContentPane().remove(countryCoordinationField);
            this.getContentPane().remove(adjacentCountriesLbl);
            this.getContentPane().remove(adjacentCountriesField);
            this.getContentPane().remove(addCountryButton);
            this.getContentPane().remove(addCountryToContinentLbl);
            this.getContentPane().remove(continentList);
            this.getContentPane().repaint();
        }
    }

    /**
     * Remove edit map form
     */
    private void removeEditMapForm() {
        if (editCountryLbl != null && editCountryList != null
                && editContinentLbl != null && editContinentList != null
                && removeCountryButton != null && removeContinentButton != null) {
            this.getContentPane().remove(editCountryLbl);
            this.getContentPane().remove(editCountryList);
            this.getContentPane().remove(editContinentLbl);
            this.getContentPane().remove(editContinentList);
            this.getContentPane().remove(removeCountryButton);
            this.getContentPane().remove(removeContinentButton);
            this.removeCreateMapForm();
            this.getContentPane().repaint();
        }
    }

    /**
     * Remove select map form
     */
    private void removeSelectMapForm() {
        if (editMapRadioBtn == null || lblMapSelect == null || mapList == null) {
            return;
        }
        this.getContentPane().remove(editMapRadioBtn);
        this.getContentPane().remove(lblMapSelect);
        this.getContentPane().remove(mapList);
        this.getContentPane().repaint();
    }

    /**
     * Remove load map form
     */
    private void removeLoadMapForm() {
        if (lblMapLoad != null && loadMapField != null && editMapRadioBtn != null) {
            this.getContentPane().remove(editMapRadioBtn);
            this.getContentPane().remove(lblMapLoad);
            this.getContentPane().remove(loadMapField);
            this.getContentPane().repaint();
        }
    }
}
