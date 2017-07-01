
package takensix.player.ia;

import java.util.List;
import java.util.Map.Entry;

import takensix.card.Card;
import takensix.context.PlayContext;
import takensix.player.PlayerChooser;
import takensix.utils.Simulator;
import takensix.utils.StringMaker;

public class PlayerChooserSimple implements PlayerChooser {

	@Override
	public Card chooseCard(PlayContext playContext) {
		Card bestCard = null;
		double bestScore = Double.MAX_VALUE;
		
		this.println(playContext, StringMaker.stacks(playContext.getStacks()));
		this.println(playContext, StringMaker.playedCards(playContext.getPlayedCardHistory()));

		this.println(playContext, "Let me evaluate my situation");
		
		// Get card with the best evaluation
		for (Card c : playContext.getPlayer().getCards()) {
			double score = eval(new PlayContext(playContext), c);
						
			if (score < bestScore) {
				bestCard = c;
				bestScore = score;
			}
		}

		this.print(playContext, "So I will play " + bestCard);
		
		return bestCard;
	}

	@Override
	public int chooseStack(PlayContext playContext) {
		int bestKey = -1;
		int bestValue = Integer.MAX_VALUE;
		
		// Get the stack with the lowest amount of point
		for (Entry<Integer, List<Card>> e : playContext.getStacks().entrySet()) {
			int score = 0;
			for (Card c : e.getValue())
				score += c.getScore();

			if (bestKey < 0 || score < bestValue) {
				bestValue = score;
				bestKey = e.getKey();
			}
		}
		
		return bestKey;
	}

	private double eval(PlayContext playContext, Card c) {
		
		// Evaluation score for the move. The lower this score is, the better the move is.
		double evalScore = 0;
		
		double scoreDiff;
		double distance;
		
		// The more I play closer to the destination stack's card, the less I can be bypassed by another player
		distance = (double) Simulator.computeDistance(playContext, c);
		
		// Compute the difference between my score before and after having played the card
		scoreDiff = -playContext.getPlayer().getScore();
		PlayContext playContextAfter = Simulator.simulate(playContext, c);
		scoreDiff += playContextAfter.getPlayer().getScore();

		// If I get a stack 
		if (scoreDiff > 0)
			evalScore += 100;
		
		evalScore += distance;
		
		this.print(playContext, "If I play " + c + ", I evaluate a score of " + evalScore);
	
		return evalScore;
	}
}
