package application;

import java.io.IOException;

import enums.Colors;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import models.MastermindBoard;
import models.MastermindPiece;

public class MasterMindMain extends Application {

	private int colSize;
	private int rowSize;
	private static Stage stage;
	private ObservableList<Colors> colors = FXCollections.observableArrayList(Colors.values());
	private static MastermindBoard mastermindBoard;
	private static BorderPane screen;
	private Label[][] labels  = new Label[10][2];
	
	public MasterMindMain(int colSize, int rowSize) {
		setColSize(colSize);
		setRowSize(rowSize);
		mastermindBoard = new MastermindBoard(rowSize, colSize);
		mastermindBoard.setAnswerSize(colSize);
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

	public BorderPane createContent() throws IOException {
		screen = FXMLLoader.load(getClass().getResource("MasterMindMainGUI.fxml"));
		GridPane board = new GridPane();
		GridPane rightOrWrong = new GridPane();
		VBox control = new VBox();
		ChoiceBox<Colors> box = new ChoiceBox<Colors>(colors);
		box.setValue(Colors.BLUE);
		Button checkGuess = new Button("Check Guess");
		Button backToMain = new Button("Back To Main Menu");
		backToMain.setOnAction(e -> {
			MainMenu mainMenu = new MainMenu();
			try {
				mainMenu.start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			});
		checkGuess.setOnAction(e -> {
			int numberRight = mastermindBoard.checkCurrentRow()[0];
			if(didWin(numberRight)){
				displayWin();
			}else if(mastermindBoard.getCurrentRow() == 10 && !didWin(numberRight)){
				displayLoss();
			}
		});
		control.getChildren().addAll(box, checkGuess, backToMain);
		screen.setLeft(control);
		board.gridLinesVisibleProperty().set(true);
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				Rectangle cell = new Rectangle();
				MastermindPiece piece = new MastermindPiece();
				mastermindBoard.addPiece(piece, row, col);
				cell.setHeight(30);
				cell.setWidth(30);
				cell.fillProperty().set(Paint.valueOf("WHITE"));
				cell.setStroke(Paint.valueOf("BLACK"));
				cell.setOnMouseClicked(e -> piece.onClick(cell, box.getValue()));
				board.add(cell, col, row);
			}

		}
		mastermindBoard.changeFirstRow();
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < 2; col++) {
				Label cell = new Label();
				labels[row][col] = cell;
				cell.setPrefHeight(30);
				cell.setPrefWidth(30);
				cell.backgroundProperty().set(new Background(new BackgroundFill(Paint.valueOf("white"), CornerRadii.EMPTY, Insets.EMPTY)));
				rightOrWrong.add(cell, col, row);
			}
		}
		mastermindBoard.setLabels(labels);
		board.setAlignment(Pos.CENTER);
		rightOrWrong.setAlignment(Pos.CENTER_LEFT);
		rightOrWrong.gridLinesVisibleProperty().set(true);
		screen.setRight(rightOrWrong);
		BorderPane.setMargin(rightOrWrong, new Insets(0, 100, 0, 0));
		screen.setCenter(board);
		return screen;
	}

	
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		Scene scene = new Scene(createContent(), 600, 600);
		primaryStage.setTitle("Mastermind");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {

		launch(args);
	}

	public static boolean didWin(int numberRight){
		if(numberRight == mastermindBoard.getAnswerSize()){
			return true;
		}else{
			return false;
		}
	}
	
	public static void displayWin(){
		Label win = new Label("You win!");
		win.setFont(Font.font(24));
		win.setTextFill(Paint.valueOf("WHITE"));
		Button playAgain = new Button("Play Again");
		DropShadow drop = new DropShadow();
		playAgain.setPrefWidth(150);
		playAgain.setPrefHeight(25);
		playAgain.setFont(Font.font(12));
		playAgain.setEffect(drop);
		playAgain.setOnAction(e -> {
			MasterMindMenu menu = new MasterMindMenu();
			try {
				menu.start(stage);
			} catch (Exception e1) {
	
			}
		});
		Button mainMenuButton = new Button("Go to Main Menu");
		mainMenuButton.setPrefWidth(150);
		mainMenuButton.setPrefHeight(25);
		mainMenuButton.setFont(Font.font(12));
		mainMenuButton.setEffect(drop);
		mainMenuButton.setOnAction(e -> {
			MainMenu menu = new MainMenu();
			try {
				menu.start(stage);
			} catch (Exception e1) {
	
			}
		});
		VBox won = new VBox(20);
		GridPane answer = new GridPane();
		for(int i = 0; i < mastermindBoard.getAnswerSize(); i++){
			Rectangle answerPiece = new Rectangle();
			answerPiece.setHeight(30);
			answerPiece.setWidth(30);
			answerPiece.fillProperty().set(Paint.valueOf(mastermindBoard.getAnswer()[i].getColor().toString()));
			answerPiece.setStroke(Paint.valueOf("BLACK"));
			answer.add(answerPiece, i, 0);
		}
		answer.setAlignment(Pos.CENTER);
		won.setAlignment(Pos.CENTER);
		won.getChildren().addAll(win, playAgain, mainMenuButton, answer);
		screen.setLeft(null);
		screen.setCenter(won);
	}
	
	public static void displayLoss(){
		Label lose = new Label("You lose...");
		lose.setFont(Font.font(24));
		lose.setTextFill(Paint.valueOf("WHITE"));
		Button playAgain = new Button("Play Again");
		DropShadow drop = new DropShadow();
		playAgain.setPrefWidth(150);
		playAgain.setPrefHeight(25);
		playAgain.setFont(Font.font(12));
		playAgain.setEffect(drop);
		playAgain.setOnAction(e -> {
			MasterMindMenu menu = new MasterMindMenu();
			try {
				menu.start(stage);
			} catch (Exception e1) {
	
			}
		});
		Button mainMenuButton = new Button("Go to Main Menu");
		mainMenuButton.setPrefWidth(150);
		mainMenuButton.setPrefHeight(25);
		mainMenuButton.setFont(Font.font(12));
		mainMenuButton.setEffect(drop);
		mainMenuButton.setOnAction(e -> {
			MainMenu menu = new MainMenu();
			try {
				menu.start(stage);
			} catch (Exception e1) {
	
			}
		});
		VBox won = new VBox(20);
		GridPane answer = new GridPane();
		answer.setAlignment(Pos.CENTER);
		for(int i = 0; i < mastermindBoard.getAnswerSize(); i++){
			Rectangle answerPiece = new Rectangle();
			answerPiece.setHeight(30);
			answerPiece.setWidth(30);
			answerPiece.fillProperty().set(Paint.valueOf(mastermindBoard.getAnswer()[i].getColor().toString()));
			answerPiece.setStroke(Paint.valueOf("BLACK"));
			answer.add(answerPiece, i, 0);
		}
		won.setAlignment(Pos.CENTER);
		won.getChildren().addAll(lose, playAgain, mainMenuButton, answer);
		screen.setLeft(null);
		screen.setCenter(won);
	}
}
