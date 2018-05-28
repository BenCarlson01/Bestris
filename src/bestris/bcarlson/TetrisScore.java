package bestris.bcarlson;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TetrisScore extends JPanel {
	
	public TetrisScore(Block[][] blocks, boolean[][] full, NextBlocks next, HoldBlock hold,
			BlockSkins skin) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JPanel scorePanel = new JPanel();
		scorePanel.setLayout(new FlowLayout());
		JLabel score = new JLabel("Score");
		JLabel scoreLabel = new JLabel("0");
		scorePanel.add(score);
		scorePanel.add(scoreLabel);
		add(scorePanel);
		
		Tetris tetris = new Tetris(blocks, full, next, hold, skin, scoreLabel);
        tetris.addKeyListener(tetris);
        tetris.setFocusable(true);
		add(tetris);
	}

}
