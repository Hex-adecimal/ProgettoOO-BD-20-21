package Model;

import java.util.ArrayList;

public class ClosedQuiz extends Quiz{
	// Attributes
	private String answerA;
	private String answerB;
	private String answerC;
	private String answerD;
	private char rightAnswer;
	private float scoreIfRight;
	private float scoreIfWrong;
	
	private ArrayList<ClosedAnswer> answers;
	
	// Methods
	public ClosedQuiz(String codQuiz,
						String question,
						String answerA,
						String answerB,
						String answerC,
						String answerD,
						char rightAnswer,
						float scoreIfRight,
						float scoreIfWrong)
	{
		super(codQuiz, question);
		this.answerA = answerA;
		this.answerB = answerB;
		this.answerC = answerC;
		this.answerD = answerD;
		this.rightAnswer = rightAnswer;
		this.scoreIfRight = scoreIfRight;
		this.scoreIfWrong = scoreIfWrong;
	}
	
	public String getAnswerA() { return answerA; }
	public void setAnswerA(String answerA) { this.answerA = answerA; }
	
	public String getAnswerB() { return answerB; }
	public void setAnswerB(String answerB) { this.answerB = answerB; }
	
	public String getAnswerC() { return answerC; }
	public void setAnswerC(String answerC) { this.answerC = answerC; }
	
	public String getAnswerD() { return answerD; }
	public void setAnswerD(String answerD) { this.answerD = answerD; }
	
	public char getRightAnswer() { return rightAnswer; }
	public void setRightAnswer(char rightAnswer) { this.rightAnswer = rightAnswer; }
	
	public float getScoreIfRight() { return scoreIfRight; }
	public void setScoreIfRight(float scoreIfRight) { this.scoreIfRight = scoreIfRight; }
	
	public float getScoreIfWrong() { return scoreIfWrong; }
	public void setScoreIfWrong(float scoreIfWrong) { this.scoreIfWrong = scoreIfWrong; }
	
	
	public ArrayList<ClosedAnswer> getAnswers() { return answers; }
	public void setAnswers(ArrayList<ClosedAnswer> answers) { this.answers = answers; }
}
