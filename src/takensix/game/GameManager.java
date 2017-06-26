package takensix.game;

import java.util.List;
import java.util.Map.Entry;

import takensix.card.Card;
import takensix.context.GameContext;
import takensix.context.PlayContext;
import takensix.player.Player;
import takensix.utils.Printer;

/**
 * The Class GameManager. This class handles a Game and ensure its process
 * rules.
 * 
 * While no player has reached the score limit :
 * * generates the card game
 * * initializes the stacks with one card
 * * cards are given to each player
 * * While players have at least one card :
 * * * asks each player to choose one card they have
 * * * serves stacks with played cards by ascending order (card n°1 is process before card n°2)
 * * * updates scores according to each move
 */
public class GameManager {

	/** The game. */
	Game game;

	/** The game context containing game parameters. */
	GameContext context;

	/** The players. */
	List<Player> players;

	/**
	 * Instantiates a new game manager and launches games.
	 *
	 * @param context
	 *            the context
	 * @param players
	 *            the players
	 */
	public GameManager(GameContext context, List<Player> players) {
		this.context = context;
		this.players = players;

		while (!isGameFinished())
			this.launch();
	}

	/**
	 * Launch one party.
	 */
	private void launch() {
		this.game = new Game(context);
		this.addPlayers(players);

		this.serveCardsToStacks();
		this.serveCardsToPlayers();

		for (int i = 0; i < context.numberOfGivenCards; i++) {
			this.playCards();
			
			Printer.print(game.getPlayedCardList());
			
			this.processPlayedCards();
		}

		Printer.pln("\nResults:");
		Printer.print(game.getPlayers());
	}

	/**
	 * Process played cards.
	 */
	private void processPlayedCards() {
		// For each player
		for (int i = 0; i < game.getPlayers().size(); i++) {
			Entry<Player, Card> playedCard = game.getLowerPlayedCard();
			Player player = playedCard.getKey();

			Printer.pln(player.getName() + " PLAYS " + playedCard.getValue().toString() + '\n');

			// Process card on stacks and compute move's score
			int score = game.getStackManager().receiveCard(playedCard.getValue(),
					new PlayContext(player, game.getStackManager().getStacks(), game.getPlayers().size()));

			Printer.print(game.getStackManager().getStacks());
			
			// Update player's score
			player.addScore(score);

			if (score > 0)
				Printer.pln(player.getName() + " GETS " + score + " POINTS");

			Printer.print(game.getPlayers());

			// Reader.enter();
		}
	}

	/**
	 * Initializes the stacks with on card.
	 */
	private void serveCardsToStacks() {
		for (int i = 0; i < context.numberOfStacks; i++) {
			Card card = game.getCards().getOneCard();
			this.game.getStackManager().receiveCard(card, null);
		}
	}

	/**
	 * Give cards to the players.
	 */
	private void serveCardsToPlayers() {
		for (Player p : game.getPlayers()) {
			for (int i = 0; i < context.numberOfGivenCards; i++)
				p.receiveCard(game.getCards().getOneCard());
		}
	}

	/**
	 * Asks a player to play a card according to game information through a PlayContext.
	 * The played card is added to the played cards list.
	 */
	private void playCards() {
		for (Player currentPlayer : game.getPlayers()) {
			Card playedCard = currentPlayer.playCard(
					new PlayContext(currentPlayer, game.getStackManager().getStacks(), game.getPlayers().size()));
			game.addPlayedCard(currentPlayer, playedCard);
		}
	}
	
	/**
	 * Checks if is game finished.
	 *
	 * @return true, if is game finished
	 */
	private boolean isGameFinished() {
		if (this.game == null || this.players == null)
			return false;

		for (Player p : this.game.getPlayers())
			if (p.getScore() >= context.partyEndScore)
				return true;
		return false;
	}

	/**
	 * Adds the players to the game.
	 *
	 * @param players
	 *            the players
	 */
	public void addPlayers(List<Player> players) {
		for (Player p : players)
			this.game.addPlayer(p);
	}
}
