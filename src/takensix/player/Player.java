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
	
	/** The number of victory (finish first player and score lower than GameConext.partyEndScore). */
	private int numberOfVictory;
	
	/** The number of best score (finish first player). */
	private int numberOfBestScore;
	
	/** The number of survive (finish with acceptable score). */
	private int numberOfSurvive;

	private int numberOfFatality;
	
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
		this.numberOfVictory = 0;
		this.numberOfSurvive = 0;
		this.numberOfBestScore = 0;
		this.numberOfFatality = 0;
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
		this.numberOfVictory = player.getNumberOfVictory();
		this.numberOfSurvive = player.getNumberOfSurvive();
		this.numberOfBestScore = player.getNumberOfBestScore();
		this.numberOfFatality = player.getNumberOfFatality();
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
	
	/**
	 * Reset score.
	 */
	public final void resetScore() {
		this.score = 0;
	}
	

	/**
	 * Gets the number of victory.
	 *
	 * @return the number of victory
	 */
	public int getNumberOfVictory() {
		return numberOfVictory;
	}

	/**
	 * Adds a victory.
	 */
	public void addVictory() {
		this.numberOfVictory++;
	}

	/**
	 * Gets the number of survive.
	 *
	 * @return the number of survive
	 */
	public int getNumberOfSurvive() {
		return numberOfSurvive;
	}

	/**
	 * Adds a survive.
	 */
	public void addSurvive() {
		this.numberOfSurvive++;
	}

	/**
	 * Gets the number of best score.
	 *
	 * @return the number of best score
	 */
	public int getNumberOfBestScore() {
		return numberOfBestScore;
	}

	/**
	 * Adds a best score count.
	 */
	public void addBestScore() {
		this.numberOfBestScore++;
	}

	/**
	 * Gets the number of fatality.
	 *
	 * @return the number of fatality
	 */
	public int getNumberOfFatality() {
		return numberOfFatality;
	}

	/**
	 * Adds the fatality.
	 */
	public void addFatality() {
		this.numberOfFatality++;
	}
	@Override
	public String toString() {
		String str = name.toUpperCase() + " [" + score + "] ";
		// for (Card c : cards)
		// str += c.toString() + " ";
		return str;
	}

}
