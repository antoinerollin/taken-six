package takensix.utils;

import java.util.List;

import takensix.card.Card;
import takensix.context.PlayContext;
import takensix.stack.StackManager;
import takensix.stack.Stacks;

/**
 * The Class Simulator. Enables to simulates moves. Useful for IA.
 */
public abstract class Simulator {

	/**
	 * Simulation of a move for a specified context.
	 *
	 * @param playContext
	 *            the play context
	 * @param card
	 *            the card played
	 * @return the new play context
	 */
	public static PlayContext simulate(PlayContext playContext, Card card) {
		return new StackManager(new Stacks(playContext.getStacks())).simulate(new PlayContext(playContext), card);
	}

	
	/**
	 * Computes distance between the played card and the last stack played card.
	 *
	 * @param playContext the play context
	 * @param card the card
	 * @return the distance
	 */
	public static int computeDistance(PlayContext playContext, Card card) {
		int stackIndex = new StackManager(new Stacks(playContext.getStacks())).getStackIndexForCard(card);

		if (stackIndex < 0)
			return -1;

		List<Card> stack = playContext.getStacks().get(stackIndex);
		Card lastCard = stack.get(stack.size() - 1);
		return card.getNumber() - lastCard.getNumber();
	}

}
