package uk.mickrisk.gamelogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnoDeck implements CardDeckInterface {
	private List<String> deck;
	private List<String> pool;

	public UnoDeck(){
		deck = new ArrayList<String>();
		fillDeck();
	}
	
	public void fillDeck() {
		for (int suitIndex = 2; suitIndex < 11; suitIndex++) {
			deck.add(suitIndex + "-diamond");
			deck.add(suitIndex + "-heart");
			deck.add(suitIndex + "-spade");
			deck.add(suitIndex + "-club");
		}

		deck.add("jack-diamond");
		deck.add("jack-heart");
		deck.add("jack-club");
		deck.add("jack-spade");
		deck.add("queen-diamond");
		deck.add("queen-heart");
		deck.add("queen-club");
		deck.add("queen-spade");
		deck.add("king-diamond");
		deck.add("king-heart");
		deck.add("king-club");
		deck.add("king-spade");
		deck.add("ace-diamond");
		deck.add("ace-heart");
		deck.add("ace-club");
		deck.add("ace-spade");
		deck.add("joker");
		deck.add("joker");	
		
		pool = new ArrayList<String>(deck);
	}

	@Override
	public List<String> allCards() {
		return allCards();
	}

	@Override
	public List<String> dealCards(int numberOfCards) {
		List<String> dealtCards = new ArrayList<String>();
		for(int cardIndex = 0; cardIndex < numberOfCards; cardIndex++){
			dealtCards.add(pool.remove(0));
		}
		return dealtCards;
	}

	@Override
	public void shuffleCards() {
		Collections.shuffle(pool);
	}

	@Override
	public void resetDeck() {
		pool = new ArrayList<String>(deck);
	}
}
