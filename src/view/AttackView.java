package src.view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * This is the class that shows attack view.
 *
 * @author Team20
 */
public class AttackView extends JPanel {
    private JTextField attackInputField;
    private JButton diceNumberButton;
    private Boolean attackerTurn = true;
    private Integer attackerDice = 0;
    private Integer defenderDice = 0;


    AttackView() {
        this.showAttackPanel();
    }

    /**
     * Show attack panel
     */
    private void showAttackPanel() {
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new GridLayout());
        this.setBounds(1024, 485, 255, 50);


        TitledBorder border = new TitledBorder("Attack Panel");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);

        JLabel attackLabel = new JLabel("Dice #: ");
        attackLabel.setBounds(1024, 485, 20, 50);

        attackInputField = new JTextField();
        attackInputField.setBounds(1044, 485, 235, 50);

        diceNumberButton = new JButton("Submit");
        diceNumberButton.setBounds(1024, 524, 200, 50);

        this.add(attackLabel);
        this.add(attackInputField);
        this.add(diceNumberButton);
        this.setBorder(border);
        this.setVisible(true);
    }

    /**
     * Showing attack information
     */
    public void showAttackInfo() {

    }

    /**
     *
     */
    public Boolean checkDiceValue() {
    	try
    	{
	        if (attackerTurn)
	        {
	            if (Integer.parseInt(attackInputField.getText()) > 0 && Integer.parseInt(attackInputField.getText()) < 4) 
	            {
	                attackerDice = Integer.parseInt(attackInputField.getText());
	                attackInputField.setText("");
	                attackerTurn = false;
	            }
	            else
	            {
	            	System.out.println("Incorrect dice roll. Attacker can roll two less dice from number of armies on territory, given max dice = 3");
	            	return false;
	            }
	        } 
	        else 
	        {
	            if (Integer.parseInt(attackInputField.getText()) > 0 && Integer.parseInt(attackInputField.getText()) < 3) 
	            {
	                defenderDice = Integer.parseInt(attackInputField.getText());
	                attackInputField.setText("");
	                attackerTurn = true;
	                if((attackerDice != null) && (defenderDice != null) &&
	                		(attackerDice > 0) && (attackerDice < 4) && 
	                		(defenderDice > 0) && (defenderDice < 3)) 
	                {
	                    return true;
	                } 
	            }
	            else
	            {
	            	System.out.println("Incorrect dice roll. Defender can roll 2 or less dice equal to number of armies on territory");
	            	return false;
	            }
	        }
    	}
    	catch(NumberFormatException e)
    	{
    		//
    	}
        return false;
    }

    /**
     * This function returns the dice values to the controller from view.
     * @return Dice Values of both attacker and defender as one String
     */
    public String getDiceValues() 
    {
        return attackerDice.toString() + "," + defenderDice.toString();
    }

    /**
     * This function is used to reset the dice values to zero
     */
    public void resetDiceValues() 
    {
    	attackerDice = 0;
    	defenderDice = 0;
    }
    
    /**
     * @param listenForDiceBtn dice btn
     */
    public void addDiceBtnListener(ActionListener listenForDiceBtn) {
        diceNumberButton.addActionListener(listenForDiceBtn);
    }

}
