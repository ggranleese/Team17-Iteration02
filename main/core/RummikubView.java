package core;


import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

@SuppressWarnings("restriction")
public class RummikubView{

	private RummikubController controller;
	private RummikubModel model;
	
	public RummikubView() {
		model = new RummikubModel();
		controller = new RummikubController(model);
	}
	
	public void buildAndShowGui(final Stage stage) {
		Button button1 = new Button("Play");
		Button button2 = new Button("Options");
		
		button1.setOnAction(e-> drawStartView(stage));
		if(model.getPlayers() == null) {
			button2.setOnAction(e-> promptNumPlayers(stage));
		}
		else {
			button2.setOnAction(e -> handleOptionsButtonAction(stage));
		}
		VBox box = new VBox(button1, button2);
		Scene scene = new Scene(box, 1000, 1000);
		
		box.setSpacing(10);
		
		stage.setScene(scene);
		stage.setTitle("Rummikub");
		stage.show();
	}
	

	private void handleOptionsButtonAction(Stage stage) {
		
		Pane root = new Pane();
		HBox box1 = new HBox(10);
		
		ComboBox<Integer> numPlayer = new ComboBox<Integer>();
		numPlayer.getItems().addAll(2, 3, 4);
		numPlayer.setPromptText("Select Number of players:");
		numPlayer.setLayoutX(150);
		
		Button button1 = new Button("Select");
		
		Button timer = new Button("Timer");
		
		box1.getChildren().add(numPlayer);
		box1.getChildren().add(button1);
		box1.getChildren().add(timer);
		
		VBox box2 = new VBox(10);
		box2.setLayoutY(50);
		
		for (int x = 2; x < model.getPlayers().size(); x++) {
			Label label;
			ArrayList<Player> players = model.getPlayers();
			Player player = players.get(x);
			int position = x;
			
			if (!player.isBot()) {
				label = new Label("Player " + (x));
								
		        ComboBox<String> playerType = new ComboBox<String>();
		        playerType.getSelectionModel().selectFirst();
		        playerType.setPromptText("Type");
		        playerType.getItems().addAll("Human", "AI");
		        playerType.setLayoutX(150);

		        
		        playerType.setOnAction(e-> {
		        	if(playerType.getValue() == "AI") {
		 
		        	controller.updatePlayerTypeBot(players, position);
		        	}
					handleOptionsButtonAction(stage);});
		        
		        box2.getChildren().add(label);
		        box2.getChildren().add(playerType);

			}
			if(player.isBot()) {
				label = new Label("Player " + (x) + " (BOT)");
				
				ComboBox<String> stratChoice = new ComboBox<String>();
		        stratChoice.getSelectionModel().selectFirst();
		        if(((AI) player).stratNum == 1) {
		        	stratChoice.setPromptText("Strategy 1");
		        }
		        if(((AI) player).stratNum == 2) {
		        	stratChoice.setPromptText("Strategy 2");
		        }
		        if(((AI) player).stratNum == 3) {
		        	stratChoice.setPromptText("Strategy 3");
		        }
		        if(((AI) player).stratNum == 4) {
		        	stratChoice.setPromptText("Strategy 4");
		        }
		      
		        stratChoice.getItems().addAll("Strategy 1", "Strategy 2", "Strategy 3", "Strategy 4");
		        stratChoice.setLayoutX(150);
		        
		        ComboBox<String> playerType = new ComboBox<String>();
		        playerType.getSelectionModel().selectFirst();
		        playerType.setPromptText("Type");
		        playerType.getItems().addAll("Human", "AI");
		        playerType.setLayoutX(150);
		        
		        playerType.setOnAction(e-> {
		        	if(playerType.getValue() == "Human") {
		        		controller.updatePlayerTypeHuman(players, position);
		        	}
					handleOptionsButtonAction(stage);});
		        
		        stratChoice.setOnAction(e-> {
		        	controller.updatePlayerStrat(players, position, stratChoice.getValue());	
				});
		        
		        box2.getChildren().add(label);
		        box2.getChildren().add(playerType);
		        box2.getChildren().add(stratChoice);
			}
			
		}
		Button confirmButton = new Button("Back");
		box2.getChildren().add(confirmButton);
		
		root.getChildren().addAll(box1);
		root.getChildren().addAll(box2);
		
		
        Scene scene = new Scene(root, 1000, 1000);
		stage.setScene(scene);
		stage.setTitle("Rummikub");
		stage.show();

		button1.setOnAction(e-> {
			controller.updatePlayers((int) (numPlayer.getValue()));
			handleOptionsButtonAction(stage);});
		confirmButton.setOnAction(e -> buildAndShowGui(stage));

	}
	

