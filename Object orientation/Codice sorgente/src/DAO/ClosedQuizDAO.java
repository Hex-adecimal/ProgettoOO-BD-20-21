package DAO;

import java.util.ArrayList;

import Model.ClosedQuiz;
import Model.OpenQuiz;

public interface ClosedQuizDAO extends QuizDAO {
	// Answer A B C D - rightanswer - scoreifright - scoreifwrong
	public String getAnswerA(int codClosedQuiz);
	public String getAnswerB(int codClosedQuiz);
	public String getAnswerC(int codClosedQuiz);
	public String getAnswerD(int codClosedQuiz);
	public String getRightAnswer(int codClosedQuiz);
	public String getScoreIfRight(int codClosedQuiz);
	public String getScoreIfWrong(int codClosedQuiz);
	
	public void setAnswerA(String codQuiz, String answerA);
	public void setAnswerB(String codQuiz, String answerB);
	public void setAnswerC(String codQuiz, String answerC);
	public void setAnswerD(String codQuiz, String answerD);
	public void setRightAnswer(String codQuiz, char rightAnswer);
	public void setScoreIfRight(String codQuiz, float scoreIfRight);
	public void setScoreIfWrong(String codQuiz, float scoreIfWrong);
	
	public void insertClosedQuiz(String question,
								String answerA,
								String answerB,
								String answerC,
								String answerD,
								char rightAnswer,
								float scoreIfRight,
								float scoreIfWrong,
								String codTest);
	public ArrayList<ClosedQuiz> getClosedQuizzes(String codTest);
}
