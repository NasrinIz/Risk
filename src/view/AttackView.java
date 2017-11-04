package src.view;

import java.awt.*;
import java.awt.event.ActionListener;

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
    private Boolean attackerTurn = true;
    private Integer attackerDice;
    private Integer defenderDice;


    AttackView() {
        this.showAttackPanel();
    }

    /**
     * Show error information
     */
    private void showAttackPanel() {
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new GridLayout());
        this.setBounds(1024, 418, 255, 150);


        TitledBorder border = new TitledBorder("Attack Panel");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);

        attackLabel = new JLabel("Dice #: ");
        attackLabel.setBounds(1024, 418, 20, 50);

        attackInputField = new JTextField();
        attackInputField.setBounds(1044, 418, 235, 50);

        diceNumberButton = new JButton("Submit");
        attackInputField.setBounds(1024, 450, 200, 50);

        this.setBorder(border);
        this.setVisible(true);
    }

    /**
     * Showing attack input
     */
    public void showAttackInfo() {


        this.add(attackLabel);
        this.add(attackInputField);
        this.add(diceNumberButton);
    }

    /**
     *
     */
    public Boolean checkDiceValue() {
        if (attackerTurn) {
            if (Integer.parseInt(attackInputField.getText()) > 0 && Integer.parseInt(attackInputField.getText()) < 4) {
                attackerDice = Integer.parseInt(attackInputField.getText());
                attackerTurn = false;
            }
        } else {
            if (Integer.parseInt(attackInputField.getText()) > 0 && Integer.parseInt(attackInputField.getText()) < 3) {
                defenderDice = Integer.parseInt(attackInputField.getText());
                attackerTurn = true;
            }
        }
        if (attackerDice > 0 && attackerDice < 4 && defenderDice > 0 && defenderDice < 3) {
            return true;
        } else {
            return false;
        }
    }

    public String getDiceValues() {
        return attackerDice.toString() + "," + defenderDice.toString();
    }

    /**
     * @param listenForDiceBtn dice btn
     */
    public void addDiceBtnListener(ActionListener listenForDiceBtn) {
        diceNumberButton.addActionListener(listenForDiceBtn);
    }

}
