package DAO;

import Model.OpenAnswer;
import Model.Test;

import java.sql.Time;

import Model.Class;

public interface ProfessorDAO{
	public String registerUser(String firstName, String lastName, String username, String email, String password);
	
	public Test createTest(String name, Time startingDateTime, Time closingDateTime, float minScore);
	public Class createClass(String name, int cfu);
	public int reviseOpenAnswer(OpenAnswer codAnswer, float score);
	public String getCodPbyUsername(String username);
	
	public Void setFirstName(String codUser, String firstName);
	public Void setLastName(String codUser, String lastName);
	public Void setEmail(String codUser, String email);
	public Void setUsername(String codUser, String username);
	public Void setPassword(String codUser, String password);
}
