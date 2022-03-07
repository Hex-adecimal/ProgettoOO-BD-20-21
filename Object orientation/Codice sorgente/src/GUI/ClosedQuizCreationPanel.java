package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClosedQuizCreationPanel extends JPanel {
	
	private JSpinner spinnerRightScore;
	private JSpinner spinnerWrongScore;
	private JComboBox comboBoxAnswers;
	private JTextArea txtrInsertQuestionHereC;
	private JTextField textFieldA;
	private JTextField textFieldB;
	private JTextField textFieldC;
	private JTextField textFieldD;
	
	private String codQuiz;
	
	/**
	 * Create the panel.
	 */
	public ClosedQuizCreationPanel() {
		this.setLayout(new BorderLayout(0, 0));
		
		JPanel thisInfo = new JPanel();
		this.add(thisInfo, BorderLayout.WEST);
		GridBagLayout gbl_thisInfo = new GridBagLayout();
		gbl_thisInfo.columnWidths = new int[]{92, 61, 0};
		gbl_thisInfo.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0};
		gbl_thisInfo.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_thisInfo.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		thisInfo.setLayout(gbl_thisInfo);
		
		JLabel lblRightAnswerScore = new JLabel("Right answer score:");
		lblRightAnswerScore.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		GridBagConstraints gbc_lblRightAnswerScore = new GridBagConstraints();
		gbc_lblRightAnswerScore.anchor = GridBagConstraints.EAST;
		gbc_lblRightAnswerScore.insets = new Insets(0, 0, 5, 5);
		gbc_lblRightAnswerScore.gridx = 0;
		gbc_lblRightAnswerScore.gridy = 1;
		thisInfo.add(lblRightAnswerScore, gbc_lblRightAnswerScore);
		
		spinnerRightScore = new JSpinner();
		spinnerRightScore.setModel(new SpinnerNumberModel(Float.valueOf(0), null, null, Float.valueOf(1)));
		GridBagConstraints gbc_spinnerRightScore = new GridBagConstraints();
		gbc_spinnerRightScore.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerRightScore.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerRightScore.anchor = GridBagConstraints.NORTH;
		gbc_spinnerRightScore.gridx = 1;
		gbc_spinnerRightScore.gridy = 1;
		thisInfo.add(spinnerRightScore, gbc_spinnerRightScore);
		
		JLabel lblWrongAnswerScore = new JLabel("Wrong answer score:");
		lblWrongAnswerScore.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		GridBagConstraints gbc_lblWrongAnswerScore = new GridBagConstraints();
		gbc_lblWrongAnswerScore.anchor = GridBagConstraints.EAST;
		gbc_lblWrongAnswerScore.insets = new Insets(0, 0, 5, 5);
		gbc_lblWrongAnswerScore.gridx = 0;
		gbc_lblWrongAnswerScore.gridy = 2;
		thisInfo.add(lblWrongAnswerScore, gbc_lblWrongAnswerScore);
		
		spinnerWrongScore = new JSpinner();
		spinnerWrongScore.setModel(new SpinnerNumberModel(Float.valueOf(0), null, null, Float.valueOf(1)));
		GridBagConstraints gbc_spinnerWrongScore = new GridBagConstraints();
		gbc_spinnerWrongScore.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerWrongScore.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerWrongScore.gridx = 1;
		gbc_spinnerWrongScore.gridy = 2;
		thisInfo.add(spinnerWrongScore, gbc_spinnerWrongScore);
		
		JLabel lblRightAnswer = new JLabel("Right answer:");
		lblRightAnswer.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		GridBagConstraints gbc_lblRightAnswer = new GridBagConstraints();
		gbc_lblRightAnswer.anchor = GridBagConstraints.EAST;
		gbc_lblRightAnswer.insets = new Insets(0, 0, 5, 5);
		gbc_lblRightAnswer.gridx = 0;
		gbc_lblRightAnswer.gridy = 4;
		thisInfo.add(lblRightAnswer, gbc_lblRightAnswer);
		
		Character[] answers = {'A', 'B', 'C', 'D'};
		comboBoxAnswers = new JComboBox(answers);
		GridBagConstraints gbc_comboBoxAnswers = new GridBagConstraints();
		gbc_comboBoxAnswers.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxAnswers.anchor = GridBagConstraints.WEST;
		gbc_comboBoxAnswers.gridx = 1;
		gbc_comboBoxAnswers.gridy = 4;
		thisInfo.add(comboBoxAnswers, gbc_comboBoxAnswers);
		
		txtrInsertQuestionHereC = new JTextArea();
		txtrInsertQuestionHereC.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(txtrInsertQuestionHereC.getText().equals("Insert question here"))
					txtrInsertQuestionHereC.setText("");
			}
		});
		txtrInsertQuestionHereC.setFont(new Font("Lucida Bright", Font.PLAIN, 20));
		txtrInsertQuestionHereC.setText("Insert question here");
		this.add(txtrInsertQuestionHereC, BorderLayout.NORTH);
		
		JPanel panelAnswers = new JPanel();
		this.add(panelAnswers, BorderLayout.CENTER);
		GridBagLayout gbl_panelAnswers = new GridBagLayout();
		gbl_panelAnswers.columnWidths = new int[]{41, 0, 0, 0};
		gbl_panelAnswers.rowHeights = new int[]{40, 0, 0, 0, 0, 43, 0, 0};
		gbl_panelAnswers.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelAnswers.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelAnswers.setLayout(gbl_panelAnswers);
		
		JLabel lblAnswerA = new JLabel("Answer A*:");
		lblAnswerA.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lblAnswerA = new GridBagConstraints();
		gbc_lblAnswerA.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnswerA.anchor = GridBagConstraints.EAST;
		gbc_lblAnswerA.gridx = 1;
		gbc_lblAnswerA.gridy = 1;
		panelAnswers.add(lblAnswerA, gbc_lblAnswerA);
		
		textFieldA = new JTextField();
		textFieldA.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_textFieldA = new GridBagConstraints();
		gbc_textFieldA.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldA.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldA.gridx = 2;
		gbc_textFieldA.gridy = 1;
		panelAnswers.add(textFieldA, gbc_textFieldA);
		textFieldA.setColumns(10);
		
		JLabel lblAnswerB = new JLabel("Answer B*:");
		lblAnswerB.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lblAnswerB = new GridBagConstraints();
		gbc_lblAnswerB.anchor = GridBagConstraints.EAST;
		gbc_lblAnswerB.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnswerB.gridx = 1;
		gbc_lblAnswerB.gridy = 2;
		panelAnswers.add(lblAnswerB, gbc_lblAnswerB);
		
		textFieldB = new JTextField();
		textFieldB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_textFieldB = new GridBagConstraints();
		gbc_textFieldB.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldB.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldB.gridx = 2;
		gbc_textFieldB.gridy = 2;
		panelAnswers.add(textFieldB, gbc_textFieldB);
		textFieldB.setColumns(10);
		
		JLabel lblAnswerC = new JLabel("Answer C:");
		lblAnswerC.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lblAnswerC = new GridBagConstraints();
		gbc_lblAnswerC.anchor = GridBagConstraints.EAST;
		gbc_lblAnswerC.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnswerC.gridx = 1;
		gbc_lblAnswerC.gridy = 3;
		panelAnswers.add(lblAnswerC, gbc_lblAnswerC);
		
		textFieldC = new JTextField();
		textFieldC.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_textFieldC = new GridBagConstraints();
		gbc_textFieldC.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldC.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldC.gridx = 2;
		gbc_textFieldC.gridy = 3;
		panelAnswers.add(textFieldC, gbc_textFieldC);
		textFieldC.setColumns(10);
		
		JLabel lblAnswerD = new JLabel("Answer D:");
		lblAnswerD.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lblAnswerD = new GridBagConstraints();
		gbc_lblAnswerD.anchor = GridBagConstraints.EAST;
		gbc_lblAnswerD.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnswerD.gridx = 1;
		gbc_lblAnswerD.gridy = 4;
		panelAnswers.add(lblAnswerD, gbc_lblAnswerD);
		
		textFieldD = new JTextField();
		textFieldD.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_textFieldD = new GridBagConstraints();
		gbc_textFieldD.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldD.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldD.gridx = 2;
		gbc_textFieldD.gridy = 4;
		panelAnswers.add(textFieldD, gbc_textFieldD);
		textFieldD.setColumns(10);
		
		JLabel lblMandatoryField = new JLabel("*This field is mandatory!");
		lblMandatoryField.setForeground(Color.RED);
		lblMandatoryField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblMandatoryField = new GridBagConstraints();
		gbc_lblMandatoryField.anchor = GridBagConstraints.WEST;
		gbc_lblMandatoryField.gridx = 2;
		gbc_lblMandatoryField.gridy = 6;
		panelAnswers.add(lblMandatoryField, gbc_lblMandatoryField);
		
		JSeparator separator = new JSeparator();
		this.add(separator, BorderLayout.SOUTH);
	}
	
	public float getRightScore()
	{
		return (float)spinnerRightScore.getValue();
	}
	public void setRightScore(float rightScore)
	{
		spinnerRightScore.setValue(Float.valueOf(rightScore));
	}
	
	public float getWrongScore()
	{
		return (float)spinnerWrongScore.getValue();
	}
	public void setWrongScore(float wrongScore)
	{
		spinnerWrongScore.setValue(Float.valueOf(wrongScore));
	}
	
	public Character getRightAnswer()
	{
		return (Character)comboBoxAnswers.getSelectedItem();
	}
	public void setRightAnswer(char rightAnswer)
	{
		comboBoxAnswers.setSelectedItem(Character.valueOf(rightAnswer));
	}
	
	public String getQuestion()
	{
		return txtrInsertQuestionHereC.getText();
	}
	public void setQuestion(String question)
	{
		txtrInsertQuestionHereC.setText(question);
	}
	
	public String getAnswerA()
	{
		String answer = textFieldA.getText();
		
		if(answer == "")	return null;
		else				return answer;
	}
	public void setAnswerA(String answer)
	{
		textFieldA.setText(answer);
	}
	
	public String getAnswerB()
	{
		String answer = textFieldB.getText();
		
		if(answer == "")	return null;
		else				return answer;
	}
	public void setAnswerB(String answer)
	{
		textFieldB.setText(answer);
	}
	
	public String getAnswerC()
	{
		String answer = textFieldC.getText();
		
		if(answer == "")	return null;
		else				return answer;
	}
	public void setAnswerC(String answer)
	{
		textFieldC.setText(answer);
	}
	
	public String getAnswerD()
	{
		String answer = textFieldD.getText();
		
		if(answer == "")	return null;
		else				return answer;
	}
	public void setAnswerD(String answer)
	{
		textFieldD.setText(answer);
	}
	
	public String getCodQuiz() {
		return codQuiz;
	}
	public void setCodQuiz(String codQuiz) {
		this.codQuiz = codQuiz;
	}
}
