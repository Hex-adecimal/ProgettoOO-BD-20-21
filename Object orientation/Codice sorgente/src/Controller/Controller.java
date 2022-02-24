package Controller;

import Model.Professor;
import Model.Student;
import Model.User;
import PostgreImplementationDAO.ClassPostgre;
import PostgreImplementationDAO.ClosedAnswerPostgre;
import PostgreImplementationDAO.ClosedQuizPostgre;
import PostgreImplementationDAO.LecturePostgre;
import PostgreImplementationDAO.OpenAnswerPostgre;
import PostgreImplementationDAO.OpenQuizPostgre;
import PostgreImplementationDAO.ProfessorPostgre;
import PostgreImplementationDAO.QuizPostgre;
import PostgreImplementationDAO.StudentPostgre;
import PostgreImplementationDAO.TestPostgre;
import PostgreImplementationDAO.TestTakenPostgre;
import PostgreImplementationDAO.UserPostgre;


public class Controller {
	private User user;
	private ClassPostgre classPostgre;
	private ClosedAnswerPostgre closedAnswerPostgre;
	private ClosedQuizPostgre closedQuizPostgre;
	private LecturePostgre lecturePostgre;
	private OpenAnswerPostgre openAnswerPostgre;
	private OpenQuizPostgre openQuizPostgre;
	private ProfessorPostgre professorPostgre;
	private QuizPostgre quizPostgre;
	private StudentPostgre studentPostgre;
	private TestPostgre testPostgre;
	private TestTakenPostgre testTakenPostgre;
	private UserPostgre userPostgre;
	
	public Controller() {
		this.classPostgre = new ClassPostgre();
		this.closedAnswerPostgre = new ClosedAnswerPostgre();
		this.closedQuizPostgre = new ClosedQuizPostgre();
		this.lecturePostgre = new LecturePostgre();
		this.openAnswerPostgre = new OpenAnswerPostgre();
		this.openQuizPostgre = new OpenQuizPostgre();
		this.professorPostgre = new ProfessorPostgre();
		this.quizPostgre = new QuizPostgre();
		this.studentPostgre = new StudentPostgre();
		this.testPostgre = new TestPostgre();
		this.testTakenPostgre = new TestTakenPostgre();
		this.userPostgre = new UserPostgre();
	}
	
	// login method
	public User logIn(String userInput, String password, String kindOfUser) {
		String s = null; 
		User user = null;
			
		s = userPostgre.logUser(userInput, password, kindOfUser);
		if (s != null)
			if (kindOfUser == "Student")
				user = new Student(s);
			else if (kindOfUser == "Professor")
				user = new Professor(s);
		
		return user;
	}
	
	// sign in methods
	public Student signInS(String firstName, String lastName, String username, String email, String password) {
		String s = null, error = null;
		Student student = null;
		
		error = studentPostgre.registerUser(firstName, lastName, username, email, password);
		if (error != null) {
			s = firstName + "|" + lastName + "|" + email + "|" + username + "|" + password + "|" + studentPostgre.getStudentIDbyUsername(username);
			student = new Student(s);
		}
		
		return student;
	}
	
	public Professor signInP(String firstName, String lastName, String username, String email, String password) {
		String s = null, error = null;
		Professor professor = null;
		
		error = professorPostgre.registerUser(firstName, lastName, username, email, password);
		if (error != null) {
			s = firstName + "|" + lastName + "|" + email + "|" + username + "|" + password + "|" + professorPostgre.getCodPbyUsername(username);
			professor = new Professor(s);
		}
		
		return professor;
	}
	
	// Getter & Setter
	public void setUser(User user) { this.user = user; }
}
