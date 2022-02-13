package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;


import DAO.UserDAO;
import Model.User;
import PostgreImplementationDAO.UserPostgre;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainLogin {

	private JFrame frmQuizzoneLogin;
	private JTextField userEmailField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainLogin window = new MainLogin();
					window.frmQuizzoneLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmQuizzoneLogin = new JFrame();
		frmQuizzoneLogin.setResizable(false);
		frmQuizzoneLogin.getContentPane().setBackground(SystemColor.activeCaption);
		frmQuizzoneLogin.setTitle("Quizzone - Login");
		frmQuizzoneLogin.setBounds(100, 100, 416, 495);
		frmQuizzoneLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frmQuizzoneLogin.getContentPane().add(panel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		frmQuizzoneLogin.getContentPane().add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{49, 301, 0};
		gbl_panel_1.rowHeights = new int[]{30, 48, 31, 30, 48, 31, 21, 55, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblUserEmail = new JLabel("Username or Email:");
		lblUserEmail.setForeground(Color.BLACK);
		lblUserEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserEmail.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 25));
		GridBagConstraints gbc_lblUserEmail = new GridBagConstraints();
		gbc_lblUserEmail.anchor = GridBagConstraints.WEST;
		gbc_lblUserEmail.insets = new Insets(0, 0, 5, 0);
		gbc_lblUserEmail.gridx = 1;
		gbc_lblUserEmail.gridy = 1;
		panel_1.add(lblUserEmail, gbc_lblUserEmail);
		
		userEmailField = new JTextField();
		userEmailField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)	checkLoginResult();
			}
		});
		userEmailField.setBackground(SystemColor.menu);
		userEmailField.setFont(new Font("Calibri", Font.PLAIN, 34));
		GridBagConstraints gbc_userEmailField = new GridBagConstraints();
		gbc_userEmailField.anchor = GridBagConstraints.NORTH;
		gbc_userEmailField.fill = GridBagConstraints.HORIZONTAL;
		gbc_userEmailField.insets = new Insets(0, 0, 5, 0);
		gbc_userEmailField.gridx = 1;
		gbc_userEmailField.gridy = 2;
		panel_1.add(userEmailField, gbc_userEmailField);
		userEmailField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 25));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.NORTH;
		gbc_lblPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 0);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 4;
		panel_1.add(lblPassword, gbc_lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)	checkLoginResult();
			}
		});
		passwordField.setBackground(SystemColor.menu);
		passwordField.setFont(new Font("Calibri", Font.PLAIN, 34));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.anchor = GridBagConstraints.NORTH;
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 5;
		panel_1.add(passwordField, gbc_passwordField);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBackground(Color.WHITE);
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkLoginResult();
			}
		});
		btnLogin.setFont(new Font("Dubai", Font.BOLD, 27));
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.insets = new Insets(0, 0, 5, 0);
		gbc_btnLogin.anchor = GridBagConstraints.NORTH;
		gbc_btnLogin.gridx = 1;
		gbc_btnLogin.gridy = 7;
		panel_1.add(btnLogin, gbc_btnLogin);
		
		JLabel lblRegistration = new JLabel("Non sei registrato? Registrati!");
		lblRegistration.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblRegistration = new GridBagConstraints();
		gbc_lblRegistration.anchor = GridBagConstraints.NORTH;
		gbc_lblRegistration.gridx = 1;
		gbc_lblRegistration.gridy = 9;
		panel_1.add(lblRegistration, gbc_lblRegistration);
	}
	
	private void checkLoginResult()
	{
		UserDAO mainUserDAO = new UserPostgre();
		
		String password = String.valueOf(passwordField.getPassword());
		
		User mainUser = mainUserDAO.logUser(userEmailField.getText(), password);
		
		if(mainUser == null)
		{
			String msg = "Error! This account does not exist or the inputted credentials are wrong!";
			JOptionPane.showMessageDialog(null, msg, "Failed Login", JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			if(mainUser.getClassName().equals("Student"))
			{
				HomeStudent frmHomeS = new HomeStudent();
				
				frmHomeS.setVisible(true);
				frmQuizzoneLogin.setVisible(false);
			}
			else
			{
				HomeProfessor frmHomeP = new HomeProfessor();
				
				frmHomeP.setVisible(true);
				frmQuizzoneLogin.setVisible(false);
			}
			//		passare il controllo a HomeProfessor
			
			
			
			// TODO: instantiate Controller somewhere
			
			
		}
	}

}
