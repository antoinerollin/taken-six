package takensix.context;

import takensix.utils.CardScoreMode;

/**
 * The Class GameContext. It contains various parameters for game instantiation.
 */
public class GameContext {

	/** The number of players given cards. */
	public int numberOfGivenCards;

	/** The number of stacks. */
	public int numberOfStacks;

	/** The number of cards for the card game. */
	public int numberOfCards;

	/** The score limit which ends a party. */
	public int partyEndScore;

	/** The card score mode which determines how to associate score to cards. */
	public CardScoreMode cardScoreMode;

	/**
	 * Instantiates a new game context.
	 *
	 * @param numberOfGivenCards
	 *            the number of given cards
	 * @param numberOfStacks
	 *            the number of stacks
	 * @param numberOfCard
	 *            the number of card
	 * @param partyEndScore
	 *            the party end score
	 * @param cardScoreMode
	 *            the card score mode
	 */
	public GameContext(int numberOfGivenCards, int numberOfStacks, int numberOfCard, int partyEndScore,
			CardScoreMode cardScoreMode) {
		this.numberOfGivenCards = numberOfGivenCards;
		this.numberOfStacks = numberOfStacks;
		this.numberOfCards = numberOfCard;
		this.partyEndScore = partyEndScore;
		this.cardScoreMode = cardScoreMode;
	}
}
