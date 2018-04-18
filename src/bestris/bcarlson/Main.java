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

		Block[][] blocks = new Block[10][22];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 22; j++) {
				blocks[i][j] = new Block();
				blocks[i][j].setColor("clear");
			}
		}
		boolean[][] full = new boolean[10][22];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 22; j++) {
				full[i][j] = false;
				
			}
		}
		NextBlocks next = new NextBlocks(blocks, full);
        Background back = new Background(blocks, full, next);
		back.addKeyListener(back);
        back.setFocusable(true);
        frame.add(back, BorderLayout.CENTER);
        frame.add(next, BorderLayout.LINE_END);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}

}
