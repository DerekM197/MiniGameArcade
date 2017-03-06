package models;

public class MastermindBoard {

	private MastermindPiece[][] board;
	private boolean isClickable;
	private int currentRow = 0;
	
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
	
	
	
	
}
