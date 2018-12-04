package core;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;
import model.Meld;
import model.RummikubModel;
import model.Run;
import model.Tile;

public class MementoTest extends TestCase{

	public void test() {
		RummikubModel game = new RummikubModel();
		RummikubController controller = new RummikubController(game);
		
		RummikubModelMemento memento = controller.save();
		
		ArrayList<Meld> newMelds = new ArrayList<Meld>();
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		tiles.add(new Tile (1,11,false));
		tiles.add(new Tile (1,12,false));
		tiles.add(new Tile (1,13,false));
		
		Run run = new Run(tiles);
		
		newMelds.add(run);
		
		controller.addMeld(tiles);
		
		controller.restoreToState(memento);
		
		assertTrue(game.getMelds().size() == 0);
	}

}
