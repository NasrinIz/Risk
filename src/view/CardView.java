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
       // InfoTextArea.setBounds(1024, 535, 200, 150);
        this.add(InfoTextArea);

        TitledBorder border = new TitledBorder("Card Panel");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);

        JLabel infantryLabel = new JLabel("I: ");
       // infantryLabel.setBounds(1024, 535, 10, 50);

        infantryInputField = new JTextField();
       // infantryInputField.setBounds(1024, 545, 50, 50);

        JLabel artilleryLabel = new JLabel("A: ");
     //   artilleryLabel.setBounds(1064, 535, 10, 50);

        artilleryInputField = new JTextField();
     //   artilleryInputField.setBounds(1064, 545, 50, 50);

        JLabel cavalryLabel = new JLabel("C: ");
      //  cavalryLabel.setBounds(1104, 535, 10, 50);

        cavalryInputField = new JTextField();
      //  cavalryInputField.setBounds(1104, 485455, 50, 50);

        JLabel wildLabel = new JLabel("W: ");
        wildLabel.setBounds(1144, 535, 10, 50);

        wildInputField = new JTextField();
    //    wildInputField.setBounds(1144, 545, 50, 50);

        cardNumberButton = new JButton("S");
     //   cardNumberButton.setBounds(1024, 585, 200, 50);

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
        ArrayList<Card> cards = ((GameConfig) o).getGameCards();

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
                if (value.cardType == 3) {
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
        return infantryInputField.getText() + "," + artilleryInputField.getText() + "," +
                infantryInputField.getText() + "," + artilleryInputField.getText();
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
