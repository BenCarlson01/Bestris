package bestris.bcarlson;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main {
	
	/**
	 * Starts up Tetris
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Bestris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new Background());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}

}
