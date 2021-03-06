package src.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import src.model.GameConfig;
import src.model.GenericFunctions;
import src.model.MapEditor;
import src.view.*;

/**
 * As our architecture is MVC, this is the main src.controller, that adds action
 * listeners too all the GUI components, defined and declared in src.view.
 * This src.controller is also connected to the src.model package, so that it can
 * initiate the execution of appropriate business logic on the basis of specific events.
 *
 * @author Team20
 */
public class MainController {

	public StarterWindow starterView;
    public MainWindow mainWindow;
    public GameConfig gameConfig;
    private GenericFunctions genericFunctionsObj = new GenericFunctions();
    private MapEditor mapEditor;
    private String previousCountryName = null;
    private String currentCountryName = null;
    private Boolean fortificationPossible = false;
    private Integer numMaps = 1;


    public Integer numGames = 1;
    public Integer drawTurns = 1;
    public ArrayList<String> playerTypes;
    
    public int turnNumber = 0;
    public Boolean aiMode = false;
    /*
     * 0 New game 1 Edit or create
     */
    private Integer applicationMode = 0;
    public HashMap<String, ArrayList<HashMap<String,String>>> winners =
    		new HashMap<String, ArrayList<HashMap<String,String>>>();
    public String currentMapPlayed;
    public String currentGameNumber;

    public void setCurrentCountryName(String currentCountryName) {
        this.currentCountryName = currentCountryName;
    }

    private void setNumMaps(Integer numMaps) {
        this.numMaps = numMaps;
    }

    private void setNumGames(Integer numGames) {
        this.numGames = numGames;
    }

    private void setDrawTurns(Integer drawTurns) {
        this.drawTurns = drawTurns;
    }

    /**
     * This is the constructor to the controller.
     *
     * @param starterView reference to the view to register appropriate action listeners.
     */
    public MainController(StarterWindow starterView) {
        this.starterView = starterView;
        this.starterView.addMenuItemNewGameActionListener(new NewGameListener());
        this.starterView.addMenuItemLoadGameActionListener(new LoadGameListener());
    }


