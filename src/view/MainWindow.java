package src.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

//import javax.swing.JButton;
import javax.swing.*;

import src.model.Maps;

/**
 * This is the main window, that contains all the other panels and in which
 * we load the map.
 *
 * @author Team20
 */
public class MainWindow extends JFrame {

    private static final int WIDTH = 1400;
    private static final int HEIGHT = 800;

    private JScrollPane mapPane;
    private InfoView infoView;
    private ErrorInfoView errorInfoView;
    private PlayerInformationView playerInformationView;
    private PlayerDominationView playerDominationView;
    private AttackView attackView;
    private CardView cardView;

    private Map<String, TerritoryView> dictTerrViews = new HashMap<>(2, 2);

    public MainWindow() {
        super("Risky Conquest");
        this.initWindow();
        this.initContentPane();
    }

    /**
     * Sets player domination view
     *
     * @param playerDominationView player domination view
     */
    public void setPlayerDominationView(PlayerDominationView playerDominationView) {
        this.playerDominationView = playerDominationView;
    }

    /**
     * Sets player information view
     *
     * @param playerInformationView player information view
     */
    public void setPlayerInformationView(PlayerInformationView playerInformationView) {
        this.playerInformationView = playerInformationView;
    }

    /**
     * Constructor of error info which shows panel
     */
    public void setCardView(CardView cardView) {
        this.cardView = cardView;
    }

    /**
     * Gets dictionary of territory values
     *
     * @return the dictTerrViews
     */
    public Map<String, TerritoryView> getDictTerrViews() {
        return dictTerrViews;
    }

    /**
     * Gets the error information view
     *
     * @return the infoView
     */
    public InfoView getInfoView() {
        return infoView;
    }

    /**
     * Gets the information view
     *
     * @return the infoView
     */
    public ErrorInfoView getErrorInfoView() {
        return errorInfoView;
    }

    /**
     * Gets the information view
     *
     * @return the infoView
     */
    public PlayerInformationView getPlayerInformationView() {
        return playerInformationView;
    }

    /**
     * Gets the information view
     *
     * @return the infoView
     */
    public PlayerDominationView getPlayerDominationView() {
        return playerDominationView;
    }

    /**
     * Gets the attack view
     *
     * @return the attackView
     */
    public AttackView getAttackView() {
        return attackView;
    }

    /**
     * Gets card view
     *
     * @return the attackView
     */
    public CardView getCardView() {
        return cardView;
    }

    /**
     * Initializes main window
     */
    private void initWindow() {
        setSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.setResizable(false);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    }

    /**
     * Initializes content pane
     */
    private void initContentPane() {
        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
    }

    /**
     * Creates country buttons or territory views
     * called by src.controller
     *
     * @param objMap passed by src.controller who has a reference to GameConfig.maps
     */
    public void addCountryButtons(Maps objMap) {
        Integer x;
        Integer y;
        mapPane = new JScrollPane();
        mapPane.setBounds(0, 0, 1024, 768);
        mapPane.setMinimumSize(new Dimension(1200, 900));
        mapPane.setWheelScrollingEnabled(true);
        Integer owner;
        for (String territoryName : objMap.getDictTerritory().keySet()) {
            x = (objMap.getDictTerritory()).get(territoryName).getX();
            y = (objMap.getDictTerritory()).get(territoryName).getY();
            owner = (objMap.getDictTerritory()).get(territoryName).getOwner();
            TerritoryView terrPanel = new TerritoryView(owner, new Point(x, y), Color.WHITE);
            mapPane.add(terrPanel);
            mapPane.setComponentZOrder(terrPanel, 0);
            dictTerrViews.put(territoryName, terrPanel);

        }

        infoView = new InfoView();
        errorInfoView = new ErrorInfoView();
        attackView = new AttackView();
        this.getContentPane().add(infoView);
        this.getContentPane().add(errorInfoView);
        this.getContentPane().add(playerInformationView);
        this.getContentPane().add(playerDominationView);
        this.getContentPane().add(attackView);
        this.getContentPane().add(cardView);
        this.getContentPane().add(mapPane);
        this.getContentPane().repaint();

    }

    /**
     * creates country buttons or territory views
     * called by src.controller
     */
    public void removeCountryButtons() {
        this.getContentPane().remove(mapPane);
        this.getContentPane().repaint();

    }


}
