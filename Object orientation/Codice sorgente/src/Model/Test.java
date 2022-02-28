package Model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Test {
	// Attributes
	private String name;
	private Date creationDate;
	private Time creationTime;
	private Date startingDate;
	private Time startingTime;
	private Date closingDate;
	private Time closingTime;
	private float minScore;
	
	private Class receivingClass;
	private Professor creator;
	private ArrayList<TestTaken> attempts;
	private ArrayList<Quiz> quizzes;
	
	// Methods
	public Test(String name, 
				Date creationDate, 
				Time creationTime, 
				Date startingDate, 
				Time startingTime,
				Date closingDate,
				Time closingTime,
				float minScore)
	{
		this.name = name;
		this.creationDate = creationDate;
		this.creationTime = creationTime;
		this.startingDate = startingDate;
		this.startingTime = startingTime;
		this.closingDate = closingDate;
		this.closingTime = closingTime;
		this.minScore = minScore;
	}
	
	public String getName() {return name;}
	public void setName(String name) { this.name = name;}
	
	public Date getCreationDate() {	return creationDate;}
	public void setCreationDate(Date creationDate) { this.creationDate = creationDate;}
	
	public Time getCreationTime() {	return creationTime;}
	public void setCreationTime(Time creationTime) { this.creationTime = creationTime;}
	
	public Date getStartingDate() {	return startingDate;}
	public void setStartingDate(Date startingDate) { this.startingDate = startingDate;}
	
	public Time getStartingTime() {	return startingTime;}
	public void setStartingTime(Time startingTime) { this.startingTime = startingTime;}
	
	public Date getClosingDate() { return closingDate;}
	public void setClosingDate(Date closingDate) { this.closingDate = closingDate;}
	
	public Time getClosingTime() { return closingTime;}
	public void setClosingTime(Time closingTime) { this.closingTime = closingTime;}
	
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
