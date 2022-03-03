package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import Model.User;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private Controller controller; 
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private JFrame login;
	
	public Login(Controller controller) {
		System.out.println("You are in the login interface!");
		this.controller = controller;
		initialize();
		this.setVisible(true);
		login = this;
	}
	
	private void initialize() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JToggleButton tglbtnUser = new JToggleButton("Student");
		tglbtnUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (tglbtnUser.getText() == "Student")
					tglbtnUser.setText("Professor");
				else
					tglbtnUser.setText("Student");
			}
		});
		tglbtnUser.setBounds(245, 46, 161, 29);
		contentPane.add(tglbtnUser);
		
		
		textFieldUsername = new JTextField();
		textFieldUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					checkLoginResult(tglbtnUser.getText());
				}
			}
		});
		textFieldUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { 
				if (textFieldUsername.getText().equals("Email or username")) {
					textFieldUsername.setText("");
					textFieldUsername.setForeground(Color.BLACK);
				}
			}
		});
		textFieldUsername.setForeground(Color.GRAY);
		textFieldUsername.setText("Email or username");
		textFieldUsername.setBounds(30, 30, 130, 26);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					checkLoginResult(tglbtnUser.getText());
				}
			}
		});
		passwordField.setBounds(30, 68, 130, 26);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				checkLoginResult(tglbtnUser.getText());
			}
		});
		btnLogin.setBounds(43, 106, 117, 29);
		contentPane.add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("I am a:");
		lblNewLabel.setBounds(196, 51, 51, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Sign up");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("You are trying to signup...");
				Signup signup = new Signup(tglbtnUser.getText() , controller, login);
				
			}
		});
		btnNewButton.setBounds(172, 106, 117, 29);
		contentPane.add(btnNewButton);
	}
	
	private void checkLoginResult(String tglbtnText)
	{
		String password;
		User user = null;
		
		if (passwordField.getPassword().length > 8) {
			if (textFieldUsername.getText().length() > 1) {
				System.out.println("You are trying to logging in ...");
				
				password = new String(passwordField.getPassword());
				user = controller.logIn(textFieldUsername.getText(), password, tglbtnText);
				
				if (user != null) { 
					System.out.println("You logged in!");
					controller.setUser(user);
					if (tglbtnText == "Student") {
						HomeStudent home = new HomeStudent(login, controller);
					} else { 
						HomeProfessor home = new HomeProfessor(login, controller);
					}
				}
				
			} else {
				System.out.println("The input box cannot be empty.");
			}
		} else {
			System.out.println("The password is not long enough.");
		}
	}
}
