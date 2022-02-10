package Model;

import java.sql.Date;
import java.sql.Time;

public class Test {
	// Attributes
	private String name;
	private Date creationDate;
	private Time startingDateTime;
	private Time closingDateTime;
	private float minScore;
	
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
}
