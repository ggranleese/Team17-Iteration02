package core;



import java.util.ArrayList;

import junit.framework.TestCase;
import model.Player;
import model.RummikubModel;
import model.Tile;

public class GUIHandRigTest extends TestCase{
	
	
	
	public void test() {
		RummikubModel model = new RummikubModel();
		RummikubController controller = new RummikubController(model);
		 
		Player player = new Player();
		ArrayList<Player> players = new ArrayList<Player>();
		
		players.add(player);
		
		String text = "b10 o4 r7 00";
		controller.updatePlayerHand(players, 0, text);
		
		for(Tile tile : players.get(0).getHand()) {
			System.out.println(tile.toString());
		}
		
		
		
		assertTrue(players.get(0).getHand().size() == 4);
	}
	
	
}
