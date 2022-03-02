package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import PostgreImplementationDAO.ProfessorPostgre;
import PostgreImplementationDAO.StudentPostgre;
import DAO.ProfessorDAO;
import DAO.StudentDAO;
import Model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Signup extends JFrame {

	private JPanel contentPane;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField usernameField;
	private JTextField emailField;
	private JPasswordField passwordField;
	private Controller controller;
	private String userType;
	private JFrame signUp;
	private User user;
	private HomeProfessor homeProfessor;
	private HomeStudent homeStudent;
	
	/**
	 * Launch the application.
	 */
	
	public Signup(String userType, Controller controller, JFrame login) {
		setResizable(false);
		this.userType = userType;
		this.controller = controller;
		login.dispose();
		signUp = this;
		this.setVisible(true);
		
		System.out.println("You are in the sign in interface!");
		
		setTitle("Quizzone - Signup");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 417, 576);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 47, 227, 0, 0};
		gbl_contentPane.rowHeights = new int[]{53, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblFirstName = new JLabel("First name:");
		lblFirstName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 25));
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.anchor = GridBagConstraints.WEST;
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 2;
		gbc_lblFirstName.gridy = 1;
		contentPane.add(lblFirstName, gbc_lblFirstName);
		
		firstNameField = new JTextField();
		firstNameField.setFont(new Font("Calibri Light", Font.PLAIN, 22));
		GridBagConstraints gbc_firstNameField = new GridBagConstraints();
		gbc_firstNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstNameField.insets = new Insets(0, 0, 5, 5);
		gbc_firstNameField.gridx = 2;
		gbc_firstNameField.gridy = 2;
		contentPane.add(firstNameField, gbc_firstNameField);
		firstNameField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last name:");
		lblLastName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 25));
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.WEST;
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 2;
		gbc_lblLastName.gridy = 3;
		contentPane.add(lblLastName, gbc_lblLastName);
		
		lastNameField = new JTextField();
		lastNameField.setFont(new Font("Calibri Light", Font.PLAIN, 22));
		GridBagConstraints gbc_lastNameField = new GridBagConstraints();
		gbc_lastNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastNameField.insets = new Insets(0, 0, 5, 5);
		gbc_lastNameField.gridx = 2;
		gbc_lastNameField.gridy = 4;
		contentPane.add(lastNameField, gbc_lastNameField);
		lastNameField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 25));
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.WEST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 2;
		gbc_lblUsername.gridy = 5;
		contentPane.add(lblUsername, gbc_lblUsername);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Calibri Light", Font.PLAIN, 22));
		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameField.insets = new Insets(0, 0, 5, 5);
		gbc_usernameField.gridx = 2;
		gbc_usernameField.gridy = 6;
		contentPane.add(usernameField, gbc_usernameField);
		usernameField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 25));
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 2;
		gbc_lblEmail.gridy = 7;
		contentPane.add(lblEmail, gbc_lblEmail);
		
		emailField = new JTextField();
		emailField.setFont(new Font("Calibri Light", Font.PLAIN, 22));
		GridBagConstraints gbc_emailField = new GridBagConstraints();
		gbc_emailField.insets = new Insets(0, 0, 5, 5);
		gbc_emailField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailField.gridx = 2;
		gbc_emailField.gridy = 8;
		contentPane.add(emailField, gbc_emailField);
		emailField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 25));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 2;
		gbc_lblPassword.gridy = 9;
		contentPane.add(lblPassword, gbc_lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Calibri Light", Font.PLAIN, 22));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 10;
		contentPane.add(passwordField, gbc_passwordField);
		
		JButton btnSignupButton = new JButton("Sign Up");
		btnSignupButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkSignupResult();
			}
		});
		btnSignupButton.setFont(new Font("Dubai Medium", Font.PLAIN, 32));
		GridBagConstraints gbc_btnSignupButton = new GridBagConstraints();
		gbc_btnSignupButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnSignupButton.gridx = 2;
		gbc_btnSignupButton.gridy = 12;
		contentPane.add(btnSignupButton, gbc_btnSignupButton);
	}
	
	private void checkSignupResult() {
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		String username = usernameField.getText();
		String email = emailField.getText();
		String password = String.valueOf(passwordField.getPassword());
		
		String error = "The following errors have been detected:";
		int emptyErrorLength = error.length();
		
		if(!email.contains("@"))
			error += "\n\t- Invalid email address";
		
		if(password.length() < 8)
			error += "\n\t- The password is weak. It needs to have at least 8 characters!";
		
		if(firstName.isBlank()|| 
				lastName.isBlank()|| 
				username.isBlank() ||
				email.isBlank()||
				password.isBlank())
			error += "\n\t- Some fields are empty";
		
		
		if(error.length() > emptyErrorLength)
			JOptionPane.showMessageDialog(null, error, "Failed Registration", JOptionPane.INFORMATION_MESSAGE);
		else
		{
			if(this.userType.equals("Student"))
				user = controller.signUpS(firstName, lastName, username, email, password);
				
			else
				user = controller.signUpP(firstName, lastName, username, email, password);
			
			if(user != null)
			{
				JOptionPane.showMessageDialog(null, 
						"Congratulations! You have signed up successfully", 
						"Successful Registration", 
						JOptionPane.INFORMATION_MESSAGE);
				
				if (user.getTypeName() == "Model.Student") {
					homeStudent = new HomeStudent(this, controller);
				} else {
					homeProfessor = new HomeProfessor(this, controller);
				}
			}
			else { JOptionPane.showMessageDialog(null, error, "Failed Registration", JOptionPane.INFORMATION_MESSAGE); }
		}
	}

}
