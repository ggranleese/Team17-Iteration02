package core;

import java.util.ArrayList;

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
		for(int x = 0; x < i; x++) {
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

	
}