    /**
     * This is the inner class, to define action listener to the menu option "New Game"
     *
     * @author Team20
     */
    private class NewGameListener implements ActionListener {
        /**
         * This function over-rides the actionPerformed function of ActionListerner class
         * and registers listeners for starterview
         *
         * @param e Action Event received by user interaction
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            starterView.showStarterForm();
            starterView.addRadioLoadMapActionListener(new loadMapListener());
            starterView.addRadioSelectMapActionListener(new selectMapListener());
            starterView.addRadioCreateMapActionListener(new createMapListener());
            starterView.addRadioTournamentActionListener(new tournamentListener());
            starterView.addSubmitButtontActionListener(new submitButtonListener());

        }
    }

    /**
     * This is the inner class, to define action listener to the menu option "Save Game"
     *
     * @author Team20
     */
    private class SaveGameListener implements ActionListener {
        /**
         * This function over-rides the actionPerformed function of ActionListerner class
         * and registers listeners for starterview
         *
         * @param e Action Event received by user interaction
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String path = "D:\\APP_Project\\Workspace\\Pushed_Repo\\Risk\\resources\\myObjects.txt";
                FileOutputStream f = new FileOutputStream(new File(path));
                ObjectOutputStream o = new ObjectOutputStream(f);

                o.writeObject(gameConfig);
                o.close();
                f.close();
            } catch (FileNotFoundException excep) {
                System.out.println("File not found");
            } catch (IOException excep) {
                System.out.println("Error initializing stream");
            } catch (Exception excep) {
                excep.printStackTrace();
            }
            System.exit(0);
        }
    }

    /**
     * This is the inner class, to define action listener to the menu option "Load Game"
     *
     * @author Team20
     */
    private class LoadGameListener implements ActionListener {
        /**
         * This function over-rides the actionPerformed function of ActionListerner class
         * and registers listeners for starterview
         *
         * @param e Action Event received by user interaction
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            GameConfig loadedObj = null;
            try {
                String path = "D:\\APP_Project\\Workspace\\Pushed_Repo\\Risk\\resources\\myObjects.txt";
                FileInputStream fi = new FileInputStream(new File(path));
                ObjectInputStream oi = new ObjectInputStream(fi);
                loadedObj = (GameConfig) oi.readObject();


                System.out.println(loadedObj.toString());

                oi.close();
                fi.close();

            } catch (FileNotFoundException excep) {
                System.out.println("File not found");
            } catch (IOException excep) {
                System.out.println("Error initializing stream");
            } catch (ClassNotFoundException excep) {
                // TODO Auto-generated catch block
                excep.printStackTrace();
            }

            //gameConfig = new GameConfig(playerNum, selectedMap, mainWindow);

            if (loadedObj == null) {
                System.out.println("Loaded map is null");
                return;
            }

            mainWindow = new MainWindow();
            gameConfig = loadedObj;
            PlayerDominationView playerDominationView = new PlayerDominationView();
            mainWindow.setPlayerDominationView(playerDominationView);
            gameConfig.addObserver(playerDominationView);
            mainWindow.getPlayerDominationView().showInfoPanel();

            PlayerInformationView playerInformationView = new PlayerInformationView();
            mainWindow.setPlayerInformationView(playerInformationView);
            gameConfig.addObserver(playerInformationView);
            mainWindow.getPlayerInformationView().showInfoPanel();

            CardView cardView = new CardView();
            mainWindow.setCardView(cardView);
            gameConfig.addObserver(cardView);
            mainWindow.getCardView().showCardPanel();

            mainWindow.addCountryButtons(gameConfig.getMapObj());
            mainWindow.setVisible(true);
            starterView.setVisible(false);
            addTerritoryListeners();

            mainWindow.getInfoView().passBtnActionListener(new passTurnBtn());

            mainWindow.getCardView().addCardBtnListener(new exchangeCard());


        }
    }

    /**
     * This is the inner class, to define action listener to the option "Load Map"
     *
     * @author Team20
     */
    private class loadMapListener implements ActionListener {
        /**
         * This function over-rides the actionPerformed function of ActionListerner class
         * and registers listeners for starter view's load map form.
         *
         * @param e Action Event received by user interaction
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            applicationMode = 0;
            starterView.showLoadMapForm();
        }
    }

    /**
     * This is the inner class, to define action listener to the option "Select Map"
     *
     * @author Team20
     */
    private class selectMapListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            applicationMode = 0;
            starterView.showSelectMapForm();
            starterView.addEditMapRadioBtnListener(new editMapListener());
        }
    }

    /**
     * This is the inner class, to define action listener to the option "Select Map"
     *
     * @author Team20
     */
    private class tournamentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            applicationMode = 0;
            starterView.showTournamentForm();
        }
    }

