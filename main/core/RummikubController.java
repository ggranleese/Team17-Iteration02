package core;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class RummikubController {
	
	private final RummikubModel model;
	private int numBots;
	
	public RummikubController(RummikubModel m) {
		model = m;
		
	}
	
	public void updateGameInfo(ArrayList<Player> players, Table table) {
		model.setTable(table);
		model.setPlayers(players);
	}

	public void getNumBots(Object object) {
		
		if(object == "1") {
			numBots = 1;
			System.out.println(numBots);
		}else if(object == "2") {
			numBots = 2;
			System.out.println(numBots);
		}else{
			numBots = 3;
			System.out.println(numBots);
		}

	}

	public void updatePlayers(int i) {
		ArrayList<Player> players = new ArrayList<Player>();
		for(int x = 0; x < i; x++) {
			players.add(new Player());
		}
		model.setPlayers(players);
	}

	
}
