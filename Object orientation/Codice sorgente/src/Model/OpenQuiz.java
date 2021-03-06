package Model;

import java.util.ArrayList;

public class OpenQuiz extends Quiz{
	// Attributes
	private float maxScore;
	private float minScore;
	private int maxLength;
	
	private ArrayList<OpenAnswer> answers;
	
	// Methods
	public OpenQuiz(String codQuiz,
					String question, 
					float maxScore,
					float minScore,
					int maxLength)
	{
		super(codQuiz, question);
		this.maxScore = maxScore;
		this.minScore = minScore;
		this.maxLength = maxLength;
	}
	
	public float getMaxScore() { return maxScore; }
	public void setMaxScore(float maxScore) { this.maxScore = maxScore; }
	
	public float getMinScore() { return minScore; }
	public void setMinScore(float minScore) { this.minScore = minScore;	}
	
	public int getMaxLength() { return maxLength; }
	public void setMaxLength(int maxLength) { this.maxLength = maxLength; }
	
	
	public ArrayList<OpenAnswer> getAnswers() {	return answers; }
	public void setAnswers(ArrayList<OpenAnswer> answers) {	this.answers = answers; }
}
