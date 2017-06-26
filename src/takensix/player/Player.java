package takensix.player;

import java.util.ArrayList;
import java.util.List;

import takensix.card.Card;
import takensix.context.PlayContext;

/**
 * The Class Player. A Player has a name, a list of cards to play, a score and
 * determines each move thanks to a PlayerChooser. You need to implement a new
 * PlayerChooser if you want to create your own IA.
 */
public class Player {

	/** The name. */
	private String name;

	/** The cards to play. */
	private List<Card> cards;

	/** The score. */
	private int score;

	/** The brain. */
	private PlayerChooser chooser;

	/**
	 * Instantiates a new player.
	 *
	 * @param name
	 *            the name
	 * @param chooser
	 *            the chooser
	 */
	public Player(String name, PlayerChooser chooser) {
		this.name = name;
		this.cards = new ArrayList<>();
		this.chooser = chooser;
		this.score = 0;
	}

	/**
	 * Instantiates a new player.
	 *
	 * @param player
	 *            the player
	 */
	public Player(Player player) {
		this.name = player.getName();
		this.cards = new ArrayList<>(player.getCards());
		this.chooser = player.getChooser();
		this.score = player.getScore();
	}

	/**
	 * Asks the chooser to select a card to play according to various
	 * information contained in the PlayContext. The selected card is then
	 * removed from the cards to play.
	 *
	 * @param playContext
	 *            the play context
	 * @return the card
	 */
	public final Card playCard(PlayContext playContext) {
		Card playedCard = chooser.chooseCard(playContext);
		this.cards.remove(playedCard);

		return playedCard;
	}

	/**
	 * Asks the chooser to select a stack when the played card can't be stacked.
	 *
	 * @param playContext
	 *            the play context
	 * @return the stack index
	 */
	public int chooseStack(PlayContext playContext) {
		return this.chooser.chooseStack(playContext);
	}

	/**
	 * Adds the score to the current one.
	 *
	 * @param score
	 *            the score
	 */
	public final void addScore(int score) {
		this.score += score;
	}

	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public final int getScore() {
		return this.score;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public final String getName() {
		return this.name.toUpperCase();
	}

	/**
	 * Receive card.
	 *
	 * @param card
	 *            the card
	 */
	public final void receiveCard(Card card) {
		this.cards.add(card);
		this.sortCards();
	}

	/**
	 * Gets the chooser.
	 *
	 * @return the chooser
	 */
	public final PlayerChooser getChooser() {
		return chooser;
	}

	/**
	 * Gets the cards.
	 *
	 * @return the cards
	 */
	public final List<Card> getCards() {
		return cards;
	}

	/**
	 * Sorts the cards by ascending order.
	 */
	private final void sortCards() {
		List<Card> sortedList = new ArrayList<>();

		while (!this.cards.isEmpty())
			sortedList.add(getLowerCard());

		this.cards = sortedList;
	}

	/**
	 * Gets the lower card according to card's number. This methods is used for
	 * sorting.
	 * 
	 * @return the lower card
	 */
	private final Card getLowerCard() {
		Card minimumCard = null;
		for (Card c : this.cards) {
			if (minimumCard == null || minimumCard.getNumber() > c.getNumber())
				minimumCard = c;
		}

		this.cards.remove(minimumCard);
		return minimumCard;
	}

	@Override
	public String toString() {
		String str = name.toUpperCase() + " [" + score + "] ";
		// for (Card c : cards)
		// str += c.toString() + " ";
		return str;
	}
}