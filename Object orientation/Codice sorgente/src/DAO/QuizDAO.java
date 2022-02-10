package DAO;

public interface QuizDAO {
	public String getQuestion(int codQuiz);
	
	public Void setQuestion(int codQuiz, String question);
}
