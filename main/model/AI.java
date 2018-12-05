package model;

import java.util.ArrayList;

public class AI extends Player{
	//MEMBERS
private IStrategy strategy;
public int stratNum;
public int pointCounter;
public boolean playWithBoard;

	//CONSTRUCTORS
	public AI(int strat) {
		this.hand = new ArrayList<Tile>();
		this.stratNum = strat;
		if(strat == 1) {
			this.strategy = new StrategyOne();
		}else if(strat == 2){
			this.strategy = new StrategyTwo();
		}else{
			this.strategy = new StrategyThree();
		}
		pointCounter = 0;
		playWithBoard = false;
	}
	
	//METHODS
	public void drawHand() {
		for(int i = 0; i<14; i++) {
			drawTile(this.tableSnapshot.getPile());
		}
		sortHand();
		System.out.println("Bot " + stratNum + " Hand:");
		System.out.println("\n");
	}
	
	public ArrayList<Meld> doTurn(RummikubModel model) {
		
		if(pointCounter >=30) {
			playWithBoard = true;
		}
		
		System.out.println("BOT " + this.stratNum+ " TURN");
		ArrayList<Meld> whatToPlay;
		whatToPlay = strategy.play(this.hand, model, playWithBoard);
			
		for(Meld m : whatToPlay) {
			System.out.println("p" + stratNum + " played tiles.");
			//this removes every tile used in the meld from your hand
			for(Tile t: m.getTiles()) {
				super.removeTile(t.getColour(), t.getValue());
			}	
		}
		
		return whatToPlay;
		
	}
	//GETTERS
	@Override
	public boolean isBot() {
		return true;
	}
	
	public void setStrategy(IStrategy s) {
		this.strategy = s;
	}
}