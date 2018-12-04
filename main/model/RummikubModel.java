package model;

import java.util.ArrayList;

public class RummikubModel {

	
	private ArrayList<Player> players;
	private Table table;
	private Pile pile;
	private ArrayList<Meld> melds;
	private boolean timer;
	
	public RummikubModel() {
		table = new Table();
		pile = new Pile();
		pile.populate();
		pile.shuffle();
		melds = new ArrayList<Meld>();
		ArrayList<Tile> meld = new ArrayList<Tile>();
		Tile tile = new Tile(1, 1, false);
		Tile tile2 = new Tile(2, 13, false);
		Tile tile3 = new Tile(3, 8, false);
		meld.add(tile);
		meld.add(tile2);
		meld.add(tile3);
		Set set = new Set(meld);
		ArrayList<Tile> meld2 = new ArrayList<Tile>();
		Tile tile4 = new Tile(4, 13, false);
		Tile tile5 = new Tile(3, 11, false);
		Tile tile6 = new Tile(1, 10, false);
		meld2.add(tile4);
		meld2.add(tile5);
		meld2.add(tile6);
		Set set2 = new Set(meld2);
		melds.add(set);
		melds.add(set2);
		timer = false;
	}
	
	
	//GETTERS and SETTERS
	
	public Table getTable() {
		return this.table;
	}
	
	public void setPlayers(ArrayList<Player> p) {
		players = p;
	}
	
	public void setTable(Table t) {
		table = t;
	}


	public ArrayList<Player> getPlayers() {
		return players;
	}

	public boolean getTimer() {
		return this.timer;
	}
	
	public Pile getPile() {
		return this.pile;
	}
	
	public void setTimer(boolean b) {
		this.timer = b;
		
	}


	public void findTurnOrder() {
		
		Pile tmpPile = new Pile();
		for(int i=1; i<5; i++) {
			for(int j=1; j<=13; j++) {
				tmpPile.addTile(new Tile(i,j,false));
			}
		}
		
		for(Player p : players) {
			p.drawInitialTile(tmpPile);
		}
	}


	public ArrayList<Meld> getMelds() {
		return this.melds;
	}


	public void dealPlayerHands() {
		for(Player p : players) {
			p.drawHand(pile);
		}
		
	}
	
}
