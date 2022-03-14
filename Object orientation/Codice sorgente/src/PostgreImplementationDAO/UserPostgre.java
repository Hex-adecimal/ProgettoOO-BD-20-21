package PostgreImplementationDAO;

import java.sql.*;

import DAO.UserDAO;
import Database.QuizDBConnection;

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
}
