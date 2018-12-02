package model;

import java.util.ArrayList;
import java.util.Random;




public class Pile {

	//MEMBERS
public ArrayList<Tile> pile;
	
	//CONSTRUCTOR
	public Pile(){
		this.pile = new ArrayList<Tile>();
	}
	public Pile(Pile copy){
		this.pile = copy.getPile();
	}
	//METHODS
	public void shuffle() {
		ArrayList<Tile> tmp = new ArrayList<Tile>();
		Random rand = new Random();
		int index = 0;
		int size = this.pile.size();
		for(int i=0; i < size; i++) {
			index = rand.nextInt((this.pile.size()-1)+1);
			tmp.add(this.pile.get(index));
			this.pile.remove(index);
		}
		
		this.pile = tmp;
	}
	
	public void removeTile() {
		this.pile.remove(0);
	}
	
	public void addTile(Tile t) {
		this.pile.add(0,t);
	}
	
	public void populate() {
		
		for(int x=0; x<2; x++){
			for(int i=1; i<5; i++) {
				for(int j=1; j<=13; j++) {
					this.pile.add(new Tile(i,j,false));
				}
			}
		}
		this.pile.add(new Tile(0,0,true));
		this.pile.add(new Tile(0,0,true));
	}
	

	//GETTERS
	public Tile getTile(int index) {
		return this.pile.get(index);
	}
	
	public ArrayList<Tile> getPile() {
		return this.pile;
	}

}
