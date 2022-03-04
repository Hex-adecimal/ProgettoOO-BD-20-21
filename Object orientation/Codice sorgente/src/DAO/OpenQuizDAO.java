package DAO;

public interface OpenQuizDAO extends QuizDAO {
	// maxscore - minscore - maxlength
	public float getMaxScore(int codOpenQuiz);
	public float getMinScore(int codOpenQuiz);
	public int getMaxLength(int codOpenQuiz);
	
	public void setMaxScore(int codOpenQuiz, float maxScore);
	public void setMinScore(int codOpenQuiz, float minScore);
	public void setMaxLength(int codOpenQuiz, int maxLength);
	
	public void insertOpenQuiz(String question, 
								float maxScore, 
								float minScore, 
								int maxLength, 
								String codTest);
}
