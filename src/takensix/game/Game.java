package takensix.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import takensix.card.Card;
import takensix.card.CardGame;
import takensix.context.GameContext;
import takensix.player.Player;
import takensix.stack.StackManager;

/**
 * The Class Game which represents a TakenSix game. This object is manipulated
 * by the GameManager.
 */
public class Game {

	/** The players. */
	private List<Player> players;

	/** The cards game. */
	private CardGame cards;

	/** The stack manager which contains and handles the stacks. */
	private StackManager stackManager;

	/**
	 * The played card list corresponding to the card played by players at a
	 * certain time.
	 */
	private Map<Player, Card> playedCardList;

	/**
	 * Instantiates a new game.
	 *
	 * @param context
	 *            the context containing various parameters for the game.
	 */
	public Game(GameContext context) {
		this.players = new ArrayList<>();
		this.stackManager = new StackManager(context.numberOfStacks);
		this.cards = new CardGame(context.cardScoreMode, context.numberOfCards);
		this.playedCardList = new HashMap<>();
	}

	/**
	 * Gets the lower played card according to the card's number and remove it
	 * from played cards list.
	 *
	 * @return the lower played card
	 */
	public Entry<Player, Card> getLowerPlayedCard() {
		Entry<Player, Card> minimumCard = null;
		for (Entry<Player, Card> entry : this.playedCardList.entrySet()) {
			if (minimumCard == null || minimumCard.getValue().getNumber() > entry.getValue().getNumber())
				minimumCard = entry;
		}

		playedCardList.remove(minimumCard.getKey());
		return minimumCard;
	}

	/**
	 * Gets the cards.
	 *
	 * @return the cards
	 */
	public CardGame getCards() {
		return cards;
	}

	/**
	 * Sets the cards.
	 *
	 * @param cards
	 *            the new cards
	 */
	public void setCards(CardGame cards) {
		this.cards = cards;
	}

	/**
	 * Gets the played card list.
	 *
	 * @return the played card list
	 */
	public Map<Player, Card> getPlayedCardList() {
		return playedCardList;
	}

	/**
	 * Adds the player.
	 *
	 * @param p
	 *            the p
	 */
	public void addPlayer(Player p) {
		this.players.add(p);
	}

	/**
	 * Gets the players.
	 *
	 * @return the players
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * Sets the players.
	 *
	 * @param players
	 *            the new players
	 */
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	/**
	 * Gets the stack manager.
	 *
	 * @return the stack manager
	 */
	public StackManager getStackManager() {
		return stackManager;
	}

	/**
	 * Sets the stack manager.
	 *
	 * @param stackManager
	 *            the new stack manager
	 */
	public void setStackManager(StackManager stackManager) {
		this.stackManager = stackManager;
	}

	/**
	 * Adds the played card.
	 *
	 * @param player
	 *            the player
	 * @param card
	 *            the card
	 */
	public void addPlayedCard(Player player, Card card) {
		this.playedCardList.put(player, card);
	}

}
