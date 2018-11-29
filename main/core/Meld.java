package core;

import java.util.ArrayList;

public interface Meld {
	public ArrayList<Tile> getTiles();
	public int getTypeOfMeld();
	public boolean isValid();
}
