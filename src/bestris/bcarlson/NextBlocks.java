package bestris.bcarlson;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

public class NextBlocks extends JPanel {
	
	private char[] nextBlock;
	private Block[][] blocks;
	private boolean[][] full;
	private BlockPreview[] prevs;
	
	private boolean random;
	private int bagCount;
	private ArrayList<Character> bag;
	
	public NextBlocks(Block[][] blocks, boolean[][] full, boolean TrueRandomness) {
		setLayout(new GridLayout(5, 1));
		this.blocks = blocks;
		this.full = full;
		
		random = TrueRandomness;
		bagCount = 0;
		bag = new ArrayList<>();
		bag.add('I');
		bag.add('J');
		bag.add('L');
		bag.add('O');
		bag.add('S');
		bag.add('T');
		bag.add('Z');
		Collections.shuffle(bag);
		
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
		if (random) {
			int rand = (int) (Math.random() * 7);
			switch (rand) {
			case 0:
				return 'I';
			case 1:
				return 'J';
			case 2:
				return 'L';
			case 3:
				return 'O';
			case 4:
				return 'S';
			case 5:
				return 'T';
			case 6:
				return 'Z';
			default:
				System.out.println("Error in block generation");
				return 'T';
			}
		} else {
			if (bagCount == 7) {
				Collections.shuffle(bag);
				bagCount = 0;
			}
			char ret = bag.get(bagCount);
			bagCount += 1;
			return ret;
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
