package bestris.bcarlson;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tetris extends JPanel implements KeyListener{
	/**
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
	private Map<String, BufferedImage> colorToBlock;
	
	private Block[][] blocks;
	private boolean[][] full;
	private Block4 cur;
	private int speed = 10; // 1000 / speed milliseconds
	private int time;
	private Timer timer;
	
	private NextBlocks next;
	private HoldBlock hold;
	
	public Tetris(Block[][] blocks, boolean[][] full, NextBlocks next, HoldBlock hold,
			BlockSkins skin) {
		setBorder(BorderFactory.createLineBorder(Color.black));
		this.blocks = blocks;
		this.full = full;
		this.next = next;
		this.hold = hold;
		
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
	}
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(250, 550);
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
        for (int i = 0; i < blocks.length; i++) {
        	for (int j = 0; j < blocks[0].length; j++) {
                g.drawImage(colorToBlock.get(blocks[i][j].getType()), i * 25, j * 25, this);
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
		for (int j = 0; j < 22; j++) {
			int count = 0;
			for (int i = 0; i < 10; i++) {
				if (full[i][j]) {
					count++;
				}
			}
			if (count == 10) {
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
			}
		}
	}
	
	public int updateScore(int amt) {
		return amt;
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
			cur.moveDown();
		} else if (key == KeyEvent.VK_LEFT) {
			cur.moveLeft();
		} else if (key == KeyEvent.VK_RIGHT) {
			cur.moveRight();
		} else if (key == KeyEvent.VK_SPACE) {
            cur.hardDrop();
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
