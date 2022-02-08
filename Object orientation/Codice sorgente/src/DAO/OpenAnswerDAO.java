package DAO;

public interface OpenAnswerDAO {
	// given answer - score
	public String getGivenAnswer(int codOpenAnswer);
	public float getScore(int codOpenAnswer);
	
	public Void setGivenAnswer(int codOpenAnswer, String givenAnswer);
	public Void setScore(int codOpenAnswer, float score);
}
