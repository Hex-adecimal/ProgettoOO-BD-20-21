package GUI;

import java.awt.EventQueue;
import Controller.Controller;

public class Main {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("Starting Graphics User Interface ...");
					Controller controller = new Controller();
					Login login = new Login(controller);
				} catch (Exception e) { e.printStackTrace(); }
			}
		});
	}
	
}
