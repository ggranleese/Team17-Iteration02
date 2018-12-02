package view;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import core.RummikubController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jfxtras.labs.util.event.MouseControlUtil;
import model.AI;
import model.Player;
import model.RummikubModel;
import model.Tile;
import javafx.scene.Group;
import javafx.scene.layout.FlowPane;

@SuppressWarnings("restriction")
public class RummikubView{

	private RummikubController controller;
	public RummikubModel model;
	public Player currentPlayer;
	
	public RummikubView() {
		model = new RummikubModel();
		controller = new RummikubController(model);
	}
	
	public void buildAndShowGui(final Stage stage) {
		AnchorPane mainPane = new AnchorPane();
		RummikubButton startButton = new RummikubButton("Play");
		RummikubButton optionsButton = new RummikubButton("Options");
		startButton.setLayoutX(400);
		startButton.setLayoutY(400);
		
		optionsButton.setLayoutX(400);
		optionsButton.setLayoutY(500);
		 
		startButton.setOnAction(e-> drawStartView(stage));
		
		if(model.getPlayers() == null) {
			optionsButton.setOnAction(e-> promptNumPlayers(stage));
		}
		else {
			optionsButton.setOnAction(e -> handleOptionsButtonAction(stage));
		}
		mainPane.getChildren().addAll(startButton, optionsButton);
		Scene scene = new Scene(mainPane,1800,900);
		
		
		mainPane.setBackground(new Background(createStartBackground(stage)));
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.setResizable(true);
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
		
		RadioButton timer = new RadioButton("Timer");
		if(model.getTimer()) {
			timer.setSelected(true);
		}
		
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
		
		timer.setOnAction(e -> controller.updateTimer());

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
		controller.namePlayers();
		GameView(stage);
	}

	
	private void drawStartView(Stage stage) {
		
		Pane start = new Pane();
		//HBox box = new HBox(10);
		RummikubButton nextButton = new RummikubButton("Next");
		Label label;
		
		nextButton.setLayoutX(400);
		nextButton.setLayoutY(500);
		
		if(model.getPlayers() == null) {
			controller.setDefaultGame();
		}
		
		start.getChildren().add(nextButton);
		
		ArrayList<Player> players = model.getPlayers();
		ArrayList<Player> sortedPlayers = new ArrayList<>();
		
		
		controller.findTurnOrder();
		controller.namePlayers();
		
		HBox cardBox = new HBox();
		cardBox.setSpacing(50);
		for(Player p : players) {
			String filename =  "file:main/Tiles/"+ p.turnOrderCard.toString().toLowerCase() +".jpg";
			ImageView image = new ImageView(new Image(filename));
			image.setFitHeight(150);
			image.setFitWidth(100);
			image.setPreserveRatio(true);
			cardBox.getChildren().add(image);
		}
		
		cardBox.setLayoutX(225);
		cardBox.setLayoutY(300);
		
		Collections.sort(players, new Comparator<Player>() {
			public int compare(Player s2, Player s1) {
				return Integer.compare(s1.turnOrderCard.getValue(), s2.turnOrderCard.getValue());
			}
		});
	
		sortedPlayers.addAll(players);
		
		label = new Label("Player " + sortedPlayers.get(0).playerNum + " goes first!");
		
		label.setLayoutX(400);
		label.setLayoutY(250);
		try {
			label.setFont(Font.loadFont(new FileInputStream("main/resources/kenvector_future.ttf"),23));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		start.getChildren().add(cardBox);
		start.getChildren().add(label);
		
		nextButton.setOnAction(e-> {
			currentPlayer = sortedPlayers.get(0);
			GameView(stage);
		});
		
		start.setBackground(new Background(createBackground()));
		Scene drawTurnBoard = new Scene(start,1000,1000);
		stage.setScene(drawTurnBoard);
		stage.setResizable(true);
		stage.setMaximized(true);
		stage.show();
			
	}
	
	

	private void GameView(Stage stage) {

		BorderPane screen = new BorderPane();
		RummikubTimer timer = new RummikubTimer(); 
		RummikubButton endTurn = new RummikubButton("End Turn");
		Pane stand = new Pane();
		//these are the image height/width
		stand.setMaxHeight(178);
		stand.setMaxWidth(700);

		stand.setStyle("-fx-background-color: Transparent; -fx-background-image: url('/resources/playerStand.png');");
//		for(Meld m : model.getMelds()) {
//			
//		}
		
		TextField tileInput = new TextField();
		double i = 65;
		Pane g = new Pane();
		for(Tile t : currentPlayer.getHand()) {
			ImageView tileImage = displayTile(t.toString());
			HBox tile = new HBox();
			tile.getChildren().add(tileImage);
			tile.setLayoutX(i);
			tile.setLayoutY(tile.getLayoutY()+60);
			i += 40;
			g.getChildren().add(tile);
			MouseControlUtil.makeDraggable(tile);
		}
		
		stand.setPadding(new Insets(0,0,100,100));

		g.setPadding(new Insets(10,10,10,10));
		//stand.getChildren().add(g);
		stand.getChildren().add(g);
		
		screen.setLeft(tileInput);

		screen.setBottom(stand);
		screen.setRight(endTurn);
		screen.setTop(timer);
		screen.setBackground(new Background(createBackground()));
		
		endTurn.setOnAction(e  -> {
			if(currentPlayer.hand.size() == 0) {
				WinView(stage, currentPlayer);
			}else {
				nextPlayerTurn();
				GameView(stage);
				e.consume();
			}
		});
		
		Scene display = new Scene(screen,1000,900);
		stage.setScene(display);
		stage.setMaximized(true);
		stage.show();

//		
	}
	
	private void WinView(Stage stage, Player winner) {
		
		BorderPane screen = new BorderPane();
		screen.setBackground(new Background(createBackground()));
		
		Scene display = new Scene(screen,1000,900);
		
		Label label = new Label("Player " + winner.playerNum + " wins!");
		//label.setLayoutX(400);
		//label.setLayoutY(250);
		screen.setCenter(label);
		try {
			label.setFont(Font.loadFont(new FileInputStream("main/resources/kenvector_future.ttf"),23));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//screen.getChildren().add(label);
		
		stage.setScene(display);
		stage.setMaximized(true);
		stage.show();
		
	}

	private BackgroundImage createBackground() {
		Image backgroundImage = new Image("file:main/resources/tableTexture.jpg",1800,1500,true, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.SPACE, BackgroundRepeat.SPACE, BackgroundPosition.CENTER, null );
		return background;
	}

	private ImageView displayTile(String tile) {
		//jokers are a thing
		String filename =  "file:main/Tiles/"+ tile +".jpg";
		ImageView image = new ImageView(new Image(filename));
		image.setFitHeight(51);
		image.setFitWidth(51);
		image.setPreserveRatio(true);
		
		return image;
	}	
	
	private BackgroundImage createStartBackground(Stage stage) {
		Image backgroundImage = new Image("file:main/resources/startPage.jpg",1800,900,true, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.SPACE, BackgroundRepeat.SPACE, BackgroundPosition.CENTER, null );
		return background;
	}
	
	public void nextPlayerTurn() {
		int i = model.getPlayers().indexOf(currentPlayer);
		try {
			currentPlayer = model.getPlayers().get(i+1);
		}catch (IndexOutOfBoundsException e) {
			currentPlayer = model.getPlayers().get(0);
		}
	}


}
