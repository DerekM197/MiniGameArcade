package models;

public class MastermindBoard {

	private MastermindPiece[][] board;
	
	public MastermindBoard(int rowSize, int colSize){
		board = new MastermindPiece[rowSize][colSize];
	}
	
	public MastermindPiece[][] getBoard(){
		return board;
	}
	
	public void setBoard(MastermindPiece[][] board){
		this.board = board;
	}
	
	public void addPiece(MastermindPiece piece, int row, int col){
		board[row][col] = piece;
	}
}
