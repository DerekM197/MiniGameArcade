package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MinesweeperMenu extends Application{

	public BorderPane makeMenu() throws IOException{
		BorderPane screen = FXMLLoader.load(getClass().getResource("MinesweeperMenuGUI.fxml"));
		
		return screen;
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		BorderPane screen = FXMLLoader.load(getClass().getResource("MinesweeperMenuGUI.fxml"));
		VBox center = new VBox(20);
		DropShadow drop = new DropShadow();
		Button easy = new Button("Easy");
		easy.setPrefWidth(200);
		easy.setEffect(drop);
		easy.setOnAction(e -> {
			MinesweeperMain minesweeper = new MinesweeperMain(9, 9, 10);
			minesweeper.start(stage);
		});
		Button intermediate = new Button("Intermediate");
		intermediate.setPrefWidth(200);
		intermediate.setEffect(drop);
		intermediate.setOnAction(e -> {
			MinesweeperMain minesweeper = new MinesweeperMain(16, 16, 40);
			minesweeper.start(stage);
		});
		Button hard  = new Button("Hard");
		hard.setPrefWidth(200);
		hard.setEffect(drop);
		hard.setOnAction(e -> {
			MinesweeperMain minesweeper = new MinesweeperMain(30, 16, 99);
			minesweeper.start(stage);
		});
		center.getChildren().addAll(easy, intermediate, hard);
		center.setAlignment(Pos.CENTER);
		screen.setCenter(center);
		Scene menuScreen = new Scene(screen, 700, 500);
		stage.setTitle("Minesweeper");
		stage.setScene(menuScreen);
		stage.show();
	}
	
	public static void main(String[] args){
		launch(args);
	}

}
