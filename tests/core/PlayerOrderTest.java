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
		
		model.findTurnOrder();
		for(Player p : model.getPlayers()) {
			System.out.println(p.turnOrderCard.toString());
		}
		
		assertTrue(model.getPlayers().size() == 4);
	}

}
