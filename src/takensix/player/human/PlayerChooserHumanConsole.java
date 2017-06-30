
package takensix.player.human;

import java.util.List;
import java.util.Map.Entry;

import takensix.card.Card;
import takensix.context.PlayContext;
import takensix.player.PlayerChooser;
import takensix.stack.Stacks;
import takensix.utils.StringMaker;
import takensix.utils.Reader;

public class PlayerChooserHumanConsole implements PlayerChooser {

	@Override
	public Card chooseCard(PlayContext playContext) {

		this.print(playContext, StringMaker.stacks(playContext.getStacks()));

		int selected = -1;
		List<Card> cards = playContext.getPlayer().getCards();
		while (selected < 1 || selected > cards.size()) {
			this.print(playContext, StringMaker.askCard(cards));
			selected = Reader.nextInt();
		}
		
		return cards.get(selected - 1);
	}

	@Override
	public int chooseStack(PlayContext playContext) {
		
		Stacks stacks = playContext.getStacks();
				
		int suggestion = getStackSuggestion(stacks);
		
		int selected = -1;
		int numberOfStacks = stacks.size();
		while (selected < 1 || selected > numberOfStacks) {
			this.print(playContext, StringMaker.askStack(stacks, suggestion));
			selected = Reader.nextInt();
		}
		
		return selected;
	}

	private int getStackSuggestion(Stacks stacks) {
		int bestKey = -1;
		int bestValue = 1000;

		for (Entry<Integer, List<Card>> e : stacks.entrySet()) {
			int score = 0;
			for (Card c : e.getValue())
				score += c.getScore();

			if (score < bestValue) {
				bestValue = score;
				bestKey = e.getKey();
			}
		}
		return bestKey;
	}
}
