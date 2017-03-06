package models;

import enums.Colors;

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
	
	public void onClick(Colors color){
		setColor(color);
	}
}
