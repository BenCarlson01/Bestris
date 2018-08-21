package bestris.bcarlson;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tetris extends JPanel implements KeyListener{
	private int speed = 10;
	
	private Map<Character, BufferedImage> colorToBlock;
	private Block[][] blocks;
	private boolean[][] full;
	private Block4 cur;
	private int time;
	private Timer timer;
	
	private NextBlocks next;
	private HoldBlock hold;
	
	//[# Combos, # of Tetrises, B2B T-Spin mini, B2B single T-Spins, B2B double T-Spins, B2B triple T-Spins]
	private int[] clearTracker;
	private int level;
	private JLabel scoreLabel;
	
	/**
	 * Tetris game main class
	 * Uses a left to right increasing x (normal)
	 * 	and a top to bottom increasing y (reversed)
	 * 
	 * Block Information:
	 * 	B: part of block
	 * 	C: center of block
	 * 
	 * I-Block:
	 * 	Default color: Light Blue
	 * 	Shape:	 BBBB
	 * 
	 * J-Block:
	 * 	Default color: Dark Blue
	 * 	Shape:	B
	 * 			BCB
	 * 
	 * L-Block:
	 * 	Default color: Orange
	 * 	Shape:	  B
	 * 			BCB
	 * 
	 * O-Block:
	 * 	Default color: Yellow
	 * 	Shape:	BB
	 * 			BB
	 * 
	 * S-Block:
	 * 	Default color: Green
	 * 	Shape:	 BB
	 * 			BC
	 * 
	 * T-Block:
	 * 	Default color: Purple
	 * 	Shape:	 B
	 * 			BCB
	 * 
	 * Z-Block:
	 *  Default color: Red
	 * 	Shape:	BB
	 * 			 CB
	 */
	public Tetris(Block[][] blocks, boolean[][] full, NextBlocks next, HoldBlock hold,
			BlockSkins skin, JLabel scoreLabel) {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(new GridLayout(2, 1));
		this.blocks = blocks;
		this.full = full;
		this.next = next;
		this.hold = hold;
		this.scoreLabel = scoreLabel;
		
		colorToBlock = skin.getBlockToArt();
		cur = next.getNextBlock();
		time = 0;
		timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
        
        clearTracker = new int[5];
        level = 1;
	}
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(250, 500);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (time > 1000) {
        	cur.moveDown();
        	if (cur.isStuck()) {
        		if (time > 5000) {
        			newBlock();
        		}
        	} else {
        		time = 0;
        	}
        }
        time += speed;
        for (int i = 0; i < 10; i++) {
        	for (int j = 0; j < 20; j++) {
                g.drawImage(colorToBlock.get(blocks[i][j + 2].getType()), i * 25, j * 25, this);
        	}
        }
    }
	
	public void newBlock() {
		time = 0;
		cur.updateFull(); 
		clearLine();
		cur = next.getNextBlock();
	}
	
	public void swapHold() {
		cur.eraseBlock();
		cur = hold.swap(cur);
		if (cur == null) {
			cur = next.getNextBlock();
		}
		repaint();
	}
	
	public void clearLine() {
		ArrayList<Integer> fullRows = new ArrayList<>();
		for (int j = 0; j < 22; j++) {
			boolean fullRow = true;
			for (int i = 0; i < 10; i++) {
				fullRow = fullRow && full[i][j];
			}
			if (fullRow) {
				fullRows.add(j);
				/*
				updateScore(100);
				for (int i = 0; i < 10; i++) {
					blocks[i][j].setType('C');
					full[i][j] = false;
				}
				for (int j2 = j - 1; j2 >= 0; j2--) {
					for (int i = 0; i < 10; i++) {
						blocks[i][j2 + 1].setType(blocks[i][j2].getType());
						full[i][j2 + 1] = full[i][j2];
					}
				}
				*/
			}
		}
		int score = 0;
		//For T-Spins
		if (cur.getType() == 'T' && ((TBlock) cur).spun()) {
			next.clearLabelAnimate2("   T-Spin   ");
			switch (fullRows.size()) {
			//Special because should not reset Tetris
			//clearTracker[2]
			case 0:
				clearTracker[0] = 0;
				score += 400 * level;
				if (clearTracker[2] == 0) {
					clearTracker[2] = 1;
				} else {
					score += 150 * level;	
				}
				updateScore(score);
				return;
			//clearTracker[3]
			case 1:
				if (clearTracker[3] == 0) {
					score += updateTracker(100, 2);
				}
				next.clearLabelAnimate("   Single   ");
				break;
			//clearTracker[4]
			case 2:
				clearTracker[0] += 1;
				clearTracker[1] = 0;
				score += 300 * level;
				next.clearLabelAnimate("   Double   ");
				break;
			//clearTracker[5]
			case 3:
				clearTracker[0] += 1;
				clearTracker[1] = 0;
				score += 500 * level;
				next.clearLabelAnimate("   Triple   ");
				break;
			default:
				throw new GameException("Error in Tetris.clearLine():\n\tImpossible number of lines cleared with T-Block");
			}
		} else {
			switch (fullRows.size()) {
			//Special because should not reset Tetris
			case 0:
				clearTracker[0] = 0;
				clearTracker[2] = 0;
				return;
			case 1:
				score += updateTracker(100, 0);
				next.clearLabelAnimate("   Single   ");
				break;
			case 2:
				score += updateTracker(300, 0);
				next.clearLabelAnimate("   Double   ");
				break;
			case 3:
				score += updateTracker(500, 0);
				next.clearLabelAnimate("   Triple   ");
				break;
			case 4:
				next.clearLabelAnimate("   Tetris   ");
				if (clearTracker[1] == 0) {
					score += updateTracker(800, 1);
				} else {
					score += updateTracker(1200, 1);
					next.clearLabelAnimate2("    B2B    ");
				}
				break;
			default:
				throw new GameException("Error in Tetris.clearLine():\n\tImpossible number of lines cleared");
			}
		}
		if (clearTracker[0] > 1) {
			hold.comboAnimate(clearTracker[0] - 1);
		}
		updateScore(score);
		for (Integer j : fullRows) {
			for (int i = 0; i < 10; i++) {
				blocks[i][j].setType('C');
				full[i][j] = false;
			}
			for (int j2 = j - 1; j2 >= 0; j2--) {
				for (int i = 0; i < 10; i++) {
					blocks[i][j2 + 1].setType(blocks[i][j2].getType());
					full[i][j2 + 1] = full[i][j2];
				}
			}
		}
	}
	
	public void updateScore(int amt) {
		int cur = Integer.parseInt(scoreLabel.getText());
		scoreLabel.setText("" + (cur + amt));
	}
	
	public int updateTracker(int amt, int type) {
		int score = 0;
		score += 50 * clearTracker[0] * level;
		score += amt * level;
		clearTracker[0] += 1;
		clearTracker[1] = 0;
		clearTracker[2] = 0;
		clearTracker[3] = 0;
		clearTracker[4] = 0;
		switch (type) {
		case 0: 
			//Does nothing
			break;
		case 1:
			clearTracker[1] = 1;
			break;
		case 2:
			clearTracker[2] = 1;
			break;
		case 3:
			clearTracker[3] = 1;
			break;
		case 4:
			clearTracker[4] = 1;
			break;
		default:
			throw new GameException("Error in Tetris.updateTacker():\n\tImpossible type");
		}
		return score;
	}

    /*
     * Handle the key-pressed event from the text field. 
     */
    public void keyPressed(KeyEvent event) {
    	int key = event.getKeyCode();
		if (key == KeyEvent.VK_UP) {
			cur.turnRight();
		} else if (key == KeyEvent.VK_ALT) {
			cur.turnLeft();
    	} else if (key == KeyEvent.VK_DOWN) {
    		updateScore(cur.moveDown());
		} else if (key == KeyEvent.VK_LEFT) {
			cur.moveLeft();
		} else if (key == KeyEvent.VK_RIGHT) {
			cur.moveRight();
		} else if (key == KeyEvent.VK_SPACE) {
            updateScore(cur.hardDrop());
            newBlock();
		} else if (key == KeyEvent.VK_SHIFT) {
			swapHold();
		}
    }

	@Override
	public void keyTyped(KeyEvent event) {
		//Currently does nothing
	}

	@Override
	public void keyReleased(KeyEvent event) {
		//Currently does nothing
	}
    
}
