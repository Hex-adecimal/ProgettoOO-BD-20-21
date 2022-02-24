package PostgreImplementationDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import DAO.TestDAO;
import Database.QuizDBConnection;

public class TestPostgre implements TestDAO {
	private Connection connection;
	
	public TestPostgre() {
		try { connection = QuizDBConnection.getInstance().getConnection(); }
		catch (SQLException e){ e.printStackTrace(); }
	}
	
	@Override
	public String getName(int codTest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getCreationDate(int codTest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Time getStartingDateTime(int codTest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Time getClosingDateTime(int codTest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getMinScore(int codTest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Void setName(int codTest, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setStartingDateTime(int codTest, Time startingDatetime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setClosingDateTime(int codTest, Time closingDatetime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setMinScore(int codTest, float minScore) {
		// TODO Auto-generated method stub
		return null;
	}

}
