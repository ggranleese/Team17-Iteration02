package core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.AI;
import model.Meld;
import model.Pile;
import model.Player;
import model.RummikubModel;
import model.RummikubModelMemento;
import model.Run;
import model.Set;
import model.StrategyOne;
import model.StrategyThree;
import model.StrategyTwo;
import model.Table;
import model.Tile;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException; 
import javafx.scene.control.Alert.*;
import javafx.scene.control.Alert;


public class RummikubController {
	
	public RummikubModel model;
	
	public RummikubController(RummikubModel m) {
		this.model = m;
	} 
	
	public void updateGameInfo(ArrayList<Player> players, Table table) {
		model.setTable(table);
		model.setPlayers(players);
	}

	public void updatePlayers(int i) {
		ArrayList<Player> players = new ArrayList<Player>();
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
		for (Player p : model.getPlayers()) {
			if(p.getHand().size() == 0 ) {
				p.drawHand(model.getPile());
			}
		}
	}

	@SuppressWarnings("restriction")
	public void updatePlayerHand(int x, String text) {
		ArrayList<Tile> hold = new ArrayList<Tile>();
		String[] splitted = text.split(" ");
		for (String s : splitted) {
				if(model.getPile().getPile().contains(parseToTile(s))){
					hold.add(parseToTile(s));
					model.getPile().removeTile(parseToTile(s));
				
			}
				else {
					Alert errorAlert = new Alert(AlertType.ERROR);
					errorAlert.setHeaderText("WARNING: Input not valid");
					errorAlert.setContentText("Too many repeated tiles! ");
					errorAlert.showAndWait();
				}
		}
		model.getPlayers().get(x).setHand(hold);
	}
	
	@SuppressWarnings("restriction")
	public void addSingleTile(int x, String s) {
		
		try {
			if(model.getPile().getPile().contains(parseToTile(s))){
			model.getPlayers().get(x).getHand().add(parseToTile(s));
			model.getPile().removeTile(parseToTile(s));
			System.out.println("added " + parseToTile(s).toString() + " to " + model.getPlayers().get(x).playerNum);
			}
			else {
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("WARNING: Input not valid");
				errorAlert.setContentText("Too many repeated tiles! ");
				errorAlert.showAndWait();
			}
		}catch(IndexOutOfBoundsException e){
			model.getPlayers().get(3).getHand().add(parseToTile(s));
		}
		//System.out.println("added " + parseToTile(s).toString() + " to " + model.getPlayers().get(x).playerNum);
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
	
	public void drawTile(Player player) {
		player.hand.add(model.getPile().getTile(0));
		System.out.println("\nDRAWING TILE... " + model.getPile().getTile(0) + "\n\n");
		System.out.println(player.hand.toString());
	}

	@SuppressWarnings("unchecked")
	public void createJSONFile() throws IOException, JSONException {
		
		JSONObject object = new JSONObject();
		
		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		Player player4 = new Player();
		
		player1.hand.add(new Tile(1,1,false));
		player1.hand.add(new Tile(1,2,false));
		player1.hand.add(new Tile(1,3,false));
		
		player2.hand.add(new Tile(2,2,false));
		player2.hand.add(new Tile(3,2,false));
		player2.hand.add(new Tile(4,2,false));
		
		player3.hand.add(new Tile(4,13,false));
		player3.hand.add(new Tile(4,12,false));
		player3.hand.add(new Tile(4,11,false));
		
		player4.hand.add(new Tile(3,9,false));
		player4.hand.add(new Tile(2,9,false));
		player4.hand.add(new Tile(0,0,true));

		player1.turnOrderCard = new Tile(1,13,false);
		player2.turnOrderCard = new Tile(1,12, false);
		player3.turnOrderCard = new Tile(1,11, false);
		player4.turnOrderCard = new Tile(1,10, false);
		
		JSONArray players = new JSONArray();
		
		
		object.put("players", players);
		
		try(FileWriter file = new FileWriter("JSONFile.json")){
			
			file.write(object.toString());
			file.flush();
			
		}catch(IOException e) {e.printStackTrace();}
		
		readJSONFile("JSONFile.json");
	}

	
	@SuppressWarnings("unchecked")
	private void readJSONFile(String file) throws IOException, JSONException {
		
		JSONParser parser = new JSONParser();
		ArrayList<Player> players = new ArrayList<>();
		
		try {
			
			Object obj = parser.parse(new FileReader(file));
			JSONObject jsonObj = (JSONObject) obj;
			JSONArray playersJSON = (JSONArray) jsonObj.get("players");
			Iterator<Player> iterator = ((List<Player>) playersJSON).iterator();
			
			
			while(iterator.hasNext()) {
				players.add(iterator.next());
			}
			
			if(players.size() >0) {
				model.setPlayers(players);
				System.out.println("FILE PLAYERS: " + model.getPlayers().get(0).getHand().toString());
			}
			
		}catch(FileNotFoundException e) {e.printStackTrace();}
		catch(ParseException e) {e.printStackTrace();}
		
	} 

	public void clearMelds() {
		model.clearMelds();
		
	}

	public boolean addMeld(ArrayList<Tile> meld) {
		Run run = new Run(meld);
		Set set = new Set(meld);
		
		if(run.isValid()) {
			model.getMelds().add(new Run(meld));
			return true;
		}
		else if(set.isValid()) {
			model.getMelds().add(new Run(meld));
			return true;
		}
		else {
			return false;
		}
	
		
	}
	 public void setState(RummikubModel model){
	      this.model = model;
	   }

	   public RummikubModel getState(){
	      return model;
	   }

	   public RummikubModelMemento saveStateToMemento(){
	      return new RummikubModelMemento(model);
	   }

	   public void restoreToState(RummikubModelMemento memento){
	      this.model = memento.getState();
	   }
	
}










