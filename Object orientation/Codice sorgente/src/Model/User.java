package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	// Attributes
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String username;
	protected String password;
	
	public User(String firstName, String lastName, String email, String username, String password)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	public User(ResultSet rs) throws SQLException {
		this.firstName = rs.getString("firstname");
		this.lastName = rs.getString("lastname");
		this.email = rs.getString("email");
		this.username = rs.getString("username");
		this.password = rs.getString("pw");
	}
	
	public User(String s) {
		this.firstName = s.substring(0, s.indexOf('|'));
		s = s.substring(s.indexOf('|')+1);
		
		this.lastName = s.substring(0, s.indexOf('|'));
		s = s.substring(s.indexOf('|')+1);
		
		this.email = s.substring(0, s.indexOf('|'));
		s = s.substring(s.indexOf('|')+1);
		
		this.username = s.substring(0, s.indexOf('|'));
		s = s.substring(s.indexOf('|')+1);
		
		this.password = s.substring(0, s.indexOf('|'));
		s = s.substring(s.indexOf('|')+1);
	}
	
	// Methods
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
	public String getTypeName()
	{
		String className = this.getClass().getName(); 
		
		//System.out.println(className);
		
		return className;
	}
}
