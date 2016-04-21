package uk.mickrisk.gamelogic;

import java.util.ArrayList;
import java.util.List;

public class UnoPlayer {

	private List<String> hand;
	private String name;
	
	public UnoPlayer()
	{
		hand = new ArrayList<String>();
		setName("default");
	}
	
	public UnoPlayer(String playerName)
	{
		hand = new ArrayList<String>();
		setName(playerName);
	}
	
	public void addCardToHand(String card)
	{
		hand.add(card);
	}
	
	public void emptyHand()
	{
		hand.clear();
	}

	public String getName() {
		return name;
	}
	
	public List<String> getHand() {
		return hand;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
