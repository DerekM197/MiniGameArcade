package models;

public class MinesweeperBoard {
	private int rowSize;
	private int colSize;
	Cell[][] board = new Cell[colSize][rowSize];
	
	public MinesweeperBoard(int colSize, int rowSize){
		setColSize(colSize);
		setRowSize(rowSize);
	}
	
	public int getRowSize() {
		return rowSize;
	}
	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}
	public int getColSize() {
		return colSize;
	}
	public void setColSize(int colSize) {
		this.colSize = colSize;
	}
	public Cell[][] getBoard() {
		return board;
	}
	
	public void settingBoard(Cell cell){
		
	}
	
}
