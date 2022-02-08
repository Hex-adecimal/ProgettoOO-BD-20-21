package PostgreImplementationDAO;

import java.sql.Date;
import java.sql.Time;

import DAO.TestDAO;

public class TestPostgre implements TestDAO {

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
