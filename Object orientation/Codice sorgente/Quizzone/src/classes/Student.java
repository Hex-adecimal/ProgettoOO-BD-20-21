package classes;

public class Student extends User{
	// Attributes
	private String studentID;
	
	// Methods
	public Student(String firstName, String lastName, String email, String username, String password) {
		super(firstName, lastName, email, username, password);
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	
	
}