package models;

import java.util.ArrayList;

import interfaces.Publishable;
import interfaces.Subscribable;

public class Mine extends Cell implements Publishable<Integer>{
	ArrayList<Subscribable<Integer>> subs  = new ArrayList<>();
	public Mine(){
		setRevealed(false);
		setMine(true);
		setMarked(false);
	}
	
	@Override
	public void add(Subscribable<Integer> sub) {
		subs.add(sub);
		notifySubscribers();
	}

	@Override
	public void notifySubscribers() {
		
	}

}
