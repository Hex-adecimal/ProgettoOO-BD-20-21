package GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.toedter.calendar.*;

import Controller.Controller;

import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JSpinner;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;

import java.util.ArrayList;

public class TestCreation extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	private JPanel panelQuizzes;
	private JTextField textFieldName;
	private JSpinner spinnerMinScore;
	private JCalendar calendarStartingDate;
	private JCalendar calendarClosingDate;
	private JComboBox comboBoxStartingHour;
	private JComboBox comboBoxStartingMinute;
	private JComboBox comboBoxClosingHour;
	private JComboBox comboBoxClosingMinute;
	private JButton btnAddOpenQuiz;
	private JButton btnAddClosedQuiz;
	private ArrayList<OpenQuizCreationPanel> openQuizzes = new ArrayList<OpenQuizCreationPanel>();
	private ArrayList<ClosedQuizCreationPanel> closedQuizzes = new ArrayList<ClosedQuizCreationPanel>();
	private ArrayList<OpenQuizCreationPanel> newOpenQuizzes = new ArrayList<OpenQuizCreationPanel>();
	private ArrayList<ClosedQuizCreationPanel> newClosedQuizzes = new ArrayList<ClosedQuizCreationPanel>();
	private JPanel panelButtons;
	private JButton btnFinish;
	
	private int nextAvailableRow;
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

	/**
	 * Create the frame.
	 */
	public TestCreation(JFrame homeProfessor, Controller c, String selectedTest)
	{
		initialize(homeProfessor, c);
		
		String trimmedSelTest = selectedTest.substring(selectedTest.indexOf(":") + 1);
		String codTest = trimmedSelTest.substring(0, trimmedSelTest.indexOf(" "));
		String testString = controller.getTestString(codTest);
		
		String ogTestName = testString.substring(0, testString.indexOf("|"));
		testString = testString.substring(testString.indexOf("|") + 1);
		
		java.util.Date ogStartingDate = new java.util.Date(java.sql.Date.valueOf(testString.substring(0, testString.indexOf("|"))).getTime());
		testString = testString.substring(testString.indexOf("|") + 1);
		String ogStartingDateString = dateFormat.format(ogStartingDate);
		
		String ogStartingHour = testString.substring(0, testString.indexOf(":"));
		testString = testString.substring(testString.indexOf(":") + 1);
		
		String ogStartingMinute = testString.substring(0, testString.indexOf(":"));
		testString = testString.substring(testString.indexOf("|") + 1);
		
		String ogStartingTimeString = ogStartingHour + ":" + ogStartingMinute + ":00";
		
		java.util.Date ogClosingDate = new java.util.Date( java.sql.Date.valueOf( testString.substring(0, testString.indexOf("|")) ).getTime() );
		testString = testString.substring(testString.indexOf("|") + 1);
		String ogClosingDateString = dateFormat.format(ogClosingDate);
		
		String ogClosingHour = testString.substring(0, testString.indexOf(":"));
		testString = testString.substring(testString.indexOf(":") + 1);
		
		String ogClosingMinute = testString.substring(0, testString.indexOf(":"));
		testString = testString.substring(testString.indexOf("|") + 1);
		
		String ogClosingTimeString = ogClosingHour + ":" + ogClosingMinute + ":00";
		
		float ogMinScore = Float.valueOf(testString.substring(0, testString.indexOf("|")));
		
		
		textFieldName.setText(ogTestName);
		calendarStartingDate.setDate(ogStartingDate);
		comboBoxStartingHour.setSelectedItem(ogStartingHour);
		comboBoxStartingMinute.setSelectedItem(ogStartingMinute);
		calendarClosingDate.setDate(ogClosingDate);
		comboBoxClosingHour.setSelectedItem(ogClosingHour);
		comboBoxClosingMinute.setSelectedItem(ogClosingMinute);
		spinnerMinScore.setValue(ogMinScore);
		
		ArrayList<String> ogOpenQuizzes = controller.getOpenQuizStrings(codTest);
		
		String codQuiz;
		String question;
		float maxScore;
		float minScore;
		int maxLength;
		
		for(String i : ogOpenQuizzes)
		{
			codQuiz = i.substring(0, i.indexOf("|"));
			i = i.substring(i.indexOf("|") + 1);
			
			question = i.substring(0, i.indexOf("|"));
			i = i.substring(i.indexOf("|") + 1);
			
			maxScore = Float.valueOf( i.substring(0, i.indexOf("|")) );
			i = i.substring(i.indexOf("|") + 1);
			
			minScore = Float.valueOf( i.substring(0, i.indexOf("|")) );
			i = i.substring(i.indexOf("|") + 1);
			
			maxLength = Integer.valueOf( i.substring(0, i.indexOf("|")) );
			i = i.substring(i.indexOf("|") + 1);
			
			createOpenQuizPanel(codQuiz, question, maxScore, minScore, maxLength);
		}
		
		
		ArrayList<String> ogClosedQuizzes = controller.getClosedQuizStrings(codTest);
		
		String answerA;
		String answerB;
		String answerC;
		String answerD;
		char rightAnswer;
		float scoreIfRight;
		float scoreIfWrong;
		
		for(String i : ogClosedQuizzes)
		{
			codQuiz = i.substring(0, i.indexOf("|"));
			i = i.substring(i.indexOf("|") + 1);
			
			question = i.substring(0, i.indexOf("|"));
			i = i.substring(i.indexOf("|") + 1);
			
			answerA = i.substring(0, i.indexOf("|"));
			i = i.substring(i.indexOf("|") + 1);
			
			answerB = i.substring(0, i.indexOf("|"));
			i = i.substring(i.indexOf("|") + 1);
			
			answerC = i.substring(0, i.indexOf("|"));
			i = i.substring(i.indexOf("|") + 1);
			
			answerD = i.substring(0, i.indexOf("|"));
			i = i.substring(i.indexOf("|") + 1);
			
			//rightAnswer = Character.valueOf( i.substring(0, i.indexOf("|")) );
			if(i.substring(0, i.indexOf("|")).equals("A"))
				rightAnswer = 'A';
			else if(i.substring(0, i.indexOf("|")).equals("B"))
				rightAnswer = 'B';
			else if(i.substring(0, i.indexOf("|")).equals("C"))
				rightAnswer = 'C';
			else
				rightAnswer = 'D';
			i = i.substring(i.indexOf("|") + 1);
			
			scoreIfRight = Float.valueOf( i.substring(0, i.indexOf("|")) );
			i = i.substring(i.indexOf("|") + 1);
			
			scoreIfWrong = Float.valueOf( i.substring(0, i.indexOf("|")) );
			i = i.substring(i.indexOf("|") + 1);
			
			createClosedQuizPanel(codQuiz,
									question,
									answerA,
									answerB,
									answerC,
									answerD,
									rightAnswer,
									scoreIfRight,
									scoreIfWrong);
			
		}
		
		btnAddOpenQuiz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				createNewOpenQuizPanel();
			}
		});
		
		btnAddClosedQuiz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				createNewClosedQuizPanel();
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				deleteTest(codTest);
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 0, 5);
		gbc_btnDelete.gridx = 5;
		gbc_btnDelete.gridy = 0;
		panelButtons.add(btnDelete, gbc_btnDelete);
		
		btnFinish.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				updateTest(ogTestName,
							ogStartingDateString,
							ogStartingTimeString,
							ogClosingDateString,
							ogClosingTimeString,
							ogMinScore,
							ogOpenQuizzes,
							ogClosedQuizzes);
			}
		});
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public TestCreation(JFrame homeProfessor, Controller c) {
		initialize(homeProfessor, c);
		
		btnAddOpenQuiz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				createOpenQuizPanel();
			}
		});
		
		btnAddClosedQuiz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				createClosedQuizPanel();
			}
		});
		
		btnFinish.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				createTest();
			}
		});
	}
	
	private void initialize(JFrame homeProfessor, Controller c)
	{
		setTitle("Create a new test");
		setResizable(false);
		homeProfessor.dispose();
		this.controller = c;
		this.setVisible(true);
		nextAvailableRow = 1;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 923, 767);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panelTestInfo = new JPanel();
		GridBagLayout gbl_panelTestInfo = new GridBagLayout();
		gbl_panelTestInfo.columnWidths = new int[]{124, 246, 58, 46, 257, 0};
		gbl_panelTestInfo.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 42, 0};
		gbl_panelTestInfo.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelTestInfo.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelTestInfo.setLayout(gbl_panelTestInfo);
		scrollPane.setColumnHeaderView(panelTestInfo);
		
		JLabel lblTestName = new JLabel("Name:");
		lblTestName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblTestName = new GridBagConstraints();
		gbc_lblTestName.anchor = GridBagConstraints.EAST;
		gbc_lblTestName.insets = new Insets(0, 0, 5, 5);
		gbc_lblTestName.gridx = 0;
		gbc_lblTestName.gridy = 1;
		panelTestInfo.add(lblTestName, gbc_lblTestName);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldName.gridx = 1;
		gbc_textFieldName.gridy = 1;
		panelTestInfo.add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblMinScore = new JLabel("Minimal score:");
		lblMinScore.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblMinScore = new GridBagConstraints();
		gbc_lblMinScore.anchor = GridBagConstraints.WEST;
		gbc_lblMinScore.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinScore.gridx = 3;
		gbc_lblMinScore.gridy = 1;
		panelTestInfo.add(lblMinScore, gbc_lblMinScore);
		
		spinnerMinScore = new JSpinner();
		spinnerMinScore.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		spinnerMinScore.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_spinnerMinScore = new GridBagConstraints();
		gbc_spinnerMinScore.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerMinScore.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerMinScore.gridx = 4;
		gbc_spinnerMinScore.gridy = 1;
		panelTestInfo.add(spinnerMinScore, gbc_spinnerMinScore);
		
		JLabel lblStartingDate = new JLabel("Starting date:");
		lblStartingDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblStartingDate = new GridBagConstraints();
		gbc_lblStartingDate.anchor = GridBagConstraints.EAST;
		gbc_lblStartingDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartingDate.gridx = 0;
		gbc_lblStartingDate.gridy = 3;
		panelTestInfo.add(lblStartingDate, gbc_lblStartingDate);
		
		calendarStartingDate = new JCalendar();
		GridBagConstraints gbc_calendarStartingDate = new GridBagConstraints();
		gbc_calendarStartingDate.insets = new Insets(0, 0, 5, 5);
		gbc_calendarStartingDate.fill = GridBagConstraints.BOTH;
		gbc_calendarStartingDate.gridx = 1;
		gbc_calendarStartingDate.gridy = 3;
		panelTestInfo.add(calendarStartingDate, gbc_calendarStartingDate);
		
		calendarStartingDate.setMinSelectableDate(new java.util.Date());
		
		JLabel lblClosingDate = new JLabel("Closing date:");
		lblClosingDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblClosingDate = new GridBagConstraints();
		gbc_lblClosingDate.anchor = GridBagConstraints.EAST;
		gbc_lblClosingDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblClosingDate.gridx = 3;
		gbc_lblClosingDate.gridy = 3;
		panelTestInfo.add(lblClosingDate, gbc_lblClosingDate);
		
		calendarClosingDate = new JCalendar();
		GridBagConstraints gbc_calendarClosingDate = new GridBagConstraints();
		gbc_calendarClosingDate.insets = new Insets(0, 0, 5, 0);
		gbc_calendarClosingDate.fill = GridBagConstraints.BOTH;
		gbc_calendarClosingDate.gridx = 4;
		gbc_calendarClosingDate.gridy = 3;
		panelTestInfo.add(calendarClosingDate, gbc_calendarClosingDate);
		
		calendarClosingDate.setMinSelectableDate(new java.util.Date());
		
		JLabel lblStartingTime = new JLabel("Starting time:");
		lblStartingTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblStartingTime = new GridBagConstraints();
		gbc_lblStartingTime.anchor = GridBagConstraints.EAST;
		gbc_lblStartingTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartingTime.gridx = 0;
		gbc_lblStartingTime.gridy = 5;
		panelTestInfo.add(lblStartingTime, gbc_lblStartingTime);
		
		JPanel panelStartingTime = new JPanel();
		GridBagConstraints gbc_panelStartingTime = new GridBagConstraints();
		gbc_panelStartingTime.anchor = GridBagConstraints.WEST;
		gbc_panelStartingTime.insets = new Insets(0, 0, 5, 5);
		gbc_panelStartingTime.fill = GridBagConstraints.VERTICAL;
		gbc_panelStartingTime.gridx = 1;
		gbc_panelStartingTime.gridy = 5;
		panelTestInfo.add(panelStartingTime, gbc_panelStartingTime);
		
		
		String[] hours = new String[24];
		for(int i = 0; i < 24; i++)
		{
			if(i < 10)	hours[i] = "0" + Integer.toString(i);
			else		hours[i] = Integer.toString(i);
		}
		
		String[] minutes = new String[60];
		for(int i = 0; i < 60; i++)
		{
			if(i < 10)	minutes[i] = "0" + Integer.toString(i);
			else		minutes[i] = Integer.toString(i);
		}
		
		comboBoxStartingHour = new JComboBox(hours);
		panelStartingTime.add(comboBoxStartingHour);
		
		JLabel lblHourMinSeparator = new JLabel(":");
		panelStartingTime.add(lblHourMinSeparator);
		
		comboBoxStartingMinute = new JComboBox(minutes);
		panelStartingTime.add(comboBoxStartingMinute);
		
		JLabel lblClosingTime = new JLabel("Closing time:");
		lblClosingTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblClosingTime = new GridBagConstraints();
		gbc_lblClosingTime.anchor = GridBagConstraints.EAST;
		gbc_lblClosingTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblClosingTime.gridx = 3;
		gbc_lblClosingTime.gridy = 5;
		panelTestInfo.add(lblClosingTime, gbc_lblClosingTime);
		
		JPanel panelClosingTime = new JPanel();
		GridBagConstraints gbc_panelClosingTime = new GridBagConstraints();
		gbc_panelClosingTime.anchor = GridBagConstraints.WEST;
		gbc_panelClosingTime.insets = new Insets(0, 0, 5, 0);
		gbc_panelClosingTime.fill = GridBagConstraints.VERTICAL;
		gbc_panelClosingTime.gridx = 4;
		gbc_panelClosingTime.gridy = 5;
		panelTestInfo.add(panelClosingTime, gbc_panelClosingTime);
		
		comboBoxClosingHour = new JComboBox(hours);
		panelClosingTime.add(comboBoxClosingHour);
		
		JLabel lblHourMinSeparator_1 = new JLabel(":");
		panelClosingTime.add(lblHourMinSeparator_1);
		
		comboBoxClosingMinute = new JComboBox(minutes);
		panelClosingTime.add(comboBoxClosingMinute);
		
		
		panelQuizzes = new JPanel();
		scrollPane.setViewportView(panelQuizzes);
		GridBagLayout gbl_panelQuizzes = new GridBagLayout();
		gbl_panelQuizzes.columnWidths = new int[]{0, 0};
		gbl_panelQuizzes.rowHeights = new int[]{0, 0};
		gbl_panelQuizzes.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelQuizzes.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelQuizzes.setLayout(gbl_panelQuizzes);
		
		panelButtons = new JPanel();
		contentPane.add(panelButtons, BorderLayout.SOUTH);
		GridBagLayout gbl_panelButtons = new GridBagLayout();
		gbl_panelButtons.columnWidths = new int[]{9, 27, 66, 76, 212, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelButtons.rowHeights = new int[]{23, 0};
		gbl_panelButtons.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelButtons.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelButtons.setLayout(gbl_panelButtons);
		
		btnAddOpenQuiz = new JButton("+ Add Open Quiz");
		btnAddOpenQuiz.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnAddOpenQuiz = new GridBagConstraints();
		gbc_btnAddOpenQuiz.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAddOpenQuiz.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddOpenQuiz.gridx = 2;
		gbc_btnAddOpenQuiz.gridy = 0;
		panelButtons.add(btnAddOpenQuiz, gbc_btnAddOpenQuiz);
		
		btnAddClosedQuiz = new JButton("+ Add Closed Quiz");
		btnAddClosedQuiz.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnAddClosedQuiz = new GridBagConstraints();
		gbc_btnAddClosedQuiz.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAddClosedQuiz.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddClosedQuiz.gridx = 3;
		gbc_btnAddClosedQuiz.gridy = 0;
		panelButtons.add(btnAddClosedQuiz, gbc_btnAddClosedQuiz);
		
		btnFinish = new JButton("Finish");
		btnFinish.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnFinish = new GridBagConstraints();
		gbc_btnFinish.insets = new Insets(0, 0, 0, 5);
		gbc_btnFinish.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnFinish.gridx = 7;
		gbc_btnFinish.gridy = 0;
		panelButtons.add(btnFinish, gbc_btnFinish);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				cancelCreation();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 6;
		gbc_btnCancel.gridy = 0;
		panelButtons.add(btnCancel, gbc_btnCancel);
	}
	
	private void cancelCreation()
	{
		HomeProfessor home = new HomeProfessor(this, controller);
	}
	
	private void createNewOpenQuizPanel()
	{
		OpenQuizCreationPanel panelOpenQuiz = new OpenQuizCreationPanel();
		newOpenQuizzes.add(panelOpenQuiz);
		
		placeOpenQuizPanel(panelOpenQuiz);
		this.validate();
	}
	
	private void createOpenQuizPanel()
	{
		OpenQuizCreationPanel panelOpenQuiz = new OpenQuizCreationPanel();
		openQuizzes.add(panelOpenQuiz);
		
		placeOpenQuizPanel(panelOpenQuiz);
		this.validate();
	}
	
	private void createOpenQuizPanel(String codQuiz, String question, float maxScore, float minScore, int maxLength)
	{
		OpenQuizCreationPanel panelOpenQuiz = new OpenQuizCreationPanel();
		openQuizzes.add(panelOpenQuiz);
		
		panelOpenQuiz.setCodQuiz(codQuiz);
		panelOpenQuiz.setQuestion(question);
		panelOpenQuiz.setMaxScore(maxScore);
		panelOpenQuiz.setMinScore(minScore);
		panelOpenQuiz.setMaxLength(maxLength);
		
		placeOpenQuizPanel(panelOpenQuiz);
		this.validate();
	}
	
	private void placeOpenQuizPanel(OpenQuizCreationPanel panelOpenQuiz)
	{
		GridBagConstraints gbc_panelOpenQuiz = new GridBagConstraints();
		gbc_panelOpenQuiz.fill = GridBagConstraints.BOTH;
		gbc_panelOpenQuiz.gridx = 0;
		gbc_panelOpenQuiz.gridy = nextAvailableRow++;
		panelQuizzes.add(panelOpenQuiz, gbc_panelOpenQuiz);
	}
	
	private void createNewClosedQuizPanel()
	{
		ClosedQuizCreationPanel panelClosedQuiz = new ClosedQuizCreationPanel();
		newClosedQuizzes.add(panelClosedQuiz);
		
		placeClosedQuizPanel(panelClosedQuiz);
		this.validate();
	}
	
	private void createClosedQuizPanel()
	{
		ClosedQuizCreationPanel panelClosedQuiz = new ClosedQuizCreationPanel();
		closedQuizzes.add(panelClosedQuiz);
		
		placeClosedQuizPanel(panelClosedQuiz);
		this.validate();
	}
	
	private void createClosedQuizPanel(String codQuiz,
										String question,
										String answerA,
										String answerB,
										String answerC,
										String answerD,
										char rightAnswer,
										float scoreIfRight,
										float scoreIfWrong)
	{
		ClosedQuizCreationPanel panelClosedQuiz = new ClosedQuizCreationPanel();
		closedQuizzes.add(panelClosedQuiz);
		
		panelClosedQuiz.setCodQuiz(codQuiz);
		panelClosedQuiz.setQuestion(question);
		panelClosedQuiz.setAnswerA(answerA);
		panelClosedQuiz.setAnswerB(answerB);
		panelClosedQuiz.setAnswerC(answerC);
		panelClosedQuiz.setAnswerD(answerD);
		panelClosedQuiz.setRightAnswer(rightAnswer);
		panelClosedQuiz.setRightScore(scoreIfRight);
		panelClosedQuiz.setWrongScore(scoreIfWrong);
		
		placeClosedQuizPanel(panelClosedQuiz);
		this.validate();
	}
	
	private void placeClosedQuizPanel(ClosedQuizCreationPanel panelClosedQuiz)
	{
		GridBagConstraints gbc_panelClosedQuiz = new GridBagConstraints();
		gbc_panelClosedQuiz.fill = GridBagConstraints.BOTH;
		gbc_panelClosedQuiz.gridx = 0;
		gbc_panelClosedQuiz.gridy = nextAvailableRow++;
		panelQuizzes.add(panelClosedQuiz, gbc_panelClosedQuiz);
	}
	
	private String checkGeneralErrors(String testName,
			String startingDateString,
			String startingTimeString,
			String closingDateString,
			String closingTimeString,
			String currentDateString,
			String currentTimeString)
	{
		String error = null;
		
		if(( testName.equals("") || testName.contains("'") ) && error == null)
			error = "Test name is invalid!";
		
		if(closingDateString.compareTo(startingDateString) < 0 && error == null)
			error = "The closing date of a test cannot come before its starting date!";
		
		if(startingDateString.compareTo(currentDateString) == 0 && 
				startingTimeString.compareTo(currentTimeString) < 0 &&
				error == null)
			error = "The starting time of a test cannot come before the time of its creation!";
		
		if(closingDateString.compareTo(startingDateString) == 0 && 
				closingTimeString.compareTo(startingTimeString) < 0 && 
				error == null)
			error = "The closing time of a test cannot come before its starting time when the two dates are the same!";
		
		for(ClosedQuizCreationPanel i : closedQuizzes)
		{
			if(( i.getQuestion().equals("") || i.getQuestion().contains("'") ) && 
					error == null)
				error = "There's a closed quiz with an invalid question!";
			
			if(i.getWrongScore() >= i.getRightScore() && error == null)
				error = "There's a closed quiz with the wrong answer score greater than or equal to the right answer score!";
			
			if( ( i.getAnswerA().equals("") || i.getAnswerB().equals("") ||
					i.getAnswerA().contains("'") || i.getAnswerA().contains("'") ) &&
					error == null)
				error = "There's a closed quiz with invalid answer A or B!";
			
			if(( i.getAnswerC().equals("") || i.getAnswerC().contains("'") ) && 
					i.getRightAnswer().equals('C') && error == null)
				error = "There's a closed quiz with right answer C, but the answer C field is invalid!";
			
			if(( i.getAnswerD().equals("") || i.getAnswerD().contains("'") ) && 
					i.getRightAnswer().equals('D') && error == null)
				error = "There's a closed quiz with right answer D, but the answer D field is invalid!";
		}
		
		for(OpenQuizCreationPanel i : openQuizzes)
		{
			if(( i.getQuestion().equals("") || i.getQuestion().contains("'") ) && 
					error == null)
				error = "There's a open quiz with an invalid question!";
			
			if(i.getMinScore() >= i.getMaxScore() && error == null)
				error = "There's a open quiz with the min score greater than or equal to the max score!";
		}
		
		return error;
	}
	
	private void createTest()
	{
		String error = null;
		String errorTitle = "Failed Test Creation";
		
		String testName = textFieldName.getText();
		
		String startingDateString = getStartingDateString();
		String closingDateString = getClosingDateString();
		
		String startingTimeString = getStartingTimeString();
		String closingTimeString = getClosingTimeString();
		
		java.util.Date currentDateTime = new java.util.Date();
		
		String currentDateString = dateFormat.format(currentDateTime);
		String currentTimeString = timeFormat.format(currentDateTime);
		
		if(openQuizzes.isEmpty() && closedQuizzes.isEmpty())
			error = "Create some quizzes first!";
		
		if(!( controller.getCodTestByName(testName).equals("0") ) && error == null)	
			error = "Test name is already in use! Pick another name!";
		
		if(error == null)
			error = checkGeneralErrors(testName,
									startingDateString,
									startingTimeString,
									closingDateString,
									closingTimeString,
									currentDateString,
									currentTimeString);
		
		if(error != null)
		{
			JOptionPane.showMessageDialog(null, error, errorTitle, JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		
		controller.insertTestNQuizzes(this, currentDateString, currentTimeString);
		HomeProfessor home = new HomeProfessor(this, controller);
	}
	
	private void updateTest(String ogTestName,
			String ogStartingDateString,
			String ogStartingTimeString,
			String ogClosingDateString,
			String ogClosingTimeString,
			float ogMinScore,
			ArrayList<String> ogOpenQuizzes,
			ArrayList<String> ogClosedQuizzes)
	{
		String error = null;
		String errorTitle = "Failed Test Update";
		
		String testName = textFieldName.getText();
		
		String startingDateString = getStartingDateString();
		String closingDateString = getClosingDateString();
		
		String startingTimeString = getStartingTimeString();
		String closingTimeString = getClosingTimeString();
		
		java.util.Date currentDateTime = new java.util.Date();
		
		String currentDateString = dateFormat.format(currentDateTime);
		String currentTimeString = timeFormat.format(currentDateTime);
		
		if( !( testName.equals(ogTestName) ) )
		{
			if(!( controller.getCodTestByName(testName).equals("0") ) && error == null)	
				error = "Test name is already in use! Pick another name!";
		}
		
		if(error == null)
			error = checkGeneralErrors(testName,
									startingDateString,
									startingTimeString,
									closingDateString,
									closingTimeString,
									currentDateString,
									currentTimeString);
		
		for(ClosedQuizCreationPanel i : newClosedQuizzes)
		{
			if(( i.getQuestion().equals("") || i.getQuestion().contains("'") ) && 
				error == null)
					error = "There's a closed quiz with an invalid question!";
			
			if(i.getWrongScore() >= i.getRightScore() && error == null)
				error = "There's a closed quiz with the wrong answer score greater than or equal to the right answer score!";
			
			if( ( i.getAnswerA().equals("") || i.getAnswerB().equals("") ||
				i.getAnswerA().contains("'") || i.getAnswerA().contains("'") ) &&
					error == null)
				error = "There's a closed quiz with invalid answer A or B!";
			
			if(( i.getAnswerC().equals("") || i.getAnswerC().contains("'") ) && 
					i.getRightAnswer().equals('C') && error == null)
				error = "There's a closed quiz with right answer C, but the answer C field is invalid!";
			
			if(( i.getAnswerD().equals("") || i.getAnswerD().contains("'") ) && 
					i.getRightAnswer().equals('D') && error == null)
				error = "There's a closed quiz with right answer D, but the answer D field is invalid!";
		}
		
		for(OpenQuizCreationPanel i : newOpenQuizzes)
		{
			if(( i.getQuestion().equals("") || i.getQuestion().contains("'") ) && 
				error == null)
					error = "There's a open quiz with an invalid question!";
			
			if(i.getMinScore() >= i.getMaxScore() && error == null)
				error = "There's a open quiz with the min score greater than or equal to the max score!";
		}
		
		if(error != null)
		{
			JOptionPane.showMessageDialog(null, error, errorTitle, JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		
		controller.updateTestNQuizzes(this, 
									ogTestName, 
									ogStartingDateString, 
									ogStartingTimeString, 
									ogClosingDateString, 
									ogClosingTimeString, 
									ogMinScore, 
									ogOpenQuizzes, 
									ogClosedQuizzes);
		HomeProfessor home = new HomeProfessor(this, controller);
	}
	
	private void deleteTest(String codTest)
	{
		// TODO: delete quizzes BEFORE test!
		controller.deleteTestNQuizzes(codTest);
		
		HomeProfessor home = new HomeProfessor(this, controller);
	}
	
	public String getTestName()
	{
		return textFieldName.getText();
	}
	
	public float getMinScore()
	{
		return (float)spinnerMinScore.getValue();
	}
	
	public String getStartingDateString()
	{
		String startingDateString = dateFormat.format(calendarStartingDate.getDate());
		
		return startingDateString;
	}
	
	public String getClosingDateString()
	{
		String closingDateString = dateFormat.format(calendarClosingDate.getDate());
		
		return closingDateString;
	}
	
	public String getStartingTimeString()
	{
		String startingTimeString = (String)comboBoxStartingHour.getSelectedItem() + 
				":" + 
				(String)comboBoxStartingMinute.getSelectedItem() + 
				":00";
		
		return startingTimeString;
	}
	
	public String getClosingTimeString()
	{
		String closingTimeString = (String)comboBoxClosingHour.getSelectedItem() + 
				":" + 
				(String)comboBoxClosingMinute.getSelectedItem() + 
				":00";
		
		return closingTimeString;
	}
	
	public ArrayList<OpenQuizCreationPanel> getOpenQuizzes()
	{
		return openQuizzes;
	}
	
	public ArrayList<ClosedQuizCreationPanel> getClosedQuizzes()
	{
		return closedQuizzes; 
	}
	
	public ArrayList<OpenQuizCreationPanel> getNewOpenQuizzes()
	{
		return newOpenQuizzes;
	}
	
	public ArrayList<ClosedQuizCreationPanel> getNewClosedQuizzes()
	{
		return newClosedQuizzes; 
	}
}
