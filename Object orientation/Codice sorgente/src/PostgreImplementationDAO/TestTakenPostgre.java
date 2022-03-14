package PostgreImplementationDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.TestTakenDAO;
import Database.QuizDBConnection;

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
