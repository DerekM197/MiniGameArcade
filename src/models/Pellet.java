package models;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pellet {

	private int[] location = new int[2];
	Rectangle food;
	
	public Pellet(){
		
	}
	
	public Rectangle placePellet(){
		Random rand = new Random();
		int randX = rand.nextInt((48) + 1) * 10;
		int randY = rand.nextInt((48) + 1) * 10;
		if((randX < 10) || (randX > 490) || (randY < 10) || (randX > 490)){
		 randX = 250;
		 randY = 250;
		}
		location[0] = randX;
		location[1] = randY;
		food = new Rectangle(randX, randY, 10, 10);
		food.setFill(Color.RED);
		return food;
	}
	
	public int[] getLocation(){
		return location;
	}
	
	public Rectangle getPellet(){
		return food;
	}
}
