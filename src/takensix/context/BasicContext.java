package takensix.context;

import takensix.card.CardScoreMode;
import takensix.output.OutputMode;

/**
 * The Class BasicContext.
 * The real TakenSix context :
 * * 10 cards given to players
 * * 5 stacks to play with
 * * 104 unique cards
 * * 66 is the end of party score
 */
public class BasicContext extends GameContext {
	
	/**
	 * Instantiates a new basic context.
	 */
	public BasicContext() {
		super(10, 5, 104, 66, 1, CardScoreMode.RANDOM_MODE, OutputMode.CONSOLE_AND_FILE);
	}
}
