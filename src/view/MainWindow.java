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
 * @author Team20
 */
public class MainWindow extends JFrame 
{
	
	private static final int WIDTH = 1400;
	private static final int HEIGHT = 800;
	
	private JScrollPane mapPane;
	private InfoView infoView;
	private ErrorInfoView errorInfoView;
	private PlayerInformationView playerInformationView;
	private PlayerDominationView playerDominationView;
	
	private Map<String, TerritoryView> dictTerrViews = new HashMap<>(2, 2);
	public MainWindow() 
	{
		super("Risky Conquest");		
		this.initWindow();
		this.initContentPane();
	}

	private void initWindow() 
	{
		// Size the frame.
	    setSize( new Dimension(WIDTH, HEIGHT) );
	    setMinimumSize( new Dimension(WIDTH, HEIGHT) );
	    setMaximumSize( new Dimension(WIDTH, HEIGHT) );    
		this.setResizable(false);
		
//		setSize(getMaximumSize());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		setBounds(100, 100, 567, 489);
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
	}

	private void initContentPane() 
	{
		BorderLayout borderLayout = new BorderLayout();
		this.setLayout(borderLayout);
	}

	/**
	 * creates country buttons or territory views
	 * called by src.controller
	 * @param objMap passed by src.controller who has a reference to GameConfig->maps
	 * 
	 */
	public void addCountryButtons(Maps objMap) 
	{
		Integer x;
		Integer y;
		mapPane = new JScrollPane();
		mapPane.setBounds(0, 0, 1024, 768);
		mapPane.setMinimumSize(new Dimension(1200, 900));
		mapPane.setWheelScrollingEnabled(true);
		Integer owner;
		for (String territoryName : objMap.getDictTerritory().keySet()) 
		{
			x = (objMap.getDictTerritory()).get(territoryName).getX();
			y = (objMap.getDictTerritory()).get(territoryName).getY();
			owner = (objMap.getDictTerritory()).get(territoryName).getOwner();
			TerritoryView terrPanel = new TerritoryView(owner, new Point(x,y), Color.WHITE );
			mapPane.add(terrPanel);
			mapPane.setComponentZOrder(terrPanel, 0);
			dictTerrViews.put(territoryName, terrPanel);
			
		}

		infoView = new InfoView();
		errorInfoView = new ErrorInfoView();
        playerInformationView = new PlayerInformationView();
        playerDominationView = new PlayerDominationView();
		this.getContentPane().add(infoView);
		this.getContentPane().add(errorInfoView);
        this.getContentPane().add(playerInformationView);
        this.getContentPane().add(playerDominationView);
		this.getContentPane().add(mapPane);
		this.getContentPane().repaint();

	}
	
	/**
	 * creates country buttons or territory views
	 * called by src.controller
	 */
	public void removeCountryButtons() 
	{
		this.getContentPane().remove(mapPane);
		this.getContentPane().repaint();

	}

	/**
	 * @return the dictTerrViews
	 */
	public Map<String, TerritoryView> getDictTerrViews() 
	{
		return dictTerrViews;
	}

	/**
	 * @return the infoView
	 */
	public InfoView getInfoView() 
	{
		return infoView;
	}
	
	/**
	 * @return the infoView
	 */
	public ErrorInfoView getErrorInfoView() 
	{
		return errorInfoView;
	}
    /**
     * @return the infoView
     */
    public PlayerInformationView getPlayerInformationView()
    {
        return playerInformationView;
    }
	/**
	 * @return the infoView
	 */
	public PlayerDominationView getPlayerDominationView()
	{
		return playerDominationView;
	}
}
