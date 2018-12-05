package core;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.RummikubView;
import javafx.stage.WindowEvent;
import javafx.application.Platform;

public class RummikubApp extends Application{

	@SuppressWarnings("restriction")
	@Override
	public void start(Stage stage) throws Exception{
	
		new RummikubView().buildAndShowGui(stage);
	
		stage.setOnCloseRequest(e -> {
	        Platform.exit();
	        System.exit(0);
	        });
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	 
}