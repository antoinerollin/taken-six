package takensix.player.ia;

import java.util.List;
import java.util.Map.Entry;

import takensix.card.Card;
import takensix.context.PlayContext;
import takensix.player.PlayerChooser;
import takensix.utils.Randomizer;

public class PlayerChooserRandom implements PlayerChooser {

	@Override
	public Card chooseCard(PlayContext playContext) {
		List<Card> cards = playContext.getPlayer().getCards();
		return cards.get(Randomizer.nextInt(cards.size()));
	}

	@Override
	public int chooseStack(PlayContext playContext) {
		int bestKey = -1;
		int bestValue = 1000;

		for (Entry<Integer, List<Card>> e : playContext.getStacks().entrySet()) {
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