	private void promptNumPlayers(Stage stage) {
		HBox box = new HBox(10);
		
		ComboBox<Integer> numPlayers = new ComboBox<Integer>();
		numPlayers.getSelectionModel().selectFirst();
		numPlayers.getItems().addAll(2, 3, 4);
		numPlayers.setPromptText("Select Number of players:");
		numPlayers.setLayoutX(150);
		
		Button button1 = new Button("Select");
		
		box.getChildren().add(numPlayers);
		box.getChildren().add(button1);
		
        Scene scene = new Scene(box, 1000, 1000);
		stage.setScene(scene);
		stage.setTitle("Rummikub");
		stage.show();
		
		
		button1.setOnAction(e-> {
			controller.updatePlayers((int) (numPlayers.getValue()));
			handleOptionsButtonAction(stage);});
	}


	private void handleStartButton(Stage stage) {
		//drawStartView(stage);
		if(model.getPlayers() == null) {
			controller.setDefaultGame();
		}
		controller.namePlayers();
		GameView(stage);
	}

	
	private void drawStartView(Stage stage) {
		
		Pane start = new Pane();
		HBox box = new HBox(10);
		Button nextButton = new Button("Next");
		Label label;
		
		
		start.setStyle("-fx-background-color: green");
		box.getChildren().add(nextButton);
		start.getChildren().add(box);
		nextButton.setOnAction(e-> GameView(stage));
		
		ArrayList<Player> players = model.getPlayers();
		
		for(Player p : players) {
			ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/Tiles/" + p.turnOrderCard.toString().toLowerCase + ".jpg")));
			start.getChildren().add(image);
		}
		
		Player firstPlayer =  Collections.max(players, Comparator.comparing(p->p.turnOrderCard.getValue()));
		label = new Label("Player:" + firstPlayer.playerNum + "goes first!");
		start.getChildren().add(label);
		
		
		//ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/Tiles/b10.jpg")));
		//start.getChildren().add(image);
		
		Scene drawTurnBoard = new Scene(start,1000,1000);
		stage.setScene(drawTurnBoard);
		stage.show();
			
	}
	
	

	private void GameView(Stage stage) {

		GridPane screen = new GridPane();
		screen.setStyle("-fx-background-color: black");
		screen.setHgap(100);
		screen.setVgap(100);
		screen.setPadding(new Insets(100,100,100,100));
		
		GridPane board = new GridPane();
		board.setStyle("-fx-background-color: green");
		board.setPrefWidth(900);
		board.setPrefHeight(500);
		
		Button endTurn = new Button("End Turn");
		board.setAlignment(Pos.BOTTOM_RIGHT);
		
		screen.getChildren().add(board);
		screen.getChildren().add(endTurn);
		
		
		Scene display = new Scene(screen,1000,750);
		stage.setScene(display);
		stage.show();


//		for(int i = 0; i<this.model.getTable().getMelds().size(); i++) {
//			for(int j=0; j<this.model.getTable().getMelds().get(i).getTiles().size(); j++) {
//				
//				displayTile(this.model.getTable().getMelds().get(i).getTiles().get(j).toString());
//				
//			}
//			
//		}

		
//		for(int i = 0; i<this.model.getTable().getMelds().size(); i++) {
//			for(int j=0; j<this.model.getTable().getMelds().get(i).getTiles().size(); j++) {
//				
//				displayTile(this.model.getTable().getMelds().get(i).getTiles().get(j).toString());
//				
//			}
//			
//		}
//		
	}
	
//	private ImageView displayTile(String tile) {
//		
//		//ImageView image = new ImageView(new Image("/Tiles/" + tile.toLowerCase() + ".jpg"));
//		ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/Tiles/" + tile.toLowerCase() + ".jpg")));
//		image.setFitHeight(50);
//		image.setFitWidth(50);
//		image.setPreserveRatio(true);
//		
//		return image;
//	}

	

}
