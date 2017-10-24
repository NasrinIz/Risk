package src.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * this class is used to show informatiokn for each territory that the
 * user clicks on on a separate panel.
 * @author Team20
 */
public class InfoView extends JPanel {
	private JTextArea InfoTextArea;
	private JButton passTurnBtn;
	public InfoView() {
		this.addPassButton();
		this.showTerritoryPanel();
	}

	/**
	 * It adds Pass Button
	 */
	public void addPassButton() {
		passTurnBtn = new JButton("Pass turn");
		passTurnBtn.setBounds(1024, 1000, 200, 30);
		this.add(passTurnBtn);
	}
	/**
	 * Show territory information panel
	 */
	public void showTerritoryPanel() {
		this.setBackground(Color.GRAY);
		this.setLayout(new FlowLayout());
		InfoTextArea = new JTextArea();
		InfoTextArea.setRows(10);
		this.setBounds(1024, 0, 255, 468);
		InfoTextArea.setBounds(1024, 0, 255, 468);
		this.add(InfoTextArea);
		
		
		TitledBorder border = new TitledBorder("Information Panel");
	    border.setTitleJustification(TitledBorder.CENTER);
	    border.setTitlePosition(TitledBorder.TOP);
		this.setBorder(border);
		this.setVisible(true);
	}

	/**
	 * @param listenForSubmitButton
	 */
	public void passBtnActionListener(ActionListener listenForPassBtn) {
		passTurnBtn.addActionListener(listenForPassBtn);
	}
	
	/**
	 * It shows the information of territory
	 * @param info
	 */
	public void showTerritoryInfo(String info) {
		InfoTextArea.setText(info);
	}

}