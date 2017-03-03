package models;

public class MastermindBoard {

	private MastermindPiece[] board;
	
	public MastermindBoard(){
		
	}
	
	public MastermindPiece[] getBoard(){
		return board;
	}
	
	public void setBoard(MastermindPiece[] board){
		this.board = board;
	}
	
	
}
