package application;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Cell;
import models.MinesweeperBoard;

public class MinesweeperMain extends Application {	
	private int colSize;
	private int rowSize;
	private MinesweeperBoard board = new MinesweeperBoard(16, 16);
	private Scene scene;
//	private TextField timer = new TextField();
	private GridPane grid;
//	private Timer ti;
//	private int time;
	public int getColSize() {
		return colSize;
	}



	public void setColSize(int colSize) {
		this.colSize = colSize;
	}



	public int getRowSize() {
		return rowSize;
	}



	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}

	public void setSene(Scene value) {
		scene = value;
	}
	
	

	public BorderPane createContent() {
//		time = 0;
		board.setNumOfUnopend();
		board.setNumOfBombs(board.getNumOfUnopend());
		BorderPane mineField = null;
		try {
			mineField = FXMLLoader.load(getClass().getResource("MinesweeperGUI.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		VBox menutab = new VBox();
		Button backToMenu = new Button("Back to main menu");
		Button newGame = new Button("New Game");
		newGame.setOnMousePressed( e -> purgeBoardAndTimer());
		menutab.getChildren().addAll(backToMenu, newGame);
		menutab.alignmentProperty().set(Pos.CENTER);
		menutab.spacingProperty().set(10);
		grid = new GridPane();
		grid.gridLinesVisibleProperty().set(true);
		grid.alignmentProperty().set(Pos.CENTER);
		mineField.setTop(menutab);
		
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				Label state = new Label();
				state.setGraphic(new ImageView(new Image("file:images/Tile.png")));
				grid.add(state, j, i);
				Cell cell = new Cell();
				board.getBoard()[i][j] = cell;
				board.getBoard()[i][j].setState(state);
				state.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent t) {
						if (t.getButton() == MouseButton.PRIMARY) {
							cell.leftMouseClicked(t, state, board);
						} else if (t.getButton() == MouseButton.SECONDARY) {
							cell.rightMouseClicked(t, state);
						}else if(t.getButton() == MouseButton.PRIMARY && t.getButton() == MouseButton.SECONDARY){
							cell.both();
						}
					}
				});

			}
		}
		mineField.setCenter(grid);
//		ti = new Timer();
//		TimerTask task = new TimerTask()
//		{
//			@Override
//			public void run() { 
//				++time;
//				
//				timer.setText("Time in seconds: "+time);
//			
//			}
//		};
//		ti.scheduleAtFixedRate(task, 1000, 1000);
//		mineField.setBottom(timer);
		board.settingMines();
		board.settingNeighbors();
		return mineField;
	}

	public void purgeBoardAndTimer(){
//		ti.cancel();
//		ti = null;
		grid = null;
		scene.setRoot(createContent());
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			scene = new Scene(createContent(), 600, 600);
			primaryStage.setTitle("Minesweeper");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	

	public static void main(String[] args) {
		launch(args);
	}
}
