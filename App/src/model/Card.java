package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Team20
 *
 */
public class Card {
	
	private final Integer CARDNONE = 0;
	private final Integer CARDINFANTORY = 1;
	private final Integer CARDCAVALRY = 2;
	private final Integer CARDARTILLERY = 3;
	
	private Integer cardId = null;
	private Integer ownerId = null;
	public Integer cardType = CARDNONE;
	public Integer cardRewardArmy = 15;
	
	public Card(Integer cardId, Integer cardType)
	{
		this.cardId = cardId;
		this.cardType = cardType;
	}
	
	/**
	 * This method get the owner id
	 */
	public Integer getOwnerId()
	{
		return this.ownerId;
	}
	
	/**
	 * This method set owner id
	 * @param id
	 */
	public void setOwnerId(Integer id)
	{
		this.ownerId = id;
	}
}

