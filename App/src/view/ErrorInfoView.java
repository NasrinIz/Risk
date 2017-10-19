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
public class ErrorInfoView extends JPanel {
	private JLabel infoLabel;
	
	public ErrorInfoView() {
		this.showErrorPanel();
	}

	/**
	 * Show error information 
	 */
	public void showErrorPanel() {
		this.setBackground(Color.RED);
		this.setLayout(new FlowLayout());
		this.setBounds(1024, 468, 255, 300);
		TitledBorder border = new TitledBorder("Error Panel");
	    border.setTitleJustification(TitledBorder.CENTER);
	    border.setTitlePosition(TitledBorder.TOP);
		this.setBorder(border);
		this.setVisible(true);
	}
	
	public void showErrorInfo(String error){
		infoLabel = new JLabel(error);
		infoLabel.setBounds(1024, 468, 200, 30);
		this.add(infoLabel);
	}

}
