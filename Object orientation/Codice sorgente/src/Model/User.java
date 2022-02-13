package Model;

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
	
	public String getClassName()
	{
		String className = this.getClass().getName()/*.substring(0, getClass().getName().indexOf("$"))*/; 
		
		System.out.println(className);
		
		return className;
	}
}
