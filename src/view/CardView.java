package src.view;

import src.model.Card;
import src.model.GameConfig;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * This is the calss that shows card information
 *
 * @author Team20
 */
public class CardView extends JPanel implements Observer {
    private JTextArea InfoTextArea;

    /**
     * Show card panel
     */
    public void showCardPanel() {
        this.setBackground(Color.CYAN);
        this.setLayout(new FlowLayout());
        this.setBounds(1024, 568, 255, 50);
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

        if(cards.size() <= 0){
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
                cardInfantry.toString() + "Infantry" + "," +
                cardCavalry.toString() + "Cavalry" + "," +
                cardArtillery.toString() + "Artillery" + "," +
                cardWild.toString() + "Wild");
    }

}
