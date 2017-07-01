/*
 * 
 */
package takensix.game;

import java.util.List;
import java.util.Map.Entry;

import takensix.card.Card;
import takensix.context.GameContext;
import takensix.context.PlayContext;
import takensix.player.Player;
import takensix.stack.Stacks;
import takensix.utils.Counter;
import takensix.utils.StringMaker;

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
		this.context.numberOfPlayers = players.size();
		this.players = players;
	}
	
	/**
	 * Launch a set of parties.
	 */
	public void launch() {
		for (int i = 1; i <= context.numberOfParty; i++)
		{
			this.print(StringMaker.party(i));
			
			while (!isPartyFinished()) {
				this.launchOneGame();
			}
			
			this.updatePlayersCounts();
			this.resetPlayersScore();
			
			this.print(StringMaker.scores(players, context));
			this.print(StringMaker.statistics(Counter.getScoreStatistics(players)));
		}
		
		context.outputs.close();
	}

	/**
	 * Launch one party.
	 */
	private void launchOneGame() {
		this.game = new Game(context);
		this.addPlayers(players);

		this.serveCardsToStacks();
		this.serveCardsToPlayers();

		for (int i = 0; i < context.numberOfGivenCards; i++) {
			this.playCards();
			
			this.print(StringMaker.playedCards(game.getPlayedCardList()));
			
			this.processPlayedCards();
		}
	}

	/**
	 * Process played cards.
	 */
	private void processPlayedCards() {
		// For each player
		for (int i = 0; i < game.getPlayers().size(); i++) {
			Entry<Player, Card> playedCard = game.getLowerPlayedCard();
			Player player = playedCard.getKey();
			Card card = playedCard.getValue();
			Stacks stacks = game.getStackManager().getStacks();

			this.print(StringMaker.playsCard(player, card));

			// Process card on stacks and compute move's score
			int score = game.getStackManager().receiveCard(card,
					new PlayContext(context, game.getPlayedCardsHistory(), player, stacks));

			this.print(StringMaker.stacks(stacks));
			
			// Update player's score
			if (score > 0) {
				player.addScore(score);
				this.print(StringMaker.getsPoints(player, score));
			}

			this.print(StringMaker.players(players));

			// Reader.enter();
		}
	}

	/**
	 * Initializes the stacks with on card.
	 */
	private void serveCardsToStacks() {
		this.print(StringMaker.serving());
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
					new PlayContext(context, game.getPlayedCardsHistory(), currentPlayer, game.getStackManager().getStacks()));
			game.addPlayedCard(currentPlayer, playedCard);
		}
	}
	
	/**
	 * Checks if is game finished.
	 *
	 * @return true, if is game finished
	 */
	private boolean isPartyFinished() {
		if (this.game == null || this.players == null)
			return false;

		for (Player p : this.game.getPlayers())
			if (p.getScore() >= context.partyEndScore)
				return true;
		return false;
	}	

	/**
	 * Resets players score.
	 */
	private void resetPlayersScore() {
		for (Player p : players)
			p.resetScore();
	}

	/**
	 * Update players counts (victory & survive).
	 */
	private void updatePlayersCounts() {
		int bestScore = Integer.MAX_VALUE;
		
		// Count survive and get best score
		for (Player p : players) {
			int score = p.getScore();
			if (score < context.partyEndScore)
				p.addSurvive();
			
			if (score < bestScore)
				bestScore = score;
		}
		
		boolean isBestScoreAVictory = bestScore < context.partyEndScore;
		boolean isBestScoreAFatality = bestScore == 0;
		
		// Update best score and victory on second time to avoid draw complexity 
		for (Player p : players) {
			if (p.getScore() == bestScore) {
				p.addBestScore();
				if (isBestScoreAVictory)
					p.addVictory();
				if (isBestScoreAFatality)
					p.addFatality();
			}
		}
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
	
	private void print(String s) {
		this.context.outputs.print(s);
	}
}
