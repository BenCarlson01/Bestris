package bestris.bcarlson;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class NextBlocks extends JPanel {
	
	private char[] nextBlock;
	private Block[][] blocks;
	private boolean[][] full;
	
	public NextBlocks(Block[][] blocks, boolean[][] full) {
		this.blocks = blocks;
		this.full = full;
		nextBlock = new char[5];
		for (int i = 0; i < nextBlock.length; i++) {
			nextBlock[i] = getNewBlock();
		}
		repaint();
	}
	
	public Block4 getNextBlock() {
		char ret = nextBlock[0];
		for (int i = 0; i < nextBlock.length - 1; i++) {
			nextBlock[i] = nextBlock[i + 1];
		}
		nextBlock[nextBlock.length - 1] = getNewBlock();
		repaint();
		return getCorrespondingBlock(ret);
	}
	
	private char getNewBlock() {
		int rand = (int) (Math.random() * 7);
		switch (rand) {
		case 0:
			return 'J';
		case 1:
			return 'S';
		case 2:
			return 'I';
		case 3:
			return 'L';
		case 4:
			return 'T';
		case 5:
			return 'Z';
		case 6:
			return 'O';
		default:
			System.out.println("Error in block generation");
			return 'T';
	}
	}
	
	private Block4 getCorrespondingBlock(char name) {
		switch (name) {
		case 'J':
			return new JBlock(blocks, full);
		case 'S':
			return new SBlock(blocks, full);
		case 'I':
			return new IBlock(blocks, full);
		case 'L':
			return new LBlock(blocks, full);
		case 'T':
			return new TBlock(blocks, full);
		case 'Z':
			return new ZBlock(blocks, full);
		case 'O':
			return new OBlock(blocks, full);
		default:
			System.out.println("Error in block generation");
			return new TBlock(blocks, full);
	}
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (int i = 0; i < nextBlock.length; i++) {
        	try {
        		g.drawImage(Background.resize(ImageIO.read(new File("art/T-Block.png")), 25, 25), 0, i * 30, this);
        	} catch (IOException e) {
        		System.out.println("IOError");
    			return;
        	}
        }
    }

}
