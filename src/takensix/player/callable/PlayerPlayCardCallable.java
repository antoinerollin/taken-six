package takensix.player.callable;

import java.util.concurrent.Callable;

import takensix.card.Card;
import takensix.context.PlayContext;
import takensix.player.PlayerChooser;

public class PlayerPlayCardCallable implements Callable<Card> {

	private PlayerChooser chooser;
	private PlayContext context;

	public PlayerPlayCardCallable(PlayerChooser pc, PlayContext context) {
		this.chooser = pc;
		this.context = context;
	}

	@Override
	public Card call() throws Exception {
		return chooser.chooseCard(context);
	}

}
