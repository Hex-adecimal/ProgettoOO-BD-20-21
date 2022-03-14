package Controller;

import Model.Professor;
import Model.Student;
import Model.Test;
import Model.User;
import Model.Class;
import Model.ClosedQuiz;
import Model.OpenQuiz;
import PostgreImplementationDAO.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

import DAO.*;
import GUI.ClosedQuizCreationPanel;
import GUI.OpenQuizCreationPanel;
import GUI.TestCreation;

public class Controller {
	private User user;
	private ClassDAO classDAO;
	private ClosedQuizDAO closedQuizDAO;
	private OpenQuizDAO openQuizDAO;
	private ProfessorDAO professorDAO;
	private StudentDAO studentDAO;
	private TestDAO testDAO;
	private TestTakenDAO testTakenDAO;
	private UserDAO userDAO;
	
	public Controller() {
		this.classDAO = new ClassPostgre();
		this.closedQuizDAO = new ClosedQuizPostgre();
		this.openQuizDAO = new OpenQuizPostgre();
		this.professorDAO = new ProfessorPostgre();
		this.studentDAO = new StudentPostgre();
		this.testDAO = new TestPostgre();
		this.testTakenDAO = new TestTakenPostgre();
		this.userDAO = new UserPostgre();
	}
	
	// login method
	public void logIn(String userInput, String password, String kindOfUser) {
		String s = null; 
		User user = null;
			
		s = userDAO.logUser(userInput, password, kindOfUser);
		if (s != null)
			if (kindOfUser == "Student")
				user = new Student(s);
			else if (kindOfUser == "Professor")
				user = new Professor(s);
		
		setUser(user);
	}
	
	public boolean userIsNull()
	{
		return (user == null);
	}
	
	// sign in methods
	public void signUpS(String firstName, String lastName, String username, String email, String password) {
		String s = null, error = null;
		Student student = null;
		
		error = studentDAO.registerUser(firstName, lastName, username, email, password);
		if (error != null) {
			s = firstName + "|" + lastName + "|" + email + "|" + username + "|" + password + "|" + studentDAO.getStudentIDbyUsername(username);
			student = new Student(s);
		}
		
		setUser(student);
	}
	
	public void signUpP(String firstName, String lastName, String username, String email, String password) {
		String s = null, error = null;
		Professor professor = null;
		
		error = professorDAO.registerUser(firstName, lastName, username, email, password);
		if (error != null) {
			s = firstName + "|" + lastName + "|" + email + "|" + username + "|" + password + "|" + professorDAO.getCodPbyUsername(username);
			professor = new Professor(s);
		}
		
		setUser(professor);
	}
	
	public ArrayList<String> getNSetProfessorClasses()
	{
		Professor p = (Professor)user;
		
		ArrayList<Class> classes = p.getClasses();
		
		if(classes == null)
		{
			classes = classDAO.getProfessorClasses(p.getCodP());
			p.setClasses(classes);
		}
		
		ArrayList<String> classStrings = new ArrayList<String>();
		
		for(Class i : classes)
		{
			classStrings.add(i.getName() + " - Year " + i.getYear() + " - " + i.getCFU() + " CFU");
		}
		
		return classStrings;
	}
	
	public ArrayList<String> getNSetProfessorTests()
	{
		Professor p = (Professor)user;
		
		ArrayList<Test> tests = testDAO.getProfessorTests(p.getCodP());
		
		p.setCreatedTests(tests);
		
		ArrayList<String> testStrings = new ArrayList<String>();
		
		for(Test i : tests)
		{
			testStrings.add("test code:" + i.getCodTest() + 
							" - name: " + i.getName() + " - created on: " + 
					  		i.getCreationDate() + " " + 
					  		i.getCreationTime() + " - starting time: " + 
					  		i.getStartingDate() + " " + 
					  		i.getStartingTime() + " - turn in deadline: " + 
					  		i.getClosingDate() + " " + 
					  		i.getClosingTime() + " - minimal score: " + 
					  		i.getMinScore());
		}
		
		return testStrings;
	}
	
	public String getTestString(String codTest)
	{
		Professor p = (Professor)user;
		String testString = null;
		ArrayList<Test> tests = p.getCreatedTests();
		
		for(Test i : tests)
		{
			if(i.getCodTest().equals(codTest))
			{
				testString = i.getName() + "|" +
							 i.getStartingDate() + "|" +
							 i.getStartingTime() + "|" +
							 i.getClosingDate() + "|" +
							 i.getClosingTime() + "|" +
							 i.getMinScore() + "|";
				
				break;
			}
		}
		
		return testString;
	}
	
