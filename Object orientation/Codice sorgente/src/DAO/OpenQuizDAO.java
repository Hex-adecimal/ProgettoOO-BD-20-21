package DAO;

import java.util.ArrayList;

import Model.OpenQuiz;

public interface OpenQuizDAO extends QuizDAO {
	// maxscore - minscore - maxlength
	public void setMaxScore(String codQuiz, float maxScore);
	public void setMinScore(String codQuiz, float minScore);
	public void setMaxLength(String codQuiz, int maxLength);
	
	public void insertOpenQuiz(String question, 
								float maxScore, 
								float minScore, 
								int maxLength, 
								String codTest);
	public ArrayList<OpenQuiz> getOpenQuizzes(String codTest);
}
