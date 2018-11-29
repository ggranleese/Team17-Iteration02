package core;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class RummikubApp extends Application{

	@Override
	public void start(Stage stage) {
		RummikubModel model = new RummikubModel();
		RummikubController controller = new RummikubController(model);
		RummikubView view = new RummikubView(controller, model);
		
		Scene scene = new Scene(view.getView(), 1000,1000);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}