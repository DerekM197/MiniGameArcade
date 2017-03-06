package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainMenu extends Application{

	Stage window;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("M A I N   M E N U");
		BorderPane layout = FXMLLoader.load(getClass().getResource("../xmls/MainMenu.fxml"));
		VBox center = new VBox(20);
		DropShadow drop = new DropShadow();
		Button playMinesweeper = new Button("Play Minesweeper");
		playMinesweeper.setPrefWidth(200);
		playMinesweeper.setPrefHeight(30);
		playMinesweeper.setFont(Font.font("Berlin Sans FB", 12));
		playMinesweeper.setEffect(drop);
		playMinesweeper.setOnAction(e -> {
			MinesweeperMenu minesweeper = new MinesweeperMenu();
			try {
				minesweeper.start(window);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		Button playSudoku = new Button("Play Sudoku");
		playSudoku.setPrefWidth(200);
		playSudoku.setPrefHeight(30);
		playSudoku.setFont(Font.font("Berlin Sans FB", 12));
		playSudoku.setEffect(drop);
		playSudoku.setOnAction(e -> {
			SudokuMain sudokuMain = new SudokuMain();
			sudokuMain.start(window);
		});
		Button playSnake = new Button("Play Snake");
		playSnake.setPrefWidth(200);
		playSnake.setPrefHeight(30);
		playSnake.setFont(Font.font("Berlin Sans FB", 12));
		playSnake.setEffect(drop);
		playSnake.setOnAction(e -> {
			SnakeMenu snakeMenu = new SnakeMenu();
			try {
				snakeMenu.start(window);
			} catch (Exception e1) {

			}
		});
		Button playMastermind = new Button("Play Mastermind");
		playMastermind.setPrefWidth(200);
		playMastermind.setPrefHeight(30);
		playMastermind.setFont(Font.font("Berlin Sans FB", 12));
		playMastermind.setEffect(drop);
		playMastermind.setOnAction(e -> {
			MasterMindMenu masterMindMenu = new MasterMindMenu();
			try {
				masterMindMenu.start(window);
			}catch(Exception e1){
				
			}
		});
		Button exitButton = new Button("Exit");
		exitButton.setPrefWidth(200);
		exitButton.setPrefHeight(30);
		exitButton.setFont(Font.font("Berlin Sans FB", 12));
		exitButton.setEffect(drop);
		exitButton.setOnAction(e -> {
			window.close();
		});
		center.getChildren().addAll(playMinesweeper, playSudoku, playSnake, playMastermind, exitButton);
		center.setAlignment(Pos.TOP_CENTER);
		layout.setCenter(center);
		BorderPane.setAlignment(center, Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.show();
	}
}
