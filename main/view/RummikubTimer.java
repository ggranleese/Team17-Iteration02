package view;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.*;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.KeyFrame;

public class RummikubTimer extends SubScene{
	private final Integer starttime= 120;
	private Integer seconds= starttime;
	private Label lb;
	private final String FONT_PATH= "main/resources/kenvector_future.ttf";
	private final static String BACKGROUND_IMAGE = "main/resources/blue_button00.png";
	public RummikubTimer() {
		super(new AnchorPane(), 50, 50);
		prefWidth(50);
		prefHeight(50);
		lb = new Label();
		try {
			lb.setFont(Font.loadFont(new FileInputStream(FONT_PATH),23));
			}catch (FileNotFoundException e) {
				lb.setFont(Font.font("Veranda",23));
			}
		lb.setText("120");
		doTime();
		HBox layout= new HBox(5);
	    layout.getChildren().add(lb);
	    AnchorPane root2 = (AnchorPane) this.getRoot();
	    root2.getChildren().add(layout);
	}
	private void doTime() {
		Timeline time= new Timeline();
		  
		  KeyFrame frame= new KeyFrame(Duration.seconds(1.0), new EventHandler<ActionEvent>(){

		   public void handle(ActionEvent event) {
		    seconds--;
		    lb.setText(seconds.toString());
		    if(seconds<=0){
		     time.stop();
		    }
		   }
		   
		  });
		  
		  time.setCycleCount(Timeline.INDEFINITE);
		  time.getKeyFrames().add(frame);
		  if(time!=null){
		   time.stop();
		  }
		  time.play();
		  
		  
		
	}

}
