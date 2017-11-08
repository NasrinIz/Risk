package src.model;

/**
 * This class is used to create cards according to the Risk rules.
 * The created cards can then be assigned to players, and they receive additional
 * army for exchanging cards.
 *
 * @author Team20
 */
public class Card {

    private final Integer CARDNONE = 0;
    private final Integer CARDINFANTORY = 1;
    private final Integer CARDCAVALRY = 2;
    private final Integer CARDARTILLERY = 3;

    /**
     * The card's unique ID
     */
    private Integer cardId = null;
    /**
     * The card's owner ID. Every card has only one owner.
     */
    private Integer ownerId = null;
    /**
     * The cardtype is set to none by default.
     */
    public Integer cardType = CARDNONE;
    /**
     * The number of armies rewarded for exchanging cards.
     */
    public Integer cardRewardArmy = 15;

    /**
     * This is the constructor, that initializes the class variables
     *
     * @param cardId   The ID for this card
     * @param cardType The type of card
     */
    public Card(Integer cardId, Integer cardType) {
        this.cardId = cardId;
        this.cardType = cardType;
    }

    /**
     * This method get the owner id
     */
    public Integer getOwnerId() {
        return this.ownerId;
    }

    /**
     * This method set owner id
     *
     * @param id The new card owner ID
     */
    void setOwnerId(Integer id) {
        this.ownerId = id;
    }
}

