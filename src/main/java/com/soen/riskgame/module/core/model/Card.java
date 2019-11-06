package com.soen.riskgame.module.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.soen.riskgame.module.core.constants.Cards;
/**
 * This class supports the functionality of providing the player with cards of certain type which can be
 *  exchanged to get the armies in return.
 * @author Hitansh
 *
 *
 */
public class Card {
	
	/**
	 * List of cards obtained by a particular player
	 */
	private List<String> cards;
	
	/**
	 * It is a default constructor
	 */
	public Card() {
		cards=new ArrayList<String>();
	}
	
	/**
	 * 
	 * @return 0 is failure case when a player already has maximum number of cards
	 * @return 1 if the success case when a player is assigned a cards randomly
	 */
	public int getCard()
	{
		if(cards.size()<5) {
		Random random=new Random();
		int n=random.nextInt(3);
		n=n+1;
		if(n==1) {
			cards.add(Cards.INFANTRY);
		}
		else if(n==2) {
			cards.add(Cards.CAVALRY);
		}
		else
			cards.add(Cards.ARTILLERY);
		return 1;
		}
		else
			return 0;
	}
	
	/**
	 * This method supports the functionality of exchanging cards to get the armies
	 * @param x index value of the card which the player wants to exchange
	 * @param y index value of the card which the player wants to exchange
	 * @param z index value of the card which the player wants to exchange
	 * @return 1 when all cards are successfully removed from the list of cards
	 * @return 0 failure case when the card is not present
	 */
	public int reduceCard(int x,int y,int z) {
		int size=this.cards.size();
		if(x<size && y<size && z<size) {
			this.cards.remove(x);
			this.cards.remove(y);
			this.cards.remove(z);
			return 1;
		}
		else 
			return 0;
		}
	
	/**
	 * Method to get the list of the cards
	 * @return list of cards that a particular player holds
	 */
	public List<String> getPlayerCards() {
		return cards;
	}
	
	/**
	 * This method returns the number of cards that a player holds.
	 * @return count of the cards
	 */
	public int getNumberOfCards() {
		return this.cards.size();
	}
	

}
