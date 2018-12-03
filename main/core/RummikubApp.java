package core;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.RummikubView;


public class RummikubApp extends Application{

	@SuppressWarnings("restriction")
	@Override
	public void start(Stage stage) throws Exception{
	
		new RummikubView().buildAndShowGui(stage);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	 
}