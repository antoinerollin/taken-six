package takensix.player.callable;

import java.util.concurrent.Callable;

import takensix.context.PlayContext;
import takensix.player.PlayerChooser;

public class PlayerSelectStackCallable implements Callable<Integer> {

	private PlayerChooser chooser;
	private PlayContext context;

	public PlayerSelectStackCallable(PlayerChooser pc, PlayContext context) {
		this.chooser = pc;
		this.context = context;
	}

	@Override
	public Integer call() throws Exception {
		return chooser.chooseStack(context);
	}

}
