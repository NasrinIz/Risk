package src.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * This is the src.view that displays the error messages while loading the map.
 *
 * @author Team20
 */
public class AttackView extends JPanel {
    private JTextField attackInputField;
    private JLabel attackLabel;
    private JButton diceNumberButton;


    AttackView() {
        this.showAttackPanel();
    }

    /**
     * Show error information
     */
    private void showAttackPanel() {
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new BorderLayout());
        this.setBounds(1024, 418, 255, 150);


        TitledBorder border = new TitledBorder("Attack Panel");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        this.setBorder(border);
        this.setVisible(true);
    }

    /**
     * Showing attack input
     */
    public void showAttackInfo() {
        attackInputField = new JTextField();
        attackLabel = new JLabel("Dice #: ");
        attackLabel.setBounds(1024, 418, 20, 50);

        attackInputField = new JTextField();
        attackInputField.setBounds(1044, 418, 235, 50);

        diceNumberButton = new JButton("Submit");
        attackInputField.setBounds(1024, 450, 200, 50);
        this.add(attackLabel);
        this.add(attackInputField);
        this.add(diceNumberButton);
    }

}
