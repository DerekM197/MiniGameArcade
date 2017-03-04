package application;

import java.io.IOException;

import enums.Colors;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MasterMindMain extends Application {

	private int colSize = 4;
	private int rowSize = 10;
	private int answerSize;

	// public MasterMindMain(int colSize, int rowSize){
	// setColSize(colSize);
	// setRowSize(rowSize);
	// setAnswerSize(colSize);
	// }

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
				TextField cell = new TextField();
				cell.setPrefWidth(30);
				cell.setPrefHeight(30);
				board.add(cell, col, row);
				Label lab = new Label();
				lab.setStyle(value);
			}
		}
		for(int row = 0; row < rowSize; row++){
			for(int col = 0; col < 2; col++){
				
			}
		}
		board.setAlignment(Pos.CENTER);
		screen.setCenter(board);
		return screen;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Scene scene = new Scene(createContent());
		primaryStage.setFullScreen(true);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {

		launch(args);
	}

}
