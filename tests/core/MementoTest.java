package core;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;
import model.Meld;
import model.RummikubModel;
import model.RummikubModelMemento;
import model.Run;
import model.Tile;

public class MementoTest extends TestCase{

	public void test() {
		RummikubModel game = new RummikubModel();
		RummikubController controller = new RummikubController(game);
		
		ArrayList<Tile> tiles2 = new ArrayList<Tile>();
		tiles2.add(new Tile (1,11,false));
		tiles2.add(new Tile (1,12,false));
		tiles2.add(new Tile (1,13,false));
		
		controller.addMeld(tiles2);
		
		RummikubModelMemento memento = controller.saveStateToMemento();
	
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		tiles.add(new Tile (1,11,false));
		tiles.add(new Tile (1,12,false));
		tiles.add(new Tile (1,13,false));
		
		controller.addMeld(tiles);
		
		System.out.println("Controller model has: "+controller.model.getMelds().size());
		System.out.println("Memento state has: " +memento.getState().getMelds().size());
		
		controller.restoreToState(memento);
		
		System.out.println("Restored Controller model has: "+controller.model.getMelds().size());
		System.out.println(game.getMelds().size());
		
		assertTrue(game.getMelds().size() == 0);
	}

}
