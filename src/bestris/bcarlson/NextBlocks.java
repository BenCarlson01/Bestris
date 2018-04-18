package bestris.bcarlson;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class NextBlocks extends JPanel {
	
	private Block4[] nextBlock;
	private Block[][] blocks;
	private boolean[][] full;
	
	public NextBlocks(Block[][] blocks, boolean[][] full) {
		this.blocks = blocks;
		this.full = full;
		nextBlock = new Block4[5];
		for (int i = 0; i < nextBlock.length; i++) {
			nextBlock[i] = getNewBlock();
		}
		repaint();
	}
	
	public Block4 getNextBlock() {
		Block4 ret = nextBlock[0];
		for (int i = 0; i < nextBlock.length - 1; i++) {
			nextBlock[i] = nextBlock[i + 1];
		}
		nextBlock[nextBlock.length - 1] = getNewBlock();
		repaint();
		return ret;
	}
	
	private Block4 getNewBlock() {
		int rand = (int) (Math.random() * 7);
		switch (rand) {
			case 0:
				return new DBlueBlock(blocks, full);
			case 1:
				return new GreenBlock(blocks, full);
			case 2:
				return new LBlueBlock(blocks, full);
			case 3:
				return new OrangeBlock(blocks, full);
			case 4:
				return new PurpleBlock(blocks, full);
			case 5:
				return new RedBlock(blocks, full);
			default:
				return new YellowBlock(blocks, full);
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
