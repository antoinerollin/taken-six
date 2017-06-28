package takensix.player;

import takensix.card.Card;
import takensix.context.PlayContext;

/**
 * The Interface PlayerChooser. This is the brain of the Players. You need to
 * implement this interface if you want to create your own IA.
 */
public interface PlayerChooser {

	/**
	 * Chooses a card to play. This method is called each turn of the party.
	 * The cards to play are contained by the Player object in the PlayContext.
	 * 
	 * @param playContext
	 *            the play context
	 * @return the card selected
	 */
	Card chooseCard(PlayContext playContext);

	/**
	 * Chooses a stack when the played card is lower than the every last stacked card. This method is called each time the player plays a card
	 * which can't be stacked on a stack.
	 * 
	 * Example : 
	 * The stacks are filled as follow :
	 * {1} [N°12 (6p)] (1 card(s) - total : 6pt(s))
	 * {2} [N°18 (7p)] (4 card(s) - total : 25pt(s))
	 * {3} [N°98 (4p)] (5 card(s) - total : 18pt(s))
	 * {4} [N°73 (3p)] (1 card(s) - total : 3pt(s))
	 * {5} [N°44 (6p)] (5 card(s) - total : 14pt(s))
	 * 
	 * I played [N°9 (2p)]. 
	 * This card can't be stacked on any stack because 9 is lower than 12, 18, 98, 73 and 44.
	 * I need to clean one of the stacks in order to play my card. 
	 * For example, I will select the stack {4}, get 3 points 
	 * (because the sum of the score of the cards contained b the stack is 3)
	 * and the stacks will be as follow :
	 * {1} [N°12 (6p)] (1 card(s) - total : 6pt(s))
	 * {2} [N°18 (7p)] (4 card(s) - total : 25pt(s))
	 * {3} [N°98 (4p)] (5 card(s) - total : 18pt(s))
	 * {4} [N°9 (2p)] (1 card(s) - total : 2pt(s))
	 * {5} [N°44 (6p)] (5 card(s) - total : 14pt(s))
	 *
	 * @param playContext
	 *            the play context
	 * @return the stack index to get (begins at 1)
	 */
	int chooseStack(PlayContext playContext);
	
	/**
	 * Write a string into outputs.
	 * @param playContext
	 * @param s
	 */
	default void print(PlayContext playContext, String s){
		String template = "[" + playContext.getPlayer().getName() + "] ";
		s = s.trim().replaceAll("\n", "\n"+template);
		playContext.getGameContext().outputs.printDebug(template + s);
	}
	
	/**
	 * Write a line into outputs.
	 * @param playContext
	 * @param s
	 */
	default void println(PlayContext playContext, String s){
		String template = "[" + playContext.getPlayer().getName() + "] ";
		s = s.trim().replaceAll("\n", "\n"+template);
		playContext.getGameContext().outputs.printlnDebug(template + s);
	}
}
