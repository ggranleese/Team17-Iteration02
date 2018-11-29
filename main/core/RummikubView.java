package core;



import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.imageio.stream.FileImageInputStream;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RummikubView {
	
	private RummikubController controller;
	private RummikubModel model;
	private Pane view;
	private ComboBox <String> numPlayers;
	VBox box = new VBox();
	Scene scene = new Scene(box);
	

	public RummikubView(RummikubController controller, RummikubModel model) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
		this.model = model;

		initView();
		
	}
	
	public Parent getView() {
		return view;
	}
	
	@SuppressWarnings("restriction")
	public void initView() {
		view = new Pane();
		view.setStyle("-fx-background-color: green");
		
		ImageView image = new ImageView(new Image("/Tiles/b1.jpg"));
		
		Button startButton = new Button("START");
		startButton.setLayoutX(5);
		
		Button optionsButton = new Button("OPTIONS");
		optionsButton.setLayoutX(75);
		
		ComboBox numPlayers = new ComboBox<String>();
		numPlayers.getSelectionModel().selectFirst();
		numPlayers.getItems().addAll("1", "2", "3", "4");
		numPlayers.setPromptText("Select Number of players:");
		numPlayers.setLayoutX(150);
		
		numPlayers.setOnAction(e -> this.controller.getNumBots(numPlayers.getValue()));
		
		optionsButton.setOnAction(e -> optionsMenu());
		
		view.getChildren().addAll(startButton, optionsButton ,numPlayers);
		
	}
	
	public void optionsMenu() {
		view = new Pane();
		view.setStyle("-fx-background-color: orange");
		
		ImageView image = new ImageView(new Image("/Tiles/b1.jpg"));
		
		Button startButton = new Button("START");
		startButton.setLayoutX(5);
		
		Button optionsButton = new Button("OPTIONS");
		optionsButton.setLayoutX(75);
		
		ComboBox numPlayers = new ComboBox<String>();
		numPlayers.getSelectionModel().selectFirst();
		numPlayers.getItems().addAll("1", "2", "3", "4");
		numPlayers.setPromptText("Select Number of players:");
		numPlayers.setLayoutX(150);
		
		numPlayers.setOnAction(e -> this.controller.getNumBots(numPlayers.getValue()));
		
		optionsButton.setOnAction(e -> optionsMenu());
		
		view.getChildren().addAll(optionsButton ,numPlayers);

	}
	
	public void gameBoard() {
		view = new Pane();
		view.
	}

	
}
