package PostgreImplementationDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import DAO.OpenQuizDAO;
import Database.QuizDBConnection;

public class OpenQuizPostgre implements OpenQuizDAO {
	private Connection connection;
	
	public OpenQuizPostgre() {
		try { connection = QuizDBConnection.getInstance().getConnection(); }
		catch (SQLException e){ e.printStackTrace(); }
	}
	
	@Override
	public void insertOpenQuiz(String question, 
								float maxScore, 
								float minScore, 
								int maxLength, 
								String codTest)
	{
		try
		{
			Statement stmt = connection.createStatement();
			
			String query = "INSERT INTO OPEN_QUIZ(question, maxscore, minscore, maxlength, codtest) VALUES ('" +
							question + "', " +
							maxScore + ", " +
							minScore + ", " +
							maxLength + ", " +
							codTest + ");";
			
			stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public String getQuestion(int codQuiz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setQuestion(int codQuiz, String question) {
		
	}

	@Override
	public float getMaxScore(int codOpenQuiz) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMinScore(int codOpenQuiz) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxLength(int codOpenQuiz) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaxScore(int codOpenQuiz, float maxScore) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMinScore(int codOpenQuiz, float minScore) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMaxLength(int codOpenQuiz, int maxLength) {
		// TODO Auto-generated method stub
		
	}

}
