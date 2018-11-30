package core;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class RummikubApp extends Application{

	@Override
	public void start(Stage stage) {
		new RummikubView().buildAndShowGui(stage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}