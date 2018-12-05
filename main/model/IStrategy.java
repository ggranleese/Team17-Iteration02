package model;

import java.util.ArrayList;

public interface IStrategy {

	public ArrayList<Meld> play(ArrayList<Tile> hand, RummikubModel model, boolean playWithBoard);
	
}
