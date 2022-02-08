package DAO;

public interface OpenQuizDAO extends QuizDAO {
	// maxscore - minscore - maxlength
	public float getMaxScore(int codOpenQuiz);
	public float getMinScore(int codOpenQuiz);
	public int getMaxLength(int codOpenQuiz);
	
	public Void setMaxScore(int codOpenQuiz, float maxScore);
	public Void setMinScore(int codOpenQuiz, float minScore);
	public Void setMaxLength(int codOpenQuiz, int maxLength);
}
