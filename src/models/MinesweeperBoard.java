package models;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
			i = randnum.nextInt(colSize);
			j = randnum.nextInt(colSize);
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
	
	public void revealMines(){
		for(Cell[] c : board){
			for(Cell cell : c){
				if(cell.isMine()){
					cell.setRevealed(true);
					cell.getState().setGraphic(new ImageView(new Image("file:images/mine.png")));
				}
			}
		}
	}

	public void settingNeighbors() {
		// //right
		for (int i = 0; i < colSize; i++) {
			for (int j = 0; j < colSize - 1; j++) {
				if (board[i][j + 1].isMine()) {
					board[i][j].setZoneValue(1);
				}
				board[i][j].setNeighbors(board[i][j + 1]);
			}
		}
		// left
		for (int i = 0; i < colSize; i++) {
			for (int j = 1; j < colSize; j++) {
				if (board[i][j - 1].isMine()) {
					board[i][j].setZoneValue(1);
				}
				board[i][j].setNeighbors(board[i][j - 1]);
			}
		}
		// bottom
		for (int i = 0; i < colSize - 1; i++) {
			for (int j = 0; j < colSize; j++) {
				if (board[i + 1][j].isMine()) {
					board[i][j].setZoneValue(1);
				}
				board[i][j].setNeighbors(board[i + 1][j]);
			}
		}
		// top
		for (int i = 1; i < colSize; i++) {
			for (int j = 0; j < colSize; j++) {
				if (board[i - 1][j].isMine()) {
					board[i][j].setZoneValue(1);
				}
				board[i][j].setNeighbors(board[i - 1][j]);
			}
		}
		// right bot
		for (int i = 0; i < colSize - 1; i++) {
			for (int j = 0; j < colSize - 1; j++) {
				if (board[i + 1][j + 1].isMine()) {
					board[i][j].setZoneValue(1);
				}
				board[i][j].setNeighbors(board[i + 1][j + 1]);
			}
		}
		// left top
		for (int i = 1; i < colSize; i++) {
			for (int j = 1; j < colSize; j++) {
				if (board[i - 1][j - 1].isMine()) {
					board[i][j].setZoneValue(1);
				}
				board[i][j].setNeighbors(board[i - 1][j - 1]);
			}
		}
		// right top
		for (int i = 1; i < colSize; i++) {
			for (int j = 0; j < colSize - 1; j++) {
				if (board[i - 1][j + 1].isMine()) {
					board[i][j].setZoneValue(1);
				}
				board[i][j].setNeighbors(board[i - 1][j + 1]);
			}
		}
		// left bot        //colSize -1 = 15 colsize -2 = 14
		for (int i = 0; i < colSize - 1; i++) {
			for (int j = 1; j < colSize; j++) {
				if (board[i + 1][j - 1].isMine()) {
					board[i][j].setZoneValue(1);
				}
				board[i][j].setNeighbors(board[i + 1][j - 1]);
			}
		}

	}
}
