package src.view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * This is the src.view that displays the error messages while loading the map.
 * @author Team20
 */
public class CardView extends JPanel
{
    private JTextArea InfoTextArea;

    CardView()
    {
        this.showCardPanel();
    }

    /**
     * Show error information
     */
    private void showCardPanel()
    {
        this.setBackground(Color.CYAN);
        this.setLayout(new FlowLayout());
        this.setBounds(1024, 568, 255, 100);
        InfoTextArea = new JTextArea();
        InfoTextArea.setRows(2);
        InfoTextArea.setBounds(1024, 568, 200, 50);
        this.add(InfoTextArea);

        TitledBorder border = new TitledBorder("Card Panel");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        this.setBorder(border);
        this.setVisible(true);
    }

    /**
     * error to show in panel
     */
    public void showCardInfo()
    {
        System.out.println();
    }

}
