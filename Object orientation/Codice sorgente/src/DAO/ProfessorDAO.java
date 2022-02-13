package DAO;

import Model.Lecture;

import Model.OpenAnswer;
import Model.Test;

import java.sql.Time;

import Model.Class;

public interface ProfessorDAO extends UserDAO {
	public Test createTest(String name, Time startingDateTime, Time closingDateTime, float minScore);
	public Class createClass(String name, int cfu);
	public Lecture createLecture(String title, String link);
	
	public int reviseOpenAnswer(OpenAnswer codAnswer, float score);
}
