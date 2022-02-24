package PostgreImplementationDAO;

import java.sql.Connection;
import java.sql.SQLException;

import DAO.OpenQuizDAO;
import Database.QuizDBConnection;

public class OpenQuizPostgre implements OpenQuizDAO {
	private Connection connection;
	
	public OpenQuizPostgre() {
		try { connection = QuizDBConnection.getInstance().getConnection(); }
		catch (SQLException e){ e.printStackTrace(); }
	}
	
	@Override
	public String getQuestion(int codQuiz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setQuestion(int codQuiz, String question) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getMaxScore(int codOpenQuiz) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMinScore(int codOpenQuiz) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxLength(int codOpenQuiz) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Void setMaxScore(int codOpenQuiz, float maxScore) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setMinScore(int codOpenQuiz, float minScore) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setMaxLength(int codOpenQuiz, int maxLength) {
		// TODO Auto-generated method stub
		return null;
	}

}
