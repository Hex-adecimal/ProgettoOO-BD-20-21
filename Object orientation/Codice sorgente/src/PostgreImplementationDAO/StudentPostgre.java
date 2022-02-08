package PostgreImplementationDAO;

import DAO.StudentDAO;
import Model.Test;
import Model.TestTaken;
import Model.User;

public class StudentPostgre implements StudentDAO {

	@Override
	public User registerUser(String firstName, String lastName, String username, String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User logUser(String email, String password) {
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
	public int turnInTest(Test test) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int viewTestResult(TestTaken test) {
		// TODO Auto-generated method stub
		return 0;
	}

}
