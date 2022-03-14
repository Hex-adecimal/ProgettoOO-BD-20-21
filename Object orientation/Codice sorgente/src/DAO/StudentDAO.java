package DAO;

import Model.Test;
import Model.TestTaken;

public interface StudentDAO{
	public String registerUser(String firstName, String lastName, String username, String email, String password);
	
	public int turnInTest(Test test);
	public TestTaken viewTestResult(int codTestTaken);
	public String getStudentIDbyUsername(String username);
	
	public Void setFirstName(String codUser, String firstName);
	public Void setLastName(String codUser, String lastName);
	public Void setEmail(String codUser, String email);
	public Void setUsername(String codUser, String username);
	public Void setPassword(String codUser, String password);
}