	public Date getTestStartingDate(String codTest)
	{
		Professor p = (Professor)user;
		Date startingDate = null;
		ArrayList<Test> tests = p.getCreatedTests();
		
		for(Test i : tests)
		{
			if(i.getCodTest().equals(codTest))
			{
				startingDate = i.getStartingDate();
				break;
			}
		}
		
		return startingDate;
	}
	
	public Time getTestStartingTime(String codTest)
	{
		Professor p = (Professor)user;
		Time startingTime = null;
		ArrayList<Test> tests = p.getCreatedTests();
		
		for(Test i : tests)
		{
			if(i.getCodTest().equals(codTest))
			{
				startingTime = i.getStartingTime();
				break;
			}
		}
		
		return startingTime;
	}
	
	public String getCodTestByName(String name)
	{
		return testDAO.getCodTestByName(name);
	}
	
	public ArrayList<String> getOpenQuizStrings(String codTest)
	{
		ArrayList<String> openQuizStrings = new ArrayList<String>();
		ArrayList<OpenQuiz> openQuizzes = openQuizDAO.getOpenQuizzes(codTest);
		
		for(OpenQuiz i : openQuizzes)
		{
			openQuizStrings.add(i.getCodQuiz() + "|" +
								i.getQuestion() + "|" +
								i.getMaxScore() + "|" +
								i.getMinScore() + "|" +
								i.getMaxLength() + "|");
		}
		
		return openQuizStrings;
	}
	
	public ArrayList<String> getClosedQuizStrings(String codTest)
	{
		ArrayList<String> closedQuizStrings = new ArrayList<String>();
		ArrayList<ClosedQuiz> closedQuizzes = closedQuizDAO.getClosedQuizzes(codTest);
		
		for(ClosedQuiz i : closedQuizzes)
		{
			closedQuizStrings.add(i.getCodQuiz() + "|" + 
								i.getQuestion() + "|" +
								i.getAnswerA() + "|" +
								i.getAnswerB() + "|" +
								i.getAnswerC() + "|" +
								i.getAnswerD() + "|" +
								i.getRightAnswer() + "|" +
								i.getScoreIfRight() + "|" +
								i.getScoreIfWrong() + "|");
		}
		
		return closedQuizStrings;
	}
	
	public void insertTestNQuizzes(TestCreation TCWizard, 
									String testCreationDateString, 
									String testCreationTimeString)
	{
		String testName = TCWizard.getTestName();
		String startingDateString = TCWizard.getStartingDateString();
		String closingDateString = TCWizard.getClosingDateString();
		String startingTimeString = TCWizard.getStartingTimeString();
		String closingTimeString = TCWizard.getClosingTimeString();
		float minScore = TCWizard.getMinScore();
		
		Professor p = (Professor)user;
		
		testDAO.insertNewTest(testName,
							testCreationDateString, 
							testCreationTimeString,
							startingDateString, 
							startingTimeString, 
							closingDateString, 
							closingTimeString, 
							minScore,
							p.getCodP());
		
		ArrayList<OpenQuizCreationPanel> openQuizPanels = TCWizard.getOpenQuizzes();
		ArrayList<ClosedQuizCreationPanel> closedQuizPanels = TCWizard.getClosedQuizzes();
		
		String codTest = getCodTestByName(testName);
		
		for(OpenQuizCreationPanel i : openQuizPanels)
		{
			openQuizDAO.insertOpenQuiz(i.getQuestion(), 
										i.getMaxScore(), 
										i.getMinScore(), 
										i.getMaxLength(),
										codTest);
		}
		
		for(ClosedQuizCreationPanel i : closedQuizPanels)
		{
			closedQuizDAO.insertClosedQuiz(i.getQuestion(), 
											i.getAnswerA(), 
											i.getAnswerB(), 
											i.getAnswerC(), 
											i.getAnswerD(), 
											i.getRightAnswer().charValue(), 
											i.getRightScore(), 
											i.getWrongScore(), 
											codTest);
		}
	}
	
