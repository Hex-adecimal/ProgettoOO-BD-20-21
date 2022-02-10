package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QuizDBConnection {
	
	// ATTRIBUTI
	private static QuizDBConnection instance;
	private Connection conn = null;
	private String nome = "postgres";
	private String password = "";
	private String url = "jdbc:postgresql://localhost:5432/progetto_test";
	private String driver = "org.postgresql.Driver";

	// COSTRUTTORE
	public QuizDBConnection() throws SQLException {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, nome, password);
		} 
		catch (Exception ex) {
			System.out.println("Database Connection Creation Failed : " + ex.getMessage());
			ex.printStackTrace();
		}

	}

	public Connection getConnection() {	return conn; }
		
	public static QuizDBConnection getInstance() throws SQLException {
		if (instance == null) 	
			instance = new QuizDBConnection();
		else if (instance.getConnection().isClosed())	
			instance = new QuizDBConnection();
		
		return instance;
	}
}
