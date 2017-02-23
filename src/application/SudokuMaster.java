package application;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class SudokuMaster {
	
	public SudokuMaster() {
	}
	@FXML
	public GridPane MainBox;
	public GridPane[] Boxes;
	
	public void initialize(){
		System.out.println("StuffAndThings");
		Boxes = MainBox.getChildren().toArray(new GridPane[0]);
	}
	public void testing()
	{
		System.out.println("No para");
		System.out.println(this.toString());
	}
	
	/*[0][1][2] [0][1][2] [0][1][2]
	 *[3][4][5] [3][4][5] [3][4][5]
	 *[6][7][8] [6][7][8] [6][7][8] 
	 *
	 *[0][1][2] [0][1][2] [0][1][2]
	 *[3][4][5] [3][4][5] [3][4][5]
	 *[6][7][8] [6][7][8] [6][7][8] 
	 *
	 *[0][1][2] [0][1][2] [0][1][2]
	 *[3][4][5] [3][4][5] [3][4][5]
	 *[6][7][8] [6][7][8] [6][7][8] 
	*/
	
	
	private TextField[][] getRows()
	{
		TextField[][] test = new TextField[9][9];
		GridPane[] boxe =  MainBox.getChildren().toArray(new GridPane[0]);
		int count = 0;
		for(int i =0,r=0;i<9;++i,r+=3)
		{
			for(int j = 0,k=0;j<9;++k)
			{
				if(i!=0&&i/3==0&&k==0)
				{
					k+=3;
				}else if(i!=0&&i/6==0&&k==0)
				{
					k+=6;
				}
				TextField[] hi = boxe[k].getChildren().toArray(new TextField[0]);
				for(int p = 0;p<3;++p,++j)
				{
					System.out.println("I: "+i+" R: "+r+" J: "+j+" K: "+k+" P: "+p+" C: "+count++);
					test[i][j] = hi[p+r];
				
				}
				System.out.println("--");
				
			}
		
			if(r==6)
			{
				
				r=-3;
			}
		}
		return test;
	}
	
	public boolean checkFullBoard()
	{
		
//		System.out.println(getRows());
		TextField[][] rows = getRows();
		for(int i = 0;i<9;++i)
		{
			for(int j=0;j<9;++j)
			{
				System.out.print(rows[i][j].getText()+"|");
			}
			System.out.println();
		}
		boolean valid = true; 
		/*
		
		for(int i = 0;i<9;++i)
		{//Does all rows
			if(!checkArrSolution(rows[i]))
			{
				valid = false;
				i = 10;
			}
			
		}
		
		
		for(int i = 0;i<9;++i)
		{//Does all boxes
			if(!checkArrSolution(Boxes[i].getChildren().toArray(new TextField[0]))){
				valid = false;
				i = 10;
			}
		}	*/
		return valid;
	}
	
	

	
	private boolean checkArrSolution(TextField[] arr)
	{//Given an array it will make sure there is 1-9 with no repeats and none missing
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
