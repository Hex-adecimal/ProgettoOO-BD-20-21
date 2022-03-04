package PostgreImplementationDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import DAO.ClosedQuizDAO;
import Database.QuizDBConnection;

public class ClosedQuizPostgre implements ClosedQuizDAO{
	private Connection connection;
	
	public ClosedQuizPostgre() {
		try { connection = QuizDBConnection.getInstance().getConnection(); }
		catch (SQLException e){ e.printStackTrace(); }
	}
	
	@Override
	public void insertClosedQuiz(String question,
			String answerA,
			String answerB,
			String answerC,
			String answerD,
			char rightAnswer,
			float scoreIfRight,
			float scoreIfWrong,
			String codTest)
	{
		try
		{
			Statement stmt = connection.createStatement();
			
			String query = "INSERT INTO CLOSED_QUIZ" + 
							"(question, answerA, answerB, answerC, answerD," + 
							"rightanswer, scoreifright, scoreifwrong, codtest)" + 
							" VALUES ('" +
							question + "', '" +
							answerA + "', '" +
							answerB + "', '" +
							answerC + "', '" +
							answerD + "', '" +
							Character.toLowerCase(rightAnswer) + "', " +
							scoreIfRight + ", " + 
							scoreIfWrong + ", " +
							codTest + ");";
			
			stmt.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public String getQuestion(int codQuiz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setQuestion(int codQuiz, String question) {
		
	}

	@Override
	public String getAnswerA(int codClosedQuiz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAnswerB(int codClosedQuiz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAnswerC(int codClosedQuiz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAnswerD(int codClosedQuiz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRightAnswer(int codClosedQuiz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getScoreIfRight(int codClosedQuiz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getScoreIfWrong(int codClosedQuiz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setAnswerA(int codClosedQuiz, String answerA) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setAnswerB(int codClosedQuiz, String answerB) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setAnswerC(int codClosedQuiz, String answerC) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setAnswerD(int codClosedQuiz, String answerD) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setRightAnswer(int codClosedQuiz, char rightAnswer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setScoreIfRight(int codClosedQuiz, float scoreIfRight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setScoreIfWrong(int codClosedQuiz, float scoreIfWrong) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
