package models;

import java.util.Random;

public class MinesweeperBoard {
	private Random randnum = new Random();
	private int rowSize;
	private int colSize;
	Cell[][] board;

	public MinesweeperBoard(int rowSize, int colSize) {
		setRowSize(rowSize);
		setColSize(colSize);
		board = new Cell[rowSize][colSize];
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

	public void settingMines() {
		int i, j;
		
		for (int counter = 0; counter < 40; counter++) {
			i = randnum.nextInt(16);
			j = randnum.nextInt(16);
			if (board[i][j].isMine()) {
				counter--;
				if (counter < 0) {
					counter = 0;
				}
			} else {
				board[i][j].setMine(true);
			}
		}
	}

}
