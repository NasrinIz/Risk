package src.view;

import src.model.Card;
import src.model.GameConfig;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * This is the class that shows card information
 *
 * @author Team20
 */
public class CardView extends JPanel implements Observer {
    private JTextArea InfoTextArea;
    private JTextField infantryInputField;
    private JTextField artilleryInputField;
    private JTextField cavalryInputField;
    private JTextField wildInputField;
    private JButton cardNumberButton;
    private JLabel infoArea;


    /**
     * Show card panel
     */
    public void showCardPanel() {
        this.setBackground(Color.CYAN);
        this.setLayout(new GridLayout());
        this.setBounds(1024, 535, 255, 100);
        InfoTextArea = new JTextArea();
        InfoTextArea.setRows(2);
        this.add(InfoTextArea);

        TitledBorder border = new TitledBorder("Card Panel");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);

        JLabel infantryLabel = new JLabel("I: ");
        infantryInputField = new JTextField();
        JLabel artilleryLabel = new JLabel("A: ");
        artilleryInputField = new JTextField();
        JLabel cavalryLabel = new JLabel("C: ");
        cavalryInputField = new JTextField();
        JLabel wildLabel = new JLabel("W: ");
        wildLabel.setBounds(1144, 535, 10, 50);
        wildInputField = new JTextField();
        cardNumberButton = new JButton("S");
        this.add(infantryLabel);
        this.add(infantryInputField);

        this.add(artilleryLabel);
        this.add(artilleryInputField);

        this.add(cavalryLabel);
        this.add(cavalryInputField);

        this.add(wildLabel);
        this.add(wildInputField);

        this.add(cardNumberButton);
        this.setBorder(border);
        this.setVisible(true);
    }

    /**
     * Show card information
     */
    private void showCardInfo(String Info) {
        InfoTextArea.setText(Info);
    }

    @Override
    public void update(Observable o, Object arg) {
        Integer playerId = ((GameConfig) o).getCurrentPlayer().getPlayerId();
        ArrayList<Card> cards = ((GameConfig) o).getCurrentPlayer().getPlayerCards();

        Integer cardNone = 0;
        Integer cardInfantry = 0;
        Integer cardCavalry = 0;
        Integer cardArtillery = 0;
        Integer cardWild = 0;

        if (cards.size() <= 0) {
            return;
        }
        for (Card value : cards) {
            if (value.getOwnerId() == playerId) {
                if (value.cardType == 0) {
                    cardNone++;
                }
                if (value.cardType == 1) {
                    cardInfantry++;
                }
                if (value.cardType == 2) {
                    cardCavalry++;
                }
                if (value.cardType == 3) {
                    cardArtillery++;
                }
                if (value.cardType == 4) {
                    cardWild++;
                }
            }
        }

        this.showCardInfo(
                cardInfantry.toString() + "I" + "," + "\n" +
                cardCavalry.toString() + "C" + "," + "\n" +
                cardArtillery.toString() + "A" + "," + "\n" +
                cardWild.toString() + "W");
    }

    /**
     * @param listenForCardBtn dice btn
     */
    public void addCardBtnListener(ActionListener listenForCardBtn) {
        cardNumberButton.addActionListener(listenForCardBtn);
    }


    /**
     * This function returns the card values to the controller from view.
     *
     * @return Card Values for all cards
     */
    public String getCardValues() {
    	String rt = "";
        if(!infantryInputField.getText().equals("")){
            rt += infantryInputField.getText();
        }
        else {
        	rt += Integer.toString(0);
        }
        rt += ",";
        if(!artilleryInputField.getText().equals("")){
        	rt += artilleryInputField.getText();
        }
        else {
        	rt += Integer.toString(0);
        }
        rt += ",";
        if(!cavalryInputField.getText().equals("")){
        	rt += cavalryInputField.getText();
        }
        else {
        	rt += Integer.toString(0);
        }
        rt += ",";
        if(!wildInputField.getText().equals("")){
        	rt += wildInputField.getText();
        }
        else {
        	rt += Integer.toString(0);
        }
        return rt;
    }

    /**
     *
     */
    public boolean checkCardValue() {

        Integer sum = 0;
        try {
            if(!infantryInputField.getText().equals("")){
                sum = Integer.parseInt(infantryInputField.getText()) + sum;
            }
            if(!artilleryInputField.getText().equals("")){
                sum = Integer.parseInt(artilleryInputField.getText()) + sum;
            }
            if(!cavalryInputField.getText().equals("")){
                sum = Integer.parseInt(cavalryInputField.getText()) + sum;
            }
            if(!wildInputField.getText().equals("")){
                sum = Integer.parseInt(wildInputField.getText()) + sum;
            }
            return sum == 3;
        } catch (NumberFormatException e) {
            System.out.println("Either leave card text area blank or enter an integer");
            return false;
        }

    }


}
