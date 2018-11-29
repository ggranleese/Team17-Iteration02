package core;

import java.util.ArrayList;

public class RummikubModel {

	
	private ArrayList<Player> players;
	private Table table;
	private Pile pile;
	
	public RummikubModel() {
		
	}
	
	
	//GETTERS and SETTERS
	public ArrayList<Player> getPlayer(){
		return this.players;
	}
	
	public Table getTable() {
		return this.table;
	}
	
	public void setPlayers(ArrayList<Player> p) {
		players = p;
	}
	
	public void setTable(Table t) {
		table = t;
	}
	
	
	
}
