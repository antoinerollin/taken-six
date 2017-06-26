package takensix.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import takensix.utils.Randomizer;
import takensix.utils.CardScoreMode;
import takensix.utils.Constants;

/**
 * The Class CardGame which contains all the cards of the game. Each Card is
 * unique. When a Card distributed to a Player or a Stack, it is remove from
 * this set.
 */
public class CardGame {

	/** All the cards. */
	private List<Card> cards;

	/**
	 * Instantiates a new card game and shuffles it.
	 *
	 * @param cardScoreMode
	 *            the card score mode which determine how to associate a score
	 *            to a card
	 * @param numberOfCards
	 *            the number of cards to generate
	 */
	public CardGame(CardScoreMode cardScoreMode, int numberOfCards) {
		this.cards = new ArrayList<>();
		this.populate(cardScoreMode, numberOfCards);
		this.shuffle();
	}

	/**
	 * Shuffles the set of cards.
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}

	/**
	 * Generates the cards according to the card score mode.
	 *
	 * @param cardScoreMode
	 *            the card score mode which determine how to associate a score
	 * @param numberOfCards
	 *            the number of cards
	 */
	private void populate(CardScoreMode cardScoreMode, int numberOfCards) {
		for (int i = 1; i <= numberOfCards; i++) {
			int cardScore = getScore(i, cardScoreMode);
			this.cards.add(new Card(i, cardScore));
		}
	}

	/**
	 * Gets one card from the set and remove it.
	 *
	 * @return the one card
	 */
	public Card getOneCard() {
		return this.cards.remove(0);
	}

	/**
	 * Gets a score to assign to a card.
	 *
	 * @param cardScoreMode
	 *            the card score mode which determine how to associate a score
	 * @return the score
	 */
	private int getScore(int cardNumber, CardScoreMode cardScoreMode) {
		if (cardScoreMode == CardScoreMode.RANDOM_MODE)
			return Randomizer.nextInt(Constants.MIN_CARD_SCORE, Constants.MAX_CARD_SCORE);
		else if (cardScoreMode == CardScoreMode.REAL_SCORE_MODE)
			return 0;

		return 0;
	}
}
