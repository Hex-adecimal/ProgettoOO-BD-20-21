package DAO;

public interface QuizDAO {
	public String getQuestion(int codQuiz);
	
	public void setQuestion(int codQuiz, String question);
}
