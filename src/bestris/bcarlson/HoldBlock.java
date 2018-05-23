package bestris.bcarlson;

import java.awt.FlowLayout;

import javax.swing.JPanel;

public class HoldBlock extends JPanel {
	
	private BlockPreview heldBlock;
	private boolean hasBlock;
	private char curBlock;
	private Block[][] blocks;
	private boolean[][] full;
	
	public HoldBlock(Block[][] blocks, boolean[][] full) {
		setLayout(new FlowLayout());
		heldBlock = new BlockPreview();
		heldBlock.displayBlock('C');
		add(heldBlock);
		hasBlock = false;
		
		this.blocks = blocks;
		this.full = full;
	}
	
	public Block4 swap(Block4 newBlock4) {
		char newBlock = newBlock4.getType();
		if (hasBlock) {
			char ret = curBlock;
			heldBlock.displayBlock(newBlock);
			curBlock = newBlock;
			return getCorrespondingBlock(ret);
		} else {
			heldBlock.displayBlock(newBlock);
			curBlock = newBlock;
			hasBlock = true;
			return null;
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
