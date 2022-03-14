package DAO;

public interface UserDAO {
	public String registerUser(String firstName, String lastName, String username, String email, String password);
	public String logUser(String email, String password, String kindOfUser); 
	
	public String getFirstName(int codUser);
	public String getLastName(int codUser);
	public String getEmail(int codUser);
	public String getUsername(int codUser);
	public String getPassword(int codUser);
	
	public Void setFirstName(String codUser, String firstName);
	public Void setLastName(String codUser, String lastName);
	public Void setEmail(String codUser, String email);
	public Void setUsername(String codUser, String username);
	public Void setPassword(String codUser, String password);
}
