package Model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Test {
	// Attributes
	private String name;
	private Date creationDate;
	private Time startingDateTime;
	private Time closingDateTime;
	private float minScore;
	
	private Class receivingClass;
	private Professor creator;
	private ArrayList<TestTaken> attempts;
	private ArrayList<Quiz> quizzes;
	
	// Methods
	public String getName() {return name;}
	public void setName(String name) { this.name = name;}
	
	public Date getCreationDate() {	return creationDate;}
	public void setCreationDate(Date creationDate) { this.creationDate = creationDate;}
	
	public Time getStartingDateTime() {	return startingDateTime;}
	public void setStartingDateTime(Time startingDateTime) { this.startingDateTime = startingDateTime;}
	
	public Time getClosingDateTime() { return closingDateTime;}
	public void setClosingDateTime(Time closingDateTime) { this.closingDateTime = closingDateTime;}
	
	public float getMinScore() { return minScore;}
	public void setMinScore(float minScore) { this.minScore = minScore;}
	
	
	public Class getReceivingClass() { return receivingClass; }
	public void setReceivingClass(Class receivingClass) { this.receivingClass = receivingClass;	}
	
	public Professor getCreator() {	return creator;	}
	/*public void setCreator(Professor creator) {
		this.creator = creator;
	}*/
	
	public ArrayList<TestTaken> getAttempts() {	return attempts; }
	public void setAttempts(ArrayList<TestTaken> attempts) { this.attempts = attempts; }
	
	public ArrayList<Quiz> getQuizzes() { return quizzes; }
	public void setQuizzes(ArrayList<Quiz> quizzes) { this.quizzes = quizzes; }
}
