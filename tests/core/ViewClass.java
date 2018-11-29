package core;


import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;

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

public class ViewClass {
	private Pane view;
	private RummikubController controller;
	private RummikubModel model;
	
	public ViewClass() {
		model = new RummikubModel();
		controller = new RummikubController(model);
	}
	
	public void buildAndShowGui(final Stage stage) {
		Button button1 = new Button("Play");
		Button button2 = new Button("Options");
		
		button1.setOnAction(e-> handleStartButton(stage));
		if(model.getPlayers() == null) {
			button2.setOnAction(e-> promptNumPlayers(stage));
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
		
		ComboBox numPlayer = new ComboBox<String>();
		//numPlayer.getSelectionModel().selectFirst();
		numPlayer.getItems().addAll("2", "3", "4");
		numPlayer.setPromptText("Select Number of players:");
		numPlayer.setLayoutX(150);
		numPlayer.setValue(1);
		System.out.println(numPlayer.getValue());
		
		Button button1 = new Button("Select");
		
		box1.getChildren().add(numPlayer);
		box1.getChildren().add(button1);
		
		VBox box2 = new VBox(10);
		box2.setLayoutY(50);
		
		System.out.println(model.getPlayers().size());
		
		for (int x = 0; x < model.getPlayers().size(); x++) {
			
			Label label = new Label("Player " + (x + 1));
	        ComboBox<String> stratChoice = new ComboBox<String>();
	        stratChoice.getSelectionModel().selectFirst();
	        stratChoice.setPromptText("Strategy");
	        stratChoice.getItems().addAll("Strategy 1", "Strategy 2", "Strategy 3", "Strategy 4");
	        stratChoice.setLayoutX(150);
	
	        ComboBox<String> playerType = new ComboBox<String>();
	        playerType.getSelectionModel().selectFirst();
	        playerType.setPromptText("Type");
	        playerType.getItems().addAll("Human", "AI");
	        playerType.setLayoutX(150);
	
	        box2.getChildren().add(label);
	        box2.getChildren().add(playerType);
	        box2.getChildren().add(stratChoice);
		}
		
		root.getChildren().addAll(box1);
		root.getChildren().addAll(box2);
		
        Scene scene = new Scene(root, 1000, 1000);
		stage.setScene(scene);
		stage.setTitle("Rummikub");
		stage.show();
		
		
		int numBots;
		if(numPlayer.getValue() == "1") {
			numBots = 1;
		}else if(numPlayer.getValue() == "2") {
			numBots = 2;
		}else{
			System.out.println(numPlayer.getValue());
			numBots = 3;
		}
		
<<<<<<< HEAD
		button1.setOnAction(e-> {
			controller.updatePlayers(4);
			handleOptionsButtonAction(stage);});
		//playerType.setOnAction(e-> handlePlayerTypeAction(e));
=======
		button1.setOnAction(e-> handleOptionsButtonAction(stage, numBots));

>>>>>>> 3ccd1f9e9a9ad6e236458c77d117b186e5c3f2ea
	}
	

	private void promptNumPlayers(Stage stage) {
		HBox box = new HBox(10);
		
		ComboBox numPlayers = new ComboBox<String>();
		numPlayers.getSelectionModel().selectFirst();
		numPlayers.getItems().addAll("2", "3", "4");
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
			controller.updatePlayers(4);
			handleOptionsButtonAction(stage);});
	}


	private void handleStartButton(Stage stage) {
		GameView(stage);
	}


	private void GameView(Stage stage) {
		// TODO Auto-generated method stub

		for(int i = 0; i<this.model.getTable().getMelds(); i++) {
			for(int j=0; j<this.model.getTable().getMelds()[i]; j++) {
				
				try {
					displayTile(this.model.getTable().getMelds()[i][j].toString());
				}catch(FileNotFoundException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
	private ImageView displayTile(String tile) {
		
		ImageView image = new ImageView(new Image("/Tiles/" + tile.toLowerCase() + ".jpg"));
		image.setFitHeight(50);
		image.setFitWidth(50);
		image.setPreserveRatio(true);
		
		return image;
	}

	

}
