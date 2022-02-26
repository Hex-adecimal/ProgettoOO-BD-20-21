package Model;

import java.util.ArrayList;

public class Class {
	// Attributes
	private String name;
	private int cfu;
	private int year;
	
	private Professor professor;
	private ArrayList<Student> students;
	private ArrayList<Test> tests;
	
	// Methods
	public Class(String name, int year, int cfu)
	{
		this.name = name;
		this.cfu = cfu;
		this.year = year;
	}
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public int getCFU() { return cfu; }
	public void setCFU(int cfu) { this.cfu = cfu; }
	
	public int getYear() { return year; }
	public void setYear(int year) {	this.year = year; }
	
	
	public Professor getProfessor() { return professor;	}
	public void setProfessor(Professor professor) { this.professor = professor;	}
	
	public ArrayList<Student> getStudents() { return students; }
	public void setStudents(ArrayList<Student> students) { this.students = students; }
	
	public ArrayList<Test> getTests() {	return tests; }
	public void setTests(ArrayList<Test> tests) { this.tests = tests; }
}
