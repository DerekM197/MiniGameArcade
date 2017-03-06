package application;

import java.net.URL;
import java.util.ResourceBundle;

import enums.Colors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import models.MastermindPiece;

public class MasterMindMainGUIController implements Initializable {
	ObservableList<Colors> colors = FXCollections.observableArrayList(Colors.values());
	
	
	
	@FXML
	private ChoiceBox<Colors> ColorPiecePicker;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ColorPiecePicker.setValue(colors.get(0));
		ColorPiecePicker.setItems(colors);
	}

	private int[] checkCurrentRow(MastermindPiece[] guess,MastermindPiece[] answer)
	{
		MastermindPiece[] localGuess = guess.clone();
		int wrongSpot = 0;
		int rightSpot = 0;
		for(int i = 0;i<guess.length;++i)
		{
			if(guess[i].getColor().equals(answer[i].getColor()))
			{
				localGuess[i] = null;
				++rightSpot;
			}
		}
		
		for(int i = 0;i<localGuess.length;++i)
		{			
			for(int j = 0;j<answer.length;++j)
			{
				if(localGuess != null && localGuess[i].getColor().equals(answer[j].getColor()))
				{
					++wrongSpot;
					j=answer.length;
				}
			}
		}
		
		int[] result = {rightSpot,wrongSpot};
		return result;
	}
	
	private MastermindPiece[] generateAnswer()
	{
		 int answerSize = MasterMindMain.getInt();
		 
		 
		return null;
	}
}
