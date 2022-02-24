package PostgreImplementationDAO;

import java.sql.Connection;
import java.sql.SQLException;

import DAO.ClosedAnswerDAO;
import Database.QuizDBConnection;

public class ClosedAnswerPostgre implements ClosedAnswerDAO{
	private Connection connection;
	
	public ClosedAnswerPostgre() {
		try { connection = QuizDBConnection.getInstance().getConnection(); }
		catch (SQLException e) { e.printStackTrace(); }
	}
	
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
