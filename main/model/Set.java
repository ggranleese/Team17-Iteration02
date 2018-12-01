package model;

import java.util.ArrayList;
import java.util.HashSet;

public class Set implements Meld{
	//MEMBERS
private	ArrayList<Tile> tiles;
private int typeOfMeld;
private boolean valid;

	//CONSTRUCTORS
	public Set(ArrayList<Tile> tiles) {
		this.tiles = tiles;
		checkValid(tiles);
		this.typeOfMeld = 2;
	}
	
	//METHODS
	private void checkValid(ArrayList<Tile> tiles) {
		boolean isValid = true;
		int value = tiles.get(0).getValue();
		
		//checks colours are all different
		HashSet<Integer> colours = new HashSet<Integer>();
		  for (Tile t : tiles)
		  {
		    if (colours.contains(t.getColour())) {
		    	isValid = false;
		    	break;
		    }
		    //checks if all the values are the same
		    if(t.getValue() != value) {
				isValid = false;
				break;
				}
		    colours.add(t.getColour());
		  }
		  
		//checks if meld is at least 3
	   if(tiles.size() < 3) {
			isValid = false;
	   }
	   this.valid = isValid;
	}

	//GETTERS
	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	public int getTypeOfMeld() {
		return this.typeOfMeld = 0;
	}

	public boolean isValid() {
		checkValid(this.tiles);
		return this.valid;
	}
}
