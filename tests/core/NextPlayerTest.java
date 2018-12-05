package core;

import java.util.ArrayList;

import junit.framework.TestCase;
import model.Player;
import model.RummikubModel;
import view.RummikubView;

public class NextPlayerTest extends TestCase{
	public void test() {
		RummikubView view = new RummikubView();
		RummikubModel model = new RummikubModel();
		RummikubController controller = new RummikubController(model);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player());
		players.add(new Player());
		view.currentPlayer = controller.model.getPlayers().get(0);
		ArrayList<Player> modelplayers = controller.model.getPlayers();
		modelplayers = players;
		view.nextPlayerTurn();
		
		assertTrue(view.currentPlayer == modelplayers.get(1));
		
	}

}
