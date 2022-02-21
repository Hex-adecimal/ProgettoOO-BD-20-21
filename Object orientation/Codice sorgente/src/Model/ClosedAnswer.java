package Model;

public class ClosedAnswer {
	// Attributes
	private char givenAnswer;
	private float score;
	
	private TestTaken testTaken;
	private ClosedQuiz quiz;
	
	// Methods
	public char getGivenAnswer() { return givenAnswer; }
	public void setGivenAnswer(char givenAnswer) { this.givenAnswer = givenAnswer; }
	
	public float getScore() { return score; }
	public void setScore(float score) { this.score = score; }
	
	
	public TestTaken getTestTaken() { return testTaken;	}
	public void setTestTaken(TestTaken testTaken) {	this.testTaken = testTaken;	}
	
	public ClosedQuiz getQuiz() { return quiz; }
	public void setQuiz(ClosedQuiz quiz) { this.quiz = quiz; }
}
