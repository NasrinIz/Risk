package view;
import Resources.Maps.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.GenFun;
import model.Maps;

import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	
	/*   Game Settings   */
	String gameMapLocation = null;
	Integer gameNumPlayers = null;
	
	private BufferedImage graphicsContext;
	private RenderingHints antialiasing;
	
	Integer gameMapStartX = new Integer(0);
	Integer gameMapStartY = new Integer(0);
	ImageIcon mapImage = null;


	String tmpLoc = new String("World.map");
	String tmpLoctwo = new String("Resources//Maps//World.bmp");
	Maps objMap = new Maps(tmpLoc);
	Vector<JButton> btnTerritories= new Vector<JButton>(2, 2);
	JScrollPane mapImagePane = null;
	
	GenFun objGenFun = new GenFun();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setSize(getMaximumSize());
		initWindow();
	}
	
	/* */
	private void initWindow() {
		setTitle("Risk");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 489);
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		
		initMenu();
		initContentPane();
		/* Content Pane */
	}
	
	private void initContentPane() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	private void initMenu() {
		/* Menu */
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mnFileNewGame = new JMenuItem("New Game");
		mnFileNewGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newGameConfig();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				newGameConfig();
			}
		});
		mnFile.add(mnFileNewGame);
	}
	private void newGameConfig() {
		
		/* Map Path */
		JLabel lblMapPath = new JLabel("Path to user created map: ");
		lblMapPath.setBounds(10, 10, 200, 20);
		
		JTextField txtMapPath = new JTextField();
		txtMapPath.setBounds(220, 10, 200, 20);
		
		/* Number of Players */
		JLabel lblNumPlayers = new JLabel("Number of Human Players: ");
		lblNumPlayers.setBounds(10, 40, 200, 20);
		
		JTextField txtNumPlayers = new JTextField();
		txtNumPlayers.setBounds(220, 40, 200, 20);
		
		/* Submit Button */
		JButton btnSubmitNewGame = new JButton("Start Game");
		btnSubmitNewGame.setBounds(150, 90, 100, 30);
		btnSubmitNewGame.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) {
	    	    gameMapLocation = new String(txtMapPath.getText());
	    	    gameNumPlayers = new Integer(objGenFun.genStrToInt(txtNumPlayers.getText()));
	    	    // vj TBD validation

				contentPane.remove(txtMapPath);
				contentPane.remove(lblMapPath);
				contentPane.remove(lblNumPlayers);
				contentPane.remove(txtNumPlayers);
				contentPane.remove(btnSubmitNewGame);
				contentPane.repaint();
				mainStartGame();
	      }
	    });
		
		contentPane.add(txtMapPath);
		contentPane.add(lblMapPath);
		contentPane.add(lblNumPlayers);
		contentPane.add(txtNumPlayers);
		contentPane.add(btnSubmitNewGame);
		contentPane.repaint();
	}
	
	private void mainStartGame() {
		loadObjects();
	}
	
	private void loadObjects() {
		loadMapImage();
		loadArmies();
	}
	
	private void loadArmies() {
		Integer x = new Integer(0);
		Integer y = new Integer(0);
		
		for(String territoryName : objMap.dictTerritory.keySet()) {
			 x = (objMap.dictTerritory).get(territoryName).X;
			 y = (objMap.dictTerritory).get(territoryName).Y;
			 JButton btnTemp = new JButton("1");
			 btnTemp.setBounds(x-4, y-4, 10, 10);
			 mapImagePane.add(btnTemp);
			 mapImagePane.setComponentZOrder(btnTemp, 0);
			 btnTerritories.add(btnTemp);
		}
		
	}
	
	private BufferedImage convertBmpToJpg(String inMapName) {
		FileInputStream inStream = null;
		BufferedImage mapJpg = null;
		try {
			ClassLoader classLoader = this.getClass().getClassLoader();
		    //File tmpFile=new File(classLoader.getResource(inMapName).getFile());
			File tmpFile=new File(inMapName);
		    inStream = new FileInputStream(tmpFile);
		    mapJpg = ImageIO.read(inStream);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return mapJpg;
	}
	
	private void loadMapImage() {
		try {
			//Image mapImage = ImageIO.read(new FileInputStream(tmpLoctwo));
			//File mapBmp = new File(tmpLoctwo);
			BufferedImage tmpImageJpg = convertBmpToJpg(tmpLoctwo);
			if(tmpImageJpg == null) {
				// vj TBD error
				return;
			}
			mapImage = new ImageIcon(tmpImageJpg);
			
			JLabel mapLabel = new JLabel();
			mapLabel.setVerticalAlignment(SwingConstants.TOP);
			//mapLabel.setIcon(new ImageIcon("D:\\APP_Project\\conquest maps\\Battlestar Galactica\\Battlestar Galactica.jpg"));
			mapLabel.setIcon(mapImage);
			mapLabel.setBounds(0, 0, mapImage.getIconWidth(), mapImage.getIconHeight());
			//JScrollPane mapImagePane = new JScrollPane(new JLabel(mapImage));
			mapImagePane = new JScrollPane(mapLabel);
			mapImagePane.setBounds(0, 0, 1024, 768);
			checkForImageSize(mapImage);
			//mapImagePane.setComponentZOrder(mapLabel, 1);
			//mapImagePane.setBounds(100, 100, 800, 700);
			contentPane.add(mapImagePane);
			contentPane.repaint();
			mapImagePane.repaint();
			mapLabel.repaint();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void checkForImageSize(ImageIcon inMapImage) {
		if(((inMapImage.getIconWidth() * 2) < mapImagePane.getWidth()) ||
				((inMapImage.getIconHeight() * 2) < mapImagePane.getHeight())){
			Image imgTemp = inMapImage.getImage();
			imgTemp = imgTemp.getScaledInstance(inMapImage.getIconWidth() * 2, inMapImage.getIconHeight() * 2, java.awt.Image.SCALE_SMOOTH);
			mapImage = new ImageIcon(imgTemp);
		}
	}
}
