package models;

import interfaces.Subscribable;

public class NotMine implements Subscribable<Integer> {
	
	public int zoneValue;
	
	@Override
	public void update(Integer value) {
		zoneValue += 1;
	}


}
