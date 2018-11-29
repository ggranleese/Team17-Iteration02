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
	
	public void buildAndShowGui(final Stage stage) {
		Button button1 = new Button("Play");
		Button button2 = new Button("Options");
		
		button1.setOnAction(e-> handleStartButton(stage));
		button2.setOnAction(e-> promptNumPlayers(stage));
		
		VBox box = new VBox(button1, button2);
		Scene scene = new Scene(box, 1000, 1000);
		
		box.setSpacing(10);
		
		stage.setScene(scene);
		stage.setTitle("Rummikub");
		stage.show();
	}
	

	private void handleOptionsButtonAction(Stage stage, int numPlayers) {
		
		Pane root = new Pane();
		HBox box1 = new HBox(10);
		
		ComboBox numPlayer = new ComboBox<String>();
		numPlayer.getSelectionModel().selectFirst();
		numPlayer.getItems().addAll("2", "3", "4");
		numPlayer.setPromptText("Select Number of players:");
		numPlayer.setLayoutX(150);
		
		Button button1 = new Button("Select");
		
		box1.getChildren().add(numPlayer);
		box1.getChildren().add(button1);
		
		VBox box2 = new VBox(10);
		box2.setLayoutY(50);
		
		for (int x = 0; x < numPlayers; x++) {
	        ComboBox<String> combo = new ComboBox<String>();
	        combo.getSelectionModel().selectFirst();
	        combo.setPromptText("Strategy");
	        combo.getItems().addAll("Strategy 1", "Strategy 2", "Strategy 3", "Strategy 4");
	        combo.setLayoutX(150);
	
	        Label label = new Label("Player " + (x + 1));
	
	        box2.getChildren().add(label);
	        box2.getChildren().add(combo);
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
		
		button1.setOnAction(e-> handleOptionsButtonAction(stage, numBots));

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
		
		int numBots;
		if(numPlayers.getValue() == "1") {
			numBots = 1;
		}else if(numPlayers.getValue() == "2") {
			numBots = 2;
		}else{
			numBots = 3;
		}

		button1.setOnAction(e-> handleOptionsButtonAction(stage, numBots));
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
