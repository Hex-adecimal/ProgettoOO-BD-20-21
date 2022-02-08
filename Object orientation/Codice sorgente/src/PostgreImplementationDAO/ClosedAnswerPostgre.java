package PostgreImplementationDAO;

import DAO.ClosedAnswerDAO;

public class ClosedAnswerPostgre implements ClosedAnswerDAO{

	@Override
	public char getGivenAnswer(int codClosedAnswer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getScore(int codClosedAnswer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Void setGivenAnswer(int codClosedAnswer, char givenAnswer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setScore(int codClosedAnswer, float score) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void reviseClosedAnswer(Model.ClosedAnswer answer) {
		// TODO Auto-generated method stub
		return null;
	}	
}
