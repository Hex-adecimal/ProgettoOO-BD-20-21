package DAO;

import java.util.ArrayList;
import Model.Class;

public interface ClassDAO {
	public String getName(int codClass);
	public int getYear(int codClass);
	public int getCfu(int codClass);
	
	public Void setName(int codClass, String name);
	public Void setYear(int codClass, int year);
	public Void setCfu(int codClass, int cfu);
	
	public ArrayList<Class> getProfessorClasses(String codP);
}
