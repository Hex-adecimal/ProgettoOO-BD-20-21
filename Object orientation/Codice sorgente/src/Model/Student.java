package Model;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;

public class Student extends User{
	// Attributes
	private String studentID;
	
	private ArrayList<Class> classes;
	private ArrayList<TestTaken> testsTaken;

	// Methods
	public Student(String studentID, String firstName, String lastName, String email, String username, String password)
	{
		super(firstName, lastName, email, username, password);
		this.studentID = studentID;
	}
	
	public Student(ResultSet rs) throws SQLException {
		super(rs);
		this.studentID = rs.getString("studentID");
	}
	
	public Student(String s) {
		super(s);
		s = s.substring(s.indexOf('|')+1); s = s.substring(s.indexOf('|')+1);
		s = s.substring(s.indexOf('|')+1); s = s.substring(s.indexOf('|')+1);
		s = s.substring(s.indexOf('|')+1);
		this.studentID = s;
	}
	
	public String getStudentID() { return studentID; }
	public void setStudentID(String studentID) { this.studentID = studentID; }

	
	public ArrayList<Class> getClasses() { return classes; }
	public void setClasses(ArrayList<Class> classes) { this.classes = classes; }

	public ArrayList<TestTaken> getTestsTaken() { return testsTaken; }
	public void setTestsTaken(ArrayList<TestTaken> testsTaken) { this.testsTaken = testsTaken; }
}
