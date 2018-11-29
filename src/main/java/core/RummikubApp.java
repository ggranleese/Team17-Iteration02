package core;

import java.awt.Image;
import java.io.File;
import java.io.FilenameFilter;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RummikubApp extends Application{


	@SuppressWarnings("restriction")
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		initView(stage);
	}
	
	@SuppressWarnings("restriction")
	private void initView(Stage stage) {
		Pane canvas = new Pane();
		
		canvas.setStyle("-fx-background-color: green");

		Scene scene = new Scene(canvas, 1000,1000);
		stage.setScene(scene);
		stage.setTitle("RUMMIKUB");
		stage.show();
	}
	
	private void startTiles(Pane canvas) {
		Image img = null;
		File tiles = new File("src/main/resources/core/Tiles/");
		FilenameFilter tileFilter = new FilenameFilter() {
			
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.toLowerCase().endsWith("jpg");
			}
			
		};
		
		File[] tilesFile = tiles.listFiles(tileFilter);
		tilesImage = new Image[tilesFile.length];
	}


}