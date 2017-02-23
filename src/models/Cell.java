package models;

import java.util.ArrayList;

import interfaces.Publishable;
import interfaces.Subscribable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Cell implements Subscribable<Integer>, Publishable<Integer>{
	ArrayList<Subscribable<Integer>> subs = new ArrayList<>();
	private SimpleBooleanProperty revealed;
	private SimpleBooleanProperty isMine;
	private SimpleIntegerProperty xCoord;
	private SimpleIntegerProperty yCoord;
	private SimpleBooleanProperty marked;
	private SimpleIntegerProperty zoneValue;
	
	public Cell(){
		
	}
	
	public SimpleIntegerProperty getZoneValue() {
		return zoneValue;
	}

	public void setZoneValue(SimpleIntegerProperty zoneValue) {
		this.zoneValue = zoneValue;
	}

	public ArrayList<Subscribable<Integer>> getSubs() {
		return subs;
	}

	
	public SimpleBooleanProperty isRevealed() {
		return revealed;
	}
	public void setRevealed(SimpleBooleanProperty revealed) {
		this.revealed = revealed;
	}
	public SimpleBooleanProperty isMine() {
		return isMine;
	}
	public void setMine(SimpleBooleanProperty isMine) {
		this.isMine = isMine;
	}
	public SimpleIntegerProperty getxCoord() {
		return xCoord;
	}
	public void setxCoord(SimpleIntegerProperty xCoord) {
		this.xCoord = xCoord;
	}
	public SimpleIntegerProperty getyCoord() {
		return yCoord;
	}
	public void setyCoord(SimpleIntegerProperty yCoord) {
		this.yCoord = yCoord;
	}
	public SimpleBooleanProperty isMarked() {
		return marked;
	}
	public void setMarked(SimpleBooleanProperty marked) {
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
		// TODO Auto-generated method stub
		
	}
	
	
}
