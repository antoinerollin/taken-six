
package takensix.player.human;

import java.util.List;
import java.util.Map.Entry;

import takensix.card.Card;
import takensix.context.PlayContext;
import takensix.player.PlayerChooser;
import takensix.utils.Printer;
import takensix.utils.Reader;

public class PlayerChooserHumanConsole implements PlayerChooser {

	@Override
	public Card chooseCard(PlayContext playContext) {
		Printer.sep();
		Printer.print(playContext.getStacks());

		int selected = -1;
		List<Card> cards = playContext.getPlayer().getCards();
		while (selected < 1 || selected > cards.size()) {
			Printer.selectCard(cards);
			selected = Reader.nextInt();
		}

		Printer.sep();
		return cards.get(selected - 1);
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
