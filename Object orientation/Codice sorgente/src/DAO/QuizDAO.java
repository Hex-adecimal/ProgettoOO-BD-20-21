package DAO;

public interface QuizDAO {
	public String getQuestion(String codQuiz);
	public void setQuestion(String codQuiz, String question);
	
	public void deleteQuizzes(String codTest);
}
