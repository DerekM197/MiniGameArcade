package models;

import enums.Colors;

public class MastermindPiece {

	private Colors color;
	
	public MastermindPiece() {
		
	}
	
	public Colors getColor(){
		return color;
	}
	
	public void setColor(Colors color){
		this.color = color;
	}
}
