package models;

import java.util.ArrayList;

import interfaces.Publishable;
import interfaces.Subscribable;
import javafx.scene.shape.Rectangle;

public class Snake implements Publishable<ArrayList<Rectangle>> {

	ArrayList<Rectangle> body = new ArrayList<>();
	Subscribable<ArrayList<Rectangle>> sub;
	int snakeDirectionX;
	int snakeDirectionY;
	
	public Snake(){
		for(int i = 0; i < 3; i++){
			 body.add(new Rectangle(20, 20, 10, 10));
		}
	}
	
	public boolean didSnakeHitItself(){
		for(int i = 1; i < body.size(); i++){
			if(body.get(0).getX() == body.get(i).getX() && body.get(0).getY() == body.get(i).getY()){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Rectangle> getBody(){
		return body;
	}
	
	public void setBody(ArrayList<Rectangle> body){
		this.body = body;
	}
	
	public Rectangle getHead(){
		return body.get(0);
	}
	
	public void moveSnake(int dx, int dy){
		if(dx == 0 && dy == 0){
			return;
		}else if(dx == dy || dx < 0 && dy > 0 || dx > 0 && dy < 0){
			return;
		}
		if(snakeDirectionX < 0 && dx > 0 || snakeDirectionX > 0 && dx < 0){
			dx = 0;
		}
		if(snakeDirectionY < 0 && dy > 0 || snakeDirectionY > 0 && dy < 0){
			dy = 0;
		}
		for(int i = body.size()-1; i > 0; i--){
			body.get(i).setX(body.get(i-1).getX());
			body.get(i).setY(body.get(i-1).getY());
		}
		body.get(0).setX(body.get(0).getX() + dx);
		body.get(0).setY(body.get(0).getY() + dy);
		snakeDirectionX = dx;
		snakeDirectionY = dy;
	}
	
	public void growSnake(){
		body.add(new Rectangle(body.get(body.size()-1).getX(), body.get(body.size()-1).getY(), 10, 10));
	}
	
	@Override
	public void addNeighbor(Subscribable<ArrayList<Rectangle>> sub) {
		this.sub = sub;
	}

	@Override
	public void notifySubscribers() {
		sub.update(body);
	}
}
