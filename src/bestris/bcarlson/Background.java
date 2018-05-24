package bestris.bcarlson;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Background extends JPanel implements KeyListener{
	/**
	 * Block Information:
	 * 	B: part of block
	 * 	C: center of block
	 * T-Block:
	 * 	Color: Purple ("purple")
	 * 	Shape:	 B
	 * 			BCB
	 * I-Block:
	 * 	Color: Light Blue ("lblue")
	 * 	Shape:	 BCBB
	 * L-Block:
	 * 	Color: Orange
	 * 	Shape:	BCB
	 * 			B
	 * Backwards L-Block:
	 * 	Color: Dark Blue
	 * 	Shape:	B
	 * 			BCB
	 * Square-Block:
	 * 	Color: Yellow
	 * 	Shape:	BB
	 * 			BB
	 * 	Rotates, but stays in the same order
	 * S-Block:
	 * 	Color: Green
	 * 	Shape:	 CB
	 * 			BB
	 * Backwards S-Block:
	 * 	Color: Red
	 * 	Shape:	BC
	 * 			 BB
	 */
	private static final String[] SET_VALUES =
			new String[] {"red", "green", "dblue", "orange", "yellow", "purple", "lblue", "clear" };
	private final Set<String> BLOCKTYPES = new HashSet<>(Arrays.asList(SET_VALUES));
	private Map<String, BufferedImage> colorToBlock;
	
	private Block[][] blocks;
	private boolean[][] full;
	private Block4 cur;
	private int speed = 10; // 1000 / speed milliseconds
	private int time;
	private Timer timer;
	
	private NextBlocks next;
	private HoldBlock hold;
	
	public Background(Block[][] blocks, boolean[][] full, NextBlocks next, HoldBlock hold) {
		this.blocks = blocks;
		this.full = full;
		this.next = next;
		this.hold = hold;
		Map<String, BufferedImage> temp = new HashMap<>();
		try {
			temp.put("red", HelperMethods.resize(ImageIO.read(new File("art/Red Tetris Block.png")), 25, 25));
			temp.put("lblue", HelperMethods.resize(ImageIO.read(new File("art/LBlue Tetris Block.png")), 25, 25));
			temp.put("dblue", HelperMethods.resize(ImageIO.read(new File("art/DBlue Tetris Block.png")), 25, 25));
			temp.put("purple", HelperMethods.resize(ImageIO.read(new File("art/Purple Tetris Block.png")), 25, 25));
			temp.put("green", HelperMethods.resize(ImageIO.read(new File("art/Green Tetris Block.png")), 25, 25));
			temp.put("yellow", HelperMethods.resize(ImageIO.read(new File("art/Yellow Tetris Block.png")), 25, 25));
			temp.put("orange", HelperMethods.resize(ImageIO.read(new File("art/Orange Tetris Block.png")), 25, 25));
			temp.put("ghost", HelperMethods.resize(ImageIO.read(new File("art/G.png")), 25, 25));
		} catch (IOException e) {
			System.out.println("IOError");
			return;
		}
		colorToBlock = Collections.unmodifiableMap(temp);
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
                g.drawImage(colorToBlock.get(blocks[i][j].getColor()), i * 25, j * 25, this);
                //blocks[i][j].setColor(SET_VALUES[(int) (Math.random() *SET_VALUES.length)]);
        	}
        }
    }
	
	public void newBlock() {
		time = 0;
		cur.updateFull(); 
		clearLine();
		cur = next.getNextBlock(); //new PurpleBlock(blocks, full); 
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
					blocks[i][j].setColor("clear");
					full[i][j] = false;
				}
				for (int j2 = j - 1; j2 >= 0; j2--) {
					for (int i = 0; i < 10; i++) {
						blocks[i][j2 + 1].setColor(blocks[i][j2].getColor());
						full[i][j2 + 1] = full[i][j2];
					}
				}
			}
		}
	}
	
	public int updateScore(int amt) {
		return amt;
	}

    /** Handle the key-pressed event from the text field. */
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
