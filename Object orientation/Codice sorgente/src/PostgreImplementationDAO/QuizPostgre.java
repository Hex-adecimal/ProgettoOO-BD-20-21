package PostgreImplementationDAO;

import java.sql.Connection;
import java.sql.SQLException;

import DAO.QuizDAO;
import Database.QuizDBConnection;

public class QuizPostgre implements QuizDAO {
	private Connection connection;
	
	public QuizPostgre() {
		try { connection = QuizDBConnection.getInstance().getConnection(); }
		catch (SQLException e){ e.printStackTrace(); }
	}
	
	@Override
	public String getQuestion(String codQuiz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setQuestion(String codQuiz, String question) {
		
	}
	
	public void deleteQuizzes(String codTest)
	{
		
	}

}
