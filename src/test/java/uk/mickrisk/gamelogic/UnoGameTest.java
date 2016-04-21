package uk.mickrisk.gamelogic;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
public class UnoGameTest {

	@Test
	public void testDealShufflesTheCard() {
		UnoDeck unoDeck = mock(UnoDeck.class);
		UnoGame gameUnderTest = new UnoGame(unoDeck);
		
		gameUnderTest.deal(2);
		
		verify(unoDeck).shuffleCards();
	}
	
	@Test
	public void testDealGetsCorrectNumberOfCardsFromDeck() {
		UnoDeck unoDeck = mock(UnoDeck.class);
		UnoGame gameUnderTest = new UnoGame(unoDeck);
		
		gameUnderTest.addPlayer(new UnoPlayer("MIKE"));
		gameUnderTest.deal(2);
		
		verify(unoDeck).dealCards(2);
	}
	
	@Test
	public void testDealGetsCorrectNumberOfCardsFromDeckMultiplePlayers() {
		UnoDeck unoDeck = mock(UnoDeck.class);
		UnoGame gameUnderTest = new UnoGame(unoDeck);
		
		gameUnderTest.addPlayer(new UnoPlayer("MIKE"));
		gameUnderTest.addPlayer(new UnoPlayer("PAUL"));
		gameUnderTest.deal(2);
		
		verify(unoDeck,times(2)).dealCards(2);
	}

}
