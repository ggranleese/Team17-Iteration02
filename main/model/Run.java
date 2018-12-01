package model;

import java.util.ArrayList;

public class Run implements Meld{
	
	//MEMBERS
private	ArrayList<Tile> tiles;
private int typeOfMeld;
private boolean valid;

	//CONSTRUCTORS
	public Run(ArrayList<Tile> tiles) {
		this.tiles = tiles;
		checkValid(tiles);
		this.typeOfMeld = 1;
	}
	
	//METHODS
	private void checkValid(ArrayList<Tile> tiles) {
		boolean isValid = true;
		int colour = tiles.get(0).getColour();
		int value = tiles.get(0).getValue() - 1;
			
		for (Tile t: tiles) {
			//checks colours are equal
			if(t.getColour() != colour) {
			isValid = false;
			break;
			}
		//checks values are incrementing by 1
			if(t.getValue() - value != 1) {
				isValid = false;
				break;
			}
		value ++;
		}
			  
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
		return this.typeOfMeld;
	}

	public boolean isValid() {
		checkValid(this.tiles);
		return this.valid;
	}

}
