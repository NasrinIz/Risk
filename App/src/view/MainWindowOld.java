package view;

import java.awt.EventQueue;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Image;
//import java.awt.RenderingHints;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import model.GameConfig;
import model.GenFun;
import model.Maps;
import model.Territory;

import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.util.Map;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;

public class MainWindowOld extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private String gameMapLocation = null;
	private Integer gameNumPlayers = null;

	private Integer gameMapStartX = new Integer(0);
	private Integer gameMapStartY = new Integer(0);
	private ImageIcon mapImage = null;

	public String mapImgLoc = "";
	public String maptxtLoc = "";
	public Maps objMap = null;

	private JScrollPane mapImagePane = null;

	GenFun objGenFun = new GenFun();

	private JButton submitButton = new JButton("Submit");

	public MainWindowOld() {
		// Sets up the view and adds the components

		setSize(getMaximumSize());
		initWindow();
	}

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
		
		GameConfig gameConfigObj = new GameConfig();

		/* Number of Human Players */
		JLabel lblNumPlayers = new JLabel("Number of Human Players: ");
		lblNumPlayers.setBounds(10, 10, 200, 20);
		JTextField txtNumPlayers = new JTextField();
		txtNumPlayers.setBounds(220, 10, 200, 20);

		JRadioButton radioLoadMap = new JRadioButton("Load map");
		radioLoadMap.setBounds(10, 40, 200, 30);

		JRadioButton radioSelectMap = new JRadioButton("Select map");
		radioSelectMap.setBounds(220, 40, 200, 30);

		JRadioButton radioCreateMap = new JRadioButton("Create map");
		radioCreateMap.setBounds(430, 40, 200, 30);

		JLabel lblMapPath = new JLabel("Path to user created map: ");
		lblMapPath.setBounds(10, 80, 200, 20);

		JTextField txtMapPath = new JTextField();
		txtMapPath.setBounds(220, 80, 200, 20);

		JLabel lblMapSelect = new JLabel("Select a map: ");
		lblMapSelect.setBounds(10, 80, 200, 20);

		String[] mapTitles = new String[] { "Atlantis", "DiMul", "Europe", "Old Yorkshire", "Polygons", "Twin Volcano",
				"USA", "World" };

		JComboBox<String> mapList = new JComboBox<>(mapTitles);
		mapList.setBounds(100, 80, 200, 20);

		JButton btnEditMap = new JButton("Edit Map");
		btnEditMap.setBounds(430, 80, 100, 20);

		JButton btnSubmitNewGame = new JButton("Start Game");
		btnSubmitNewGame.setBounds(10, 500, 100, 30);

		contentPane.add(lblNumPlayers);
		contentPane.add(txtNumPlayers);
		contentPane.add(radioLoadMap);
		contentPane.add(radioSelectMap);
		contentPane.add(radioCreateMap);
		contentPane.add(btnSubmitNewGame);
		
		submitButton.setBounds(550, 80, 100, 20);
		
		contentPane.add(submitButton);
		
		contentPane.repaint();

		radioSelectMap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				radioLoadMap.setSelected(false);
				radioCreateMap.setSelected(false);
				contentPane.add(lblMapSelect);
				contentPane.add(mapList);
				contentPane.remove(lblMapPath);
				contentPane.remove(txtMapPath);
				contentPane.remove(btnEditMap);
				contentPane.repaint();

				mapList.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						contentPane.add(btnEditMap);
						contentPane.repaint();

						/*
						 * String selectedMap =
						 * String.valueOf(mapList.getSelectedItem()); mapImgLoc
						 * = String.format("Resources//Maps//%s.map",
						 * selectedMap); maptxtLoc =
						 * String.format("Resources//Maps//%s.bmp",
						 * selectedMap);
						 */
					}
				});

			}
		});

		radioLoadMap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				radioSelectMap.setSelected(false);
				radioCreateMap.setSelected(false);
				contentPane.add(lblMapPath);
				contentPane.add(txtMapPath);
				contentPane.remove(lblMapSelect);
				contentPane.remove(mapList);
				contentPane.remove(btnEditMap);
				contentPane.repaint();
			}
		});

		radioCreateMap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				radioSelectMap.setSelected(false);
				radioLoadMap.setSelected(false);
				contentPane.remove(lblMapPath);
				contentPane.remove(txtMapPath);
				contentPane.remove(lblMapSelect);
				contentPane.remove(mapList);
				contentPane.remove(btnEditMap);
				contentPane.repaint();
				showCreateMapForm();
			}
		});

		btnEditMap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				radioSelectMap.setSelected(false);
				radioLoadMap.setSelected(false);
				showEditMapForm();
			}
		});

		btnSubmitNewGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				gameMapLocation = new String(txtMapPath.getText());
				gameNumPlayers = new Integer(objGenFun.genStrToInt(txtNumPlayers.getText()));

				String selectedMap = String.valueOf(mapList.getSelectedItem());

				mapImgLoc = String.format("Resources//Maps//%s.map", selectedMap);
				maptxtLoc = String.format("Resources//Maps//%s.bmp", selectedMap);
				objMap = gameConfigObj.createMap(mapImgLoc);
				objMap.setMapName(selectedMap);

				try {
					gameConfigObj.setNumPlayers(Integer.parseInt(txtNumPlayers.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				contentPane.remove(txtMapPath);
				contentPane.remove(lblMapPath);
				contentPane.remove(lblNumPlayers);
				contentPane.remove(txtNumPlayers);
				contentPane.remove(radioLoadMap);
				contentPane.remove(radioSelectMap);
				contentPane.remove(mapList);
				contentPane.remove(lblMapSelect);
				contentPane.remove(btnSubmitNewGame);
				contentPane.remove(radioCreateMap);
				contentPane.remove(btnEditMap);
				contentPane.repaint();

				mainStartGame();
			}
		});

	}

	public void addBtnSubmitActionListener(ActionListener listenForBtnSubmit) {
		submitButton.addActionListener(listenForBtnSubmit);
	}

	private void mainStartGame() {
		loadObjects();
	}

	private void loadObjects() {
		loadMapImage();
		addButtonOnArmies();
	}

	private void addButtonOnArmies() {
		Integer x = new Integer(0);
		Integer y = new Integer(0);

		for (String territoryName : objMap.dictTerritory.keySet()) {

			x = (objMap.dictTerritory).get(territoryName).getX();
			y = (objMap.dictTerritory).get(territoryName).getY();
			JButton btnTemp = new JButton("1");
			btnTemp.setBounds(x - 4, y - 4, 10, 10);
			mapImagePane.add(btnTemp);
			mapImagePane.setComponentZOrder(btnTemp, 0);
			(objMap.dictTerritory).get(territoryName).getBtnTerritories().add(btnTemp);

			btnTemp.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					String continent = (objMap.dictTerritory).get(territoryName).getContinent();
					Vector<String> adjacentCountries = (objMap.dictTerritory).get(territoryName).getAdjacentCountries();
					contentPane.repaint();

					showCountryInfoPanel(territoryName, continent, adjacentCountries);
				}
			});
		}

	}

	private BufferedImage convertBmpToJpg(String inMapName) {
		FileInputStream inStream = null;
		BufferedImage mapJpg = null;
		try {
			ClassLoader classLoader = this.getClass().getClassLoader();
			// File tmpFile=new
			// File(classLoader.getResource(inMapName).getFile());
			File tmpFile = new File(inMapName);
			inStream = new FileInputStream(tmpFile);
			mapJpg = ImageIO.read(inStream);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapJpg;
	}

	private void loadMapImage() {
		try {
			// Image mapImage = ImageIO.read(new FileInputStream(tmpLoctwo));
			// File mapBmp = new File(tmpLoctwo);
			BufferedImage tmpImageJpg = convertBmpToJpg(maptxtLoc);
			if (tmpImageJpg == null) {
				return;
			}
			mapImage = new ImageIcon(tmpImageJpg);

			JLabel mapLabel = new JLabel();
			mapLabel.setVerticalAlignment(SwingConstants.TOP);

			mapLabel.setIcon(mapImage);
			mapLabel.setBounds(0, 0, mapImage.getIconWidth(), mapImage.getIconHeight());

			mapImagePane = new JScrollPane(mapLabel);
			mapImagePane.setBounds(0, 0, 1024, 768);
			checkForImageSize(mapImage);

			contentPane.add(mapImagePane);
			contentPane.repaint();
			mapImagePane.repaint();
			mapLabel.repaint();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void checkForImageSize(ImageIcon inMapImage) {
		if (((inMapImage.getIconWidth() * 2) < mapImagePane.getWidth())
				|| ((inMapImage.getIconHeight() * 2) < mapImagePane.getHeight())) {
			Image imgTemp = inMapImage.getImage();
			imgTemp = imgTemp.getScaledInstance(inMapImage.getIconWidth() * 2, inMapImage.getIconHeight() * 2,
					java.awt.Image.SCALE_SMOOTH);
			mapImage = new ImageIcon(imgTemp);
		}
	}

	private void showCountryInfoPanel(String name, String continent, Vector<String> adjacentCountries) {
		// JPanel territoryInfoPanel = new JPanel();
		// territoryInfoPanel.setBounds(1024, 0, 800, 800);

		JLabel nameLabel = new JLabel("Country Name: " + name);
		nameLabel.setBounds(1024, 0, 800, 800);

		JLabel continentLable = new JLabel("Continent Name: " + continent);
		continentLable.setBounds(1024, 100, 800, 800);

		JLabel adjacentCountriesLable = new JLabel("Adjacent Countries: " + adjacentCountries);
		adjacentCountriesLable.setBounds(1024, 200, 800, 800);
		// territoryInfoPanel.setVisible(true);
		// territoryInfoPanel.add(nameLabel);
		// territoryInfoPanel.add(continentLable);
		contentPane.add(nameLabel);
		contentPane.add(continentLable);
		contentPane.add(adjacentCountriesLable);
		contentPane.repaint();
	}

	public void showCreateMapForm() {

	}

	public void showEditMapForm() {

	}
}
