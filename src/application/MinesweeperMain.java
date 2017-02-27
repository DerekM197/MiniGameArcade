package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Cell;
import models.MinesweeperBoard;

public class MinesweeperMain extends Application {

	private MinesweeperBoard board = new MinesweeperBoard(16, 16);
	private Scene scene;

	public void setSene(Scene value) {
		scene = value;
	}

	public BorderPane createContent() {
		BorderPane mineField = null;
		try {
			mineField = FXMLLoader.load(getClass().getResource("MinesweeperGUI.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		VBox menutab = new VBox();
		Button backToMenu = new Button("Back to main menu");
		Button newGame = new Button("New Game");
		newGame.setOnMousePressed(e -> scene.setRoot(createContent()));
		menutab.getChildren().addAll(backToMenu, newGame);
		menutab.alignmentProperty().set(Pos.CENTER);
		menutab.spacingProperty().set(10);
		GridPane grid = new GridPane();
		grid.gridLinesVisibleProperty().set(true);
		grid.alignmentProperty().set(Pos.CENTER);
		mineField.setCenter(grid);
		mineField.setTop(menutab);
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				Label state = new Label();
				state.setGraphic(new ImageView(new Image("file:images/Tile.png")));
				grid.add(state, j, i);
				Cell cell = new Cell();
				board.getBoard()[i][j] = cell;
				board.getBoard()[i][j].setState(state);
				state.setOnMousePressed(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent t) {
						if (t.isPrimaryButtonDown()) {
							cell.leftMouseClicked(t, state, board);
						} else if (t.isSecondaryButtonDown()) {
							cell.rightMouseClicked(t, state);
						}
					}
				});

			}
		}
		board.settingMines();
		board.settingNeighbors();
//		for (Cell[] cell : board.getBoard()) {
//			for (Cell c : cell) {
//				System.out.print(c.getNeighbors().size());
//			}
//			System.out.println("\n");
//		}
		return mineField;
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


	public GridPane makeGrid(BorderPane mineField) {
		GridPane grid = new GridPane();
		grid.gridLinesVisibleProperty().set(true);
		grid.alignmentProperty().set(Pos.CENTER);
		return grid;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
