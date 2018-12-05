package model;

import java.util.ArrayList;

public class RummikubModel {

	
	private ArrayList<Player> players;
	private Table table;
	private Pile pile;
	public ArrayList<Meld> melds;
	private boolean timer;
	public boolean status;
	
	public RummikubModel() {
		table = new Table();
		pile = new Pile();
		pile.populate();
		pile.shuffle();
		melds = new ArrayList<Meld>();

//		ArrayList<Tile> run1 = new ArrayList<>();
//		run1.add(new Tile(1,3, false));
//		run1.add(new Tile(1,4, false));
//		run1.add(new Tile(1,5, false));
//				
//			
//		ArrayList<Tile> run2 = new ArrayList<>();
//		run2.add(new Tile(4,1, false));
//		run2.add(new Tile(4,2, false));
//		run2.add(new Tile(4,3, false));	
//		run2.add(new Tile(4,4, false));	
//		
//		
//		ArrayList<Tile> run3 = new ArrayList<>();
//		run3.add(new Tile(2,3, false));
//		run3.add(new Tile(2,4, false));
//		run3.add(new Tile(2,5, false));
//		run3.add(new Tile(2,6, false));
//		
//		ArrayList<Tile> run4 = new ArrayList<>();
//		run4.add(new Tile(3,1, false));
//		run4.add(new Tile(3,2, false));
//		run4.add(new Tile(3,3, false));	
//		run4.add(new Tile(3,4, false));	
//		
//		ArrayList<Tile> set1 = new ArrayList<>();
//		set1.add(new Tile(3,6, false));
//		set1.add(new Tile(1,6, false));
//		set1.add(new Tile(4,6, false));
//		
//		ArrayList<Tile> set2 = new ArrayList<>();
//		set2.add(new Tile(1,3, false));
//		set2.add(new Tile(4,3, false));
//		set2.add(new Tile(3,3, false));
//		set2.add(new Tile(2,3, false));
//
//		melds.add(new Run(run1));
//		melds.add(new Run(run2));
//		melds.add(new Run(run3));
//		melds.add(new Run(run4));
//		melds.add(new Set(set1));
//		melds.add(new Set(set2));
		
		timer = false;
	}
	
	public RummikubModel(RummikubModel model) {
		this.melds = new ArrayList<Meld>(model.melds);
		this.pile = new Pile(model.pile);
		this.players = new ArrayList<Player>(model.getPlayers());
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


	public void clearMelds() {
		this.melds.clear();
		
	}
	
}
