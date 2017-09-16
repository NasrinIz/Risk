import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.TextField;
import java.awt.Label;

public class AppWbMainWindow extends JFrame {

	private JPanel contentPane;
	
	/* Game Settings */
	String gameMapLocation = null;
	Integer gameNumPlayers = null;

	GenFun objGenFun = new GenFun();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppWbMainWindow frame = new AppWbMainWindow();
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
	public AppWbMainWindow() {
		setSize(getMaximumSize());
		initWindow();
	}
	
	/* */
	private void initWindow() {
		setTitle("Risk");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 489);
		
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
		
	}
}
