package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SnakeMenu extends Application{

	Stage window = new Stage();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("SNAKE MENU");
		VBox layout = FXMLLoader.load(getClass().getResource("../xmls/SnakeXML.fxml"));
		Button playButton = new Button("Play");
		DropShadow drop = new DropShadow();
		playButton.setPrefWidth(60);
		playButton.setPrefHeight(25);
		playButton.setFont(Font.font("Berlin Sans FB", 12));
		playButton.setEffect(drop);
		playButton.setOnAction(e -> {
			SnakeGame game = new SnakeGame();
			try {
				game.start(window);
			} catch (Exception e1) {
				
			}
		});
		Button exitButton = new Button("Exit");
		exitButton.setPrefWidth(60);
		exitButton.setPrefHeight(25);
		exitButton.setFont(Font.font("Berlin Sans FB", 12));
		exitButton.setEffect(drop);
		exitButton.setOnAction(e -> {
			//TODO
		});
		layout.getChildren().addAll(playButton, exitButton);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.show();
	}

}
