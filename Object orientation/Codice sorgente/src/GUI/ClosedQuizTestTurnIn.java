package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;

public class ClosedQuizTestTurnIn extends JPanel {

	/**
	 * Create the panel.
	 */
	public ClosedQuizTestTurnIn(String string) {
		setLayout(new MigLayout("", "[grow][]", "[][][][][][]"));
		
		string = string.substring(string.indexOf(" --- ") + 5);
		JLabel lblQuestion = new JLabel(string.substring(0, string.indexOf(" --- ")));
		add(lblQuestion, "cell 0 0");
		
		string = string.substring(string.indexOf(" --- ") + 5);
		JLabel lblAnswerA = new JLabel("A: " + string.substring(0, string.indexOf(" --- ")));
		add(lblAnswerA, "cell 1 1");
		
		string = string.substring(string.indexOf(" --- ") + 5);
		JLabel lblAnswerB = new JLabel("B: " + string.substring(0, string.indexOf(" --- ")));
		add(lblAnswerB, "cell 1 2");
		
		JComboBox comboBox = new JComboBox();
		add(comboBox, "cell 0 5,growx");
		comboBox.addItem(" ");
		comboBox.addItem("A");
		comboBox.addItem("B");
		
		string = string.substring(string.indexOf(" --- ") + 5);
		if (! string.isEmpty()) {
			JLabel lblAnswerC = new JLabel("C: " + string.substring(0, string.indexOf(" --- ")));
			add(lblAnswerC, "cell 1 3");
			
			// opzione E
			comboBox.addItem("C");;
			
			string = string.substring(string.indexOf(" --- ") + 5);
			if (! string.isEmpty()) {
				JLabel lblAnswerD = new JLabel("D: " + string);
				add(lblAnswerD, "cell 1 4");
				
				comboBox.addItem("D");;
				// opzione D
			}
		}
		
	}
}
