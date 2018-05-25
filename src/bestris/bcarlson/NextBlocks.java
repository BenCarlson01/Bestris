package bestris.bcarlson;

import java.awt.GridLayout;
import javax.swing.JPanel;

public class NextBlocks extends JPanel {
	
	private char[] nextBlock;
	private Block[][] blocks;
	private boolean[][] full;
	private BlockPreview[] prevs;
	
	public NextBlocks(Block[][] blocks, boolean[][] full) {
		setLayout(new GridLayout(5, 1));
		this.blocks = blocks;
		this.full = full;
		nextBlock = new char[5];
		for (int i = 0; i < nextBlock.length; i++) {
			nextBlock[i] = getNewBlock();
		}
		prevs = new BlockPreview[5];
		for (int i = 0; i < prevs.length; i++) {
			prevs[i] = new BlockPreview();
			prevs[i].displayBlock(nextBlock[i]);
			add(prevs[i]);
		}
	}
	
	public Block4 getNextBlock() {
		char ret = nextBlock[0];
		for (int i = 0; i < nextBlock.length - 1; i++) {
			nextBlock[i] = nextBlock[i + 1];
		}
		nextBlock[nextBlock.length - 1] = getNewBlock();
		for (int i = 0; i < prevs.length; i++) {
			prevs[i].displayBlock(nextBlock[i]);
		}
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

}
