package takensix.utils;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import takensix.card.Card;
import takensix.card.PlayedCardCollection;
import takensix.context.GameContext;
import takensix.player.Player;
import takensix.stack.Stacks;

public class StringMaker {
	public final static String SEPARATOR = "------------------------\n";
	public final static String CONSOLE_INPUT_SYMBOL = "$>";

	
	public static String players(List<Player> players) {
		String str = "[PLAYERS & SCORES]\n";
		for (Player p : players)
			str += p.toString() + "\n";
		return separate(str);
	}
	
	public static String scores(List<Player> players, GameContext gameContext) {
		String str = "[SCORES]\n\n";
		
		int partyEndScore = gameContext.partyEndScore;
		str	+= "VICTORY : when someone has reached " + partyEndScore + " points and you have the lowest amount of points and lower than " + partyEndScore + " points\n";
		str	+= "BEST : when someone has reached " + partyEndScore + " points and you have the lowest amount of points\n";
		str	+= "SURVIVE : when someone has reached " + partyEndScore + " points and you have less than " + partyEndScore + " points\n";
		str	+= "FATALITY : when someone has reached " + partyEndScore + " points and you don't have any point\n";
		str	+= "REFLECTION : average time before player submit a card\n\n";
		
		int numberOfParty = gameContext.numberOfParty;
		for (Player p : players)
			str += p.getName() 
			+ " [VICTORIES: " + p.getNumberOfVictory() + "/" + numberOfParty  
			+ " | BEST: " + p.getNumberOfBestScore() + "/" + numberOfParty
			+ " | SURVIVES: " + p.getNumberOfSurvive() + "/" + numberOfParty
			+ " | FATALITIES: " + p.getNumberOfFatality() + "/" + numberOfParty
			+ " | REFLECTION: " + p.getAveragePlayTime() + "ms"
			+ "]\n";
		
		return separate(str);
	}
	
	public static String statistics(Map<String, List<Player>> stats) {
		String str = "[STATISTICS]\n\n";
		
		final String joinSymbol = " & ";
		for (Entry<String, List<Player>> e : stats.entrySet()){
			final List<Player> value = e.getValue();
			str += getStatisticTitle(e.getKey(), value) + " : ";
			for (Player p : value)
				str += p.getName() + joinSymbol;
			str = StringMaker.removeLast(str, joinSymbol);
			str += "\n";
		}
		
		return separate(str);
	}
	
	public static String playedCards(Map<Player, Card> playedCards) {
		String str = "[PLAYED CARDS]\n";
		for (Entry<Player, Card> e : playedCards.entrySet())
			str += e.getKey().getName() + ": " + e.getValue().toString() + "\n";
		return separate(str);
	}
	
	public static String playsCard(Player player, Card card) {
		String str = player.getName() + " PLAYS " + card.toString() + '\n';
		return separate(str);
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
		String str = "[YOUR CARDS]\n";
		int size = playerCards.size();
		for (int i = 1; i <= size; i++)
			str += i + ": " + playerCards.get(i-1).toString() + "\n";
		str += "Choose a card ? [1-"+size+"]\n";
		return str + CONSOLE_INPUT_SYMBOL;
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
		return separate("\tPARTY " + i + "\n", "*", 23) + "\n";
	}

	public static String serving() {
		return separate("SERVING CARDS\n");
	}

	public static String removeLast(String s, String toRemove) {
		if (!s.contains(toRemove))
			return s;
		
		StringBuilder sb = new StringBuilder(s);
		int l = sb.lastIndexOf(toRemove);
		return s.substring(0, l);
	}

	public static String askStack(Stacks stacks, int suggestion) {
		String str = StringMaker.stacks(stacks);
		str = str.replace("{", "").replace("}", ":");
		str += "Which stack do you want to get ? [1-"+ stacks.size() + "] (stack n°"+suggestion+ " suggested)\n";
		return str + CONSOLE_INPUT_SYMBOL;
	}

	public static String playedCards(PlayedCardCollection playedCardHistory) {
		String str = "[PLAYED CARDS HISTORY]\n";
		for (Entry<String, List<Card>> entry : playedCardHistory.entrySet()){
			str += entry.getKey() + " |";
			for (Card c : entry.getValue())
				str += " " + c.toString();
			str += "\n";
		}
		return separate(str);
	}
	
	private static String getStatisticTitle(String str, List<Player> bestVictoryPlayers) {
		return str + (bestVictoryPlayers.size() > 1 ? 'S' : "");
	}
}
