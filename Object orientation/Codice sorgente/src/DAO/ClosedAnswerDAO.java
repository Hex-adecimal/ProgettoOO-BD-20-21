package DAO;

public interface ClosedAnswerDAO {

	public char getGivenAnswer(int codClosedAnswer);
	public float getScore(int codClosedAnswer);
	
	public Void setGivenAnswer(int codClosedAnswer, char givenAnswer);
	public Void setScore(int codClosedAnswer, float score);
}
