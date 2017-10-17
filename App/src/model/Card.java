package model;

/**
 * @author Team20
 *
 */
public class Card {
	RiskCard cardType;
	String territoryName;

	/**
	 * @param cardType
	 * @param territoryName
	 */
	public Card(RiskCard cardType, String territoryName) {
		this.cardType = cardType;
		this.territoryName = territoryName;
	}

	
	
	
	/**
	 * @return the cardType
	 */
	public RiskCard getCardType() {
		return cardType;
	}




	/**
	 * @param cardType the cardType to set
	 */
	public void setCardType(RiskCard cardType) {
		this.cardType = cardType;
	}




	/**
	 * @return the territoryName
	 */
	public String getTerritoryName() {
		return territoryName;
	}




	/**
	 * @param territoryName the territoryName to set
	 */
	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}


	public int getWorth() {
		
		switch (cardType) {
		case WILD:
			return 8;
		case CAVALRY:
			return 5;
		case ARTILLERY:
			return 10;
		
		default:
			return 1;
		}
	}

	/**
	 * This method return the information of all types of cards
	 */
	public String toString() {
		String Info = "Card Type:	" + this.cardType +"\n"
				+ "Card Territory name:	" + this.getTerritoryName() +"\n"
				+ "card value:	" + this.getWorth() +"\n";
		return Info;
		
	}
	
	/**
	 * compares this.cardType to the passed type
	 * @param type
	 * @return 0 if this.cardType == type, 1 if this.cardType > type, -1 if this.cardType < type
	 */
	public int compareTypeTo(RiskCard type) {
		return this.cardType.compareTo(type);
	}
	
}

