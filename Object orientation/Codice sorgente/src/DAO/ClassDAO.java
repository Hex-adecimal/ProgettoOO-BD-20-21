package DAO;

import java.util.ArrayList;
import Model.Class;

public interface ClassDAO {
	public ArrayList<Class> getProfessorClasses(String codP);
	public ArrayList<Class> getStudentClasses(String studentID);
}
