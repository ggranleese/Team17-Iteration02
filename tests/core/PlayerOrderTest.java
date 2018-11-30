package core;

import java.util.ArrayList;

import junit.framework.TestCase;

public class PlayerOrderTest extends TestCase{

	public void test() {
		RummikubModel model = new RummikubModel();
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player());
		players.add(new Player());
		players.add(new Player());
		players.add(new Player());
		model.setPlayers(players);
		
		model.initialDraw();
		
		assertTrue(model.getPlayers().size() == 4);
	}

}
