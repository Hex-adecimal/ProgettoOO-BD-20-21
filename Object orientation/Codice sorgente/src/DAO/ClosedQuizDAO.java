package DAO;

public interface ClosedQuizDAO extends QuizDAO {
	// Answer A B C D - rightanswer - scoreifright - scoreifwrong
	public String getAnswerA(int codClosedQuiz);
	public String getAnswerB(int codClosedQuiz);
	public String getAnswerC(int codClosedQuiz);
	public String getAnswerD(int codClosedQuiz);
	public String getRightAnswer(int codClosedQuiz);
	public String getScoreIfRight(int codClosedQuiz);
	public String getScoreIfWrong(int codClosedQuiz);
	
	public Void setAnswerA(int codClosedQuiz, String answerA);
	public Void setAnswerB(int codClosedQuiz, String answerB);
	public Void setAnswerC(int codClosedQuiz, String answerC);
	public Void setAnswerD(int codClosedQuiz, String answerD);
	public Void setRightAnswer(int codClosedQuiz, char rightAnswer);
	public Void setScoreIfRight(int codClosedQuiz, float scoreIfRight);
	public Void setScoreIfWrong(int codClosedQuiz, float scoreIfWrong);
	
	public void insertClosedQuiz(String question,
								String answerA,
								String answerB,
								String answerC,
								String answerD,
								char rightAnswer,
								float scoreIfRight,
								float scoreIfWrong,
								String codTest);
}
