package DAO;

import java.util.ArrayList;

public interface TestTakenDAO {
	// revised - passed - totalscore
	public boolean isRevised(int codTestTaken);
	public boolean isPassed(int codTestTaken);
	public float getTotalScore(int codTestTaken);
	
	public Void setRevised(int codTestTaken, boolean revised);
	public Void setPassed(int codTestTaken, boolean passed);
	public Void setTotalScore(int codTestTaken, float totalScore);
	
	public ArrayList<String> getTestsTakenByStudent(int studentID);
}
