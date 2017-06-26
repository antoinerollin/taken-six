package takensix.context;

import takensix.utils.CardScoreMode;

/**
 * The Class BasicContext.
 * The real TakenSix context :
 * * 10 cards given to players
 * * 5 stacks to play with
 * * 104 unique cards
 */
public class BasicContext extends GameContext {
	
	/**
	 * Instantiates a new basic context.
	 */
	public BasicContext() {
		super(10, 5, 104, 100, CardScoreMode.RANDOM_MODE);
	}
}
