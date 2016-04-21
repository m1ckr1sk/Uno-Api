package uk.mickrisk.gamelogic;

import java.util.List;

public interface CardDeckInterface {
	public List<String> allCards();
	public List<String> dealCards(int numberOfCards);
	public void shuffleCards();
	public void resetDeck();
}
