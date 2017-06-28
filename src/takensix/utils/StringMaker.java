package takensix.utils;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import takensix.card.Card;
import takensix.player.Player;
import takensix.stack.Stacks;

public class StringMaker {
	public final static String SEPARATOR = "------------------------\n";
	
	public static String players(List<Player> players) {
		String str = "[PLAYERS & SCORES]\n";
		for (Player p : players)
			str += p.toString() + "\n";
		return separate(str);
	}
	
	public static String playedCards(Map<Player, Card> playedCards) {
		String str = "[PLAYED CARDS]\n";
		for (Entry<Player, Card> e : playedCards.entrySet())
			str += e.getKey().getName() + ": " + e.getValue().toString() + "\n";
		return separate(str);
	}
	
	public static String playsCard(Player player, Card card) {
		return player.getName() + " PLAYS " + card.toString() + '\n';
	}
	
	public static String getsPoints(Player player, int points){
		return player.getName() + " GETS " + points + " POINTS";
	}
	
	public static String choosesStack(Player player, int stackIndex){
		return player.getName() + " CHOOSES STACK {" + stackIndex + "}\n";
	}
	
	public static String stacks(Stacks stacks) {
		String str = "[STACKS]\n";
		str += stacks.toString() + "\n";
		return separate(str);
	}
	
	public static String askCard(List<Card> playerCards) {
		String str = "";
		int size = playerCards.size();
		for (int i = 1; i <= size; i++)
			str += i + ": " + playerCards.get(i-1).toString() + "\n";
		str += "Choose a card ? [1-"+size+"]\n";
		return str + "$> ";
	}

	public static String pressEnter() {
		return separate(">> PRESS ENTER");
	}
	
	public static String separate(String s) {
		return SEPARATOR + s + SEPARATOR + "\n";
	}
	
	public static String separate(String s, String separator, int nbOccurence) {
		String sep = "";
		for (int i = 0; i < nbOccurence; i++)
			sep += separator;
		sep += "\n";
		return sep + s + sep;
	}

	public static String party(int i) {
		return separate(separate("\tPARTY " + i + "\n", "*", 23), "-", 23) + "\n";
	}
}
