package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Professor extends User{
	// Attributes
	private String codP;
	
	private ArrayList<Class> classes;
	private ArrayList<Lecture> lectures;
	private ArrayList<Test> createdTests;
	
	// Methods
	public Professor(String codP, String firstName, String lastName, String email, String username, String password)
	{
		super(firstName, lastName, email, username, password);
		
		this.setCodP(codP);
	}
	
	public Professor(ResultSet rs) throws SQLException
	{
		super(rs);
		
		this.setCodP(rs.getString("codp"));
	}

	public String getCodP() { return codP; }
	public void setCodP(String codP) { this.codP = codP; }

	
	public ArrayList<Class> getClasses() { return classes; }
	public void setClasses(ArrayList<Class> classes) { this.classes = classes; }

	public ArrayList<Lecture> getLectures() { return lectures; }
	public void setLectures(ArrayList<Lecture> lectures) { this.lectures = lectures; }

	public ArrayList<Test> getCreatedTests() { return createdTests; }
	public void setCreatedTests(ArrayList<Test> createdTests) { this.createdTests = createdTests; }
}
