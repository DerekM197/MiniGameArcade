package application;

import java.net.URL;
import java.util.Random;
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
	private MastermindPiece[] answer = generateAnswer();
	
	
	@FXML
	private ChoiceBox<Colors> ColorPiecePicker;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	private int[] checkCurrentRow(MastermindPiece[] guess)
	
	{
		MastermindPiece[] localGuess = guess;
		int wrongSpot = 0;
		int rightSpot = 0;
		for(int i = 0;i<guess.length;++i){
			if(guess[i].getColor().equals(answer[i].getColor())){
				localGuess[i] = null;
				++rightSpot;
			}
		}
		
		for(int i = 0;i<localGuess.length;++i){			
			for(int j = 0;j<answer.length;++j){
				if(localGuess != null && localGuess[i].getColor().equals(answer[j].getColor())){
					++wrongSpot;
					j=answer.length;
				}
			}
		}
		
		int[] result = {rightSpot,wrongSpot};
		return result;
	}
	
	private MastermindPiece[] generateAnswer(){
		 int answerSize = MasterMindMain.getInt();
		 
		 MastermindPiece[] answer = new MastermindPiece[answerSize];
				 
		 for(int i = 0;i<answer.length;++i){
			 answer[i].flipEditable();
			 answer[i].setColor(getRandomColor());
			 answer[i].flipEditable();
		 }
		return answer;
	}
	
	private Colors getRandomColor(){
		Random randy = new Random();
		
		int pick = randy.nextInt(4);
		Colors choice;
		switch(pick){
			case(0):{
				choice = Colors.BLUE;
				break;
			}
			
			case(1):{
				choice = Colors.GREEN;
				break;
			}
			
			case(2):{
				choice = Colors.RED;
				break;
			}
			case(3):{
				choice = Colors.YELLOW;
				break;
			}
			default:{
				choice = Colors.BLUE;
			}
		}
		return choice;	
	}
	
//	public Colors getColor(){
//		Colors color = ColorPiecePicker.getValue();
//		System.out.println(color.toString());
//		return color;
//	}
}
