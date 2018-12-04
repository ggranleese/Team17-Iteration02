package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.util.Observable;

import java.util.Timer;


import javafx.geometry.Pos;
import core.RummikubController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.AI;
import model.Player;
import model.RummikubModel;
import model.Tile;
import javafx.scene.Group;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.TransferMode;
import javafx.scene.input.DragEvent;
import javafx.scene.Node;

import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.collections.*;

import javafx.application.Platform;
import java.util.TimerTask;

import javafx.scene.input.KeyCode;
import org.json.JSONException;


@SuppressWarnings("restriction")
public class RummikubView{

	private RummikubController controller;
	public RummikubModel model;
	public Player currentPlayer;
	public boolean cheatsSelected;
	public Tile tileBeingMoved;
	public Tile[][] boardTracker;
	
	public RummikubView() {
		model = new RummikubModel();
		controller = new RummikubController(model);
		boardTracker = new Tile[14][14];
	}
	
	public void buildAndShowGui(final Stage stage) {
		AnchorPane mainPane = new AnchorPane();
		RummikubButton startButton = new RummikubButton("Play");
		RummikubButton optionsButton = new RummikubButton("Options");
		RummikubButton rigGameFromFile = new RummikubButton("Load File");
		
		
		startButton.setLayoutX(400);
		startButton.setLayoutY(400);
		
		optionsButton.setLayoutX(400);
		optionsButton.setLayoutY(500);
		
		rigGameFromFile.setLayoutX(400);
		rigGameFromFile.setLayoutY(600);
		 
		startButton.setOnAction(e-> drawStartView(stage));
		
		if(controller.model.getPlayers() == null) {
			optionsButton.setOnAction(e-> promptNumPlayers(stage));
		}
		else {
			optionsButton.setOnAction(e -> handleOptionsButtonAction(stage));
		}
		
		rigGameFromFile.setOnAction(e -> {
			try {
				controller.createJSONFile();
			} catch (IOException | JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
		try {
			mainPane.getChildren().addAll(startButton, optionsButton, rigGameFromFile);
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		Scene scene = new Scene(mainPane,1800,900);
		
		
		mainPane.setBackground(new Background(createStartBackground(stage)));
		stage.setScene(scene);
		stage.setResizable(true);
		stage.setTitle("Rummikub");
		stage.show();
	}
	


	private void handleOptionsButtonAction(Stage stage) {
		
		Pane root = new Pane();
		HBox box1 = new HBox(10);
		
		ComboBox<Integer> numPlayer = new ComboBox<Integer>();
		numPlayer.getItems().addAll(2, 3, 4);
		numPlayer.setPromptText("Select Number of players:");
		numPlayer.setLayoutX(150);
		
		Button button1 = new Button("Select");
		
		RadioButton timer = new RadioButton("Timer");
		if(controller.model.getTimer()) {
			timer.setSelected(true);
		}
		
		RadioButton cheats = new RadioButton("Cheats");
		if(this.cheatsSelected) {
			cheats.setSelected(true);
		}
		
		box1.getChildren().add(numPlayer);
		box1.getChildren().add(button1);
		box1.getChildren().add(timer);
		box1.getChildren().add(cheats);
		
		VBox box2 = new VBox(10);
		box2.setLayoutY(50);
		
		for (int x = 0; x < controller.model.getPlayers().size(); x++) {
			Label label;
			ArrayList<Player> players = controller.model.getPlayers();
			Player player = players.get(x);
			int position = x;
			
			if (!player.isBot()) {
				label = new Label("Player " + (x+1));
								
		        ComboBox<String> playerType = new ComboBox<String>();
		        playerType.getSelectionModel().selectFirst();
		        playerType.setPromptText("Type");
		        playerType.getItems().addAll("Human", "AI");
		        playerType.setLayoutX(150);

		        TextField handRig = new TextField("Hand (Optional)");
		        handRig.setStyle("-fx-text-inner-color: grey;");
		        handRig.setOnMouseClicked(e -> {
		        	handRig.clear();
		        	handRig.setStyle("-fx-text-inner-color: black;");
		        });
		        
		        handRig.setOnKeyPressed(e -> {
		        	if (e.getCode() == KeyCode.ENTER) {
		                controller.updatePlayerHand(position , handRig.getText());
		            }
		        });

		        playerType.setOnAction(e-> {
		        	if(playerType.getValue() == "AI") {
		 
		        	controller.updatePlayerTypeBot(players, position);
		        	}
					handleOptionsButtonAction(stage);});
		        
		        box2.getChildren().add(label);
		        box2.getChildren().add(playerType);
		        box2.getChildren().add(handRig);

			}
			if(player.isBot()) {
				label = new Label("Player " + (x+1) + " (BOT)");
				
				ComboBox<String> stratChoice = new ComboBox<String>();
		        stratChoice.getSelectionModel().selectFirst();
		        if(((AI) player).stratNum == 1) {
		        	stratChoice.setPromptText("Strategy 1");
		        }
		        if(((AI) player).stratNum == 2) {
		        	stratChoice.setPromptText("Strategy 2");
		        }
		        if(((AI) player).stratNum == 3) {
		        	stratChoice.setPromptText("Strategy 3");
		        }
		        if(((AI) player).stratNum == 4) {
		        	stratChoice.setPromptText("Strategy 4");
		        }
		      
		        stratChoice.getItems().addAll("Strategy 1", "Strategy 2", "Strategy 3", "Strategy 4");
		        stratChoice.setLayoutX(150);
		        
		        ComboBox<String> playerType = new ComboBox<String>();
		        playerType.getSelectionModel().selectFirst();
		        playerType.setPromptText("Type");
		        playerType.getItems().addAll("Human", "AI");
		        playerType.setLayoutX(150);
		        
		        TextField handRig = new TextField("Hand (Optional)");
		        handRig.setStyle("-fx-text-inner-color: grey;");
		        handRig.setOnMouseClicked(e -> {
		        	handRig.clear();
		        	handRig.setStyle("-fx-text-inner-color: black;");
		        });
		        
		        handRig.setOnKeyPressed(e -> {
		        	if (e.getCode() == KeyCode.ENTER) {
		                controller.updatePlayerHand(position , handRig.getText());
		            }
		        });

		   
		        playerType.setOnAction(e-> {
		        	if(playerType.getValue() == "Human") {
		        		controller.updatePlayerTypeHuman(players, position);
		        	}
					handleOptionsButtonAction(stage);});
		        
		        stratChoice.setOnAction(e-> {
		        	controller.updatePlayerStrat(players, position, stratChoice.getValue());	
				});
		        
		        box2.getChildren().add(label);
		        box2.getChildren().add(playerType);
		        box2.getChildren().add(stratChoice);
		        box2.getChildren().add(handRig);
			}
			
		}
		Button confirmButton = new Button("Back");
		box2.getChildren().add(confirmButton);
		
		root.getChildren().addAll(box1);
		root.getChildren().addAll(box2);
		
		
        Scene scene = new Scene(root, 1000, 1000);
		stage.setScene(scene);
		stage.setTitle("Rummikub");
		stage.show();

		button1.setOnAction(e-> {
			controller.updatePlayers((int) (numPlayer.getValue()));
			handleOptionsButtonAction(stage);});
		
		confirmButton.setOnAction(e -> {buildAndShowGui(stage);});
		
		timer.setOnAction(e -> controller.updateTimer());
		
		cheats.setOnAction(e -> {
			if(this.cheatsSelected) {
				cheatsSelected = false;
			}
			else
				cheatsSelected = true;
		});

	}
	

	private void promptNumPlayers(Stage stage) {
		HBox box = new HBox(10);
		
		ComboBox<Integer> numPlayers = new ComboBox<Integer>();
		numPlayers.getSelectionModel().selectFirst();
		numPlayers.getItems().addAll(2, 3, 4);
		numPlayers.setPromptText("Select Number of players:");
		numPlayers.setLayoutX(150);
		
		Button button1 = new Button("Select");
		
		box.getChildren().add(numPlayers);
		box.getChildren().add(button1);
		
        Scene scene = new Scene(box, 1000, 1000);
		stage.setScene(scene);
		stage.setTitle("Rummikub");
		stage.show();
		
		
		button1.setOnAction(e-> {
			controller.updatePlayers((int) (numPlayers.getValue()));
			handleOptionsButtonAction(stage);});
	}


	private void handleStartButton(Stage stage) {
		//drawStartView(stage);
		controller.namePlayers();
		GameView(stage);
	}

	
	private void drawStartView(Stage stage) {
		
		Pane start = new Pane();
		//HBox box = new HBox(10);
		RummikubButton nextButton = new RummikubButton("Next");
		Label label;
		
		nextButton.setLayoutX(400);
		nextButton.setLayoutY(500);
		
		if(model.getPlayers() == null) {
			controller.setDefaultGame();
		}
		
		start.getChildren().add(nextButton);
		
		ArrayList<Player> players = controller.model.getPlayers();
		ArrayList<Player> sortedPlayers = new ArrayList<>();
		
		
		controller.namePlayers();
		controller.findTurnOrder();
	
		
		HBox cardBox = new HBox();
		cardBox.setSpacing(50);
		for(Player p : players) {
			String filename =  "file:main/Large Tiles/"+ p.turnOrderCard.toString().toLowerCase() +".jpg";
			ImageView image = new ImageView(new Image(filename));
			image.setFitHeight(150);
			image.setFitWidth(100);
			image.setPreserveRatio(true);
			cardBox.getChildren().add(image);
		}
		
		cardBox.setLayoutX(225);
		cardBox.setLayoutY(300);
		
		sortedPlayers.addAll(players);
		Collections.sort(sortedPlayers, new Comparator<Player>() {
			public int compare(Player s2, Player s1) {
				return Integer.compare(s1.turnOrderCard.getValue(), s2.turnOrderCard.getValue());
			}
		});
		
		label = new Label("Player " + sortedPlayers.get(0).playerNum + " goes first!");
		
		label.setLayoutX(400);
		label.setLayoutY(250);
		try {
			label.setFont(Font.loadFont(new FileInputStream("main/resources/kenvector_future.ttf"),23));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		start.getChildren().add(cardBox);
		start.getChildren().add(label);
		
		nextButton.setOnAction(e-> {
			currentPlayer = sortedPlayers.get(0);
			GameView(stage);
		});
		//test
		start.setBackground(new Background(createBackground()));
		Scene drawTurnBoard = new Scene(start,1000,1000);
		stage.setScene(drawTurnBoard);
		stage.setResizable(true);
		stage.show();
			 
	}
	
	

	private Scene GameView(Stage stage) {
		
		BorderPane screen = new BorderPane();
		RummikubTimer timer = new RummikubTimer(); 
		RummikubButton endTurn = new RummikubButton("End Turn");

	    GridPane stand = new GridPane();
		GridPane board = new GridPane();
	
		Image gameBoard = new Image("file:main/resources/gameBoard.jpg",850,850,true,true);
		BackgroundImage boardBackground = new BackgroundImage(gameBoard, BackgroundRepeat.SPACE, BackgroundRepeat.SPACE, BackgroundPosition.CENTER, null);
		board.setBackground(new Background(boardBackground));;		
		 Timer yes = new Timer();
		    yes.scheduleAtFixedRate(new TimerTask() {
		        @Override
		        public void run() {
		            Platform.runLater(() -> {
		            	if(currentPlayer.hand.size() == 0) {
		    				WinView(stage, currentPlayer);
		    			}else {
		    				nextPlayerTurn();
		    				stage.setScene(GameView(stage));
		    				yes.cancel();
		    			}
		            });
		        }
		    }, 120000, 120000);
		
		//these are the image height/width
		stand.setMaxHeight(178);
		stand.setMaxWidth(700);
	
		board.setAlignment(Pos.CENTER);
		
		for(int i = 0; i<14; i ++) {
			for(int j = 0 ; j < 11; j++) {
				HBox hbox = new HBox();
				hbox.setMinSize(33, 50);
				board.add(hbox,i,j);
			}
		}
		
	
		for(int x = 0; x < controller.model.getMelds().size(); x++) {
			for(int y =0; y < controller.model.getMelds().get(x).getTiles().size(); y++) {
				
				HBox test = (HBox) getNode(x,y,board);
				String tile = controller.model.getMelds().get(x).getTiles().get(y).toString();
				String filename =  "file:main/Tiles/"+ tile +".jpg";
				ImageView image = new ImageView(new Image(filename));
				test.getChildren().add(image);
				boardTracker[x][y] = controller.model.getMelds().get(x).getTiles().get(y);
				
				image.setOnDragDetected(new EventHandler<MouseEvent>() {
			        public void handle(MouseEvent event) {
			        	Dragboard db = image.startDragAndDrop(TransferMode.ANY);

			        	ClipboardContent cbContent = new ClipboardContent();
				        cbContent.putImage(image.getImage());
				           
				        db.setDragView(image.getImage());
				        db.setContent(cbContent);
				        image.setVisible(false);
			        	
			            Node source = (Node)event.getTarget();
			            Integer colIndex = GridPane.getColumnIndex(test);
			            Integer rowIndex = GridPane.getRowIndex(test);
			           
			        	tileBeingMoved = boardTracker[rowIndex][colIndex];
			        	System.out.println(boardTracker[rowIndex][colIndex]);
			        	boardTracker[rowIndex][colIndex] = null;
			            System.out.println(tileBeingMoved + " being moved from cell: " + colIndex + "," + rowIndex);

			            event.consume();
			        }
			    });
				

			}
		}
		
		stand.setStyle("-fx-background-color: Transparent; -fx-background-image: url('/resources/playerStand.png');");
//		for(Meld m : controller.model.getMelds()) {
//			
//		}
		
		TextField tileInput = new TextField();
		
		GridPane Stand = new GridPane();
		
		//these are the image height/width
		Stand.setMaxHeight(178);
		Stand.setMaxWidth(700);
		
		
		Stand.setStyle("-fx-background-color: transparent; -fx-background-image: url('/resources/playerStand.png');");
		
		//Padding for stand
		Pane springV = new Pane();
		springV.setMinHeight(55);
		Stand.add(springV,0,0);
		
		Pane springH = new Pane();
		springH.setMinWidth(70);
		Stand.add(springH, 0, 1);
		
		Stand.setMinSize(700, 175);
		
		ArrayList<ImageView> tiles = new ArrayList<ImageView>();
		int num = 0;
		for(Tile t : currentPlayer.getHand()){
			ImageView tileImage = displayTile(t.toString());
            tiles.add(tileImage);
            tiles.get(num).setPreserveRatio(true);
            tiles.get(num).setFitHeight(49);
            tiles.get(num).setFitWidth(49);
            Stand.add(tiles.get(num), num+1, 1);
            num++;
			}
		ArrayList<Tile> removeThese = new ArrayList<Tile>();
		for (ImageView tileImage: tiles) {
			tileImage.setOnDragDetected(new EventHandler<MouseEvent>() {
		        public void handle(MouseEvent event) {
		 
		        	int tileNumber = board.getColumnIndex(tileImage) - 1;
		        	//this causes bug
		        	tileBeingMoved = currentPlayer.getHand().get(tileNumber);
		        	System.out.println("from hand: " + tileBeingMoved.toString());
		        	//remove this tile from player hand
		        	//currentPlayer.getHand().remove(board.getColumnIndex(tileImage) - 1);
		        	//adds all handled tiles to a list to remove later
		        	removeThese.add(currentPlayer.getHand().get(board.getColumnIndex(tileImage) - 1));
		        	
		        	ImageView highlightedTile = highlightTile(tileBeingMoved.toString());
		        	
		        	Stand.add( highlightedTile, board.getColumnIndex(tileImage), board.getRowIndex(tileImage));
		        	
		            Dragboard db = highlightedTile.startDragAndDrop(TransferMode.ANY);

		            ClipboardContent cbContent = new ClipboardContent();
		            cbContent.putImage(highlightedTile.getImage());
		           
		            db.setDragView(highlightedTile.getImage());
		            db.setContent(cbContent);
		            tileImage.setVisible(false);
		            event.consume();
		        }
		    });
				
			board.setOnDragOver(new EventHandler<DragEvent>() {
	 			public void handle(DragEvent event) {
	            if(event.getGestureSource() != board && event.getDragboard().hasImage()){
	                event.acceptTransferModes(TransferMode.MOVE);
	            }
	            event.consume();
	        }
	 		});
	 		
			board.setOnDragEntered(new EventHandler<DragEvent>() {
		        public void handle(DragEvent event) {
		            if(event.getGestureSource() != board && event.getDragboard().hasImage()){
		            	//tileImage.setVisible(false);
		            }
		            event.consume();
		        }
		    });
			 board.setOnDragExited(new EventHandler<DragEvent>() {
			        public void handle(DragEvent event) {
			            //mouse moved away, remove graphical cues
			        	//tileImage.setVisible(true);
			            screen.setOpacity(1);

			            event.consume();
			        }
			    });
			 
			 board.setOnDragDropped(new EventHandler<DragEvent>() {
			        public void handle(DragEvent event) {
			        	
			            Node source = event.getPickResult().getIntersectedNode();
			            
			            Integer colIndex = GridPane.getColumnIndex(source);
			            Integer rowIndex = GridPane.getRowIndex(source);
			            System.out.println( tileBeingMoved.toString() + " dropped in cell: " + colIndex + "," + rowIndex);
			            boardTracker[board.getRowIndex(source)][board.getColumnIndex(source)] = tileBeingMoved;
			            
			            Dragboard db = event.getDragboard();
			            boolean success = false;
			            if(source != board && db.hasImage()){

			                Integer cIndex = GridPane.getColumnIndex(source);
			                Integer rIndex = GridPane.getRowIndex(source);
			                int x = cIndex == null ? 0 : cIndex;
			                int y = rIndex == null ? 0 : rIndex;
			         
			                
			                ImageView image = new ImageView(db.getImage());
			                
			                image.setOnDragDetected(new EventHandler<MouseEvent>() {
			    		        public void handle(MouseEvent event) {
			    		        	System.out.println("from board: " + board.getColumnIndex(image)+ "," +board.getRowIndex(image));
			    		        	tileBeingMoved = boardTracker[board.getRowIndex(image)][board.getColumnIndex(image)];
			    		        	boardTracker[rIndex][cIndex] = null;
			    		        	System.out.println("Just removed " + board.getColumnIndex(image) + "," + board.getRowIndex(image) + " from Tracker");
			    		        	
			    		            Dragboard db = image.startDragAndDrop(TransferMode.ANY);

			    		            ClipboardContent cbContent = new ClipboardContent();
			    		            cbContent.putImage(image.getImage());
			    		           
			    		            db.setDragView(image.getImage());
			    		            db.setContent(cbContent);
			    		            image.setVisible(false);
			    		            event.consume();
			    		        }
			    		    });
			                
			                board.add(image, x, y, 1, 1);
			                success = true;
			            }
			            //let the source know whether the image was successfully transferred and used
			            event.setDropCompleted(success);

			            event.consume();
			        }
			    });
			 
			 tileImage.setOnDragDone(new EventHandler<DragEvent>() {
			        public void handle(DragEvent event) {
			            //the drag ended
			            //if the data was successfully moved, clear it
			            if(event.getTransferMode() == TransferMode.MOVE){
			                tileImage.setVisible(false);
			            }
			            event.consume();
			        }
			    });
        

		}
		
		screen.setPadding(new Insets(10,10,10,10));
		
		screen.setTop(timer);
		//screen.add(tileInput,1,1);
		
		screen.setCenter(board);
		screen.setRight(endTurn);
		
		screen.setBottom(Stand);
		GridPane.setColumnSpan(Stand, GridPane.REMAINING);
		GridPane.setRowSpan(Stand, GridPane.REMAINING);
		
		screen.setBackground(new Background(createBackground()));
		
		endTurn.setOnAction(e  -> {
			for (Tile t : removeThese) {
				currentPlayer.getHand().remove(t);
			}
			
			if(currentPlayer.hand.size() == 0) {
				WinView(stage, currentPlayer);
			}else {
					controller.clearMelds();
					for(int i = 0 ; i < 14; i++) {
						System.out.println("Melds on row " + i);
						ArrayList<Tile> meld = new ArrayList<Tile>();
						for(int j = 0 ; j < 11; j++) {
							if(boardTracker[i][j] != null) {
								meld.add(boardTracker[i][j]);
								boardTracker[i][j] = null;
							}
						}
						System.out.println(meld);
						if(meld.size() != 0) {
							controller.addMeld(meld);
						}
				}
				
				//find point difference from memento
				nextPlayerTurn();
				GameView(stage);
				yes.cancel();
			}
		});
		
		Scene display = new Scene(screen,1000,900);
		stage.setScene(display);
		stage.show();
		return display;
		
		}
	
	public Node getNode (final int row, final int column, GridPane gridPane){
		Node result = null;
		ObservableList<Node> childrens = gridPane.getChildren();
		
		for(Node node:childrens) {
			if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
				result = node;
				break;
						
			}
		}
		
		return result;
	}


	private void WinView(Stage stage, Player winner) {
		
		BorderPane screen = new BorderPane();
		screen.setBackground(new Background(createBackground()));
		
		Scene display = new Scene(screen,1000,900);
		
		Label label = new Label("Player " + winner.playerNum + " wins!");
		//label.setLayoutX(400);
		//label.setLayoutY(250);
		screen.setCenter(label);
		try {
			label.setFont(Font.loadFont(new FileInputStream("main/resources/kenvector_future.ttf"),23));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//screen.getChildren().add(label);
		
		stage.setScene(display);
		stage.show();
		
	}

	private BackgroundImage createBackground() {
		Image backgroundImage = new Image("file:main/resources/tableTexture.jpg",1800,1500,true, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.SPACE, BackgroundRepeat.SPACE, BackgroundPosition.CENTER, null );
		return background;
	}

	private ImageView displayTile(String tile) {
		//jokers are a thing
		String filename =  "file:main/Tiles/"+ tile +".jpg";
		ImageView image = new ImageView(new Image(filename));
		image.setFitHeight(51);
		image.setFitWidth(51);
		image.setPreserveRatio(true);
		
		return image;
	}	
	
	private ImageView highlightTile(String string) {
		String filename =  "file:main/highlightedTiles/"+ string.toLowerCase() +".jpg";
		ImageView image = new ImageView(new Image(filename));
		image.setFitHeight(51);
		image.setFitWidth(51);
		image.setPreserveRatio(true);
		
		return image;
	}
	
	private BackgroundImage createStartBackground(Stage stage) {
		Image backgroundImage = new Image("file:main/resources/startPage.jpg",1800,900,true, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.SPACE, BackgroundRepeat.SPACE, BackgroundPosition.CENTER, null );
		return background;
	}
	
	public void nextPlayerTurn() {
		int i = model.getPlayers().indexOf(currentPlayer);
		try {
			//this.controller.drawTile(currentPlayer);
			currentPlayer = controller.model.getPlayers().get(i+1);
		}catch (IndexOutOfBoundsException e) {
			currentPlayer = controller.model.getPlayers().get(0);
		}
	}


}
