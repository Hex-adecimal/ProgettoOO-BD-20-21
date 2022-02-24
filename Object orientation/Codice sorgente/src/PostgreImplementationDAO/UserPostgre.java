package PostgreImplementationDAO;

import java.sql.*;

import DAO.UserDAO;
import Database.QuizDBConnection;
import Model.Professor;
import Model.Student;
import Model.User;

public class UserPostgre implements UserDAO{
	private Connection conn = null;
	
	public UserPostgre()
	{
		try { conn = QuizDBConnection.getInstance().getConnection(); } 
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	@Override
	public String logUser(String userInput, String password, String kindOfUser) {
		try
		{
			Statement stmt = conn.createStatement();
			ResultSet rs;
			String queryUsername, queryEmail, ris;
			
			queryUsername = "SELECT * FROM " + kindOfUser + " WHERE Username = '" + userInput + "' AND Pw = '" + password + "' ;";
			queryEmail = "SELECT * FROM " + kindOfUser + " WHERE Email = '" + userInput + "' AND Pw = '" + password + "' ;";
			
			if (userInput.contains("@"))
				rs = stmt.executeQuery(queryEmail);
			else 
				rs = stmt.executeQuery(queryUsername);
			
			if (rs.next() == false) {
				System.out.println("Error, no user has been found. Try again!");
				return null;
			}
			
			ris = rs.getString("firstname") + "|" + rs.getString("lastname") + "|" + rs.getString("email") + "|" +
					rs.getString("username") + "|" + rs.getString("pw") + "|";
			
			if (kindOfUser == "Student") 
				ris = ris + rs.getString("StudentID");
			else
				ris = ris + rs.getString("CodP");
			
			return ris;
		} catch(SQLException e) { e.printStackTrace(); return null; }
	}

	@Override
	public String registerUser(String firstName, String lastName, String username, String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFirstName(int codUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLastName(int codUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEmail(int codUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername(int codUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword(int codUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setFirstName(int codUser, String firstName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Void setLastName(int codUser, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setEmail(int codUser, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setUsername(int codUser, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setPassword(int codUser, String password) {
		// TODO Auto-generated method stub
		return null;
	}
}
