package bestris.bcarlson;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Background extends JPanel{
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
	private int[] heights;
	
	public Background() {
		blocks = new Block[10][22];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 22; j++) {
				blocks[i][j] = new Block();
				blocks[i][j].setColor("purple");
			}
		}
		heights = new int[10];
		for (int i = 0; i < 10; i++) {
			heights[i] = 0;
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
		Timer timer = new Timer(400, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }

        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
	}
	
	public void moveDown(String blocktype) {
		if (!BLOCKTYPES.contains(blocktype)) {
			return;
		}
		if (blocktype.equals("purple")) {
			
		} else {
			System.out.println("Unsupported Operation, Please Contact Creator");
			return;
		}
	}
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(250, 550);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < blocks.length; i++) {
        	for (int j = 0; j < blocks[0].length; j++) {
                g.drawImage(colorToBlock.get(blocks[i][j].getColor()), i * 25, j * 25, this);
                blocks[i][j].setColor(SET_VALUES[(int) (Math.random() *SET_VALUES.length)]);
        	}
        }
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }  
}