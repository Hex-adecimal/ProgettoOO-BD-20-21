package PostgreImplementationDAO;

import java.sql.Connection;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DAO.StudentDAO;
import Database.QuizDBConnection;
import Model.Test;
import Model.TestTaken;

public class StudentPostgre implements StudentDAO {
	private Connection conn = null;
	
	public StudentPostgre() {
		try { conn = QuizDBConnection.getInstance().getConnection(); }
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	@Override
	public String registerUser(String firstName, String lastName, String username, String email, String password) {
		String error = null;
		
		try { 
			Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rsUsername;
			ResultSet rsEmail;
			
			String checkUsername = "SELECT Username FROM STUDENT WHERE Username = '" + username + "';";
			rsUsername = stmt1.executeQuery(checkUsername);
			
			String checkEmail = "SELECT Email FROM STUDENT WHERE Email = '" + email + "';";
			rsEmail = stmt2.executeQuery(checkEmail);
			
			if(!rsUsername.next() && !rsEmail.next()) {
				String query = "INSERT INTO STUDENT(FirstName, LastName, Username, Email, Pw) VALUES ('"
						+ firstName + "', '" + lastName + "', '" + username + "', '" + email + "', '" + password + "');";			
				stmt1.executeUpdate(query);
				System.out.println("You did it, you son of a bi***!");
			} else {
				error = "Registration failed! User with the following credentials already exists:";
				
				if(rsUsername.first())	error += "\n\t- Username";
				if(rsEmail.first())		error += "\n\t- Email";
			}
			
			stmt1.close();
			stmt2.close();
		} catch (Exception e) { e.printStackTrace(); }
		
		return error;
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
	
	@Override
	public Void setFirstName(String codUser, String firstName) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "UPDATE STUDENT SET firstname = '" + firstName + "' WHERE StudentID = " + codUser + ";";			
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public Void setLastName(String codUser, String lastName) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "UPDATE STUDENT SET lastname = '" + lastName + "' WHERE StudentID = " + codUser + ";";			
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public Void setEmail(String codUser, String email) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "UPDATE STUDENT SET email = '" + email + "' WHERE StudentID = " + codUser + ";";			
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public Void setUsername(String codUser, String username) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "UPDATE STUDENT SET username = '" + username + "' WHERE StudentID = " + codUser + ";";			
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public Void setPassword(String codUser, String password) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "UPDATE STUDENT SET pw = '" + password + "' WHERE StudentID = " + codUser + ";";			
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}
	
	
	public String getStudentIDbyUsername(String username) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "SELECT StudentID FROM STUDENT WHERE username = '" + username + "';";			
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			return rs.getString("StudentID");
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}
	
}
