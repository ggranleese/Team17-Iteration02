package core;

import java.util.ArrayList;

public interface IStrategy {

	public ArrayList<Meld> play(ArrayList<Tile> hand, Table table);
	
}
