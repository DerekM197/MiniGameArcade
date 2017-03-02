package application;

import com.sun.javafx.scene.traversal.Direction;

import enums.Directions;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import models.Snake;
import models.SnakeBoard;

public class SnakeGame extends Application{
	
	Stage window = new Stage();
	int score = 0;
	Snake snake = new Snake();
	SnakeBoard board = new SnakeBoard(snake);
	Directions direction;
	BorderPane layout;
	AnimationTimer timer;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		snake.addNeighbor(board);
		window = primaryStage;
		window.setTitle("SNAKE GAME");
		layout = FXMLLoader.load(getClass().getResource("../xmls/SnakeGameXML.fxml"));
		Label printScore = new Label("Score: " + score);
		layout.setBottom(printScore);
		printScore.setFont(Font.font("Berlin Sans FB", 16));
		printScore.setTextFill(Paint.valueOf("#FFFFFF"));
		BorderPane.setAlignment(printScore, Pos.CENTER);
		layout.setCenter(board.getBoard());
		Scene scene = new Scene(layout);
		scene.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.W && direction != Directions.DOWN){
				direction = Directions.UP;
			}
			if(e.getCode() == KeyCode.S && direction != Directions.UP){
				direction = Directions.DOWN;	
			}
			if(e.getCode() == KeyCode.A && direction != Directions.RIGHT){
				direction = Directions.LEFT;
			}
			if(e.getCode() == KeyCode.D && direction != Directions.LEFT){
				direction = Directions.RIGHT;
			}
//			switch(e.getCode()){
//			case W:
//				if(goDown != true){
//					goUp = true;
//					goLeft = false;
//					goRight = false;
//				}
//				break;
//			case A:
//				if(goRight != true){
//					goLeft = true;
//					goDown = false;
//					goUp = false;
//				}
//				break;
//			case D:
//				if(goLeft != true){
//					goRight  = true;
//					goDown = false;
//					goUp = false;
//				}
//				break;	
//			case S:
//				if(goUp != true){
//					goDown = true;
//					goLeft = false;
//					goRight = false;
//				}
//				break;
//			}
		});
		scene.setOnKeyReleased(e -> {
			switch(e.getCode()){
			case W:
				if(direction == Directions.UP){
					
				}
				break;
			case A:
				if(direction == Directions.LEFT){
					
				}
				break;
			case S:
				if(direction == Directions.DOWN){
					
				}
				break;
			case D:
				if(direction == Directions.RIGHT){
					
				}
				break;
			}
		});
		timer = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				try {
					Thread.sleep(60);
				} catch (InterruptedException e) {
					
				}
				int dx = 0;
				int dy = 0;
				if(direction == Directions.UP){
					dy-=10;
					snake.moveSnake(dx, dy);
					if(board.didSnakeHitWall()){
						displayLoss();
					}
					if(board.didSnakeHitPellet()){
						score++;
						Label printScore = new Label("Score: " + score);
						layout.setBottom(printScore);
						printScore.setFont(Font.font("Berlin Sans FB", 16));
						printScore.setTextFill(Paint.valueOf("#FFFFFF"));
						BorderPane.setAlignment(printScore, Pos.CENTER);
					}
					if(snake.didSnakeHitItself()){
						displayLoss();
					}
				}
				if(direction == Directions.RIGHT){
					dx+=10;
					snake.moveSnake(dx, dy);
					if(board.didSnakeHitWall()){
						displayLoss();
					}
					if(board.didSnakeHitPellet()){
						score++;
						Label printScore = new Label("Score: " + score);
						layout.setBottom(printScore);
						printScore.setFont(Font.font("Berlin Sans FB", 16));
						printScore.setTextFill(Paint.valueOf("#FFFFFF"));
						BorderPane.setAlignment(printScore, Pos.CENTER);
					}
					if(snake.didSnakeHitItself()){
						displayLoss();
					}
				}
				if(direction == Directions.DOWN){
					dy+=10;
					snake.moveSnake(dx, dy);
					if(board.didSnakeHitWall()){
						displayLoss();
					}
					if(board.didSnakeHitPellet()){
						score++;
						Label printScore = new Label("Score: " + score);
						layout.setBottom(printScore);
						printScore.setFont(Font.font("Berlin Sans FB", 16));
						printScore.setTextFill(Paint.valueOf("#FFFFFF"));
						BorderPane.setAlignment(printScore, Pos.CENTER);
					}
					if(snake.didSnakeHitItself()){
						displayLoss();
					}
				}
				if(direction == Directions.LEFT){
					dx-=10;
					snake.moveSnake(dx, dy);
					if(board.didSnakeHitWall()){
						displayLoss();
					}
					if(board.didSnakeHitPellet()){
						score++;
						Label printScore = new Label("Score: " + score);
						layout.setBottom(printScore);
						printScore.setFont(Font.font("Berlin Sans FB", 16));
						printScore.setTextFill(Paint.valueOf("#FFFFFF"));
						BorderPane.setAlignment(printScore, Pos.CENTER);
					}
					if(snake.didSnakeHitItself()){
						displayLoss();
					}
				}
			}
			
		};
		timer.start();
		window.setScene(scene);
		window.show();
	}

	public void displayLoss(){
		timer.stop();
		Label lose = new Label("You have lost");
		lose.setFont(Font.font("Berlin Sans FB", 24));
		lose.setTextFill(Paint.valueOf("#FFFFFF"));
		Button menuButton = new Button("Exit to Snake Menu");
		DropShadow drop = new DropShadow();
		menuButton.setPrefWidth(150);
		menuButton.setPrefHeight(25);
		menuButton.setFont(Font.font("Berlin Sans FB", 12));
		menuButton.setEffect(drop);
		menuButton.setOnAction(e -> {
			SnakeMenu menu = new SnakeMenu();
			try {
				menu.start(window);
			} catch (Exception e1) {
	
			}
		});
		Button exitButton = new Button("Exit");
		exitButton.setPrefWidth(150);
		exitButton.setPrefHeight(25);
		exitButton.setFont(Font.font("Berlin Sans FB", 12));
		exitButton.setEffect(drop);
		VBox loss = new VBox(20);
		exitButton.setOnAction(e -> {
			window.close();
		});
		loss.setAlignment(Pos.CENTER);
		loss.getChildren().addAll(lose, menuButton, exitButton);
		layout.setCenter(loss);
	}
}
