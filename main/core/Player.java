package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Player implements Observer{
	//MEMBERS
	private Boolean status;
	private int pointCounter;
	public ArrayList<Tile> hand;
	Scanner input = new Scanner(System.in);
	//These are observed from Table
	public Table tableSnapshot;
	//private	ArrayList<Meld> Melds;
	protected boolean gameOver = false;
	
	//CONSTRUCTORS
	public Player() {
		this.hand = new ArrayList<Tile>();
		this.status = false;
		this.tableSnapshot = new Table();
		}
	
	//METHODS
	public void drawHand() {
		
		for(int i = 0; i<14; i++) {
			drawTile(this.tableSnapshot.getPile());
		}
		sortHand();
		
		
		
	}
	
	public void removeTile(int c, int v) {
		
		for (Tile t : hand) {
			
			if(t.getColour() == c && t.getValue() == v) {
				this.hand.remove(t);
				break;
			}
		}
		
	}
	
	public void sortHand() {
		ArrayList<Tile> blue = new ArrayList<Tile>();
		ArrayList<Tile> red = new ArrayList<Tile>();
		ArrayList<Tile> green = new ArrayList<Tile>();
		ArrayList<Tile> orange = new ArrayList<Tile>();
		ArrayList<Tile> finished = new ArrayList<Tile>();
		
		for(int i = 0; i < this.hand.size(); i++) {
			
			if(this.hand.get(i).getColour() == 1) {
				blue.add(this.hand.get(i));
			}
			
			if(this.hand.get(i).getColour() == 2) {
				red.add(this.hand.get(i));
			}
			
			if(this.hand.get(i).getColour() == 3) {
				green.add(this.hand.get(i));
			}
			
			if(this.hand.get(i).getColour() == 4) {
				orange.add(this.hand.get(i));
			}
			
		}
		
		insertionSort(blue);
		insertionSort(red);
		insertionSort(green);
		insertionSort(orange);
		
		Collections.sort(blue, new Comparator<Tile>() {
			public int compare(Tile s2, Tile s1) {
				return Integer.compare(s2.getValue(), s1.getValue());
			}
		});
		Collections.sort(red, new Comparator<Tile>() {
			public int compare(Tile s2, Tile s1) {
				return Integer.compare(s2.getValue(), s1.getValue());
			}
		});
		Collections.sort(green, new Comparator<Tile>() {
			public int compare(Tile s2, Tile s1) {
				return Integer.compare(s2.getValue(), s1.getValue());
			}
		});
		Collections.sort(orange, new Comparator<Tile>() {
			public int compare(Tile s2, Tile s1) {
				return Integer.compare(s2.getValue(), s1.getValue());
			}
		});
		
		finished.addAll(blue);
		finished.addAll(red);
		finished.addAll(green);
		finished.addAll(orange);
		
		this.hand = finished;
		
	}
	
	public ArrayList<Tile> insertionSort(ArrayList<Tile> input) {
		for(int i =1; i < input.size(); i++) {
			for(int j = i; j > 0; j--) {
				if(input.get(j).getValue() < input.get(j-1).getValue()) {
					Tile temp = input.get(j);
					Tile x = input.get(j);
					Tile y = input.get(j-1);
					x = y;
					y = temp;
					
				}
			}	
		}
		return input;
	}

	public void playMeld(Meld m) {
		
		this.tableSnapshot.getMelds().add(m);
	
		if(this.status == false) {
			int meldPoints = 0;
			
			for(int i = 0; i < m.getTiles().size(); i++) {
				meldPoints += m.getTiles().get(i).getValue();
			}
			
			this.pointCounter += meldPoints;
			
			if(pointCounter >= 30) {
				this.status = true;
			}
		}
	}
	
	public void addTile(Tile t) {
		this.hand.add(t);
	}

	public void drawTile(Pile p) {
		this.hand.add(p.getTile(0));
		p.removeTile();
		sortHand();
	}
	


	
	public boolean endTurn() {
		ArrayList<Meld> invalidMelds = checkInvalid();
		if(invalidMelds.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public ArrayList<Meld> checkInvalid(){
		ArrayList<Meld> invalidMelds = new ArrayList<Meld>();
		for(Meld m : this.tableSnapshot.getMelds()) {
			if(!m.isValid()) {
				invalidMelds.add(m);
			}
		}
		return invalidMelds;
	}
	


	


	

	


	
	//OBSERVER METHODS
	public void update(Table table) {
		//this.Melds = (ArrayList<Meld>) table.getMelds().clone();
		this.tableSnapshot.setMelds((ArrayList<Meld>) table.getMelds().clone());
		this.tableSnapshot.setPile( table.getPile());
		this.tableSnapshot.status = table.status;
		this.tableSnapshot.setObservers(table.getObservers());
	}
	public void pushToTable(Table table) {
		if (this.hand.isEmpty()) {
			this.gameOver = true;
			System.out.println("Player Wins!\n");
		}
		if(!this.gameOver && table.getMelds().containsAll(this.tableSnapshot.getMelds()) && this.tableSnapshot.getMelds().containsAll(table.getMelds())) {
			System.out.println("No actions performed. Drawing Tile...");
			if(this.tableSnapshot.getPile().getPile().isEmpty()) {
				System.out.println("Can't draw tile. Pile is empty.\n");
			}
			else {
			drawTile(table.getPile());
			System.out.println("New Hand:");
			
			System.out.println("\n");
			}
		}
		
		table.updateTable(this.tableSnapshot.getMelds(), this.gameOver, this.status, this.tableSnapshot.getPile());
	}
	
	//GETTERS
	public ArrayList<Tile> getHand() {
		return this.hand;
	}
	public Boolean getStatus() {
		return this.status;
	}
	public Tile getTile(int i) {
		return this.hand.get(i);
	}
	public int getSize() {
		return this.hand.size();
	}
	//SETTERS
	public void setStatus(boolean b) {
		this.status = b;
	}
	public void setGameOver(boolean b) {
		this.gameOver = b;
	}
}