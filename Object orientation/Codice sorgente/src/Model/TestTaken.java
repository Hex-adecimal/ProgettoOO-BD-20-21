package Model;

import java.util.ArrayList;

public class TestTaken {
	// Attributes
	private boolean revised;
	private boolean passed;
	private float totalScore;
	
	private Student student;
	private Test testTemplate;
	private ArrayList<OpenAnswer> openAnswers;
	private ArrayList<ClosedAnswer> closedAnswers;
	
	public TestTaken(boolean revised, boolean passed, float totalScore) {
		this.revised = revised;
		this.passed = passed;
		this.totalScore = totalScore;
	}
	
	// Methods
	public TestTaken(Boolean bool) { this.setRevised(bool);	}
	
	public boolean isRevised() { return revised; }
	public void setRevised(boolean revised) { this.revised = revised; }
	
	public boolean isPassed() { return passed; }
	public void setPassed(boolean passed) { this.passed = passed; }
	
	public float getTotalScore() { return totalScore; }
	public void setTotalScore(float totalScore) { this.totalScore = totalScore; }

	
	public Student getStudent() { return student; }
	public void setStudent(Student student) { this.student = student; }

	public Test getTestTemplate() {	return testTemplate; }
	public void setTestTemplate(Test testTemplate) { this.testTemplate = testTemplate; }

	public ArrayList<OpenAnswer> getOpenAnswers() {	return openAnswers; }
	public void setOpenAnswers(ArrayList<OpenAnswer> openAnswers) {	this.openAnswers = openAnswers;	}
	
	public ArrayList<ClosedAnswer> getClosedAnswers() {	return closedAnswers; }
	public void setClosedAnswers(ArrayList<ClosedAnswer> closedAnswers) { this.closedAnswers = closedAnswers; }
}
