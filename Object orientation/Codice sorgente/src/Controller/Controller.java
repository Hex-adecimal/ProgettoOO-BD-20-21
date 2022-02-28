package Controller;

import Model.Professor;
import Model.Student;
import Model.Test;
import Model.User;
import Model.Class;
import PostgreImplementationDAO.*;

import java.util.ArrayList;

import DAO.*;

public class Controller {
	private User user;
	private ClassDAO classDAO;
	private ClosedAnswerDAO closedAnswerDAO;
	private ClosedQuizDAO closedQuizDAO;
	private LectureDAO lectureDAO;
	private OpenAnswerDAO openAnswerDAO;
	private OpenQuizDAO openQuizDAO;
	private ProfessorDAO professorDAO;
	private QuizDAO quizDAO;
	private StudentDAO studentDAO;
	private TestDAO testDAO;
	private TestTakenDAO testTakenDAO;
	private UserDAO userDAO;
	
	public Controller() {
		this.classDAO = new ClassPostgre();
		this.closedAnswerDAO = new ClosedAnswerPostgre();
		this.closedQuizDAO = new ClosedQuizPostgre();
		this.lectureDAO = new LecturePostgre();
		this.openAnswerDAO = new OpenAnswerPostgre();
		this.openQuizDAO = new OpenQuizPostgre();
		this.professorDAO = new ProfessorPostgre();
		this.quizDAO = new QuizPostgre();
		this.studentDAO = new StudentPostgre();
		this.testDAO = new TestPostgre();
		this.testTakenDAO = new TestTakenPostgre();
		this.userDAO = new UserPostgre();
	}
	
	// login method
	public User logIn(String userInput, String password, String kindOfUser) {
		String s = null; 
		User user = null;
			
		s = userDAO.logUser(userInput, password, kindOfUser);
		if (s != null)
			if (kindOfUser == "Student")
				user = new Student(s);
			else if (kindOfUser == "Professor")
				user = new Professor(s);
		
		return user;
	}
	
	// sign in methods
	public Student signUpS(String firstName, String lastName, String username, String email, String password) {
		String s = null, error = null;
		Student student = null;
		
		error = studentDAO.registerUser(firstName, lastName, username, email, password);
		if (error != null) {
			s = firstName + "|" + lastName + "|" + email + "|" + username + "|" + password + "|" + studentDAO.getStudentIDbyUsername(username);
			student = new Student(s);
		}
		
		return student;
	}
	
	public Professor signUpP(String firstName, String lastName, String username, String email, String password) {
		String s = null, error = null;
		Professor professor = null;
		
		error = professorDAO.registerUser(firstName, lastName, username, email, password);
		if (error != null) {
			s = firstName + "|" + lastName + "|" + email + "|" + username + "|" + password + "|" + professorDAO.getCodPbyUsername(username);
			professor = new Professor(s);
		}
		
		return professor;
	}
	
	public ArrayList<Class> getNSetProfessorClasses()
	{
		Professor p = (Professor)user;
		
		ArrayList<Class> classes = p.getClasses();
		
		if(classes == null)
		{
			classes = classDAO.getProfessorClasses(p.getCodP());
			p.setClasses(classes);
		}
		
		return classes;
	}
	
	public ArrayList<Test> getNSetProfessorTests()
	{
		Professor p = (Professor)user;
		
		ArrayList<Test> tests = p.getCreatedTests();
		
		if(tests == null)
		{
			tests = testDAO.getProfessorTests(p.getCodP());
			p.setCreatedTests(tests);
		}
		
		return tests;
	}
	// Getter & Setter
	public void setUser(User user) { this.user = user; }
}
