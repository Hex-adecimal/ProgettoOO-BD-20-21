package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;

public class OpenQuizCreationPanel extends JPanel {
	
	private JTextArea txtrInsertQuestionHere;
	private JSpinner spinnerQuizMaxScore;
	private JSpinner spinnerQuizMinScore;
	private JSpinner spinnerMaxLength;
	
	/**
	 * Create the panel.
	 */
	public OpenQuizCreationPanel() {
		setLayout(new BorderLayout(0, 0));
		
		txtrInsertQuestionHere = new JTextArea();
		txtrInsertQuestionHere.setText("Insert question here");
		txtrInsertQuestionHere.setFont(new Font("Lucida Bright", Font.PLAIN, 20));
		this.add(txtrInsertQuestionHere, BorderLayout.CENTER);
		
		JPanel panelQuizInfo = new JPanel();
		this.add(panelQuizInfo, BorderLayout.WEST);
		GridBagLayout gbl_panelQuizInfo = new GridBagLayout();
		gbl_panelQuizInfo.columnWidths = new int[]{46, 61, 0};
		gbl_panelQuizInfo.rowHeights = new int[]{20, 0, 0, 0, 0, 0};
		gbl_panelQuizInfo.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelQuizInfo.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelQuizInfo.setLayout(gbl_panelQuizInfo);
		
		JLabel lblQuizMaxScore = new JLabel("Maximum score:");
		lblQuizMaxScore.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		GridBagConstraints gbc_lblQuizMaxScore = new GridBagConstraints();
		gbc_lblQuizMaxScore.anchor = GridBagConstraints.EAST;
		gbc_lblQuizMaxScore.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuizMaxScore.gridx = 0;
		gbc_lblQuizMaxScore.gridy = 0;
		panelQuizInfo.add(lblQuizMaxScore, gbc_lblQuizMaxScore);
		
		spinnerQuizMaxScore = new JSpinner();
		spinnerQuizMaxScore.setModel(new SpinnerNumberModel(Float.valueOf(1), Float.valueOf(1), null, Float.valueOf(1)));
		GridBagConstraints gbc_spinnerQuizMaxScore = new GridBagConstraints();
		gbc_spinnerQuizMaxScore.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerQuizMaxScore.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerQuizMaxScore.anchor = GridBagConstraints.NORTH;
		gbc_spinnerQuizMaxScore.gridx = 1;
		gbc_spinnerQuizMaxScore.gridy = 0;
		panelQuizInfo.add(spinnerQuizMaxScore, gbc_spinnerQuizMaxScore);
		
		JLabel lblQuizMinScore = new JLabel("Minimum score:");
		lblQuizMinScore.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		GridBagConstraints gbc_lblQuizMinScore = new GridBagConstraints();
		gbc_lblQuizMinScore.anchor = GridBagConstraints.EAST;
		gbc_lblQuizMinScore.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuizMinScore.gridx = 0;
		gbc_lblQuizMinScore.gridy = 1;
		panelQuizInfo.add(lblQuizMinScore, gbc_lblQuizMinScore);
		
		spinnerQuizMinScore = new JSpinner();
		GridBagConstraints gbc_spinnerQuizMinScore = new GridBagConstraints();
		gbc_spinnerQuizMinScore.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerQuizMinScore.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerQuizMinScore.gridx = 1;
		gbc_spinnerQuizMinScore.gridy = 1;
		panelQuizInfo.add(spinnerQuizMinScore, gbc_spinnerQuizMinScore);
		
		JLabel lblMaxLength = new JLabel("Max answer length*:");
		lblMaxLength.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		GridBagConstraints gbc_lblMaxLength = new GridBagConstraints();
		gbc_lblMaxLength.anchor = GridBagConstraints.EAST;
		gbc_lblMaxLength.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxLength.gridx = 0;
		gbc_lblMaxLength.gridy = 2;
		panelQuizInfo.add(lblMaxLength, gbc_lblMaxLength);
		
		spinnerMaxLength = new JSpinner();
		spinnerMaxLength.setModel(new SpinnerNumberModel(Integer.valueOf(1024), Integer.valueOf(1024), null, Integer.valueOf(1)));
		GridBagConstraints gbc_spinnerMaxLength = new GridBagConstraints();
		gbc_spinnerMaxLength.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerMaxLength.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerMaxLength.gridx = 1;
		gbc_spinnerMaxLength.gridy = 2;
		panelQuizInfo.add(spinnerMaxLength, gbc_spinnerMaxLength);
		
		JLabel lblMaxLengthCaviat = new JLabel("*(in characters)");
		lblMaxLengthCaviat.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblMaxLengthCaviat = new GridBagConstraints();
		gbc_lblMaxLengthCaviat.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxLengthCaviat.gridx = 0;
		gbc_lblMaxLengthCaviat.gridy = 3;
		panelQuizInfo.add(lblMaxLengthCaviat, gbc_lblMaxLengthCaviat);
		
		JSeparator separator = new JSeparator();
		this.add(separator, BorderLayout.SOUTH);
	}

	public String getQuestion()
	{
		return txtrInsertQuestionHere.getText();
	}
	
	public float getMaxScore()
	{
		return (float)spinnerQuizMaxScore.getValue();
	}
	
	public float getMinScore()
	{
		return (float)spinnerQuizMinScore.getValue();
	}
	
	public float getMaxLength()
	{
		return (int)spinnerMaxLength.getValue();
	}
}
