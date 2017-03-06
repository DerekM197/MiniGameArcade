package models;

import enums.Colors;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MastermindPiece {

	private Colors color;
	private boolean isEditable = false;;
	
	public MastermindPiece() {
		
	}
	
	public Colors getColor(){
		return color;
	}
	
	public boolean isEditable()
	{
		return isEditable;
	}

	public void flipEditable()
	{
		isEditable = !isEditable;
	}
	
	public void setColor(Colors color){
		if(isEditable){
			this.color = color;
		}
	}
	
	public void onClick(Rectangle cell, Colors color){
		setColor(color);
		cell.fillProperty().set(Paint.valueOf(color.toString()));
	}
}
