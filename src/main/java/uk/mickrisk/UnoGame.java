package uk.mickrisk;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnoGame {
	private List<UnoPlayer> hands;
	private static final int MAX_PLAYERS = 4;
	private List<String> deck;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public UnoGame() {
		hands = new ArrayList<UnoPlayer>();
		deck = new ArrayList<String>();
		fillDeck();
	}

	private void fillDeck() {
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
	}

	public void deal() {
		List<String> pool = new ArrayList<String>(deck);
		Collections.shuffle(pool);
		log.debug("Dealing cards");
		for (UnoPlayer player : hands) {
			player.emptyHand();
			log.debug("Dealing to:" + player.getName());
			for (int cardIndex = 0; cardIndex < 5; cardIndex++) {
				String dealtCard = pool.remove(0);
				log.debug("Dealt card:" + dealtCard);
				player.addCardToHand(dealtCard);
			}
		}

	}
	
	public UnoPlayer getPlayer(String playerName)
	{
		for (UnoPlayer player : hands) {
		    if (player.getName().equals(playerName)) {
		        return player;
		    }
		}
		return null;
	}

	public void addPlayer(UnoPlayer player) {
		if (hands.size() < MAX_PLAYERS) {
			hands.add(player);
		}
	}

	public boolean canAddPlayer() {
		return hands.size() < MAX_PLAYERS;
	}
}
