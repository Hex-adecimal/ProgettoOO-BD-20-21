package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import Model.Professor;
import Model.Student;

import javax.swing.JTextField;
import javax.swing.JButton;

public class UpdateAccount extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldEmail;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JFrame updateAccount;
	private Controller controller;
	
	public UpdateAccount(Controller controller, JFrame jframe) {
		updateAccount = this;
		jframe.setVisible(false);
		this.setVisible(true);
		setResizable(false);
		this.controller = controller;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldFirstName = new JTextField(controller.getUser().getFirstName());
		textFieldFirstName.setBounds(6, 6, 177, 26);
		contentPane.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		textFieldLastName = new JTextField(controller.getUser().getLastName());
		textFieldLastName.setBounds(218, 6, 177, 26);
		contentPane.add(textFieldLastName);
		textFieldLastName.setColumns(10);
		
		textFieldEmail = new JTextField(controller.getUser().getEmail());
		textFieldEmail.setBounds(6, 44, 177, 26);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		textFieldUsername = new JTextField(controller.getUser().getUsername());
		textFieldUsername.setBounds(218, 44, 177, 26);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPassword = new JTextField(controller.getUser().getPassword());
		textFieldPassword.setBounds(6, 82, 177, 26);
		contentPane.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JButton btnUpdate = new JButton("Update account");
		btnUpdate.setBounds(218, 82, 117, 29);
		contentPane.add(btnUpdate);
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// Go to new gui
				if( controller.getUser() instanceof Model.Professor ) { 
					controller.updateAccount(  ((Professor) controller.getUser()).getCodP(), textFieldFirstName.getText(), 
							textFieldLastName.getText(), textFieldEmail.getText(), textFieldUsername.getText(), 
							textFieldPassword.getText() );
					HomeProfessor hp =  new HomeProfessor(updateAccount, controller);
				} else {
					controller.updateAccount(  ((Student) controller.getUser()).getStudentID(), textFieldFirstName.getText(), 
							textFieldLastName.getText(), textFieldEmail.getText(), textFieldUsername.getText(), 
							textFieldPassword.getText() );  
					HomeStudent hs =  new HomeStudent(updateAccount, controller);
				}
			}
		});
	}
}
