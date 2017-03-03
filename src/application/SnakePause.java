package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SnakePause {

	private static boolean resume = true;
	
	public static void display(String title, String message){
		resume = true;
		Stage window = new Stage();
		//This blocks input to other windows until this one is take care of
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		window.setMinHeight(150);
		Label label = new Label(message);
		label.setFont(Font.font("Berlin Sans FB", 12));
		label.setTextFill(Paint.valueOf("#FFFFFF"));
		Button resumeButton = new Button("Resume");
		resumeButton.setPrefWidth(150);
		resumeButton.setPrefHeight(25);
		resumeButton.setFont(Font.font("Berlin Sans FB", 12));
		resumeButton.setOnAction(e -> {
			window.close();
			resume = true;
		});
		Button exitButton = new Button("Exit to snake menu");
		exitButton.setPrefWidth(150);
		exitButton.setPrefHeight(25);
		exitButton.setFont(Font.font("Berlin Sans FB", 12));
		exitButton.setOnAction(e -> {
			window.close();
			resume = false;
		});
		VBox layout = new VBox(10);
		layout.setStyle("-fx-background-color: #563456");
		layout.getChildren().addAll(label, resumeButton, exitButton);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout, Color.PURPLE);
		window.setScene(scene);
		window.showAndWait();
	}
	
	public static boolean getResume(){
		return resume;
	}
}
