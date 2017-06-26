package takensix.utils;

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

}
