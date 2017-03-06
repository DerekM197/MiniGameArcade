package models;

public class MastermindBoard {

	private MastermindPiece[][] board;
	private boolean isClickable;
	
	public MastermindBoard(int rowSize, int colSize){
		board = new MastermindPiece[rowSize][colSize];
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
	
	
	
	
}
