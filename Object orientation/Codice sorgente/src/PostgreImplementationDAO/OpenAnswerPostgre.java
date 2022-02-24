package PostgreImplementationDAO;

import java.sql.Connection;
import java.sql.SQLException;

import DAO.OpenAnswerDAO;
import Database.QuizDBConnection;

public class OpenAnswerPostgre implements OpenAnswerDAO {
	private Connection connection;
	
	public OpenAnswerPostgre() {
		try { connection = QuizDBConnection.getInstance().getConnection(); }
		catch (SQLException e){ e.printStackTrace(); }
	}
	
	@Override
	public String getGivenAnswer(int codOpenAnswer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getScore(int codOpenAnswer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Void setGivenAnswer(int codOpenAnswer, String givenAnswer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setScore(int codOpenAnswer, float score) {
		// TODO Auto-generated method stub
		return null;
	}

}
