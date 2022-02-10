package DAO;

import Model.ClosedAnswer;

public interface ClosedAnswerDAO {
	public Void reviseClosedAnswer(ClosedAnswer answer);

	public char getGivenAnswer(int codClosedAnswer);
	public float getScore(int codClosedAnswer);
	
	public Void setGivenAnswer(int codClosedAnswer, char givenAnswer);
	public Void setScore(int codClosedAnswer, float score);
}
