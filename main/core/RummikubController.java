package core;

import java.util.ArrayList;

import model.AI;
import model.Player;
import model.RummikubModel;
import model.StrategyOne;
import model.StrategyThree;
import model.StrategyTwo;
import model.Table;
import model.Tile;

public class RummikubController {
	
	private final RummikubModel model;
	
	public RummikubController(RummikubModel m) {
		model = m;
	} 
	
	public void updateGameInfo(ArrayList<Player> players, Table table) {
		model.setTable(table);
		model.setPlayers(players);
	}

	public void updatePlayers(int i) {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player());
		for(int x = 1; x < i; x++) {
			players.add(new Player());
		}
		model.setPlayers(players);
	}


	public void updatePlayerTypeBot(ArrayList<Player> players, int x) {
		if (!players.get(x).isBot()) {
			players.set(x, new AI(1));
		}
		
	}

	public void updatePlayerTypeHuman(ArrayList<Player> players, int x) {
		if (players.get(x).isBot()) {
			players.set(x, new Player());
		}
		
	}

	public void updatePlayerStrat(ArrayList<Player> players, int x, String value) {

		if (value == "Strategy 1") {
			((AI) players.get(x)).setStrategy(new StrategyOne());
			((AI) players.get(x)).stratNum = 1;
		}
		if (value == "Strategy 2") {
			((AI) players.get(x)).setStrategy(new StrategyTwo());
			((AI) players.get(x)).stratNum = 2;
		}
		if (value == "Strategy 3") {
			((AI) players.get(x)).setStrategy(new StrategyThree());
			((AI) players.get(x)).stratNum = 3;
		}
		if (value == "Strategy 4") {
			//((AI) players.get(x)).setStrategy(new StrategyFour());
			((AI) players.get(x)).stratNum = 4;
		}
		
	}

	public void updateTimer() {
		if (model.getTimer()) {
			model.setTimer(false);
		}
		else {
			model.setTimer(true);
		}
	}

	public void namePlayers() {
		for (int i = 0; i < model.getPlayers().size(); i++) {
			model.getPlayers().get(i).playerNum = i+1; 
		}
		
	}

	public void setDefaultGame() {
		ArrayList<Player> p = new ArrayList<Player>();
		p.add(new Player());
		p.add(new AI(1));
		p.add(new AI(2));
		p.add(new AI(3));
		
		model.setPlayers(p);
		
	}

	//finds turn order and also deals hands
	public void findTurnOrder() {
		model.findTurnOrder();
		dealHands();
		
	}
	
	public void dealHands() {
		model.dealPlayerHands();
	}

	public void updatePlayerHand(ArrayList<Player> players, int x, String text) {
		String[] splitted = text.split(" ");
		for (String s : splitted) {
			players.get(x).addTile(parseToTile(s));
			
			
		}
		
	}

	private Tile parseToTile(String s) {
		
		s = s.toLowerCase();
		String tail = s.substring(1,s.length());
		int result = Integer.parseInt(tail);
		
		if(s.charAt(0) == 'o') {
			for(int i = 0; i<15; i++) {
				if(result == i) {
					Tile tile = new Tile(4, result, false);
					return tile;
				}
			}
		}
		
		if(s.charAt(0) == 'b') {
			for(int i = 0; i<15; i++) {
				if(result == i) {
					Tile tile = new Tile(2, result, false);
					return tile;
				}
			}
		}
		
		if(s.charAt(0) == 'r') {
			for(int i = 0; i<15; i++) {
				if(result == i) {
					Tile tile = new Tile(1, result, false);
					return tile;
				}
			}
		}
		
		if(s.charAt(0) == 'g') {
			for(int i = 0; i<15; i++) {
				if(result == i) {
					Tile tile = new Tile(3, result, false);
					return tile;
				}
			}
		}
		
		if(s.charAt(0) == '0') {
			Tile tile = new Tile(0,0,true);
			return tile;
		}
			
		return null;
	}

	
}
