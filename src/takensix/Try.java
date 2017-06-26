
package takensix;

import takensix.card.Card;
import takensix.context.PlayContext;
import takensix.hiddencode.PlayerChooserMinimal;
import takensix.player.Player;
import takensix.stack.StackManager;
import takensix.utils.Printer;

public class Try {

	public static void main(String[] args) {
		
		Player player = new Player("", new PlayerChooserMinimal());
		player.receiveCard(new Card(5,1));
		player.receiveCard(new Card(51,1));
		player.receiveCard(new Card(40,60));
		player.receiveCard(new Card(32,7));
		player.receiveCard(new Card(90,5));
		
		StackManager sm = new StackManager(2);
		sm.receiveCard(new Card(50,1), null);
		sm.receiveCard(new Card(2,100), null);
		
		int size = player.getCards().size();
		for (int i = 0; i < size; i++) {
			PlayContext pc = new PlayContext(player, sm.getStacks(), 1);
			Card c = player.playCard(pc);
			Printer.pln(c.toString());
			player.addScore(sm.receiveCard(c, pc));
			Printer.pln("--> score : " + player.getScore());
		}
	}

}
