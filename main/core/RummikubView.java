package core;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.imageio.stream.FileImageInputStream;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

public class RummikubView {
	
	private RummikubController controller;
	private RummikubModel model;
	private Pane view;

	

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
		
		
		Button button = new Button("TEST");
		//loadDeck();
		
		
		view.getChildren().addAll(button);

		
	}


	
}
