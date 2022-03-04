package PostgreImplementationDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import DAO.TestDAO;
import Database.QuizDBConnection;
import Model.Class;
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
	public String getName(int codTest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getCreationDate(int codTest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Time getStartingDateTime(int codTest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Time getClosingDateTime(int codTest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getMinScore(int codTest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Void setName(int codTest, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setStartingDateTime(int codTest, Time startingDatetime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setClosingDateTime(int codTest, Time closingDatetime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setMinScore(int codTest, float minScore) {
		// TODO Auto-generated method stub
		return null;
	}
}
