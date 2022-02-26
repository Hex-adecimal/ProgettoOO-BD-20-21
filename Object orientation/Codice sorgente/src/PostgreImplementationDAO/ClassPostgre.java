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
			//Class class1;
			
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
	
	@Override
	public String getName(int codClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getYear(int codClass) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCfu(int codClass) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Void setName(int codClass, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setYear(int codClass, int year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setCfu(int codClass, int cfu) {
		// TODO Auto-generated method stub
		return null;
	}

}
