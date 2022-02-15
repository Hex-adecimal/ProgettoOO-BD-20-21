package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Professor extends User{
	// Attributes 
	
	// Methods
	public Professor(String firstName, String lastName, String email, String username, String password)
	{
		super(firstName, lastName, email, username, password);
	}
	
	public Professor(ResultSet rs) throws SQLException
	{
		super(rs);
	}
}
