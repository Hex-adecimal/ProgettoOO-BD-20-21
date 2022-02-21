package Model;

public class OpenAnswer {
	// Attributes
	private String givenAnswer;
	private float score;
	
	private TestTaken testTaken;
	private OpenQuiz quiz;
	
	// Methods
	public String getGivenAnswer() { return givenAnswer; }
	public void setGivenAnswer(String givenAnswer) { this.givenAnswer = givenAnswer; }
	
	public float getScore() { return score; }
	public void setScore(float score) { this.score = score; }
	
	
	public TestTaken getTestTaken() { return testTaken;	}
	public void setTestTaken(TestTaken testTaken) {	this.testTaken = testTaken;	}
	
	public OpenQuiz getQuiz() {	return quiz; }
	public void setQuiz(OpenQuiz quiz) { this.quiz = quiz; }
}
