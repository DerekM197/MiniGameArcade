package application;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class SudokuMaster {


	
	@FXML
	public GridPane MainBox;
	
	
	
	@FXML
	public GridPane[] AllBoxes; 

	
	public void initialize(){
		System.out.println("StuffAndThings");
		//AllBoxes = MainBox.getChildren().toArray(new GridPane[0]);;
		System.out.println("GERE");
	}
	public void testing()
	{
		System.out.println("No para");
		System.out.println(this.toString());
	}
	
	
	public void testing(TextField thi){
//		System.out.println(BoxTL0o0.getId());
//		if(BoxTL0o0.getText().length()>1)
//		{
//			BoxTL0o0.setText(BoxTL0o0.getText().substring(BoxTL0o0.getText().length()-1));
//		}
//		System.out.println(BoxTL0o0.getText());
	}
	
	
	
	public boolean checkFullBoard()
	{
		boolean valid = true; 
		System.out.println("HIA");
		GridPane[] Boxes = MainBox.getChildren().toArray(new GridPane[0]);
		System.out.println();
		for(int i = 0;i<9;++i)
		{//Does all boxes
			if(!checkArrSolution(Boxes[i].getChildren().toArray(new TextField[0]))){
				valid = false;
				i = 10;
			}
		}
		
		
		return valid;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean checkArrSolution(TextField[] arr)
	{
		ArrayList<Integer> ints = new ArrayList<>();
		for(int i = 1;i<10;++i){
			ints.add(i);
		}
		boolean valid= true;
		for(int i = 0;i< arr.length;++i)
		{
			boolean good = false;
			int input = -1;
			input = Integer.parseInt(arr[i].getText());
			
			for(int j = 0;j<ints.size();++j)
			{
				if(input==ints.get(j))
				{
					ints.remove(j);
					good = true;
					j = arr.length+2;
				}
			}
			if(!good)
			{
				valid = false;
				i=arr.length*2;
			}
		}
		return valid;
	}
}
