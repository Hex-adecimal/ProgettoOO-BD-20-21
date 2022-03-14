package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class HomeStudent extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	private JFrame homeStudent;
	private JTextField textFieldCodTest;
	private DefaultListModel<String> dlm;
	private DefaultListModel<String> deflismod;

	public HomeStudent(JFrame login, Controller controller) {
		setResizable(false);
		login.setVisible(false);
		this.controller = controller;
		homeStudent = this;
		homeStudent.setVisible(true);

		deflismod = new DefaultListModel<String>();
		dlm = new DefaultListModel<String>();
		ArrayList<String> tests = null;
		ArrayList<String> classes = null;

		classes = controller.getStudentClasses();
		tests = controller.getStudentTestsTaken();

		deflismod.addElement("Name --- Year --- CFU");
		for (String i : classes) {
			deflismod.addElement(i);
		}

		dlm.addElement("Name --- Revised --- Passed --- Score");
		for (String i : tests) {
			dlm.addElement(i);
		}

		/*System.out.println("");
		System.out.println("You are in the student home, your credential are:");
		System.out.println("studentID: " + this.user.getStudentID());
		System.out.println("first name: " + this.user.getFirstName());
		System.out.println("last name: " + this.user.getLastName());
		System.out.println("email: " + this.user.getEmail());
		System.out.println("username: " + this.user.getUsername());
		System.out.println("");*/

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelSide = new JPanel();
		panelSide.setBounds(6, 6, 164, 680);
		contentPane.add(panelSide);
		panelSide.setLayout(null);

		textFieldCodTest = new JTextField();
		textFieldCodTest.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (textFieldCodTest.getText().equals("Insert test here")) {
					textFieldCodTest.setText("");
					textFieldCodTest.setForeground(Color.BLACK);
				}
			}
		});
		textFieldCodTest.setForeground(Color.GRAY);
		textFieldCodTest.setText("Insert test here");
		textFieldCodTest.setBounds(6, 6, 107, 26);
		panelSide.add(textFieldCodTest);
		textFieldCodTest.setColumns(10);

		JButton btnSearchTest = new JButton("...");

		btnSearchTest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// Function for search a test, and go to next gui
				if ( controller.testExists(textFieldCodTest.getText()) ) {
					TestTurnIn tti = new TestTurnIn(controller, homeStudent, textFieldCodTest.getText());
				}
			}
		});
		btnSearchTest.setBounds(114, 6, 44, 26);
		panelSide.add(btnSearchTest);

		JPanel panelMain = new JPanel();
		panelMain.setBounds(182, 6, 1092, 680);
		contentPane.add(panelMain);

		JLabel lblMyClasses = new JLabel("My Classes");
		lblMyClasses.setForeground(new Color(148, 0, 211));
		lblMyClasses.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblMyClasses.setForeground(new Color(135, 206, 250));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblMyClasses.setForeground(new Color(148, 0, 211));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// Function for search classes, and update the home
				CardLayout cl = (CardLayout)panelMain.getLayout();
				cl.show(panelMain, "scrollPaneMyClasses");
			}
		});
		lblMyClasses.setBounds(16, 44, 142, 16);
		panelSide.add(lblMyClasses);
		panelMain.setLayout(new CardLayout(0, 0));

		JScrollPane scrollPaneMyTests = new JScrollPane();
		panelMain.add(scrollPaneMyTests, "scrollPaneMyTests");

		JList listTests = new JList(dlm);
		scrollPaneMyTests.setViewportView(listTests);

		JScrollPane scrollPaneMyClasses = new JScrollPane();
		panelMain.add(scrollPaneMyClasses, "scrollPaneMyClasses");

		JList listClasses = new JList(deflismod);
		scrollPaneMyClasses.setViewportView(listClasses);

		JLabel lblMyTests = new JLabel("My Tests");
		lblMyTests.setForeground(new Color(148, 0, 211));
		lblMyTests.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblMyTests.setForeground(new Color(135, 206, 250));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblMyTests.setForeground(new Color(148, 0, 211));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// Function for search tests done, and update the home
				CardLayout cl = (CardLayout)panelMain.getLayout();
				cl.show(panelMain, "scrollPaneMyTests");
			}
		});
		lblMyTests.setBounds(16, 87, 61, 16);
		panelSide.add(lblMyTests);

		JLabel lblUpdateData = new JLabel("Account");
		lblUpdateData.setBounds(16, 371, 61, 16);
		lblUpdateData.setForeground(new Color(148, 0, 211));
		panelSide.add(lblUpdateData);
		lblUpdateData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblUpdateData.setForeground(new Color(135, 206, 250));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblUpdateData.setForeground(new Color(148, 0, 211));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// Go to new gui
				UpdateAccount ua = new UpdateAccount(controller, homeStudent);
			}
		});

	}
}
