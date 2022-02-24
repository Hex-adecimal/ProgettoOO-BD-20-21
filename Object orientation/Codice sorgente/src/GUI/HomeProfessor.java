package GUI;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import DAO.ProfessorDAO;
import Model.Professor;
import Model.Student;
import Model.User;
import PostgreImplementationDAO.ProfessorPostgre;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.CardLayout;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Container;
import javax.swing.ScrollPaneConstants;

public class HomeProfessor extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	private JTable tableClasses;
	private String myClassesLabel = "My classes";
	private String myTestsLabel = "My tests";
	private String myLecturesLabel = "My lectures";
	private HomeProfessor homeProfessor;
	Professor mainUser;
	
	/**
	 * Create the frame.
	 */
	public HomeProfessor(JFrame login, Controller controller, User user) {
		
		login.setVisible(false);
		this.controller = controller;
		homeProfessor = this;
		homeProfessor.setVisible(true);
		Vector<String> record = new Vector<String>();
		mainUser = (Professor) user;
		
		
		System.out.println("");
		System.out.println("You are in the professor home, your credential are:");
		System.out.println("codP: " + this.mainUser.getCodP());
		System.out.println("first name: " + this.mainUser.getFirstName());
		System.out.println("last name: " + this.mainUser.getLastName());
		System.out.println("email: " + this.mainUser.getEmail());
		System.out.println("username: " + this.mainUser.getUsername());
		System.out.println("");
		
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
		gbl_panelSide.rowHeights = new int[]{41, 0, 0, 0};
		gbl_panelSide.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelSide.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelSide.setLayout(gbl_panelSide);
		
		JPanel panelMain = new JPanel();
		contentPane.add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(new CardLayout(0, 0));
		
		
		JScrollPane scrollPaneClasses = new JScrollPane();
		scrollPaneClasses.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelMain.add(scrollPaneClasses, "scrollPaneClasses");
		
		JScrollPane scrollPaneTests = new JScrollPane();
		panelMain.add(scrollPaneTests, "scrollPaneTests");
		
		JPanel subpanelTests = new JPanel();
		scrollPaneTests.setViewportView(subpanelTests);
		GridBagLayout gbl_subpanelTests = new GridBagLayout();
		gbl_subpanelTests.columnWidths = new int[]{0, 0, 0};
		gbl_subpanelTests.rowHeights = new int[]{0, 0, 0};
		gbl_subpanelTests.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_subpanelTests.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		subpanelTests.setLayout(gbl_subpanelTests);
		
		JLabel lblNewLabel = new JLabel("tests");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		subpanelTests.add(lblNewLabel, gbc_lblNewLabel);
		
		JScrollPane scrollPaneLectures = new JScrollPane();
		panelMain.add(scrollPaneLectures, "scrollPaneLectures");
		
		JPanel subpanelLectures = new JPanel();
		scrollPaneLectures.setViewportView(subpanelLectures);
		GridBagLayout gbl_subpanelLectures = new GridBagLayout();
		gbl_subpanelLectures.columnWidths = new int[]{0, 0, 0};
		gbl_subpanelLectures.rowHeights = new int[]{0, 0, 0};
		gbl_subpanelLectures.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_subpanelLectures.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		subpanelLectures.setLayout(gbl_subpanelLectures);
		
		JLabel lblNewLabel_2 = new JLabel("lectures");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 1;
		subpanelLectures.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		
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
			public void mouseClicked(MouseEvent e) {
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
			public void mouseClicked(MouseEvent e) {
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
		
		JLabel lblMyLectures = new JLabel(myLecturesLabel);
		lblMyLectures.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblMyLectures.setForeground(new Color(135, 206, 250));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblMyLectures.setForeground(new Color(148, 0, 211));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout)panelMain.getLayout();
				cl.show(panelMain, "scrollPaneLectures");
			}
		});
		lblMyLectures.setForeground(new Color(148, 0, 211));
		lblMyLectures.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 30));
		GridBagConstraints gbc_lblMyLectures = new GridBagConstraints();
		gbc_lblMyLectures.anchor = GridBagConstraints.WEST;
		gbc_lblMyLectures.gridx = 0;
		gbc_lblMyLectures.gridy = 2;
		panelSide.add(lblMyLectures, gbc_lblMyLectures);
	}

}
