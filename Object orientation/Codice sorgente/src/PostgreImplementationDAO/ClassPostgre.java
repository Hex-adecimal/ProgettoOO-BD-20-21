package PostgreImplementationDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.ClassDAO;
import Database.QuizDBConnection;
import Model.Class;

public class ClassPostgre implements ClassDAO{
	private Connection connection;
	
	public ClassPostgre() {
		try { connection = QuizDBConnection.getInstance().getConnection(); }
		catch (SQLException e){ e.printStackTrace(); }
	}
	
	public ArrayList<Class> getProfessorClasses(String codP)
	{
		ArrayList<Class> classes = new ArrayList<Class>();
		
		try
		{
			Statement stmt = connection.createStatement();
			ResultSet rs;
			String query = "SELECT name, year, cfu FROM CLASS_T WHERE codp = '" + codP + "';";
			
			rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				classes.add(new Class(rs.getString("name"), rs.getInt("year"), rs.getInt("cfu")));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return classes;
	}
	
	public ArrayList<Class> getStudentClasses(String studentID)	{
		ArrayList<Class> classes = new ArrayList<Class>();
		
		try
		{
			Statement stmt = connection.createStatement();
			ResultSet rs;
			String query = "SELECT * FROM CLASS_T C JOIN TAKE T ON T.codC = C.codC WHERE studentID = '" + studentID + "';";
			
			rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				classes.add(new Class(rs.getString("name"), rs.getInt("year"), rs.getInt("cfu")));
			}
		}
		catch(Exception e) { e.printStackTrace(); };
		
		return classes;
	}
}
