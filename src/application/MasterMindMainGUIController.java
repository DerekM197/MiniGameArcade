package application;

import java.net.URL;
import java.util.ResourceBundle;

import enums.Colors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

public class MasterMindMainGUIController implements Initializable {
	ObservableList<Colors> colors = FXCollections.observableArrayList(Colors.values());

	@FXML
	private ChoiceBox<Colors> ColorPiecePicker;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ColorPiecePicker.setValue(colors.get(0));
		ColorPiecePicker.setItems(colors);
	}

}
