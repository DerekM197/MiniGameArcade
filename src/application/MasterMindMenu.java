package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MasterMindMenu extends Application{

	private static Stage stage;
	
	public static Stage getStage(){
		
		return stage;
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane screen = FXMLLoader.load(getClass().getResource("MasterMindMenuGUI.fxml"));
		VBox center = new VBox(20);
		DropShadow drop = new DropShadow();
		Button easy = new Button("Easy");
		easy.setPrefWidth(200);
		easy.setEffect(drop);
		easy.setOnAction(e -> {
			MasterMindMain mastermind = new MasterMindMain(3, 10);
			try {
				mastermind.start(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		Button intermediate = new Button("Intermediate");
		intermediate.setPrefWidth(200);
		intermediate.setEffect(drop);
		intermediate.setOnAction(e -> {
			MasterMindMain minesweeper = new MasterMindMain(4, 16);
			try {
				minesweeper.start(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		Button hard  = new Button("Hard");
		hard.setPrefWidth(200);
		hard.setEffect(drop);
		hard.setOnAction(e -> {
			MasterMindMain minesweeper = new MasterMindMain(6, 16);
			try {
				minesweeper.start(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		center.getChildren().addAll(easy, intermediate, hard);
		center.setAlignment(Pos.CENTER);
		screen.setCenter(center);
		Scene menuScreen = new Scene(screen, 700, 500);
		primaryStage.setTitle("Mastermind");
		primaryStage.setScene(menuScreen);
		primaryStage.show();
	}

	public static void main(String[] args){
		launch(args);
	}
	
	
}
