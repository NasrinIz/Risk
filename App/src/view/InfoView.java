package view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * @author Team20
 */
public class InfoView extends JPanel {
	private JTextArea InfoTextArea;
	public InfoView() {
		this.showTerritoryPanel();
	}

	/**
	 * Show territory information panel
	 */
	public void showTerritoryPanel() {
		this.setBackground(Color.GRAY);
		this.setLayout(new FlowLayout());
		InfoTextArea = new JTextArea();
		InfoTextArea.setRows(10);
		this.setBounds(1024, 0, 255, 768);
		InfoTextArea.setBounds(1024, 0, 255, 768);
		this.add(InfoTextArea);
		TitledBorder border = new TitledBorder("Information Panel");
	    border.setTitleJustification(TitledBorder.CENTER);
	    border.setTitlePosition(TitledBorder.TOP);
		this.setBorder(border);
		this.setVisible(true);
	}

	public void showTerritoryInfo(String info) {
		InfoTextArea.setText(info);
	}

}
