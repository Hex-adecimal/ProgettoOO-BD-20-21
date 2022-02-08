package DAO;

import java.sql.Date;
import java.sql.Time;

public interface TestDAO {
	// name - creationdate - startingdatetime - closingdatetime - minscore
	public String getName(int codTest);
	public Date getCreationDate(int codTest);
	public Time getStartingDateTime(int codTest);
	public Time getClosingDateTime(int codTest);
	public float getMinScore(int codTest);
	
	public Void setName(int codTest, String name);
	public Void setStartingDateTime(int codTest, Time startingDatetime);
	public Void setClosingDateTime(int codTest, Time closingDatetime);
	public Void setMinScore(int codTest, float minScore);
}
