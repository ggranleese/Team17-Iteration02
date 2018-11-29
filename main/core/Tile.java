package core;

public class Tile {
	
	private int value;
	private int colour;
	private boolean joker;
	//LEGEND:
	//R = 1,
	//B = 2,
	//G = 3,
	//O = 4

	public Tile(int c, int v, boolean j) {
		this.colour = c;
		this.value = v;
		this.joker = j;
	}
	public int getValue() {
		return this.value;
	}
	public int getColour() {
		return this.colour;
	}
	
	@Override
	public boolean equals(Object obj) {
		Tile t = (Tile) obj;
		if (this.value == t.value && this.colour == t.colour) {
			return true;
		}
		else return false;
	}
}