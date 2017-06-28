package takensix.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import takensix.card.Card;
import takensix.context.PlayContext;
import takensix.player.Player;
import takensix.utils.Constants;
import takensix.utils.StringMaker;

/**
 * The Class StackManager. It handles the game's stacks.
 */
public class StackManager {

	/** The stacks. */
	private Stacks stacks;

	/** The number of stacks. */
	int numberOfStacks;

	/**
	 * The number of initialized stacks. A stack is initialized with one card.
	 */
	int numberOfInitializedStacks;

	/**
	 * Instantiates a new stack manager.
	 *
	 * @param numberOfStacks
	 *            the number of stacks
	 */
	public StackManager(int numberOfStacks) {
		this.numberOfStacks = numberOfStacks;
		this.init();
	}

	/**
	 * Instantiates a new stack manager with existing stacks.
	 *
	 * @param stacks
	 *            the stacks
	 */
	public StackManager(Stacks stacks) {
		this.stacks = stacks;
	}

	/**
	 * Determines the stack on which the played card needs to be put and compute
	 * the move's score.
	 *
	 * @param card
	 *            the played card
	 * @param playContext
	 *            the play context
	 * @return the move's score
	 */
	public int receiveCard(Card card, PlayContext playContext) {
		int destinationStackIndex;
		int penalty = 0;

		// First received cards are for initialization (one card per stack)
		// Others will be place on the right stack
		if (numberOfInitializedStacks < numberOfStacks) {
			destinationStackIndex = getStackIndexToInitialize();
			numberOfInitializedStacks++;
		} else {
			destinationStackIndex = getStackIndexForCard(card);
		}

		// Asks the player to clean a stack if the played card can't be stacked
		if (destinationStackIndex < 0) {
			destinationStackIndex = playContext.getPlayer().chooseStack(playContext);

			playContext.getGameContext().outputs
					.print(StringMaker.choosesStack(playContext.getPlayer(), destinationStackIndex));

			penalty += cleanStack(this.stacks.get(destinationStackIndex));
		}

		// Process move and compute score
		penalty += this.addCardToStack(card, destinationStackIndex);
		return penalty;
	}

	/**
	 * Simulate a move and return the PlayContext after move process. It is used
	 * by the Simulator.
	 *
	 * @param playContext
	 *            the play context
	 * @param card
	 *            the card played
	 * @return the play context after move
	 */
	public PlayContext simulate(PlayContext playContext, Card card) {
		int penalty = 0;
		int destinationStackIndex = getStackIndexForCard(card);

		if (destinationStackIndex < 0) {
			destinationStackIndex = playContext.getPlayer().chooseStack(playContext);
			penalty += cleanStack(this.stacks.get(destinationStackIndex));
		}

		penalty += this.addCardToStack(card, destinationStackIndex);

		Player player = playContext.getPlayer();
		player.addScore(penalty);
		player.getCards().remove(card);

		return playContext;
	}

	/**
	 * Adds the card to stack and return the move's score.
	 *
	 * @param card
	 *            the card played
	 * @param destinationStackIndex
	 *            the destination stack index
	 * @return the move's score
	 */
	private int addCardToStack(Card card, int destinationStackIndex) {
		if (destinationStackIndex < 0)
			return 0;

		List<Card> stack = this.stacks.get(destinationStackIndex);

		int returnedScore = 0;

		if (stack.size() >= Constants.MAX_CARD_PER_STACK)
			returnedScore = cleanStack(stack);

		stack.add(card);
		this.stacks.put(destinationStackIndex, stack);

		return returnedScore;
	}

	/**
	 * Cleans a stack and return total stack's score.
	 *
	 * @param stack
	 *            the stack to clean
	 * @return the stack's total score
	 */
	private int cleanStack(List<Card> stack) {
		int totalScore = 0;

		for (Card c : stack)
			totalScore += c.getScore();

		stack.clear();

		return totalScore;
	}

	/**
	 * Gets the stack index to initialize.
	 *
	 * @return the stack index to initialize
	 */
	private int getStackIndexToInitialize() {
		for (Entry<Integer, List<Card>> stack : stacks.entrySet()) {
			if (stack.getValue().isEmpty())
				return stack.getKey();
		}

		return -1;
	}

	/**
	 * Determines the stack which will receive the played card.
	 *
	 * @param card
	 *            the card played
	 * @return the stack index
	 */
	public int getStackIndexForCard(Card card) {
		int bestStackIndex = -1;
		int bestStackCardNumber = -100;

		int cardNumber = card.getNumber();

		for (Entry<Integer, List<Card>> entry : stacks.entrySet()) {
			List<Card> stack = entry.getValue();
			int maxStackNumber = stack.get(stack.size() - 1).getNumber();

			if (cardNumber < maxStackNumber)
				continue;

			if (maxStackNumber > bestStackCardNumber) {
				bestStackIndex = entry.getKey();
				bestStackCardNumber = maxStackNumber;
			}
		}

		return bestStackIndex;
	}

	/**
	 * Prepare the stacks.
	 */
	private void init() {
		this.numberOfInitializedStacks = 0;

		this.stacks = new Stacks();
		for (int i = 1; i <= numberOfStacks; i++) {
			this.stacks.put(i, new ArrayList<Card>());
		}
	}

	/**
	 * Gets the stacks.
	 *
	 * @return the stacks
	 */
	public Stacks getStacks() {
		return stacks;
	}

	/**
	 * Sets the stacks.
	 *
	 * @param stacks
	 *            the new stacks
	 */
	public void setStacks(Stacks stacks) {
		this.stacks = stacks;
	}
}
