package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MinesweeperController implements Initializable{

    @FXML
    private Button backButton;

    @FXML
    private Button newGameButton;

    @FXML
    private GridPane mineField;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}

