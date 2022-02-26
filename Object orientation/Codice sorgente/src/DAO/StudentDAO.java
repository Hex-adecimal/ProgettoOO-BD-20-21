package DAO;

import Model.Test;
import Model.TestTaken;

public interface StudentDAO extends UserDAO {
	public int turnInTest(Test test);
	public TestTaken viewTestResult(int codTestTaken);
	public String getStudentIDbyUsername(String username);
	
}