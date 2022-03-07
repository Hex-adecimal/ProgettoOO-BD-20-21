package Model;

public class Quiz {
	// Attributes
	protected String codQuiz;
	protected String question;
	
	protected Test test;
	
	// Methods
	public Quiz(String codQuiz, String question)
	{
		this.question = question;
		this.codQuiz = codQuiz;
	}
	
	public String getCodQuiz() { return codQuiz; }
	public void setCodQuiz(String codQuiz) { this.codQuiz = codQuiz; }
	
	public String getQuestion() { return question; }
	public void setQuestion(String question) { this.question = question; }
	
	
	public Test getTest() { return test; }
	public void setTest(Test test) { this.test = test; }
}
