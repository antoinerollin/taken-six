package takensix.context;

import takensix.card.PlayedCardCollection;
import takensix.player.Player;
import takensix.stack.Stacks;

/**
 * The Class PlayContext. This context describes the state of the game at a
 * certain time. It is the given context given to players to help them
 * determining which move to play.
 */
public class PlayContext {

	/** The player who needs to play. */
	private Player player;

	/** The stacks statement for a certain turn. */
	private Stacks stacks;

	/** The game context. */
	private GameContext gameContext;
	
	/** The played card history. */
	private PlayedCardCollection playedCardHistory;

	/**
	 * Instantiates a new play context.
	 *
	 * @param player
	 *            the player who needs to play
	 * @param stacks
	 *            the stacks statement for a certain turn
	 * @param numberOfPlayers
	 *            the number of players
	 */
	public PlayContext(GameContext gameContext, PlayedCardCollection playedCardCollection, Player player, Stacks stacks) {
		this.gameContext = new GameContext(gameContext);
		this.playedCardHistory = new PlayedCardCollection(playedCardCollection);
		this.player = new Player(player);
		this.stacks = new Stacks(stacks);		
	}

	/**
	 * Instantiates a new play context.
	 *
	 * @param playContext
	 *            the play context
	 */
	public PlayContext(PlayContext playContext) {
		this.gameContext = new GameContext(playContext.getGameContext());
		this.playedCardHistory = new PlayedCardCollection(playContext.getPlayedCardHistory());
		this.player = new Player(playContext.getPlayer());
		this.stacks = new Stacks(playContext.getStacks());
	}

	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Sets the player.
	 *
	 * @param player
	 *            the new player
	 */
	public void setPlayer(Player player) {
		this.player = player;
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

	public GameContext getGameContext() {
		return gameContext;
	}

	public void setGameContext(GameContext gameContext) {
		this.gameContext = gameContext;
	}

	public PlayedCardCollection getPlayedCardHistory() {
		return playedCardHistory;
	}

	public void setPlayedCardHistory(PlayedCardCollection playedCardHistory) {
		this.playedCardHistory = playedCardHistory;
	}
}