    /**
     * This is the inner class, to define action listener to the option "Create Map"
     *
     * @author Team20
     */
    private class createMapListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            applicationMode = 1;
            starterView.showCreateMapForm();
            System.out.println(starterView.getMapSaveLocation());
            mapEditor = new MapEditor(2, starterView.getMapSaveLocation());
            starterView.finishAddingContinentBtnActionListener(new finishAddContinentListener());
            starterView.addContinentBtnActionListener(new addContinentListener());
        }
    }

    /**
     * This is the inner class, to define action listener to the option "Edit Map"
     *
     * @author Team20
     */
    private class editMapListener implements ActionListener {
        @SuppressWarnings("null")
        @Override
        public void actionPerformed(ActionEvent e) {
            applicationMode = 1;
            mapEditor = new MapEditor(1, starterView.getSelectedMap());
            starterView.showEditMapForm();
            starterView.finishAddingContinentBtnActionListener(new finishAddContinentListener());
            starterView.addContinentBtnActionListener(new addContinentListener());
            starterView.removeCountryBtnActionListener(new removeCountryListener());
            starterView.removeContinentBtnActionListener(new removeContinentListener());
        }
    }

    /**
     * This is the inner class, to define action listener to the option "Finish Add Continent Button"
     *
     * @author Team20
     */
    private class finishAddContinentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            starterView.showAddCountryForm();
            starterView.addCountryBtnActionListener(new addCountryListener());
        }
    }

    /**
     * This is the inner class, to define action listener to the option "Add Continent Button"
     *
     * @author Team20
     */
    private class addContinentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String continentInfo = starterView.getContinentValues();
            mapEditor.addContinent(continentInfo);
        }
    }

    /**
     * This is the inner class, to define action listener to the option "Add Country Button"
     *
     * @author Team20
     */
    private class addCountryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String countryInfo = starterView.getCountryValues();
            mapEditor.newCountry(countryInfo);
        }
    }

    /**
     * This is the inner class, to define action listener to the option "Remove Country Button"
     *
     * @author Team20
     */
    private class removeCountryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String countryInfo = starterView.getCountryValue();
            mapEditor.delCountry(countryInfo);
        }
    }

    /**
     * This is the inner class, to define action listener to the option "Remove Continent Button"
     *
     * @author Team20
     */
    private class removeContinentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String continentInfo = starterView.getContinentValue();
            mapEditor.deleteContinent(continentInfo);
        }
    }

    /**
     * This is the inner class, to define action listener to the option "Submit Button"
     *
     * @author Team20
     */
    private class submitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainWindow = new MainWindow();
            // TODO Auto-generated method stub

            PlayerDominationView playerDominationView = new PlayerDominationView();
            mainWindow.setPlayerDominationView(playerDominationView);

            PlayerInformationView playerInformationView = new PlayerInformationView();
            mainWindow.setPlayerInformationView(playerInformationView);

            CardView cardView = new CardView();
            mainWindow.setCardView(cardView);

            mainWindow.addMenuItemSaveGameActionListener(new SaveGameListener());

            if (applicationMode == 1) {
                mapEditor.finishAndValidate(starterView.saveMapPathField.getText());
                return;
            }

            Integer playerNum = starterView.getPlayerNumbers();
            String selectedMap = starterView.getSelectedMap();
            String playerTypes = starterView.getTypeOfPlayers();
            String maps = starterView.getMaps();
            String turns = starterView.getTurns();
            String games = starterView.getGames();
            if((turns != null) && (!Objects.equals(turns, "")))
            {
            	drawTurns = Integer.parseInt(turns);
            	drawTurns = drawTurns * 2;
            }
            if((games != null) && (games != ""))
            {
            	numGames = Integer.parseInt(games);
            }

            String mapArray[] = genericFunctionsObj.genCommaSepStrToArray(maps);
            ArrayList<String> strategies = genericFunctionsObj.genCommaSepStrToArrayList(playerTypes);
            numMaps = mapArray.length;

            setNumMaps(mapArray.length);
            if(!Objects.equals(turns, "") && turns != null){
                setDrawTurns(Integer.parseInt(turns));
                drawTurns = drawTurns * strategies.size();
            }

            if(!Objects.equals(games, "") && games != null){
                setNumGames(Integer.parseInt(games));
            }

            if (numGames == 1) {
                gameConfig = new GameConfig(playerNum, selectedMap, mainWindow);

                gameConfig.addObserver(playerDominationView);
                mainWindow.getPlayerDominationView().showInfoPanel();


                gameConfig.addObserver(playerInformationView);
                mainWindow.getPlayerInformationView().showInfoPanel();

                gameConfig.addObserver(cardView);
                mainWindow.getCardView().showCardPanel();

                mainWindow.addCountryButtons(gameConfig.getMapObj());
                mainWindow.setVisible(true);
                starterView.setVisible(false);
                addTerritoryListeners();

                mainWindow.getInfoView().passBtnActionListener(new passTurnBtn());

                mainWindow.getCardView().addCardBtnListener(new exchangeCard());

                String error = gameConfig.getMapObj().validateMap();

                if (!error.equals("true")) {
                    mainWindow.getErrorInfoView().showErrorInfo(error);
                    mainWindow.removeCountryButtons();
                }else {
                	//gameConfig.gameResult(ai_driver(0, 0));
                }
            } else {

            	for(int ctr = 0; ctr < numMaps; ctr++) {
            		ArrayList<HashMap<String, String>> tmpL = new ArrayList<HashMap<String, String>>();
            		winners.put(mapArray[ctr], tmpL);

            		for(int ctr2 = 0; ctr2 < numGames; ctr2++) {
            			HashMap<String,String> tmp = new HashMap<String, String>();
                        tmp.put("Game: " + Integer.toString(ctr2 + 1), "");
                        winners.get(mapArray[ctr]).add(tmp);
            		}
            	}

                for (int ctr = 0; ctr < numMaps; ctr++) {
                    for (int ctr2 = 0; ctr2 < numGames; ctr2++) {
                        gameConfig = new GameConfig(strategies, mapArray[ctr], mainWindow);
                        currentMapPlayed = mapArray[ctr];
                        currentGameNumber = "Game: " + Integer.toString(ctr2 + 1);
                        gameConfig.addObserver(playerDominationView);
                        mainWindow.getPlayerDominationView().showInfoPanel();

                        gameConfig.addObserver(playerInformationView);
                        mainWindow.getPlayerInformationView().showInfoPanel();

                        gameConfig.addObserver(cardView);
                        mainWindow.getCardView().showCardPanel();

                        mainWindow.addCountryButtons(gameConfig.getMapObj());
                        mainWindow.setVisible(true);
                        starterView.setVisible(false);
                        addTerritoryListeners();

                        mainWindow.getInfoView().passBtnActionListener(new passTurnBtn());

                        mainWindow.getCardView().addCardBtnListener(new exchangeCard());

                        String error = gameConfig.getMapObj().validateMap();

                        if (!error.equals("true")) {
                            mainWindow.getErrorInfoView().showErrorInfo(error);
                            mainWindow.removeCountryButtons();
                        }
                        else {
                        	gameConfig.gameResult(ai_driver(ctr, ctr2));
                        }
                    }
                }

                System.out.println();
                System.out.println();
                System.out.println("RESULTS: ");
                System.out.println();
                System.out.println();
                System.out.println("***************************************");
                for(String mapL : winners.keySet()) {
                	System.out.println(mapL);
                	System.out.println();
                	ArrayList<HashMap<String, String>> gameList = winners.get(mapL);
                	for(int ctr5 = 0; ctr5 < gameList.size(); ctr5++) {
                		HashMap<String, String> gameInfo = gameList.get(ctr5);
                		Map.Entry<String,String> entry = gameInfo.entrySet().iterator().next();
                		String key = entry.getKey();
                		String value = entry.getValue();
                		System.out.println(key + ":   And the winner is as always " + value);
                	}
                	System.out.println("***************************************");
                }
                System.out.println("");
            }
        }
    }

    /**
     * Driver for different kinds of players
     * @param numMap Number of maps
     * @param numGame number os games
     * @return
     */
    public String ai_driver(Integer numMap, Integer numGame) {
        PlayerDominationView playerDominationView = new PlayerDominationView();
        mainWindow.setPlayerDominationView(playerDominationView);

        PlayerInformationView playerInformationView = new PlayerInformationView();
        mainWindow.setPlayerInformationView(playerInformationView);

        Integer ctr = 0;
        Integer gamePhase = gameConfig.getGamePhase();
        aiMode = true;
        
        while (gamePhase != genericFunctionsObj.GAMEPHASENONE) {
            gamePhase = gameConfig.getGamePhase();
            try {
                Thread.sleep(50);
                //System.out.println(gameConfig.getCurrentPlayer().getPlayerId() + "_" + gameConfig.getCurrentPlayer().numOfTerritories());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            gamePhase = gameConfig.getGamePhase();
            if (gamePhase == genericFunctionsObj.GAMEPHASEATTACK) {
                mainWindow.getAttackView().showAttackInfo();
                gameConfig.attackTerritory(0, 0, gameConfig.getMapObj().getDictContinents());

                gameConfig.addObserver(playerDominationView);
                mainWindow.getPlayerDominationView().showInfoPanel();

                gameConfig.addObserver(playerInformationView);
                mainWindow.getPlayerInformationView().showInfoPanel();

            } else if ((gamePhase == genericFunctionsObj.GAMEPHASESTARTUP)
                    || (gamePhase == genericFunctionsObj.GAMEPHASEREINFORCEMENT)) {
                gameConfig.getCurrentPlayer().reinforceArmiesOnTerritory(null);

                gameConfig.addObserver(playerDominationView);
                mainWindow.getPlayerDominationView().showInfoPanel();

                gameConfig.addObserver(playerInformationView);
                mainWindow.getPlayerInformationView().showInfoPanel();

            } else if (gamePhase == genericFunctionsObj.GAMEPHASEFORTIFICATION) {
                gameConfig.fortifyArmies(null, null, 0);

                gameConfig.addObserver(playerDominationView);
                mainWindow.getPlayerDominationView().showInfoPanel();

                gameConfig.addObserver(playerInformationView);
                mainWindow.getPlayerInformationView().showInfoPanel();

            } else if (gamePhase == genericFunctionsObj.GAMEPHASENONE) {
            	ArrayList<HashMap<String, String>> gameList = this.winners.get(currentMapPlayed);
        		for(int ctr5 = 0; ctr5 < gameList.size(); ctr5++) {
        			HashMap<String, String> tmpG = gameList.get(ctr5);
        			if(tmpG.containsKey(currentGameNumber)) {
        				tmpG.put(currentGameNumber, gameConfig.gameWinner);
        			}
        		}
                return "We have a winner";
            }

            if (ctr < drawTurns) {
                if (gameConfig.gamePhaseChanged) {
                    System.out.println("********************************************");
                    System.out.println("TURN NUMBER = " + ctr);
                    System.out.println("********************************************");
                    ctr++;
                    this.turnNumber = ctr;
                    gameConfig.gamePhaseChanged = false;
                }
            }
            if (ctr == drawTurns) {
            	gameConfig.maxTurnsReached = true;
            	if(gameConfig.maxTurnsReached) {
            		ArrayList<HashMap<String, String>> gameList = this.winners.get(currentMapPlayed);
            		for(int ctr5 = 0; ctr5 < gameList.size(); ctr5++) {
            			HashMap<String, String> tmpG = gameList.get(ctr5);
            			if(tmpG.containsKey(currentGameNumber)) {
            				tmpG.put(currentGameNumber, "DRAW");
            			}
            		}
            		return "Game Draw";
            	}
            }
        }
        return "We have a winner";
    }

    /**
     * This is the inner class, to define action listener to the territory buttons
     *
     * @author Team20
     */
    private class territoryListener implements ActionListener {

        String countryName;

        territoryListener(String countryName) {
            this.countryName = countryName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            Integer currentPlayerId = gameConfig.getCurrentPlayer().getPlayerId();
            Integer territoryOwner = (gameConfig.getMapObj().getDictTerritory().get(countryName).getOwner());
            Integer gamePhase = gameConfig.getGamePhase();
            if(aiMode) {
            	String info = (gameConfig.getMapObj().getDictTerritory().get(countryName)).toString();
                mainWindow.getInfoView().showTerritoryInfo(info);
                return;
            }
            if (gamePhase == genericFunctionsObj.GAMEPHASEATTACK) {
                mainWindow.getAttackView().showAttackInfo();
                mainWindow.getAttackView().addDiceBtnListener(new attackTerritory());
                if (Objects.equals(gameConfig.getMapObj().getDictTerritory().get(countryName).getOwner(), gameConfig.getCurrentPlayer().getPlayerId())) {
                    gameConfig.getCurrentPlayer().srcAttackTerritory = gameConfig.getMapObj().getDictTerritory().get(countryName);
                    previousCountryName = countryName;
                    fortificationPossible = false;
                }

                if (gameConfig.getCurrentPlayer().srcAttackTerritory != null) {
                    if (!Objects.equals(gameConfig.getMapObj().getDictTerritory().get(countryName).getOwner(), gameConfig.getCurrentPlayer().getPlayerId())) {
                        gameConfig.getCurrentPlayer().dstAttackTerritory = gameConfig.getMapObj().getDictTerritory().get(countryName);
                        currentCountryName = countryName;
                        fortificationPossible = true;
                    }
                }

                String info = (gameConfig.getMapObj().getDictTerritory().get(countryName)).toString();
                mainWindow.getInfoView().showTerritoryInfo(info);

            } else if ((gamePhase == genericFunctionsObj.GAMEPHASESTARTUP)
                    || (gamePhase == genericFunctionsObj.GAMEPHASEREINFORCEMENT)
                    || (gamePhase == genericFunctionsObj.GAMEPHASEFORTIFICATION)) {
                if (Objects.equals(currentPlayerId, territoryOwner)) {
                    if ((gamePhase == genericFunctionsObj.GAMEPHASESTARTUP)
                            || (gamePhase == genericFunctionsObj.GAMEPHASEREINFORCEMENT)) {
                        gameConfig.getCurrentPlayer().reinforceArmiesOnTerritory(countryName);
                        previousCountryName = null;
                    }

                    if ((previousCountryName != null)
                            && (gamePhase == genericFunctionsObj.GAMEPHASEFORTIFICATION)) {
                        if (!previousCountryName.equals(countryName)) {
                            fortificationPossible = true;
                            currentCountryName = countryName;
                            //gameConfig.fortifyArmies(previousCountryName, countryName);
                        }
                    } else if ((previousCountryName == null) && (gamePhase == genericFunctionsObj.GAMEPHASEFORTIFICATION)) {
                        previousCountryName = countryName;
                        fortificationPossible = false;
                    }

                    String info = (gameConfig.getMapObj().getDictTerritory().get(countryName)).toString();
                    mainWindow.getInfoView().showTerritoryInfo(info);
                }

            } else if (gamePhase == genericFunctionsObj.GAMEPHASENONE) {
                return;
            }
        }
    }

    /**
     * This function calls for the addition of all the territory listeners in the territory hashmap
     */
    private void addTerritoryListeners() {
        Iterator<Entry<String, TerritoryView>> it = mainWindow.getDictTerrViews().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, TerritoryView> pair = it.next();
            TerritoryView tv = pair.getValue();
            tv.addTerritoryBtnListener(new territoryListener(pair.getKey()));
        }

    }


    /**
     * This is the inner class, to define action listener to the option "Submit Button"
     *
     * @author Team20
     */
    private class attackTerritory implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (mainWindow.getAttackView().checkDiceValue()) {
                String values[] = genericFunctionsObj.genCommaSepStrToArray(mainWindow.getAttackView().getDiceValues());
                mainWindow.getAttackView().resetDiceValues();
                gameConfig.attackTerritory(
                        genericFunctionsObj.genStrToInt(values[0]),
                        genericFunctionsObj.genStrToInt(values[1]),
                        gameConfig.getMapObj().getDictContinents());
            }
        }
    }

    /**
     * This is the inner class, to define action listener to the option "Submit Button"
     *
     * @author Team20
     */
    private class exchangeCard implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (gameConfig.getGamePhase() == genericFunctionsObj.GAMEPHASEREINFORCEMENT) {
                if (mainWindow.getCardView().checkCardValue()) {
                    String values[] = genericFunctionsObj.genCommaSepStrToArray(mainWindow.getCardView().getCardValues());
                    int I = genericFunctionsObj.genStrToInt(values[0]);
                    int C = genericFunctionsObj.genStrToInt(values[1]);
                    int A = genericFunctionsObj.genStrToInt(values[2]);
                    int W = genericFunctionsObj.genStrToInt(values[3]);
                    gameConfig.getCurrentPlayer().exchangeCards(I, C, A, W);
                }
            } else {
                System.out.println("The game phase is not correct");
            }

        }
    }


    /**
     * Button used to finish fortification.
     *
     * @author Team20
     */
    private class passTurnBtn implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (fortificationPossible) {
                Integer moveArmies = mainWindow.getInfoView().getMoveArmies();
                gameConfig.playerMoveArmies(moveArmies, previousCountryName, currentCountryName);
                fortificationPossible = false;
            }
        }
    }
}
