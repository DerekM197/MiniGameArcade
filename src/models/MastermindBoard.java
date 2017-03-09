package models;

import java.util.Random;


import enums.Colors;

public class MastermindBoard {

	private MastermindPiece[][] board;
	private boolean isClickable;
	private int currentRow = 0;
	private MastermindPiece[] answer;
	private MastermindPiece[] guess;
	private int answerSize;
	
	public MastermindBoard(int rowSize, int colSize){
		board = new MastermindPiece[rowSize][colSize];
		answerSize = board[0].length;
		answer = generateAnswer();
	}
	
	public void changeFirstRow(){
		for(int j = 0;j<board[0].length;++j)
		{
			if(currentRow<10)
			{
				board[0][j].flipEditable();
			}
		}
	}
	
	public void changeWorkingRow()
	{
		
		for(int j = 0;j<board[currentRow].length;++j)
		{
			if(currentRow<10)
			{
				board[currentRow][j].flipEditable();
			}
			if(currentRow < 9)
			{
				board[currentRow+1][j].flipEditable();
			}
		}
	
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
	
	private boolean isWhiteSpace(MastermindPiece[] guess){
		for(int i = 0;i<guess.length;++i){
			if(guess[i] != null && guess[i].getColor().toString().equals("WHITE")){
				return true;
			}
		}
		return false;
	}

	public int[] checkCurrentRow(){
		guess = board[currentRow];
		if(isWhiteSpace(guess))
		{
			return new int[]{0,0};
		}
		MastermindPiece[] localGuess = guess.clone();
		MastermindPiece[] localAnswer = answer.clone();
		int wrongSpot = 0;
		int rightSpot = 0;
		
		for(int i = 0;i<guess.length;++i){
			if(guess[i].getColor().equals(answer[i].getColor())){
				localGuess[i] = null;
				localAnswer[i] = null;
				++rightSpot;
			}
		}
		for(int i = 0;i<localGuess.length;++i){		
			for(int j = 0;j<localAnswer.length;++j){
				
				if(localGuess[i] != null && localAnswer[j]!=null&&localGuess[i].getColor().equals(localAnswer[j].getColor())){
					localAnswer[j] = null;
					++wrongSpot;
					j=localAnswer.length;
				}
			}
		}
		
		int[] result = {rightSpot,wrongSpot};
		changeWorkingRow();
		System.out.println(result[0]+" : "+result[1]);
		return result;
	}
	

	public int getAnswerSize() {
		return answerSize;
	}

	public void setAnswerSize(int answerSize) {
		this.answerSize = answerSize;
	}
	public MastermindPiece[] generateAnswer(){
		
		 MastermindPiece[] LocalAnswer = new MastermindPiece[answerSize];
		 
		 for(int i = 0;i<LocalAnswer.length;++i){
			 LocalAnswer[i] = new MastermindPiece();
			 LocalAnswer[i].flipEditable();
			 Colors color = getRandomColor();
			 LocalAnswer[i].setColor(color);
			 System.out.print(" | "+color.toString());
		
			 LocalAnswer[i].flipEditable();
		 }
		 System.out.println();
		return LocalAnswer;
	}
	
	public Colors getRandomColor(){
		Random randy = new Random();
		
		int pick = randy.nextInt(7);
		Colors choice;
		switch(pick){
			case(0):{
				choice = Colors.BLUE;
				break;
			}case(1):{
				choice = Colors.GREEN;
				break;
			}case(2):{
				choice = Colors.RED;
				break;
			}case(3):{
				choice = Colors.YELLOW;
				break;
			}case(4):{
				choice = Colors.BROWN;
				break;
			}case(5):{
				choice = Colors.PURPLE;
				break;
			}case(6):{
				choice = Colors.ORANGE;
				break;
			}
			default:{
				choice = Colors.WHITE;
			}
		}
		return choice;	
	}
	
	public int getCurrentRow(){
		return currentRow;
	}
}
