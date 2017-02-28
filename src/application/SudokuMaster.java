package application;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class SudokuMaster {
	
	@FXML
	private GridPane MainBox;
	@FXML
	private TextField TextMM72;
	@FXML
	private TextField timer;
	private GridPane[] Boxes;
	private TextField[][] TextBoard;
	
	public void initialize(){
		//System.out.println("StuffAndThings");
		Boxes = MainBox.getChildren().toArray(new GridPane[0]);
		
		TextBoard = getBoard();
		time();
		
	}
	
	
	int time = 0;
	Timer ti = new Timer();
	TimerTask task = new TimerTask()
	{
		@Override
		public void run() { 
			++time;
			timer.setText("Time: "+time);
		}
	};
	
	public void time()
	{
		ti.scheduleAtFixedRate(task, 1000, 1000);
	}
	
	private TimerTask test()
	{
		System.out.println("test");
		return null;
	}
	
	public void testing()
	{
		String[] nums = {"1","2","3","4","5","6","7","8","9"};
		for(int i = 0;i<9;++i)
		{
			for(int j = 0;j<9;++j)
			{
				if(TextBoard[i][j].getText().length()>1)
 				{
					TextBoard[i][j].setText(TextBoard[i][j].getText().substring(TextBoard[i][j].getText().length()-1));
				}
				String text = TextBoard[i][j].getText();
				boolean valid = false;
				for(int k = 0;k<9;++k)
				{
					if(text.equals(nums[k]))
					{
						valid = true;
						k=10;
					}
				}
				if(!valid)
				{
					TextBoard[i][j].setText("");
				}
				
			}
		}
	}
	
	private TextField[][] getBoard()
	{
		GridPane[] board = MainBox.getChildren().toArray(new GridPane[0]);
		TextField[][] field = new TextField[9][9]; 
		for(int i =0;i<9;++i)
		{
			field[i] = board[i].getChildren().toArray(new TextField[0]);
		}
		return field;
	}
	
	private void clearBoard()
	{
		for(int i = 0;i<9;++i)
		{
			for(int j = 0;j<9;++j)
			{
				TextBoard[i][j].setText("");
				TextBoard[i][j].editableProperty().set(true);
			}
		}
	}
	
	public void generateNewBoard()
	{
		clearBoard();
		Random randy = new Random();
		for(int i = 0;i<9;++i)
		{
			int j = randy.nextInt(9);
			int k = randy.nextInt(9);
			TextBoard[j][k].setText(i+1+""); 
			TextBoard[j][k].editableProperty().set(false);
			TextBoard[j][k].applyCss();
			
		}
	}
	
	private TextField[][] getRows()
	{
		TextField[][] rows = new TextField[9][9];
		GridPane[] boxe =  MainBox.getChildren().toArray(new GridPane[0]);
		for(int i =0,r=0;i<9;++i,r+=3)
		{
			for(int j = 0,k=0;j<9;++k)
			{
				if(i!=0&&i>5&&k==0)
				{
					k+=6;
				}else if(i!=0&&i>2&&k==0)
				{
					k+=3;
				}
				TextField[] hi = boxe[k].getChildren().toArray(new TextField[0]);
				for(int p = 0;p<3;++p,++j)
				{
					//System.out.println("I: "+i+" R: "+r+" J: "+j+" K: "+k+" P: "+p+" C: "+count++);
					rows[i][j] = hi[p+r];
				
				}
			}
			if(r==6)
			{
				
				r=-3;
			}
		}
		return rows;
	}
		
	private TextField[][] getColumns() {
		TextField[][] columns = new TextField[9][9];
		GridPane[] boxe =  MainBox.getChildren().toArray(new GridPane[0]);
		for(int i =0,r=0;i<9;++i,r+=3)
		{
			for(int j = 0,k=0;j<9;++k)
			{
				if(i!=0&&i>5&&k==0)
				{
					k+=6;
				}else if(i!=0&&i>2&&k==0)
				{
					k+=3;
				}
				TextField[] hi = boxe[k].getChildren().toArray(new TextField[0]);
				for(int p = 0;p<3;++p,++j)
				{
					columns[j][i] = hi[p+r];
				
				}
			}
			if(r==6)
			{
				
				r=-3;
			}
		}
		return columns;
	}
	
	public void checkBoard()
	{
		
		TextMM72.setText(checkFullBoard()? "You Win!!" : "Not complete");
	}
		
	private boolean checkFullBoard()
	{
		boolean valid = true; 
		
		TextField[][] columns = getColumns();
		System.out.println("calling columns");
		for(int i = 0;i<9;++i)
		{//Does all columns
			if(!checkArrSolution(columns[i]))
			{
				System.out.println("I: "+i);
				valid = false;
				i = 10;
			}	
			
		}
		System.out.println("calling rows");
		System.out.println("Valid: "+valid);
		if(valid)
		{
			TextField[][] rows = getRows();
			for(int i = 0;i<9;++i)
			{//Does all rows
				if(!checkArrSolution(rows[i]))
				{
					valid = false;
					i = 10;
				}		
			}
			System.out.println("Valid: "+valid);
			
			if(valid)
			{
				System.out.println("calling Boxes");
				for(int i = 0;i<9;++i)
				{//Does all boxes
					System.out.println("I: "+i);
					
					if(!checkArrSolution(Boxes[i].getChildren().toArray(new TextField[0]))){
					
						valid = false;
						i = 10;
					}
				}
			}
		}
		System.out.println("Valid: "+valid);
		return valid;
	}
	
	private boolean checkArrSolution(TextField[] arr)
	{//Given an array it will make sure there is 1-9 with no repeats and none missing
		ArrayList<Integer> ints = new ArrayList<>();
		for(int i = 1;i<10;++i){
			ints.add(i);
		}
		boolean valid= true;
		for(int i = 0;i< 8;++i)
		{
			boolean good = false;
			int input = -1;
			try{
			input = Integer.parseInt(arr[i].getText());
			}catch(NumberFormatException nfe){
				System.out.println("NumberFormatException");
				return false;
			}
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
		System.out.println("valid: "+valid);
		return valid;
	}
}

/*[0][1][2] [0][1][2] [6][7][8]
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