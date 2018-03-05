package bestris.bcarlson;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
	private int speed = 30; // 1000 / speed milliseconds
	private int time;
	private Timer timer;
	
	public Background() {
		blocks = new Block[10][22];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 22; j++) {
				blocks[i][j] = new Block();
				blocks[i][j].setColor("clear");
			}
		}
		full = new boolean[10][22];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 22; j++) {
				full[i][j] = false;
			}
		}
		Map<String, BufferedImage> temp = new HashMap<>();
		try {
			temp.put("red", resize(ImageIO.read(new File("art/Red Tetris Block.png")), 25, 25));
			temp.put("lblue", resize(ImageIO.read(new File("art/LBlue Tetris Block.png")), 25, 25));
			temp.put("dblue", resize(ImageIO.read(new File("art/DBlue Tetris Block.png")), 25, 25));
			temp.put("purple", resize(ImageIO.read(new File("art/Purple Tetris Block.png")), 25, 25));
			temp.put("green", resize(ImageIO.read(new File("art/Green Tetris Block.png")), 25, 25));
			temp.put("yellow", resize(ImageIO.read(new File("art/Yellow Tetris Block.png")), 25, 25));
			temp.put("orange", resize(ImageIO.read(new File("art/Orange Tetris Block.png")), 25, 25));
		} catch (IOException e) {
			System.out.println("IOError");
			return;
		}
		colorToBlock = Collections.unmodifiableMap(temp);
		cur = new LBlueBlock(blocks, full);
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
		cur = new LBlueBlock(blocks, full);
	}

    public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    } 

    /** Handle the key-pressed event from the text field. */
    public void keyPressed(KeyEvent event) {
    	int key = event.getKeyCode();
		if (key == KeyEvent.VK_UP) {
			cur.turnLeft();
		} else if (key == KeyEvent.VK_DOWN) {
			cur.moveDown();
		} else if (key == KeyEvent.VK_LEFT) {
			cur.moveLeft();
		} else if (key == KeyEvent.VK_RIGHT) {
			cur.moveRight();
		} else if (key == KeyEvent.VK_SPACE){
            cur.hardDrop();
            newBlock();
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
