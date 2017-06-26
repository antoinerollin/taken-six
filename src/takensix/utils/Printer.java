package takensix.utils;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import takensix.card.Card;
import takensix.player.Player;
import takensix.stack.Stacks;

public class Printer {
	
	public static void pln(String s) {
		System.out.println(s);
	}
	
	public static void p(String s) {
		System.out.print(s);
	}
	
	public static void print(List<Player> players) {
		sep();
		pln("Players:");
		for (Player p : players)
			pln(p.toString());
		sep();
	}
	
	public static void print(Map<Player, Card> playedCards) {
		pln("Played cards:");
		for (Entry<Player, Card> e : playedCards.entrySet())
			pln(e.getKey().getName() + ": " + e.getValue().toString());
		sep();
	}
	
	public static void print(Stacks sg) {
		pln("Stacks:");
		pln(sg.toString());
	}

	public static void sep() {
		pln("------------------------");
	}

	public static void selectCard(List<Card> playerCards) {
		int size = playerCards.size();
		for (int i = 1; i <= size; i++)
			pln(i + ": " + playerCards.get(i-1).toString());
		pln("Choose a card ? [1-"+size+"]");
		p("$> ");
	}

	public static void pressEnter() {
		pln(">> PRESS ENTER");
		sep();
	}
}
