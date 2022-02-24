package PostgreImplementationDAO;

import java.sql.Connection;
import java.sql.SQLException;

import DAO.LectureDAO;
import Database.QuizDBConnection;

public class LecturePostgre implements LectureDAO {
	private Connection connection;
	
	public LecturePostgre() {
		try { connection = QuizDBConnection.getInstance().getConnection(); }
		catch (SQLException e){ e.printStackTrace(); }
	}
	
	@Override
	public String getTitle(int codLecture) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLink(int codLecture) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setTitle(int codLecture, String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setLink(int codLecture, String link) {
		// TODO Auto-generated method stub
		return null;
	}

}
