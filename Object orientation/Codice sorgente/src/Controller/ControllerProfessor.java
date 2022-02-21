package Controller;

import Model.Professor;

public class ControllerProfessor {
	private Professor mainUser = null;
	
	public ControllerProfessor(Professor mainUser)
	{
		this.mainUser = mainUser;
	}
	
	public Professor getMainUser() { return mainUser; }
}
