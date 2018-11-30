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
	public String toString() {
		String printVal = "";
		int colour = this.colour;
		switch(colour) {
			case 1:
				printVal += "R";
				printVal += Integer.toString(this.value);
				break;
			case 2: 
				printVal += "B";
				printVal += Integer.toString(this.value);
				break;
			case 3:
				printVal += "G";
				printVal += Integer.toString(this.value);
				break;
			case 4:
				printVal += "O";
				printVal += Integer.toString(this.value);
				break;
			default:
				System.out.println("Invalid tile found.");
				break;
		}
		return printVal;
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