package application;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MinesweeperMenu extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane m = new BorderPane();
		VBox menu = new VBox();
		Button easy = new Button("Easy");
		easy.setOnMousePressed(e -> startGame(1));
		Button intermidiate = new Button("Intermidiate");
		intermidiate.setOnMousePressed(e -> startGame(2));
		Button hard = new Button("Hard");
		hard.setOnMousePressed(e -> startGame(3));
		menu.getChildren().addAll(easy, intermidiate, hard);
	}

	public void startGame(int i) {
		MinesweeperMain newGame = new MinesweeperMain();
		if(i == 1){
			newGame.setColSize(9);
			newGame.setRowSize(9);
		}else if(i ==2){
			newGame.setColSize(16);
			newGame.setRowSize(16);
		}else{
			newGame.setColSize(30);
			newGame.setRowSize(16);
		}
	}

}
