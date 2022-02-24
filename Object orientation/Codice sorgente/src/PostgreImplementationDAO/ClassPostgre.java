package PostgreImplementationDAO;

import java.sql.Connection;
import java.sql.SQLException;

import DAO.ClassDAO;
import Database.QuizDBConnection;

public class ClassPostgre implements ClassDAO{
	private Connection connection;
	
	public ClassPostgre() {
		try { connection = QuizDBConnection.getInstance().getConnection(); }
		catch (SQLException e){ e.printStackTrace(); }
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
