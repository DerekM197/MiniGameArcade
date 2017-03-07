package models;

import java.util.Random;

import application.MasterMindMain;
import enums.Colors;

public class MastermindBoard {

	private MastermindPiece[][] board;
	private boolean isClickable;
	private int currentRow = 0;
	private MastermindPiece[] answer = generateAnswer();
	private MastermindPiece[] guess;
	private int answerSize;
	
	public MastermindBoard(int rowSize, int colSize){
		board = new MastermindPiece[rowSize][colSize];
	}
	
	public void changeWorkingRow()
	{
		for(int j = 0;j<board[currentRow].length;++j)
		{
			board[currentRow][j].flipEditable();
			if(currentRow != 10)
			{
				board[currentRow+1][j].flipEditable();
			}
		}
		guess = getBoard()[currentRow];
		++currentRow;
		
	}
	
	public MastermindPiece[][] getBoard(){
		return board;
	}
	
	public void setBoard(MastermindPiece[][] board){
		this.board = board;
	}

	public boolean isClickable() {
		return isClickable;
	}

	public void setClickable(boolean isClickable) {
		this.isClickable = isClickable;
	}
	
	
	
	public void addPiece(MastermindPiece piece, int row, int col){
		board[row][col] = piece;
	}
	
	

	public int[] checkCurrentRow()
	
	{
		MastermindPiece[] localGuess = guess.clone();
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
	

	public int getAnswerSize() {
		return answerSize;
	}

	public void setAnswerSize(int answerSize) {
		this.answerSize = answerSize;
	}
	public MastermindPiece[] generateAnswer(){
		 int answerSize = getAnswerSize();
		 
		 
		 MastermindPiece[] answer = new MastermindPiece[answerSize];
				 
		 for(int i = 0;i<answer.length;++i){
			 answer[i].flipEditable();
			 answer[i].setColor(getRandomColor());
			 answer[i].flipEditable();
		 }
		return answer;
	}
	
	public Colors getRandomColor(){
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
}
