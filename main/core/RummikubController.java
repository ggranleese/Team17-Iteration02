package core;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class RummikubController {
	
	private final RummikubModel model;
	private int numBots;
	
	public RummikubController(RummikubModel model) {
		this.model = model;
		
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

	
}
