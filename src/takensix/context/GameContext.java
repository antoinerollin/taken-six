package takensix.context;

import takensix.card.CardScoreMode;
import takensix.output.OutputCollection;
import takensix.output.OutputConsole;
import takensix.output.OutputFile;
import takensix.output.OutputMode;

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

	/** The number of party. */
	public int numberOfParty;
	
	/** The number of players. */
	public int numberOfPlayers;
	
	/** The card score mode which determines how to associate score to cards. */
	public CardScoreMode cardScoreMode;

	/** The outputs. */
	public OutputCollection outputs;
	
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
	 * @param outputs 
	 */
	public GameContext(int numberOfGivenCards, int numberOfStacks, int numberOfCard, int partyEndScore, int numberOfParty,
			CardScoreMode cardScoreMode, OutputMode outputMode) {
		this.numberOfGivenCards = numberOfGivenCards;
		this.numberOfStacks = numberOfStacks;
		this.numberOfCards = numberOfCard;
		this.partyEndScore = partyEndScore;
		this.numberOfParty = numberOfParty;
		this.cardScoreMode = cardScoreMode;
		
		this.initOutputs(outputMode);
	}
	
	public GameContext(GameContext gameContext) {
		this.numberOfGivenCards = gameContext.numberOfGivenCards;
		this.numberOfStacks = gameContext.numberOfStacks;
		this.numberOfCards = gameContext.numberOfCards;
		this.partyEndScore = gameContext.partyEndScore;
		this.numberOfParty = gameContext.numberOfParty;
		this.cardScoreMode = gameContext.cardScoreMode;
		this.numberOfPlayers = gameContext.numberOfPlayers;
		this.outputs = gameContext.outputs;
	}

	private void initOutputs(OutputMode outputMode){
		outputs = new OutputCollection();
		if (outputMode == OutputMode.CONSOLE || outputMode == OutputMode.CONSOLE_AND_FILE)
			outputs.add(new OutputConsole());
		if (outputMode == OutputMode.FILE || outputMode == OutputMode.CONSOLE_AND_FILE)
			outputs.add(new OutputFile());
	}
}
