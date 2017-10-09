package model;

enum RiskCards {
	INFANTRY, CAVALRY, ARTILLERY, WILD
}

public class Card {
	RiskCards cardType;
	String territoryName;

	public Card(RiskCards cardType, String territoryName) {
		this.cardType = cardType;
		this.territoryName = territoryName;
	}

	
	
	
	/**
	 * @return the cardType
	 */
	public RiskCards getCardType() {
		return cardType;
	}




	/**
	 * @param cardType the cardType to set
	 */
	public void setCardType(RiskCards cardType) {
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
	
	public String toString() {
		String Info = "Card Type:	" + this.cardType +"\n"
				+ "Card Territory name:	" + this.getTerritoryName() +"\n"
				+ "card value:	" + this.getWorth() +"\n";
		return Info;
		
	}
	
}

