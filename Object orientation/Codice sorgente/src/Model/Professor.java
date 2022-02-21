package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Professor extends User{
	// Attributes
	private String codP;
	
	// Methods
	public Professor(String codP, String firstName, String lastName, String email, String username, String password)
	{
		super(firstName, lastName, email, username, password);
		
		this.setCodP(codP);
	}
	
	public Professor(ResultSet rs) throws SQLException
	{
		super(rs);
		
		this.setCodP(rs.getString("codp"));
	}

	public String getCodP() {
		return codP;
	}

	public void setCodP(String codP) {
		this.codP = codP;
	}
}
