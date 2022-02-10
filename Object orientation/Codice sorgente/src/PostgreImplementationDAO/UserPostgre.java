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
		try {
			conn = QuizDBConnection.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public User logUser(String userInput, String password) {
		try
		{
			Statement stmt = conn.createStatement();
			
			String queryS1 = "SELECT * FROM STUDENT WHERE ";
			String queryP1 = "SELECT * FROM PROFESSOR WHERE ";
			String query2;
			String query3 = " AND Pw = '" + password + "';";
			String queryStudent;
			String queryProfessor;
			
			if(userInput.contains("@"))
				query2 = "Email = '" + userInput + "'";
			else
				query2 = "Username = '" + userInput + "'";
			
			queryStudent = queryS1 + query2 + query3;
			
			ResultSet rs = stmt.executeQuery(queryStudent);
			
			if(rs.next())
			{
				Student userStudent = new Student(rs.getString("StudentID"), 
												rs.getString("FirstName"), 
												rs.getString("LastName"),
												rs.getString("Email"),
												rs.getString("Username"),
												rs.getString("Pw"));
				
				return userStudent;
			}
			else
			{
				queryProfessor = queryP1 + query2 + query3;
				
				rs = stmt.executeQuery(queryProfessor);
				
				if(rs.next())
				{
					Professor userProfessor = new Professor(rs.getString("FirstName"), 
													rs.getString("LastName"),
													rs.getString("Email"),
													rs.getString("Username"),
													rs.getString("Pw"));
					
					return userProfessor;
				}
				else
					return null;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User registerUser(String firstName, String lastName, String username, String email, String password) {
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
	public Void getLastName(int codUser, String lastName) {
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
