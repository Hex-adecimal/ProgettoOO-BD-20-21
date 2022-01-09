package classes;

public class Professor extends User{
	// Attributes
	private String codProfessor;

	// Methods 
	public Professor(String firstName, String lastName, String email, String username, String password) {
		super(firstName, lastName, email, username, password);
	}
	
	public String getCodProfessor() {
		return codProfessor;
	}

	public void setCodProfessor(String codProfessor) {
		this.codProfessor = codProfessor;
	}
}
