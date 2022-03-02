package GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.*;

import Controller.Controller;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Date;

import javax.swing.JSpinner;

/*import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;*/
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;

public class TestCreation extends JFrame {

	private JPanel contentPane;
	private Controller controller;

	/**
	 * Create the frame.
	 */
	public TestCreation(JFrame homeProfessor, Controller c) {
		setTitle("Create a new test");
		setResizable(false);
		homeProfessor.dispose();
		this.controller = c;
		this.setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 909, 719);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panelTestInfo = new JPanel();
		GridBagLayout gbl_panelTestInfo = new GridBagLayout();
		gbl_panelTestInfo.columnWidths = new int[]{124, 246, 58, 46, 257, 0};
		gbl_panelTestInfo.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0};
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
		
		JTextField textFieldName = new JTextField();
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
		
		JSpinner spinnerMinScore = new JSpinner();
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
		
		JCalendar calendarStartingDate = new JCalendar();
		GridBagConstraints gbc_calendarStartingDate = new GridBagConstraints();
		gbc_calendarStartingDate.insets = new Insets(0, 0, 5, 5);
		gbc_calendarStartingDate.fill = GridBagConstraints.BOTH;
		gbc_calendarStartingDate.gridx = 1;
		gbc_calendarStartingDate.gridy = 3;
		panelTestInfo.add(calendarStartingDate, gbc_calendarStartingDate);
		
		JLabel lblClosingDate = new JLabel("Closing date:");
		lblClosingDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblClosingDate = new GridBagConstraints();
		gbc_lblClosingDate.anchor = GridBagConstraints.EAST;
		gbc_lblClosingDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblClosingDate.gridx = 3;
		gbc_lblClosingDate.gridy = 3;
		panelTestInfo.add(lblClosingDate, gbc_lblClosingDate);
		
		JCalendar calendarClosingDate = new JCalendar();
		GridBagConstraints gbc_calendarClosingDate = new GridBagConstraints();
		gbc_calendarClosingDate.insets = new Insets(0, 0, 5, 0);
		gbc_calendarClosingDate.fill = GridBagConstraints.BOTH;
		gbc_calendarClosingDate.gridx = 4;
		gbc_calendarClosingDate.gridy = 3;
		panelTestInfo.add(calendarClosingDate, gbc_calendarClosingDate);
		
		calendarClosingDate.setMinSelectableDate(new Date());
		
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
		
		JComboBox comboBoxStartingHour = new JComboBox(hours);
		panelStartingTime.add(comboBoxStartingHour);
		
		JLabel lblHourMinSeparator = new JLabel(":");
		panelStartingTime.add(lblHourMinSeparator);
		
		JComboBox comboBoxStartingMinute = new JComboBox(minutes);
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
		
		JComboBox comboBoxClosingHour = new JComboBox(hours);
		panelClosingTime.add(comboBoxClosingHour);
		
		JLabel lblHourMinSeparator_1 = new JLabel(":");
		panelClosingTime.add(lblHourMinSeparator_1);
		
		JComboBox comboBoxClosingMinute = new JComboBox(minutes);
		panelClosingTime.add(comboBoxClosingMinute);
		
		
		JPanel panelQuizzes = new JPanel();
		scrollPane.setViewportView(panelQuizzes);
		
		JPanel panelButtons = new JPanel();
		contentPane.add(panelButtons, BorderLayout.SOUTH);
		GridBagLayout gbl_panelButtons = new GridBagLayout();
		gbl_panelButtons.columnWidths = new int[]{9, 27, 66, 76, 280, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelButtons.rowHeights = new int[]{23, 0};
		gbl_panelButtons.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelButtons.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelButtons.setLayout(gbl_panelButtons);
		
		JButton btnAddOpenQuiz = new JButton("+ Add Open Quiz");
		btnAddOpenQuiz.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnAddOpenQuiz = new GridBagConstraints();
		gbc_btnAddOpenQuiz.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAddOpenQuiz.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddOpenQuiz.gridx = 2;
		gbc_btnAddOpenQuiz.gridy = 0;
		panelButtons.add(btnAddOpenQuiz, gbc_btnAddOpenQuiz);
		
		JButton btnAddClosedQuiz = new JButton("+ Add Closed Quiz");
		btnAddClosedQuiz.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnAddClosedQuiz = new GridBagConstraints();
		gbc_btnAddClosedQuiz.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAddClosedQuiz.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddClosedQuiz.gridx = 3;
		gbc_btnAddClosedQuiz.gridy = 0;
		panelButtons.add(btnAddClosedQuiz, gbc_btnAddClosedQuiz);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cancelCreation();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 5;
		gbc_btnCancel.gridy = 0;
		panelButtons.add(btnCancel, gbc_btnCancel);
		
		JButton btnFinish = new JButton("Finish");
		btnFinish.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnFinish = new GridBagConstraints();
		gbc_btnFinish.insets = new Insets(0, 0, 0, 5);
		gbc_btnFinish.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnFinish.gridx = 6;
		gbc_btnFinish.gridy = 0;
		panelButtons.add(btnFinish, gbc_btnFinish);
	}
	
	private void cancelCreation()
	{
		HomeProfessor home = new HomeProfessor(this, controller);
	}
}
