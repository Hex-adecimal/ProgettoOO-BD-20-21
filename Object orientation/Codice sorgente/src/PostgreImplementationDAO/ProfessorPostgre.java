package PostgreImplementationDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

import DAO.ProfessorDAO;
import Model.Class;
import Model.Lecture;
import Model.OpenAnswer;
import Model.Professor;
import Model.Test;

public class ProfessorPostgre implements ProfessorDAO {
	private Connection conn = null;
	
	@Override 
	public Professor registerUser(String firstName, String lastName, String username, String email, String password) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO PROFESSOR(firstname, lastname, username, email, password) VALUES (" 
			+ firstName + ", " + lastName + ", " + username + ", " + email + "," + password + ");";			
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public Professor logUser(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Test createTest(String name, Time startingDateTime, Time closingDateTime, float minScore) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO TEST(name, startingdatetime, closingdatetime, minscore) VALUES (" 
			+ name + "," + startingDateTime + "," + closingDateTime + "," + minScore + ");";
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public Class createClass(String name, int cfu) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO CLASS(name, cfu) VALUES (" + name + "," + cfu + ");";
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public Lecture createLecture(String title, String link) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO LECTURE(title, link) VALUES (" + title + "," + link + ");";
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public int reviseOpenAnswer(OpenAnswer codAnswer, float score) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "UPDATE OPENANSWER SET Score = " + score + "WHERE CodOA = " + codAnswer + ";";
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return 0;
	}
	
	// Getter & Setter
	
	@Override
	public String getFirstName(int codUser) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "SELECT firstname FROM PROFESSOR WHERE CodP = " + codUser + ");";			
			ResultSet rs = stmt.executeQuery(query);
			return rs.toString();
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public String getLastName(int codUser) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "SELECT lastname FROM PROFESSOR WHERE CodP = " + codUser + ");";			
			ResultSet rs = stmt.executeQuery(query);
			return rs.toString();
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public String getEmail(int codUser) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "SELECT email FROM PROFESSOR WHERE CodP = " + codUser + ");";			
			ResultSet rs = stmt.executeQuery(query);
			return rs.toString();
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public String getUsername(int codUser) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "SELECT username FROM PROFESSOR WHERE CodP = " + codUser + ");";			
			ResultSet rs = stmt.executeQuery(query);
			return rs.toString();
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public String getPassword(int codUser) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "SELECT password FROM PROFESSOR WHERE CodP = " + codUser + ");";			
			ResultSet rs = stmt.executeQuery(query);
			return rs.toString();
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public Void setFirstName(int codUser, String firstName) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "UPDATE PROFESSOR SET firstname = " + firstName + " WHERE CodP = " + codUser + ";";			
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}
		
	@Override
	public Void setLastName(int codUser, String lastName) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "UPDATE PROFESSOR SET lastname = " + lastName + " WHERE CodP = " + codUser + ";";			
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}
	
	@Override
	public Void setEmail(int codUser, String email) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "UPDATE PROFESSOR SET email = " + email + " WHERE CodP = " + codUser + ";";			
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public Void setUsername(int codUser, String username) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "UPDATE PROFESSOR SET username = " + username + " WHERE CodP = " + codUser + ";";			
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public Void setPassword(int codUser, String password) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "UPDATE PROFESSOR SET password = " + password + " WHERE CodP = " + codUser + ";";			
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}
}
