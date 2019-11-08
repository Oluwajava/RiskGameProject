package com.soen.riskgame.module.core.model;


import java.util.ArrayList;
import java.util.List;


import com.soen.riskgame.module.core.constants.Cards;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {
	
	private Card card;

	@Before
	public void setUp() {
		
		card=new Card();
		List<String> cards=new ArrayList<String>();
		cards.add(Cards.ARTILLERY);
		cards.add(Cards.CAVALRY);
		cards.add(Cards.INFANTRY);
		card.setCards(cards);
		
	}
	
	@Test
	public void testgetPlayerCards() {
		List<String> cards=new ArrayList<String>();
		cards.add(Cards.ARTILLERY);
		cards.add(Cards.CAVALRY);
		cards.add(Cards.INFANTRY);
		card.setCards(cards);
		assertEquals(cards, card.getPlayerCards());
	}
	
	@Test
	public void getNumberOfCards() {
		List<String> cards=new ArrayList<String>();
		cards.add(Cards.ARTILLERY);
		cards.add(Cards.CAVALRY);
		cards.add(Cards.INFANTRY);
		card.setCards(cards);
		assertEquals(cards.size(), card.getNumberOfCards());
	}
	
	
	@Test
	public void testReduceCard() {
		List<String> cards=new ArrayList<String>();
		cards.add(Cards.ARTILLERY);
		cards.add(Cards.CAVALRY);
		cards.add(Cards.INFANTRY);
		
		card.setCards(cards);
		System.out.println("Size"+cards.size());
		int res=card.reduceCard(0, 1, 2);
		assertEquals(1, res);
		int res1=card.reduceCard(0, 1, 3);
	}
	
	@Test
	public void testReduceCard2() {
		List<String> cards=new ArrayList<String>();
		cards.add(Cards.ARTILLERY);
		cards.add(Cards.CAVALRY);
		cards.add(Cards.INFANTRY);
		
		card.setCards(cards);
		
		
		int res1=card.reduceCard(0, 1, 3);
		assertEquals(0, res1);
	}
	
	@Test
	public void testgetCard()
	{
		List<String> cards=new ArrayList<String>();
		cards.add(Cards.ARTILLERY);
		cards.add(Cards.CAVALRY);
		cards.add(Cards.INFANTRY);
		
		card.setCards(cards);
		
		assertEquals(1,card.getCard());
	}
	
	@Test
	public void testgetCard2()
	{
		List<String> cards=new ArrayList<String>();
		cards.add(Cards.ARTILLERY);
		cards.add(Cards.CAVALRY);
		cards.add(Cards.INFANTRY);
		cards.add(Cards.CAVALRY);
		cards.add(Cards.INFANTRY);
		
		card.setCards(cards);
		card.getCard();
		assertEquals(0, card.getCard());
	}

	
}
