package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MasterMindMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane hi =  FXMLLoader.load(getClass().getResource("MasterMindMainGUI.fxml"));
		Scene scene = new Scene(hi, 200, 200);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args){
		launch(args);
	}

	
}
