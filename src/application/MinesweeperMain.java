package application;
	
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Cell;
import models.MinesweeperBoard;


public class MinesweeperMain extends Application {
	private MinesweeperBoard board = new MinesweeperBoard(16, 16);
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane mineField = FXMLLoader.load(getClass().getResource("MinesweeperGUI.fxml"));
			GridPane grid = new GridPane();
			grid.gridLinesVisibleProperty().set(true);
			grid.alignmentProperty().set(Pos.CENTER);
			mineField.setCenter(grid);
			for(int i = 0; i < 16; i++){
				for(int j = 0; j < 16; j++){
					Image tile = new Image(new FileInputStream("C:\\opp\\MiniGameArcade\\images\\Tile.png"));
					Label state = new Label();
					state.setGraphic(new ImageView(tile));
					grid.add(state, i, j);
					makeAndAddCell(i, j);
				}	
			}
			Scene scene = new Scene(mineField,400,400);
			primaryStage.setTitle("Minesweeper");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void makeAndAddCell(int i, int j){
		Cell cell = new Cell();
		board.getBoard()[i][j] = cell;
		
	}
	
	public GridPane makeGrid(BorderPane mineField){
		GridPane grid = new GridPane();
		grid.gridLinesVisibleProperty().set(true);
		grid.alignmentProperty().set(Pos.CENTER);
		return grid;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
