package models;

import java.util.ArrayList;
import java.util.EventListener;

import application.MinesweeperMain;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Cell implements EventListener {

	private ArrayList<Cell> neighbors = new ArrayList<>();
	private boolean revealed;
	private boolean isMine;
	private int xCoord;
	private int yCoord;
	private boolean marked;
	private int zoneValue;
	private Label state;

	public Cell() {
		setRevealed(false);
	}

	public Label getState() {
		return state;
	}

	public void setState(Label state) {
		this.state = state;
	}

	public ArrayList<Cell> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(Cell neighbor) {
		this.neighbors.add(neighbor);
	}

	public int getZoneValue() {
		return zoneValue;
	}

	public void setZoneValue(int zoneValue) {
		this.zoneValue += zoneValue;
	}

	public boolean isRevealed() {
		return revealed;
	}

	public void setRevealed(boolean revealed) {
		this.revealed = revealed;
	}

	public boolean isMine() {
		return isMine;
	}

	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}

	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}

	public void open(Label state, MinesweeperBoard board) {
		if (!revealed && !isMine && !marked) {
			setRevealed(true);
			board.takeNumOfUnopened(1);
			if (getZoneValue() == 1) {
				this.state.setGraphic(new ImageView(new Image("file:images/number1.png")));
			} else if (getZoneValue() == 2) {
				this.state.setGraphic(new ImageView(new Image("file:images/number2.png")));
			} else if (getZoneValue() == 3) {
				this.state.setGraphic(new ImageView(new Image("file:images/number3.png")));
			} else if (getZoneValue() == 4) {
				this.state.setGraphic(new ImageView(new Image("file:images/number4.png")));
			} else if (getZoneValue() == 5) {
				this.state.setGraphic(new ImageView(new Image("file:images/number5.png")));
			} else if (getZoneValue() == 6) {
				this.state.setGraphic(new ImageView(new Image("file:images/number6.png")));
			} else if (getZoneValue() == 7) {
				this.state.setGraphic(new ImageView(new Image("file:images/number7.png")));
			} else if (getZoneValue() == 8) {
				this.state.setGraphic(new ImageView(new Image("file:images/number8.png")));
			} else {
				this.state.setGraphic(new ImageView(new Image("file:images/exposed.png")));
				revealNeighbors(board);
			}

		}
		if (board.getNumOfUnopend() == board.getNumOfBombs()) {
			board.win();
		}
	}

	public void revealNeighbors(MinesweeperBoard board) {

		for (Cell cell : neighbors) {
			if (!cell.isMine()) {
				if (!cell.isRevealed()) {
					cell.setRevealed(true);
					board.takeNumOfUnopened(1);
					if (cell.getZoneValue() == 1) {
						cell.getState().setGraphic(new ImageView(new Image("file:images/number1.png")));
					} else if (cell.getZoneValue() == 2) {
						cell.getState().setGraphic(new ImageView(new Image("file:images/number2.png")));
					} else if (cell.getZoneValue() == 3) {
						cell.getState().setGraphic(new ImageView(new Image("file:images/number3.png")));
					} else if (cell.getZoneValue() == 4) {
						cell.getState().setGraphic(new ImageView(new Image("file:images/number4.png")));
					} else if (cell.getZoneValue() == 5) {
						cell.getState().setGraphic(new ImageView(new Image("file:images/number5.png")));
					} else if (cell.getZoneValue() == 6) {
						cell.getState().setGraphic(new ImageView(new Image("file:images/number6.png")));
					} else if (cell.getZoneValue() == 7) {
						cell.getState().setGraphic(new ImageView(new Image("file:images/number7.png")));
					} else if (cell.getZoneValue() == 8) {
						cell.getState().setGraphic(new ImageView(new Image("file:images/number8.png")));
					} else {
						cell.getState().setGraphic(new ImageView(new Image("file:images/exposed.png")));
						cell.revealNeighbors(board);
					}

				}
			}
		}
		if (board.getNumOfUnopend() == board.getNumOfBombs()) {
			board.win();
		}
	}

	public void leftMouseClicked(MouseEvent t, Label state, MinesweeperBoard board) {
		if (!revealed && isMine && !marked) {
			setRevealed(true);
			this.state.setGraphic(new ImageView(new Image("file:images/hitmine.png")));
			board.revealMines();
		} else {
			open(state, board);
		}
	}
	
	

	public void rightMouseClicked(MouseEvent t, Label state) {
		if (!revealed && !marked) {
			setMarked(true);
			state.setGraphic(new ImageView(new Image("file:images/flag.png")));
		} else if (!revealed && marked) {
			setMarked(false);
			state.setGraphic(new ImageView(new Image("file:images/Tile.png")));

		}
	}

}
