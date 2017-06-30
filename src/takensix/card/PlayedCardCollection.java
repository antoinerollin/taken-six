package takensix.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import takensix.player.Player;

public class PlayedCardCollection extends HashMap<String, List<Card>>{

	private static final long serialVersionUID = 1L;

	public PlayedCardCollection(PlayedCardCollection playedCardCollection){
		for (Entry<String, List<Card>> entry : playedCardCollection.entrySet()){
			List<Card> cards = new ArrayList<>();
			
			for (Card c : entry.getValue())
				cards.add(new Card(c.getNumber(), c.getScore()));
			
			this.put(entry.getKey(), cards);			
		}
	}
	
	public PlayedCardCollection(){
		super();
	}
	
	public void updatePlayedCardsHistory(Entry<Player, Card> playedCard) {
		String playerName = playedCard.getKey().getName();
		Card c = playedCard.getValue();
		
		List<Card> cards = null;
		if (!this.containsKey(playerName))
			cards = new ArrayList<>();
		else
			cards = this.get(playerName);
		
		cards.add(c);
		this.put(playerName, cards);
	}
}
