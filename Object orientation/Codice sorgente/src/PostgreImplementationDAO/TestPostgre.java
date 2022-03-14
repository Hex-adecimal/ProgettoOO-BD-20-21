package PostgreImplementationDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.TestDAO;
import Database.QuizDBConnection;
import Model.Test;

public class TestPostgre implements TestDAO {
	private Connection connection;
	
	public TestPostgre() {
		try { connection = QuizDBConnection.getInstance().getConnection(); }
		catch (SQLException e){ e.printStackTrace(); }
	}
	
	public ArrayList<Test> getProfessorTests(String codP)
	{
		ArrayList<Test> tests = new ArrayList<Test>();
		
		try
		{
			Statement stmt = connection.createStatement();
			ResultSet rs;
			String query = "SELECT codtest, name, creationdatetime, startingdatetime, closingdatetime, minscore FROM TEST WHERE codp = '" + codP + "';";
			
			rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				tests.add(new Test(rs.getString("codtest"),
									rs.getString("name"), 
									rs.getDate("creationdatetime"),
									rs.getTime("creationdatetime"),
									rs.getDate("startingdatetime"),
									rs.getTime("startingdatetime"),
									rs.getDate("closingdatetime"),
									rs.getTime("closingdatetime"), 
									rs.getFloat("minscore")));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return tests;
	}
	
	@Override
	public String getCodTestByName(String name)
	{
		String codTest = "0";
		
		try
		{
			Statement stmt = connection.createStatement();
			ResultSet rs;
			
			String query = "SELECT codtest FROM TEST WHERE name = '" + name + "';";
			
			rs = stmt.executeQuery(query);
			
			if(rs.next())	codTest = rs.getString("codtest");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return codTest;
	}
	
	@Override
	public void insertNewTest(String name, 
							String creationDate, 
							String creationTime, 
							String startingDate, 
							String startingTime, 
							String closingDate, 
							String closingTime, 
							float minScore,
							String codP)
	{
		try
		{
			Statement stmt = connection.createStatement();
			
			String query = "INSERT INTO TEST(name, creationdatetime, startingdatetime, closingdatetime, minscore, codp) VALUES('" +
							name + "', '" +
							creationDate + " " + creationTime + "', '" +
							startingDate + " " + startingTime + "', '" +
							closingDate + " " + closingTime + "', " +
							minScore + ", " +
							codP + ");";
			
			stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteTest(String codTest)
	{
		try
		{
			Statement stmt = connection.createStatement();
			
			String query = "DELETE FROM TEST WHERE codtest = " + codTest + ";";
			
			stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void setName(String codTest, String name) {
		try
		{
			Statement stmt = connection.createStatement();
			
			String query = "UPDATE TEST SET name = '" + name + "'" +
							" WHERE codtest = " + codTest + ";";
			
			stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void setStartingDateTime(String codTest, String startingDateTime) {
		try
		{
			Statement stmt = connection.createStatement();
			
			String query = "UPDATE TEST SET startingdatetime = '" + startingDateTime + "'" + 
							" WHERE codtest = " + codTest + ";";
			
			stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void setClosingDateTime(String codTest, String closingDateTime) {
		try
		{
			Statement stmt = connection.createStatement();
			
			String query = "UPDATE TEST SET closingdatetime = '" + closingDateTime + "'" + 
							" WHERE codtest = " + codTest + ";";
			
			stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void setMinScore(String codTest, float minScore) {
		try
		{
			Statement stmt = connection.createStatement();
			
			String query = "UPDATE TEST SET minscore = " + minScore + 
							" WHERE codtest = " + codTest + ";";
			
			stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public Boolean testExists(String codTest) {
		String query = "SELECT * FROM TEST WHERE CodTest = " + codTest + ";";
		ResultSet rs;
		
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			
			return rs.next();
		} catch (Exception e) { e.printStackTrace(); }
		return false;
	}
	
	public ResultSet getOpenQuiz(String codTest) {
		String query = "SELECT * "
				+ "FROM  OPEN_QUIZ "
				+ "WHERE codTest = " + codTest + ";";
		try { 
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}
	
	public ResultSet getClosedQuiz(String codTest) {
		String query = "SELECT * "
				+ "FROM  CLOSED_QUIZ "
				+ "WHERE codTest = " + codTest + ";";
		try { 
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}
}
