package GUI;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;

import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class HomeProfessor extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	private String myClassesLabel = "My classes";
	private String myTestsLabel = "My tests";
	private HomeProfessor homeProfessor;
	
	/**
	 * Create the frame.
	 */
	public HomeProfessor(JFrame parent, Controller controller) {
		setResizable(false);
		
		parent.dispose();
		this.controller = controller;
		homeProfessor = this;
		homeProfessor.setVisible(true);
		
		/*System.out.println("");
		System.out.println("You are in the professor home, your credential are:");
		System.out.println("codP: " + this.mainUser.getCodP());
		System.out.println("first name: " + this.mainUser.getFirstName());
		System.out.println("last name: " + this.mainUser.getLastName());
		System.out.println("email: " + this.mainUser.getEmail());
		System.out.println("username: " + this.mainUser.getUsername());
		System.out.println("");*/
		
		setTitle("Home - Professor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelSide = new JPanel();
		panelSide.setBackground(Color.WHITE);
		contentPane.add(panelSide, BorderLayout.WEST);
		GridBagLayout gbl_panelSide = new GridBagLayout();
		gbl_panelSide.columnWidths = new int[]{164, 0};
		gbl_panelSide.rowHeights = new int[]{41, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelSide.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelSide.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelSide.setLayout(gbl_panelSide);
		
		JPanel panelMain = new JPanel();
		contentPane.add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(new CardLayout(0, 0));
		
		
		JScrollPane scrollPaneClasses = new JScrollPane();
		scrollPaneClasses.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelMain.add(scrollPaneClasses, "scrollPaneClasses");
		
		DefaultListModel<String> listClassesModel = new DefaultListModel<String>();
		
		ArrayList<String> classes = controller.getNSetProfessorClasses();
		
		for(String i : classes)
		{
			listClassesModel.addElement(i);
		}
		
		JList<String> listClasses = new JList<String>(listClassesModel);
		listClasses.setFont(new Font("Tahoma", Font.PLAIN, 26));
		scrollPaneClasses.setViewportView(listClasses);
		
		JLabel lblClassesHeader = new JLabel("My classes");
		lblClassesHeader.setBackground(Color.WHITE);
		lblClassesHeader.setFont(new Font("Dubai Medium", Font.PLAIN, 64));
		scrollPaneClasses.setColumnHeaderView(lblClassesHeader);
		
		
		JScrollPane scrollPaneTests = new JScrollPane();
		panelMain.add(scrollPaneTests, "scrollPaneTests");
		
		JLabel lblTestsHeader = new JLabel("My tests");
		lblTestsHeader.setFont(new Font("Dubai Medium", Font.PLAIN, 64));
		scrollPaneTests.setColumnHeaderView(lblTestsHeader);
		
		JPanel subpanelTests = new JPanel();
		scrollPaneTests.setViewportView(subpanelTests);
		subpanelTests.setLayout(new BorderLayout(0, 0));
		
		DefaultListModel<String> listTestsModel = new DefaultListModel<String>();
		
		ArrayList<String> tests = controller.getNSetProfessorTests();
		
		for(String i : tests)
		{
			listTestsModel.addElement(i);
		}
		
		JList<String> listTests = new JList<String>(listTestsModel);
		listTests.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				startTestCustomization((String)listTests.getSelectedValue());
			}
		});
		listTests.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listTests.setFont(new Font("Tahoma", Font.PLAIN, 15));
		subpanelTests.add(listTests, BorderLayout.CENTER);
		
		JButton btnNewTest = new JButton("+ New Test");
		btnNewTest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				startTestCreation();
			}
		});
		btnNewTest.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewTest.setFont(new Font("Tahoma", Font.PLAIN, 20));
		subpanelTests.add(btnNewTest, BorderLayout.SOUTH);
		
		
		JLabel lblMyClasses = new JLabel(myClassesLabel);
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
				CardLayout cl = (CardLayout)panelMain.getLayout();
				cl.show(panelMain, "scrollPaneClasses");
			}
		});
		lblMyClasses.setForeground(new Color(148, 0, 211));
		lblMyClasses.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 30));
		GridBagConstraints gbc_lblMyClasses = new GridBagConstraints();
		gbc_lblMyClasses.insets = new Insets(0, 0, 5, 0);
		gbc_lblMyClasses.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblMyClasses.gridx = 0;
		gbc_lblMyClasses.gridy = 0;
		panelSide.add(lblMyClasses, gbc_lblMyClasses);
		
		JLabel lblMyTests = new JLabel(myTestsLabel);
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
				CardLayout cl = (CardLayout)panelMain.getLayout();
				cl.show(panelMain, "scrollPaneTests");
			}
		});
		lblMyTests.setForeground(new Color(148, 0, 211));
		lblMyTests.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 30));
		GridBagConstraints gbc_lblMyTests = new GridBagConstraints();
		gbc_lblMyTests.insets = new Insets(0, 0, 5, 0);
		gbc_lblMyTests.anchor = GridBagConstraints.WEST;
		gbc_lblMyTests.gridx = 0;
		gbc_lblMyTests.gridy = 1;
		panelSide.add(lblMyTests, gbc_lblMyTests);
		
		JLabel lblUpdateAccount = new JLabel("Account");
		lblUpdateAccount.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 30));
		lblUpdateAccount.setForeground(new Color(148, 0, 211));
		GridBagConstraints gbc_lblUpdateAccount = new GridBagConstraints();
		gbc_lblUpdateAccount.gridx = 0;
		gbc_lblUpdateAccount.gridy = 10;
		panelSide.add(lblUpdateAccount, gbc_lblUpdateAccount);
		lblUpdateAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblUpdateAccount.setForeground(new Color(135, 206, 250));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblUpdateAccount.setForeground(new Color(148, 0, 211));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// Go to new gui
				UpdateAccount ua = new UpdateAccount(controller, homeProfessor);
			}
		});
	}
	
	private void startTestCreation()
	{
		TestCreation t = new TestCreation(this, controller);
	}
	
	private void startTestCustomization(String selectedTest)
	{
		String trimmedSelTest = selectedTest.substring(selectedTest.indexOf(":") + 1);
		
		String codTest = trimmedSelTest.substring(0, trimmedSelTest.indexOf(" "));
		
		java.sql.Date currentDate = new java.sql.Date(new java.util.Date().getTime());
		java.sql.Date testStartingDate = controller.getTestStartingDate(codTest);
		java.sql.Time currentTime = new java.sql.Time(new java.util.Date().getTime());
		java.sql.Time testStartingTime = controller.getTestStartingTime(codTest);
		
		if( currentDate.toString().compareTo(testStartingDate.toString()) > 0 ||
			(currentDate.toString().compareTo(testStartingDate.toString()) == 0 &&
			 currentTime.toString().compareTo(testStartingTime.toString()) > 0) )
		{
			JOptionPane.showMessageDialog(null, 
										"You cannot customize a test that has already started and/or has already been closed!", 
										null, 
										JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			TestCreation t = new TestCreation(this, controller, selectedTest);
		}
	}

}
