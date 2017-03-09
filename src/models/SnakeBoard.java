package models;

import java.util.ArrayList;

import interfaces.Subscribable;
import javafx.scene.Group;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class SnakeBoard implements Subscribable<ArrayList<Rectangle>>{

	Group board = new Group();
	Pellet food;
	Line border1 = new Line(10, 10, 10, 490);
	Line border2 = new Line(10, 10, 490, 10);
	Line border3 = new Line(10, 490, 490, 490);
	Line border4 = new Line(490, 10, 490, 490);
	Snake snake;
	
	public SnakeBoard(Snake snake){
		this.snake = snake;
		food = new Pellet(snake);
		for(int i = 10; i < 490; i += 10){
			Line gridLineX = new Line(i, 10, i, 490);
			gridLineX.strokeProperty().set(Paint.valueOf("753475"));
			Line gridLineY = new Line(10, i, 490, i);
			gridLineY.strokeProperty().set(Paint.valueOf("753475"));
			board.getChildren().addAll(gridLineX, gridLineY);
		}
		board.getChildren().addAll(snake.getBody());
		board.getChildren().addAll(border1, border2, border3, border4, food.placePellet());
		
	}
	
	public boolean didSnakeHitWall(){
		if(snake.getHead().getX() < 10 || snake.getHead().getX() >= 490 || snake.getHead().getY() < 10 || snake.getHead().getY() >= 490){
			return true;
		}
		return false;
	}
	
	public boolean didSnakeHitPellet(){
		if(snake.getHead().getX() == food.getLocation()[0] && snake.getHead().getY() == food.getLocation()[1]){
			board.getChildren().remove(food.getPellet());
			board.getChildren().add(food.placePellet());
			board.getChildren().removeAll(snake.getBody());
			snake.growSnake();
			board.getChildren().addAll(snake.getBody());
			return true;
		}
		return false;
	}
	
	public Group getBoard(){
		return board;
	}

	@Override
	public void update(ArrayList<Rectangle> value) {
		snake.setBody(value);
	}
}
