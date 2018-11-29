package core;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class RummikubView {
	
	private RummikubController controller;
	private RummikubModel model;
	private Pane view;
	

	public RummikubView(RummikubController controller, RummikubModel model) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
		this.model = model;
		
	}
	
	public Parent getView() {
		return view;
	}
	
	public void initview() {
		view = new Pane();
		view.setStyle("-fx-background-color: green");
		
	}

	
}