	public void updateTestNQuizzes(TestCreation TCWizard,
								String ogTestName,
								String ogStartingDateString,
								String ogStartingTimeString,
								String ogClosingDateString,
								String ogClosingTimeString,
								float ogMinScore,
								ArrayList<String> ogOpenQuizzes,
								ArrayList<String> ogClosedQuizzes)
	{
		String codTest = getCodTestByName(ogTestName);
		
		String testName = TCWizard.getTestName();
		float minScore = TCWizard.getMinScore();
		String startingDateString = TCWizard.getStartingDateString();
		String startingTimeString = TCWizard.getStartingTimeString();
		String closingDateString = TCWizard.getClosingDateString();
		String closingTimeString = TCWizard.getClosingTimeString();
		
		if(!( testName.equals(ogTestName) ))
		{
			testDAO.setName(codTest, testName);
		}
		
		if( !(minScore == ogMinScore) )
		{
			testDAO.setMinScore(codTest, minScore);
		}
		
		if(!( startingDateString.equals(ogStartingDateString) ) ||
			!( startingTimeString.equals(ogStartingTimeString) ))
		{
			testDAO.setStartingDateTime(codTest, startingDateString + " " + startingTimeString);
		}
		
		if(!( closingDateString.equals(ogClosingDateString) ) ||
			!( closingTimeString.equals(ogClosingTimeString) ))
		{
			testDAO.setClosingDateTime(codTest, closingDateString + " " + closingTimeString);
		}
		
		String codQuiz;
		String ogQuestion;
		Float ogQuizMaxScore;
		Float ogQuizMinScore;
		Integer ogMaxLength;
		ArrayList<OpenQuizCreationPanel> openQuizPanels = TCWizard.getOpenQuizzes();
		for(OpenQuizCreationPanel i : openQuizPanels)
		{
			for(String j : ogOpenQuizzes)
			{
				codQuiz = j.substring(0, j.indexOf("|"));
				j = j.substring(j.indexOf("|") + 1);
				
				if(codQuiz.equals(i.getCodQuiz()))
				{
					ogQuestion = j.substring(0, j.indexOf("|"));
					if(!ogQuestion.equals(i.getQuestion()))
					{
						openQuizDAO.setQuestion(codQuiz, i.getQuestion());
					}
					j = j.substring(j.indexOf("|") + 1);
					
					ogQuizMaxScore = Float.valueOf( j.substring(0, j.indexOf("|")) );
					if(!( ogQuizMaxScore.floatValue() == i.getMaxScore() ))
					{
						openQuizDAO.setMaxScore(codQuiz, i.getMaxScore());
					}
					j = j.substring(j.indexOf("|") + 1);
					
					ogQuizMinScore = Float.valueOf( j.substring(0, j.indexOf("|")) );
					if(!( ogQuizMinScore.floatValue() == i.getMinScore() ))
					{
						openQuizDAO.setMinScore(codQuiz, i.getMinScore());
					}
					j = j.substring(j.indexOf("|") + 1);
					
					ogMaxLength = Integer.valueOf( j.substring(0, j.indexOf("|")) );
					if(!( ogMaxLength.intValue() == i.getMaxLength() ))
					{
						openQuizDAO.setMaxLength(codQuiz, i.getMaxLength());
					}
					j = j.substring(j.indexOf("|") + 1);
				}	
			}
		}
		
		//String codQuiz;
		//String question;
		String ogAnswerA;
		String ogAnswerB;
		String ogAnswerC;
		String ogAnswerD;
		char ogRightAnswer;
		float ogScoreIfRight;
		float ogScoreIfWrong;
		ArrayList<ClosedQuizCreationPanel> closedQuizPanels = TCWizard.getClosedQuizzes();
		for(ClosedQuizCreationPanel k : closedQuizPanels)
		{
			for(String l : ogClosedQuizzes)
			{
				codQuiz = l.substring(0, l.indexOf("|"));
				l = l.substring(l.indexOf("|") + 1);
				
				if(codQuiz.equals(k.getCodQuiz()))
				{
					ogQuestion = l.substring(0, l.indexOf("|"));
					if(!( ogQuestion.equals(k.getQuestion()) ) )
					{
						closedQuizDAO.setQuestion(codQuiz, k.getQuestion());
					}
					l = l.substring(l.indexOf("|") + 1);
					
					ogAnswerA = l.substring(0, l.indexOf("|"));
					if(!( ogAnswerA.equals(k.getAnswerA()) ))
					{
						closedQuizDAO.setAnswerA(codQuiz, k.getAnswerA());
					}
					l = l.substring(l.indexOf("|") + 1);
					
					ogAnswerB = l.substring(0, l.indexOf("|"));
					if(!( ogAnswerB.equals(k.getAnswerB()) ))
					{
						closedQuizDAO.setAnswerB(codQuiz, k.getAnswerB());
					}
					l = l.substring(l.indexOf("|") + 1);
					
					ogAnswerC = l.substring(0, l.indexOf("|"));
					if(!( ogAnswerC.equals(k.getAnswerC()) ))
					{
						closedQuizDAO.setAnswerC(codQuiz, k.getAnswerC());
					}
					l = l.substring(l.indexOf("|") + 1);
					
					ogAnswerD = l.substring(0, l.indexOf("|"));
					if(!( ogAnswerD.equals(k.getAnswerD()) ))
					{
						closedQuizDAO.setAnswerD(codQuiz, k.getAnswerD());
					}
					l = l.substring(l.indexOf("|") + 1);
					
					if(l.substring(0, l.indexOf("|")).equals("A"))
						ogRightAnswer = 'A';
					else if(l.substring(0, l.indexOf("|")).equals("B"))
						ogRightAnswer = 'B';
					else if(l.substring(0, l.indexOf("|")).equals("C"))
						ogRightAnswer = 'C';
					else
						ogRightAnswer = 'D';
					if(ogRightAnswer != k.getRightAnswer().charValue())
					{
						closedQuizDAO.setRightAnswer(codQuiz, Character.toLowerCase( k.getRightAnswer().charValue() ));
					}
					l = l.substring(l.indexOf("|") + 1);
					
					ogScoreIfRight = Float.valueOf( l.substring(0, l.indexOf("|")) );
					if(ogScoreIfRight != k.getRightScore())
					{
						closedQuizDAO.setScoreIfRight(codQuiz, k.getRightScore());
					}
					l = l.substring(l.indexOf("|") + 1);
					
					ogScoreIfWrong = Float.valueOf( l.substring(0, l.indexOf("|")) );
					if(ogScoreIfWrong != k.getWrongScore())
					{
						closedQuizDAO.setScoreIfWrong(codQuiz, k.getWrongScore());
					}
					l = l.substring(l.indexOf("|") + 1);
				}
			}
		}
		
		ArrayList<ClosedQuizCreationPanel> newClosedQuizPanels = TCWizard.getNewClosedQuizzes();
		ArrayList<OpenQuizCreationPanel> newOpenQuizPanels = TCWizard.getNewOpenQuizzes();
		
		for(OpenQuizCreationPanel i : newOpenQuizPanels)
		{
			openQuizDAO.insertOpenQuiz(i.getQuestion(), 
										i.getMaxScore(), 
										i.getMinScore(), 
										i.getMaxLength(),
										codTest);
		}
		
		for(ClosedQuizCreationPanel j : newClosedQuizPanels)
		{
			closedQuizDAO.insertClosedQuiz(j.getQuestion(), 
											j.getAnswerA(), 
											j.getAnswerB(), 
											j.getAnswerC(), 
											j.getAnswerD(), 
											j.getRightAnswer().charValue(), 
											j.getRightScore(), 
											j.getWrongScore(), 
											codTest);
		}
	}
	
