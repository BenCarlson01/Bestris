package bestris.bcarlson;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

public class Main {
	
	/**
	 * Starts up Tetris
	 */
	public static void main(String[] args) {
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
		NextBlocks next = new NextBlocks(blocks, full);
		HoldBlock hold = new HoldBlock(blocks, full);
		BlockSkins skin = new BlockSkins();
        Tetris back = new Tetris(blocks, full, next, hold, skin);
		back.addKeyListener(back);
        back.setFocusable(true);
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        frame.add(hold, c);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 1;
        c.gridy = 0;
        frame.add(back, c);
        c.gridx = 2;
        c.gridy = 0;
        frame.add(next, c);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}

}
