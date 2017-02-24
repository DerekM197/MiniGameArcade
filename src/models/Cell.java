package models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.EventListener;

import interfaces.Publishable;
import interfaces.Subscribable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Cell implements Subscribable<Integer>, Publishable<Integer>, EventListener {
	ArrayList<Subscribable<Integer>> subs = new ArrayList<>();

	private boolean revealed;
	private boolean isMine;
	private int xCoord;
	private int yCoord;
	private boolean marked;
	private int zoneValue;

	public Cell() {
		setRevealed(false);
	}

	public int getZoneValue() {
		return zoneValue;
	}

	public void setZoneValue(int zoneValue) {
		this.zoneValue = zoneValue;
	}

	public ArrayList<Subscribable<Integer>> getSubs() {
		return subs;
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

	@Override
	public void update(Integer value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(Subscribable<Integer> sub) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifySubscribers() {

	}

	public void leftMouseClicked(MouseEvent t, Label state) {
		if (!revealed && !isMine && !marked) {
			setRevealed(true);
			try {
				state.setGraphic(
						new ImageView(new Image(new FileInputStream("C:\\opp\\MiniGameArcade\\images\\exposed.png"))));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else if (!revealed && isMine && !marked) {
			setRevealed(true);
			try {
				state.setGraphic(
						new ImageView(new Image(new FileInputStream("C:\\opp\\MiniGameArcade\\images\\hitmine.png"))));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public void rightMouseClicked(MouseEvent t, Label state) {
		if(!revealed && !marked){
			setMarked(true);
			try {
				state.setGraphic(
						new ImageView(new Image(new FileInputStream("C:\\opp\\MiniGameArcade\\images\\flag.png"))));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}else if(!revealed && marked){
			setMarked(false);
			try {
				state.setGraphic(
						new ImageView(new Image(new FileInputStream("C:\\opp\\MiniGameArcade\\images\\blank.png"))));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
