package core;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class AppTest extends Application{

	@Override
	public void start(Stage stage) {
		new ViewClass().buildAndShowGui(stage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}