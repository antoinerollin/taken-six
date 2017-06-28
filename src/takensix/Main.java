package takensix;

import java.util.ArrayList;
import java.util.List;

import takensix.context.BasicContext;
import takensix.game.GameManager;
import takensix.hiddencode.PlayerChooserAntoine;
import takensix.player.Player;
import takensix.player.human.PlayerChooserHumanConsole;
import takensix.player.ia.PlayerChooserStupid;

/**
 * The goal of the game is to get as few points as possible at the end of the
 * game. To do this, do not pick up points!
 * 
 * The cards are numbered from 1 to 104. The cards bring 1, 2, 3, 5 or 7 points
 * to the player.
 * 
 * 
 * Each player receives 10 cards at the start of the round. Then 5 cards are
 * placed on the table to form the beginning of 5 stacks.
 * 
 * Each player chooses a card from his game and places it face down on the
 * table. When all players are ready, the cards are turned face up and played in
 * ascending order of their value. The cards must be placed so as to complete
 * the stacks by satisfying 3 criteria:
 * 
 * The card must be played on a row whose the last card's value is less than
 * that of the card played; Between the stacks whose last card is lower than the
 * card played, choose the one where the gap with the card played is the lowest;
 * If the playing card is worth less than the last card of all stacks, the
 * player chooses a stack he picks up (usually the one with the least number of
 * points) and places his card as the first card of the new stack (there are
 * always exactly 5 stacks).
 * 
 * The score present on the cards that each player has won during the game are
 * added together. The result is scored on a score sheet and then a new round
 * begins.
 * 
 * The game stops when a player reaches 66 cumulative points on all previous
 * rounds. The winner is the one with the least points.
 * 
 * If you don't understand the rules, you can do so by playing against a random
 * bot. Just add a player with PlayerChooserHumanConsole and one with a
 * PlayerChooserStupid to the list of player and run the program.
 */
public class Main {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		// TODO: Put your player here
		List<Player> players = new ArrayList<>();
		players.add(new Player("Human", new PlayerChooserHumanConsole()));
		players.add(new Player("Stupid", new PlayerChooserStupid()));
		players.add(new Player("Me", new PlayerChooserAntoine()));
		
		new GameManager(new BasicContext(), players).launch();
	}

}
