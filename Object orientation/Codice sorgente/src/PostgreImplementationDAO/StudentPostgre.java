package PostgreImplementationDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DAO.StudentDAO;
import Database.QuizDBConnection;
import Model.Student;
import Model.Test;
import Model.TestTaken;
import Model.Student;
import Model.User;

public class StudentPostgre implements StudentDAO {
	private Connection conn = null;
	
	
	public StudentPostgre() {
		try {
			conn = QuizDBConnection.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Student registerUser(String firstName, String lastName, String username, String email, String password) {
		Student student = null;
		try { 
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO STUDENT VALUES (" + firstName + ", " + lastName + ", " + username + ", " + email + "," + password + ")";			
			ResultSet rs = stmt.executeQuery(query);
			
			student = new Student(rs.getString("StudentID"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("username"),
					rs.getString("email"), rs.getString("password"));
			
		} catch (Exception e) { e.printStackTrace(); }
		
		return student;
	}

	@Override
	public Student logUser(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int turnInTest(Test test) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public ResultSet viewTestDone(int studentID) {
		ResultSet rs = null;
		
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT CodTestTaken FROM TESTTAKEN WHERE StudentID = " + studentID + ";";
			rs = stmt.executeQuery(query);
			
		} catch (Exception e) { e.printStackTrace(); }
		return rs;
	}
	
	@Override
	public TestTaken viewTestResult(int codTestTaken) {
		TestTaken tt = null;
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM TESTTAKEN WHERE CodT = " + codTestTaken + ";";
			ResultSet rs = stmt.executeQuery(query);
			
			tt = new TestTaken(rs.getBoolean("revised"));
			if (tt.isPassed() == true) {
				tt.setPassed(rs.getBoolean("passed"));
				tt.setTotalScore(rs.getFloat("totalscore"));
			} else { tt = null; }
			
		} catch (Exception e) { e.printStackTrace(); }
		return tt;
	}
	
	// Getter & Setter
	@Override
	public String getFirstName(int codUser) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "SELECT firstname FROM STUDENT WHERE StudentID = " + codUser + ");";			
			ResultSet rs = stmt.executeQuery(query);
			return rs.toString();
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public String getLastName(int codUser) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "SELECT lastname FROM STUDENT WHERE StudentID = " + codUser + ");";			
			ResultSet rs = stmt.executeQuery(query);
			return rs.toString();
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public String getEmail(int codUser) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "SELECT email FROM STUDENT WHERE StudentID = " + codUser + ");";			
			ResultSet rs = stmt.executeQuery(query);
			return rs.toString();
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public String getUsername(int codUser) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "SELECT username FROM STUDENT WHERE StudentID = " + codUser + ");";			
			ResultSet rs = stmt.executeQuery(query);
			return rs.toString();
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public String getPassword(int codUser) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "SELECT password FROM STUDENT WHERE StudentID = " + codUser + ");";			
			ResultSet rs = stmt.executeQuery(query);
			return rs.toString();
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public Void setFirstName(int codUser, String firstName) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "UPDATE STUDENT SET firstname = " + firstName + " WHERE StudentID = " + codUser + ";";			
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public Void setLastName(int codUser, String lastName) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "UPDATE STUDENT SET lastname = " + lastName + " WHERE StudentID = " + codUser + ";";			
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public Void setEmail(int codUser, String email) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "UPDATE STUDENT SET email = " + email + " WHERE StudentID = " + codUser + ";";			
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public Void setUsername(int codUser, String username) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "UPDATE STUDENT SET username = " + username + " WHERE StudentID = " + codUser + ";";			
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public Void setPassword(int codUser, String password) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "UPDATE STUDENT SET firstname = " + password + " WHERE StudentID = " + codUser + ";";			
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}
}
