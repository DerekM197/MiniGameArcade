package application;

import java.io.IOException;

import enums.Colors;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MasterMindMain extends Application {

	private int colSize;
	private int rowSize;
	private static int answerSize;
	
	public MasterMindMain(int colSize, int rowSize) {
		setColSize(colSize);
		setRowSize(rowSize);
		setAnswerSize(colSize);
	}

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

	public int getAnswerSize() {
		return answerSize;
	}

	public void setAnswerSize(int answerSize) {
		this.answerSize = answerSize;
	}

	public BorderPane createContent() throws IOException {
		BorderPane screen = FXMLLoader.load(getClass().getResource("MasterMindMainGUI.fxml"));
		GridPane board = new GridPane();
		GridPane rightOrWrong = new GridPane();
		board.gridLinesVisibleProperty().set(true);

		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				Rectangle cell = new Rectangle();
				cell.setHeight(30);
				cell.setWidth(30);
				cell.fillProperty().set(Paint.valueOf("WHITE"));
				cell.setStroke(Paint.valueOf("BLACK"));
				board.add(cell, col, row);
			}

		}
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < 2; col++) {
				Rectangle cell = new Rectangle();
				cell.setHeight(30);
				cell.setWidth(30);
				cell.fillProperty().set(Paint.valueOf("WHITE"));
				cell.setStroke(Paint.valueOf("BLACK"));
				rightOrWrong.add(cell, col, row);
			}
		}
		board.setAlignment(Pos.CENTER);
		rightOrWrong.setAlignment(Pos.CENTER_LEFT);
		screen.setRight(rightOrWrong);
		BorderPane.setMargin(rightOrWrong, new Insets(0, 100, 0, 0));
		screen.setCenter(board);
		return screen;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Scene scene = new Scene(createContent(), 600, 600);
		primaryStage.setTitle("Mastermind");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {

		launch(args);
	}

}
