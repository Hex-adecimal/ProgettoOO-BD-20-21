package PostgreImplementationDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.OpenQuizDAO;
import Database.QuizDBConnection;
import Model.OpenQuiz;

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
	public void deleteQuizzes(String codTest)
	{
		try
		{
			Statement stmt = connection.createStatement();
			
			String query = "DELETE FROM OPEN_QUIZ WHERE codtest = " + codTest + ";";
			
			stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<OpenQuiz> getOpenQuizzes(String codTest)
	{
		ArrayList<OpenQuiz> openQuizzes = new ArrayList<OpenQuiz>();
		
		try
		{
			Statement stmt = connection.createStatement();
			ResultSet rs;
			String query = "SELECT codOQ, question, maxscore, minscore, maxlength " + 
							"FROM OPEN_QUIZ WHERE codtest = '" + codTest + "';";
			
			rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				openQuizzes.add(new OpenQuiz(rs.getString("codOQ"),
											rs.getString("question"),
											rs.getFloat("maxscore"),
											rs.getFloat("minscore"),
											rs.getInt("maxlength")));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return openQuizzes;
	}
	
	@Override
	public String getQuestion(String codQuiz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setQuestion(String codQuiz, String question) {
		try
		{
			Statement stmt = connection.createStatement();
			
			String query = "UPDATE OPEN_QUIZ SET question = '" + question + "'" +
							" WHERE codOQ = " + codQuiz + ";";
			
			stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public float getMaxScore(String codQuiz) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMinScore(String codQuiz) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxLength(String codQuiz) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaxScore(String codQuiz, float maxScore) {
		try
		{
			Statement stmt = connection.createStatement();
			
			String query = "UPDATE OPEN_QUIZ SET maxscore = " + maxScore + 
							" WHERE codOQ = " + codQuiz + ";";
			
			stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void setMinScore(String codQuiz, float minScore) {
		try
		{
			Statement stmt = connection.createStatement();
			
			String query = "UPDATE OPEN_QUIZ SET minscore = " + minScore +
							" WHERE codOQ = " + codQuiz + ";";
			
			stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void setMaxLength(String codQuiz, int maxLength) {
		try
		{
			Statement stmt = connection.createStatement();
			
			String query = "UPDATE OPEN_QUIZ SET maxlength = " + maxLength + 
							" WHERE codOQ = " + codQuiz + ";";
			
			stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

}
