package takensix.context;

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

	/** The number of players which can influences decisions. */
	private int numberOfPlayers;

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
	public PlayContext(Player player, Stacks stacks, int numberOfPlayers) {
		this.player = new Player(player);
		this.stacks = new Stacks(stacks);
		this.numberOfPlayers = numberOfPlayers;
	}

	/**
	 * Instantiates a new play context.
	 *
	 * @param playContext
	 *            the play context
	 */
	public PlayContext(PlayContext playContext) {
		this.player = new Player(playContext.getPlayer());
		this.stacks = (Stacks) playContext.getStacks().clone();
		this.numberOfPlayers = playContext.getNumberOfPlayers();
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

	/**
	 * Gets the number of players.
	 *
	 * @return the number of players
	 */
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	/**
	 * Sets the number of players.
	 *
	 * @param numberOfPlayers
	 *            the new number of players
	 */
	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

}
