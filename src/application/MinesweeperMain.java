package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class MinesweeperMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane mineField = FXMLLoader.load(getClass().getResource("MinesweeperGUI.fxml"));
			GridPane grid = new GridPane();
			grid.gridLinesVisibleProperty().set(true);
			grid.alignmentProperty().set(Pos.CENTER);
			mineField.setCenter(grid);
			for(int i = 0; i < 16; i++){
				for(int j = 0; j < 16; j++){
					Label cell = new Label();
					cell.setId("cell" + i+1);
					cell.setText("hi");
					grid.add(cell, i+1, j+1);
				}	
			}
			Scene scene = new Scene(mineField,400,400);
			primaryStage.setTitle("Minesweeper");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
