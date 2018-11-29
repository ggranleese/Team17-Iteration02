package core;

import java.util.ArrayList;

public class RummikubController {
	
	private final RummikubModel model;
	
	public RummikubController(RummikubModel model) {
		this.model = model;
	}
	
	public void updateGameInfo(ArrayList<Player> players, Table table) {
		model.setTable(table);
		model.setPlayers(players);
	}
	
}
