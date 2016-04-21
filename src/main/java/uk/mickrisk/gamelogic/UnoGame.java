package uk.mickrisk.gamelogic;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnoGame {
	private List<UnoPlayer> hands;
	private static final int MAX_PLAYERS = 4;

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private CardDeckInterface deck;

	public UnoGame(CardDeckInterface deck) {
		hands = new ArrayList<UnoPlayer>();
		this.deck = deck;
	}

	public void deal(int numberOfCards) {
		deck.shuffleCards();
		log.debug("Dealing cards");
		
		for (UnoPlayer player : hands) {
			player.emptyHand();
			log.debug("Dealing to:" + player.getName());
			List<String> dealtCards =  deck.dealCards(numberOfCards);
			for (String dealtCard :dealtCards) {
				player.addCardToHand(dealtCard);
			}
		}
	}

	public UnoPlayer getPlayer(String playerName) {
		for (UnoPlayer player : hands) {
			if (player.getName().equals(playerName)) {
				return player;
			}
		}
		return null;
	}

	public String addPlayer(UnoPlayer player) {
		if (hands.size() < MAX_PLAYERS) {
			hands.add(player);
			return "Joined the game:" + player.getName();
		}
		return "New player not added, the game is full";
	}
}
