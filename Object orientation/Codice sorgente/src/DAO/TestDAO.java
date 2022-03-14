package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Test;

public interface TestDAO {
	// name - creationdate - startingdatetime - closingdatetime - minscore
	public ArrayList<Test> getProfessorTests(String codP);
	
	public String getCodTestByName(String name);
	
	public void setName(String codTest, String name);
	public void setStartingDateTime(String codTest, String startingDateTime);
	public void setClosingDateTime(String codTest, String closingDateTime);
	public void setMinScore(String codTest, float minScore);
	
	public void insertNewTest(String name, 
							String creationDate,
							String creationTime, 
							String startingDate, 
							String startingTime, 
							String closingDate, 
							String closingTime, 
							float minScore,
							String codP);
	public void deleteTest(String codTest);
	
	public Boolean testExists(String codTest);
	
	public ResultSet getOpenQuiz(String codTest);
	public ResultSet getClosedQuiz(String codTest);
}
