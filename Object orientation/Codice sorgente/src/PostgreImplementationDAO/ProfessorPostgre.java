package PostgreImplementationDAO;

import java.sql.Time;

import DAO.ProfessorDAO;
import Model.Class;
import Model.Lecture;
import Model.OpenAnswer;
import Model.Professor;
import Model.Test;

public class ProfessorPostgre implements ProfessorDAO {

	@Override
	public Professor registerUser(String firstName, String lastName, String username, String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Professor logUser(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFirstName(int codUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLastName(int codUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEmail(int codUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername(int codUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword(int codUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setFirstName(int codUser, String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void getLastName(int codUser, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setEmail(int codUser, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setUsername(int codUser, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void setPassword(int codUser, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Test createTest(String name, Time startingDateTime, Time closingDateTime, float minScore) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class createClass(String name, int cfu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lecture createLecture(String title, String link) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int reviseOpenAnswer(OpenAnswer answer) {
		// TODO Auto-generated method stub
		return 0;
	}

}
