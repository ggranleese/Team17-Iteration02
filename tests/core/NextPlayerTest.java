package core;

import java.util.ArrayList;

import junit.framework.TestCase;
import model.Player;
import view.RummikubView;

public class NextPlayerTest extends TestCase{
	public void test() {
		RummikubView view = new RummikubView();
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player());
		players.add(new Player());
		view.currentPlayer = view.model.getPlayers().get(0);
		ArrayList<Player> modelplayers = view.model.getPlayers();
		modelplayers = players;
		nextPlayerTurn();
		
		assertTrue(view.currentPlayer == modelplayers.get(1));
		
	}

}
