package models;

public class Score {
	
	String name;
	int score;
	public String getName() {
		return name;
	}
	public int getScore() {
		return score;
	}

	public Score(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	@Override
	public String toString()
	{
		return score+" : "+name;
	}
	
	

}
