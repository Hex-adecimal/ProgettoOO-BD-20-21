package Model;

import java.sql.ResultSet;

import java.sql.SQLException;

public class Student extends User{
	// Attributes
	private String studentID;

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
	
	public String getStudentID() { return studentID; }
	public void setStudentID(String studentID) { this.studentID = studentID; }
	
}
