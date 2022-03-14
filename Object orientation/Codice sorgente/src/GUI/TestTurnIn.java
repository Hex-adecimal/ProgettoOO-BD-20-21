package GUI;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import javax.swing.JButton;
import java.awt.Panel;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestTurnIn extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	private JFrame testTurnIn;
	private ArrayList<ClosedQuizTestTurnIn> closedQuizes = new ArrayList<ClosedQuizTestTurnIn>();
	//...
	private int pointer = 0;
	private int maxPointer;
	
	ArrayList<String> testInfo;
	
	Panel panelQuiz = new Panel(new CardLayout(0, 0));
	JScrollPane scrollPane = new JScrollPane();
	private final JButton btnPrev = new JButton("Previous");
	private final JButton btnNxt = new JButton("Next");
	
	public TestTurnIn(Controller controller, JFrame jframe, String codTest) {
		setResizable(false);
		jframe.setVisible(false);
		this.controller = controller;
		testTurnIn = this;
		testTurnIn.setVisible(true);
		
		testInfo = controller.getTestInformations(codTest);
		maxPointer = testInfo.size();
		
		for ( String i : testInfo ) {
			if (i.startsWith("0")) { // open quiz
				// createOpenQuiz(i);
			} else { // closed quiz
				createClosedQuiz(i);
			}
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 733, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnCommitTest = new JButton("End Test");
		btnCommitTest.setBounds(5, 257, 98, 29);
		btnCommitTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Commit
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnCommitTest);
		scrollPane.setBounds(108, 5, 613, 281);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(panelQuiz);
		panelQuiz.setLayout(new CardLayout(0, 0));
		btnPrev.addActionListener(new ActionListener() { // Prev
			public void actionPerformed(ActionEvent e) { 
				CardLayout cl = (CardLayout) (panelQuiz.getLayout());
				pointer--;
				if (pointer < 0) 
					pointer = maxPointer-1;
		        cl.show(panelQuiz, testInfo.get(pointer) );
			}
		});
		btnPrev.setBounds(5, 196, 98, 29);
		
		contentPane.add(btnPrev);
		
		btnNxt.addActionListener(new ActionListener() { // Next
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (panelQuiz.getLayout());
				pointer++;
				if (pointer >= maxPointer) 
					pointer = 0;
		        cl.show(panelQuiz, testInfo.get(pointer) );
			}
		});
		btnNxt.setBounds(6, 155, 97, 29);
		
		contentPane.add(btnNxt);
	}
	
	private void createClosedQuiz(String string) {
		ClosedQuizTestTurnIn closedQuiz = new ClosedQuizTestTurnIn(string);
		closedQuizes.add(closedQuiz);
		
		panelQuiz.add(closedQuiz, string);
		scrollPane.setViewportView(panelQuiz);
	}
}
