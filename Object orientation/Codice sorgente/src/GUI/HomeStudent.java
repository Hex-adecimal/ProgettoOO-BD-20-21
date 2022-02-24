package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import Model.Student;
import Model.User;

public class HomeStudent extends JFrame {

	private Student user;
	private JPanel contentPane;
	private Controller controller;
	private JFrame homeStudent;
	
	
	public HomeStudent(JFrame login, Controller controller, User user) {
		login.setVisible(false);
		this.controller = controller;
		this.user = (Student) user;
		homeStudent = this;
		homeStudent.setVisible(true);
		
		System.out.println("");
		System.out.println("You are in the student home, your credential are:");
		System.out.println("studentID: " + this.user.getStudentID());
		System.out.println("first name: " + this.user.getFirstName());
		System.out.println("last name: " + this.user.getLastName());
		System.out.println("email: " + this.user.getEmail());
		System.out.println("username: " + this.user.getUsername());
		System.out.println("");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
}
