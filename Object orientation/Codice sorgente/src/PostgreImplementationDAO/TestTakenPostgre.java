package PostgreImplementationDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import DAO.TestDAO;
import DAO.TestTakenDAO;
import Database.QuizDBConnection;
import Model.Test;
import Model.TestTaken;

public class TestTakenPostgre implements TestTakenDAO{
	private Connection connection;
	
	public TestTakenPostgre() {
		try { connection = QuizDBConnection.getInstance().getConnection(); }
		catch (SQLException e){ e.printStackTrace(); }
	}
	
	public ArrayList<String> getTestsTakenByStudent(int studentID) {
		String query = "select * from test_taken tt join test t on t.codTest = tt.codTest where StudentID = " + studentID + ";";
		
		ResultSet rs;
		ArrayList<String> v = new ArrayList<String>();
		
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				if (rs.getBoolean("revised") == true)
					v.add(rs.getString("name") + " --- " + rs.getBoolean("revised") + " --- " + rs.getBoolean("passed") + " --- " + rs.getFloat("totalScore"));
				else 
					v.add(rs.getString("name") + " --- "  + rs.getBoolean("revised"));
			}
			
		} catch (Exception e) { e.printStackTrace(); }
		return v;
	}
	
	/*@Override
	public ArrayList<Test> getProfessorTests(String codP)
	{
		return null;
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
	}*/

	@Override
	public boolean isRevised(int codTestTaken) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPassed(int codTestTaken) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getTotalScore(int codTestTaken) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Void setRevised(int codTestTaken, boolean revised) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setPassed(int codTestTaken, boolean passed) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setTotalScore(int codTestTaken, float totalScore) {
		// TODO Auto-generated method stub
		return null;
	}

}
