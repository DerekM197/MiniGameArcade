package application;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Score;

public class SudokuMaster {
	@FXML
	private Stage window;
	
	@FXML
	private GridPane MainBox;
	@FXML
	private TextField Complete;
	@FXML
	private TextField timer;
	@FXML
	private TextField score;
	@FXML
	private TextField user;
	@FXML
	private Label LowScore1;
	@FXML
	private Label LowScore2;	
	@FXML
	private Label LowScore3;
	@FXML
	private Button MainMenu;
	private GridPane[] Boxes;
	private TextField[][] TextBoard;
	private boolean haveWon = false;
	private int multi = 1;
	private int time = 0;
	private boolean onTimer = true;
	
	public void mainMenu()
	{
		haveWon = true;
		onTimer = false;
		MainMenu menu = new MainMenu();	
		try {
			Stage stage = (Stage)MainMenu.getScene().getWindow();
			menu.start(stage);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
	
	public void initialize(){
		Boxes = MainBox.getChildren().toArray(new GridPane[0]);
		TextBoard = getBoard();
		generateSquareBoard();
		updateScoreList(readScores());
		th.start();
	}
	
	Thread th = new Thread(){
		public void run(){
			while(onTimer)
			{
				if(!haveWon)
				{
					update();
				}
				try {
					th.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		private void update()
		{
			++time;
			timer.setText("Time: "+time);
			score.setText("Score: "+(time*multi));
		}
	};
		
	private void updateScoreList(ArrayList<Score> score2)
	{
		sort(score2);
		switch(score2.size()){	
			case(3) :{
				String lowScore = score2.get(score2.size()-3).toString();
				LowScore3.setText(lowScore.substring(0,lowScore.length()-1));
			}
			case(2): {
				String lowScore = score2.get(score2.size()-2).toString();
				LowScore2.setText(lowScore.substring(0,lowScore.length()-1));
			}
			case(1): {
				String lowScore = score2.get(score2.size()-1).toString();
				LowScore1.setText(lowScore.substring(0,lowScore.length()-1));
				break;
			}
			case(0): {
				break;
			}
			default:{
				String lowScore = score2.get(score2.size()-1).toString();
				LowScore1.setText(lowScore.substring(0,lowScore.length()-1));
					lowScore = score2.get(score2.size()-2).toString();
				LowScore2.setText(lowScore.substring(0,lowScore.length()-1));
					lowScore = score2.get(score2.size()-3).toString();
				LowScore3.setText(lowScore.substring(0,lowScore.length()-1));
			}
		}	
	}
	
	private Score addNewScore()
	{
		user.requestFocus();
		int score = time*multi;
		String name = user.getText();
		user.editableProperty().set(false);
		user.setOpacity(0);
		
		return new Score(name,score);
	}
	
	public void scoreProcess()
	{
		if(user.isEditable()){
			ArrayList<Score> score = readScores();
			score.add(addNewScore());
			score = sort(score);
			updateScoreList(score);
			saveScores(score);
		}
	}
	
	private static ArrayList<Score> sort(ArrayList<Score> scores)
	{
        Score temp;
        for (int i = 1; i < scores.size(); ++i) 
        {
            for(int j = i ; j > 0 ; --j)
            {
                if(scores.get(j).getScore()> scores.get(j-1).getScore()){
                    temp = scores.get(j);
                    scores.set(j,scores.get(j-1));
                    scores.set(j-1, temp);
                }
            }
        }			
		return scores;
	}
	
	private String getScoreString()
	{
		try {
			return new String(Files.readAllBytes(Paths.get("LowScores.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String("error");
	}
	
	private ArrayList<Score> readScores()
	{
		ArrayList<Score> scores = new ArrayList<>(10);
		String[] arrScoreSting= getScoreString().split("\n");
		if(arrScoreSting.length >0 && arrScoreSting[0].length()>0)
		{
			
			for(int i = 0;i<arrScoreSting.length;++i)
			{
				String[] param = arrScoreSting[i].split(" : ");
				scores.add(new Score(param[1],Integer.parseInt(param[0])));
			}
		}
		return scores;
	}
	
	private void saveScores(ArrayList<Score> scores)
	{
		try {
			FileWriter fi = new FileWriter("LowScores.txt",false);
			StringBuilder str = new StringBuilder();
			for(int i = 0;i<scores.size();++i)
			{
				str.append(scores.get(i).toString());
				str.append("\n");
			}
			fi.write(str.toString());
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void testing(){
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
		time = 0;
		clearBoard();
		Random randy = new Random();
		
		for(int i = 0;i<9;++i)
		{
			int j = randy.nextInt(9);
			int k = randy.nextInt(9);
			TextBoard[j][k].setText(i+1+""); 
			TextBoard[j][k].editableProperty().set(false);
		}
		timer.setText("Time: "+time);
	}
	
	public void generateSquareBoard()
	{	
		time = 0;
		haveWon = false;
		clearBoard();
		ArrayList<Integer> numbs = new ArrayList<>(10);
		for(int i = 1;i<10;++i)
		{
			//ystem.out.println(i);
			numbs.add(i);
		}
		Random randy = new Random();
		for(int i = 0;i<9;++i)
		{
			TextField[] arr = Boxes[i].getChildren().toArray(new TextField[0]);
			int pick = randy.nextInt(numbs.size());
			arr[4].setText(numbs.get(pick)+"");
			numbs.remove(pick);
			arr[4].editableProperty().set(false);	
		}
	}
	
	
	public void checkBoard()
	{
		Complete.setText(checkFullBoard()? "You Win!!" : "Not Complete");	
		Complete.setText("You Win!!");
		if(Complete.getText().equals("You Win!!"));
		{
			haveWon = true;
			user.editableProperty().set(true);
			user.setOpacity(100);
		}
	}
		
	private boolean checkFullBoard()
	{
		boolean valid = true; 
		TextField[][] columns = getColumns();
		for(int i = 0;i<9;++i)
		{//Does all columns
			if(!checkArrSolution(columns[i]))
			{
				valid = false;
				i = 10;
			}	
		}
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
			if(valid)
			{
				for(int i = 0;i<9;++i)
				{//Does all boxes
					if(!checkArrSolution(Boxes[i].getChildren().toArray(new TextField[0]))){
					
						valid = false;
						i = 10;
					}
				}
			}
		}
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
				multi+=0.1;
				//System.out.println("NumberFormatException");
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
		//System.out.println("valid: "+valid);
		if(!valid)
		{
			multi+=0.1;
		}
		return valid;
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
		for(int i =0,r=0;i<9;++i,r+=3){
			for(int j = 0,k=0;j<9;++k){
				if(i!=0&&i>5&&k==0){
					k+=6;
				}else if(i!=0&&i>2&&k==0){
					k+=3;
				}
				TextField[] hi = boxe[k].getChildren().toArray(new TextField[0]);
				for(int p = 0;p<3;++p,++j){
					columns[j][i] = hi[p+r];
				
				}
			}
			if(r==6){
				r=-3;
			}
		}
		return columns;
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