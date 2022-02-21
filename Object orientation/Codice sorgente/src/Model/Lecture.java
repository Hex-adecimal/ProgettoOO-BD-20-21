package Model;

public class Lecture {
	// Attributes
	private String title;
	private String link;
	
	private Professor professor;
	private Class receivingClass;
	
	// Methods
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	
	public String getLink() { return link; }
	public void setLink(String link) { this.link = link; }
	
	
	public Professor getProfessor() { return professor;	}
	public void setProfessor(Professor professor) { this.professor = professor;	}
	
	public Class getReceivingClass() { return receivingClass; }
	public void setReceivingClass(Class receivingClass) { this.receivingClass = receivingClass;	}
}
