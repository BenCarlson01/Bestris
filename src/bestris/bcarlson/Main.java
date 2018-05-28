package bestris.bcarlson;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {
	/**
	 * Starts up Tetris
	 */
	public static void main(String[] args) {
		/**
		 * Determines whether to use true randomness or
		 *  "bag of blocks" randomness.
		 */
		boolean TrueRandomness = false;
		
		JFrame frame = new JFrame("Bestris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

		Block[][] blocks = new Block[10][22];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 22; j++) {
				blocks[i][j] = new Block();
				blocks[i][j].setType('C');
			}
		}
		boolean[][] full = new boolean[10][22];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 22; j++) {
				full[i][j] = false;
				
			}
		}
		NextBlocks next = new NextBlocks(blocks, full, TrueRandomness);
		HoldBlock hold = new HoldBlock(blocks, full);
		BlockSkins skin = new BlockSkins();
        
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new FlowLayout());
        JLabel score = new JLabel("Score");
        JLabel scoreLabel = new JLabel("0");
        scorePanel.add(score);
        scorePanel.add(scoreLabel);
        
        TetrisScore tetris = new TetrisScore(blocks, full, next, hold, skin);
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        frame.add(hold, c);
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.gridx = 2;
        c.gridy = 0;
        frame.add(next, c);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 1;
        c.gridy = 0;
        frame.add(tetris, c);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
}
