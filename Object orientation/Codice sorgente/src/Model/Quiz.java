package Model;

public class Quiz {
	// Attributes
	protected String question;
	
	protected Test test;
	
	// Methods
	public String getQuestion() { return question; }
	public void setQuestion(String question) { this.question = question; }
	
	
	public Test getTest() { return test; }
	public void setTest(Test test) { this.test = test; }
}
