package src.view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * This is the src.view that displays the error messages while loading the map.
 * @author Team20
 */
public class AttackView extends JPanel
{
    private JTextField attackInputField;
    private JLabel attackLabel;
    private JButton diceNumberButton;


    AttackView()
    {
        this.showAttackPanel();
    }

    /**
     * Show error information
     */
    private void showAttackPanel()
    {
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new FlowLayout());
        this.setBounds(1024, 468, 255, 100);


        TitledBorder border = new TitledBorder("Attack Panel");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        this.setBorder(border);
        this.setVisible(true);
        attackInputField = new JTextField();
        attackLabel= new JLabel("Dice #: ");
        attackLabel.setBounds(1024, 468, 30, 100);

        attackInputField = new JTextField();
        attackInputField.setBounds(1024, 550, 200, 100);

        diceNumberButton = new JButton("Submit");
    }

    /**
     * Showing attack input
     */
    public void showAttackInfo()
    {
        this.add(attackLabel);
        this.add(attackInputField);
        this.add(diceNumberButton);
    }

}
