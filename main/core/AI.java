package core;

import java.util.ArrayList;

public class AI extends Player{
	//MEMBERS
private IStrategy strategy;
private int stratNum;

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
	
	public void doTurn() {
		System.out.println("BOT " + this.stratNum+ " TURN");
		ArrayList<Meld> whatToPlay;
		whatToPlay = strategy.play(this.hand, this.tableSnapshot);
			
		for(Meld m : whatToPlay) {
			System.out.println("p" + stratNum + " played tiles.");
			playMeld(m);
			//this removes every tile used in the meld from your hand
			for(Tile t: m.getTiles()) {
				super.removeTile(t.getColour(), t.getValue());
			}	
		}
		
	}
	//GETTERS
	
}