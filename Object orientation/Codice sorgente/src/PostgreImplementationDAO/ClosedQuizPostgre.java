package PostgreImplementationDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.ClosedQuizDAO;
import Database.QuizDBConnection;
import Model.ClosedQuiz;

public class ClosedQuizPostgre implements ClosedQuizDAO{
	private Connection connection;
	
	public ClosedQuizPostgre() {
		try { connection = QuizDBConnection.getInstance().getConnection(); }
		catch (SQLException e){ e.printStackTrace(); }
	}
	
	@Override
	public ArrayList<ClosedQuiz> getClosedQuizzes(String codTest)
	{
		ArrayList<ClosedQuiz> closedQuizzes = new ArrayList<ClosedQuiz>();
		
		try
		{
			Statement stmt = connection.createStatement();
			ResultSet rs;
			String query = "SELECT codCQ, question, answerA, answerB, answerC, answerD, " + 
							"rightanswer, scoreifright, scoreifwrong " + 
							"FROM CLOSED_QUIZ WHERE codtest = '" + codTest + "';";
			
			rs = stmt.executeQuery(query);
			
			char rightAnswer;
			
			while(rs.next())
			{
				if(rs.getString("rightanswer").equals("a"))
					rightAnswer = 'A';
				else if(rs.getString("rightanswer").equals("b"))
					rightAnswer = 'B';
				else if(rs.getString("rightanswer").equals("c"))
					rightAnswer = 'C';
				else
					rightAnswer = 'D';
				
				closedQuizzes.add(new ClosedQuiz(rs.getString("codCQ"),
												rs.getString("question"),
												rs.getString("answerA"),
												rs.getString("answerB"),
												rs.getString("answerC"),
												rs.getString("answerD"),
												rightAnswer,
												rs.getFloat("scoreifright"),
												rs.getFloat("scoreifwrong")));
			}
		}
		catch (SQLException e){ e.printStackTrace(); }
		
		return closedQuizzes;
	}
	
	@Override
	public void insertClosedQuiz(String question,
			String answerA,
			String answerB,
			String answerC,
			String answerD,
			char rightAnswer,
			float scoreIfRight,
			float scoreIfWrong,
			String codTest)
	{
		try
		{
			Statement stmt = connection.createStatement();
			
			String query = "INSERT INTO CLOSED_QUIZ" + 
							"(question, answerA, answerB, answerC, answerD," + 
							" rightanswer, scoreifright, scoreifwrong, codtest)" + 
							" VALUES " + 
							"('" + question + "', " + 
							"'" + answerA + "', " + 
							"'" + answerB + "', " +
							"'" + answerC + "', " +
							"'" + answerD + "', " +
							"'" + Character.toLowerCase(rightAnswer) + "', " +
							scoreIfRight + ", " + 
							scoreIfWrong + ", " +
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
			
			String query = "DELETE FROM CLOSED_QUIZ WHERE codtest = " + codTest + ";";
			
			stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void setQuestion(String codQuiz, String question) {
		try
		{
			Statement stmt = connection.createStatement();
			
			String query = "UPDATE CLOSED_QUIZ SET question = '" + question + "'" +
							" WHERE codCQ = " + codQuiz + ";";
			
			stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void setAnswerA(String codQuiz, String answerA) {
		try
		{
			Statement stmt = connection.createStatement();
			
			String query = "UPDATE CLOSED_QUIZ SET answerA = '" + answerA + "'" +
							" WHERE codCQ = " + codQuiz + ";";
			
			stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void setAnswerB(String codQuiz, String answerB) {
		try
		{
			Statement stmt = connection.createStatement();
			
			String query = "UPDATE CLOSED_QUIZ SET answerB = '" + answerB + "'" +
							" WHERE codCQ = " + codQuiz + ";";
			
			stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void setAnswerC(String codQuiz, String answerC) {
		try
		{
			Statement stmt = connection.createStatement();
			
			String query = "UPDATE CLOSED_QUIZ SET answerC = '" + answerC + "'" +
							" WHERE codCQ = " + codQuiz + ";";
			
			stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void setAnswerD(String codQuiz, String answerD) {
		try
		{
			Statement stmt = connection.createStatement();
			
			String query = "UPDATE CLOSED_QUIZ SET answerD = '" + answerD + "'" +
							" WHERE codCQ = " + codQuiz + ";";
			
			stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void setRightAnswer(String codQuiz, char rightAnswer) {
		try
		{
			Statement stmt = connection.createStatement();
			
			String query = "UPDATE CLOSED_QUIZ SET rightAnswer = '" + rightAnswer + "'" +
							" WHERE codCQ = " + codQuiz + ";";
			
			stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void setScoreIfRight(String codQuiz, float scoreIfRight) {
		try
		{
			Statement stmt = connection.createStatement();
			
			String query = "UPDATE CLOSED_QUIZ SET scoreIfRight = " + scoreIfRight +
							" WHERE codCQ = " + codQuiz + ";";
			
			stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void setScoreIfWrong(String codQuiz, float scoreIfWrong) {
		try
		{
			Statement stmt = connection.createStatement();
			
			String query = "UPDATE CLOSED_QUIZ SET scoreIfWrong = " + scoreIfWrong +
							" WHERE codCQ = " + codQuiz + ";";
			
			stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
}