	public void deleteTestNQuizzes(String codTest) {
		openQuizDAO.deleteQuizzes(codTest);
		closedQuizDAO.deleteQuizzes(codTest);
		testDAO.deleteTest(codTest);
	}
	
	public ArrayList<String> getStudentTestsTaken() {
		return testTakenDAO.getTestsTakenByStudent( Integer.valueOf( ((Student) user).getStudentID()) );
	}
	
	public ArrayList<String> getStudentClasses() {
		ArrayList<Class> classes =  classDAO.getStudentClasses(((Student) user).getStudentID() );
		ArrayList<String> ss = new ArrayList<String>();
		
		for (Class i : classes) {
			ss.add(i.getName() + " --- " + i.getYear() + " --- " + i.getCFU());
		}
		
		return ss;
	}
	
	
	public void updateAccount(String cod, String firstName, String lastName, String email, String username, String password) {
		if ( user instanceof Model.Professor ) {
			professorDAO.setFirstName(cod, firstName);
			professorDAO.setLastName(cod, lastName);
			professorDAO.setEmail(cod, email);
			professorDAO.setUsername(cod, username);
			professorDAO.setPassword(cod, password);
		} else {
			studentDAO.setFirstName(cod, firstName);
			studentDAO.setLastName(cod, lastName);
			studentDAO.setEmail(cod, email);
			studentDAO.setUsername(cod, username);
			studentDAO.setPassword(cod, password);
		}
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(password);
		return ;
	}
	
	public ArrayList<String> getTestInformations(String codTest) {
		ArrayList<String> array = new ArrayList<String>();
		
		try {
			ResultSet rsOpen = testDAO.getOpenQuiz(codTest);
			ResultSet rsClosed = testDAO.getClosedQuiz(codTest);
			
			while(rsOpen.next()) {
				array.add("0 --- " + rsOpen.getString("Question") + " --- " + rsOpen.getString("MaxLength"));
			}
			while(rsClosed.next()) {
				array.add("1 --- " + rsClosed.getString("Question") + " --- " + rsClosed.getString("AnswerA") + " --- " + rsClosed.getString("AnswerB") + " --- "
						+ rsClosed.getString("AnswerC") + " --- " + rsClosed.getString("AnswerD"));
			}
			
		} catch (Exception e) { e.printStackTrace(); }
		
		return array;
	}
	
	public Boolean testExists(String codTest)
	{
		return testDAO.testExists(codTest);
	}
	
	// Getter & Setter
	public void setUser(User user) { this.user = user; }
	public User getUser() { return this.user; }
}
