package takensix.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import takensix.card.Card;

/**
 * The Class Stacks. It represents the stacks used to play the game. Each stack
 * is a set of cards played by players. This objects is handled by a
 * StackManager.
 */
public class Stacks extends HashMap<Integer, List<Card>> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Clones stacks.
	 *
	 * @param stacks
	 *            the stacks to clone
	 */
	public Stacks(Stacks stacks) {
		for (Entry<Integer, List<Card>> e : stacks.entrySet()) {
			List<Card> cards = new ArrayList<>();
			for (Card c : e.getValue())
				cards.add(new Card(c.getNumber(), c.getScore()));

			this.put(e.getKey(), cards);
		}
	}

	/**
	 * Instantiates new stacks.
	 */
	public Stacks() {
		super();
	}

	@Override
	public String toString() {
		String str = "";
		for (java.util.Map.Entry<Integer, List<Card>> e : this.entrySet()) {
			str += "{" + e.getKey().toString() + "} ";
			List<Card> value = e.getValue();
			str += value.get(value.size() - 1).toString() + " (" + value.size() + " card(s) - total : "
					+ countScore(value) + "pt(s))";
			str += "\n";
		}
		return str;
	}

	/**
	 * Count score. TODO: move this in a ScoreCounter in utils package.
	 *
	 * @param cards
	 *            the cards
	 * @return the int
	 */
	private int countScore(List<Card> cards) {
		int res = 0;
		for (Card c : cards)
			res += c.getScore();
		return res;
	}

}
