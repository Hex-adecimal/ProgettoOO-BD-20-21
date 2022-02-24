package DAO;

import Model.User;

public interface UserDAO {
	public String registerUser(String firstName, String lastName, String username, String email, String password);
	public String logUser(String email, String password, String kindOfUser); 
	
	public String getFirstName(int codUser);
	public String getLastName(int codUser);
	public String getEmail(int codUser);
	public String getUsername(int codUser);
	public String getPassword(int codUser);
	
	public Void setFirstName(int codUser, String firstName);
	public Void setLastName(int codUser, String lastName);
	public Void setEmail(int codUser, String email);
	public Void setUsername(int codUser, String username);
	public Void setPassword(int codUser, String password);
}
