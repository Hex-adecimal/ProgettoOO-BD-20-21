package PostgreImplementationDAO;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import DAO.ProfessorDAO;
import Database.QuizDBConnection;
import Model.Class;
import Model.OpenAnswer;
import Model.Test;

public class ProfessorPostgre implements ProfessorDAO {
	private Connection conn = null;
	
	public ProfessorPostgre() {
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
			
			String checkUsername = "SELECT Username FROM PROFESSOR WHERE Username = '" + username + "';";
			rsUsername = stmt1.executeQuery(checkUsername);
			
			String checkEmail = "SELECT Email FROM PROFESSOR WHERE Email = '" + email + "';";
			rsEmail = stmt2.executeQuery(checkEmail);
			
			if(!rsUsername.next() && !rsEmail.next())
			{
				String query = "INSERT INTO PROFESSOR(FirstName, LastName, Username, Email, Pw) VALUES ('"
						+ firstName + "', '" + lastName + "', '" + username + "', '" + email + "', '" + password + "');";			
				stmt1.executeUpdate(query);
				System.out.println("You did it, you son of a bi***!");
			}
			else
			{
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
	public int reviseOpenAnswer(OpenAnswer codAnswer, float score) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "UPDATE OPEN_ANSWER SET Score = " + score + "WHERE CodOA = " + codAnswer + ";";
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return 0;
	}
	
	@Override
	public Void setFirstName(String codUser, String firstName) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "UPDATE PROFESSOR SET firstname = '" + firstName + "' WHERE CodP = " + codUser + ";";			
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}
		
	@Override
	public Void setLastName(String codUser, String lastName) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "UPDATE PROFESSOR SET lastname = '" + lastName + "' WHERE CodP = " + codUser + ";";			
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}
	
	@Override
	public Void setEmail(String codUser, String email) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "UPDATE PROFESSOR SET email = '" + email + "' WHERE CodP = " + codUser + ";";			
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public Void setUsername(String codUser, String username) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "UPDATE PROFESSOR SET username = '" + username + "' WHERE CodP = " + codUser + ";";			
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public Void setPassword(String codUser, String password) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "UPDATE PROFESSOR SET password = '" + password + "' WHERE CodP = " + codUser + ";";			
			stmt.executeQuery(query);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}
	
	public String getCodPbyUsername(String username) {
		try { 
			Statement stmt = conn.createStatement();
			String query = "SELECT CodP FROM PROFESSOR WHERE username = '" + username + "';";			
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			return rs.getString("CodP");
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}
}
