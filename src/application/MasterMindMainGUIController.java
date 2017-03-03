package application;

import java.net.URL;
import java.util.ResourceBundle;

import enums.Colors;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

public class MasterMindMainGUIController implements Initializable{
//	ObservableList<Colors> colors = FXCollecitons
	
    @FXML
    private ChoiceBox<Colors> ColorPiecePicker;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		ColorPiecePicker.setItems(value);
	}
    
    
    
}
