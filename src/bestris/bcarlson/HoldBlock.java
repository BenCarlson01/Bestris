package bestris.bcarlson;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class HoldBlock extends JPanel {
	
	private BlockPreview heldBlock;
	private boolean hasBlock;
	private char curBlock;
	private Block[][] blocks;
	private boolean[][] full;
	
	private JLabel comboLabel;
	private Timer timer;
	
	public HoldBlock(Block[][] blocks, boolean[][] full) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		heldBlock = new BlockPreview();
		heldBlock.displayBlock('C');
		add(heldBlock);
		hasBlock = false;
		
		this.blocks = blocks;
		this.full = full;
		
		comboLabel = new JLabel("                ");
		add(comboLabel);
		timer = new Timer(2500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	comboLabel.setText("                ");
            }
        });
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

	public void comboAnimate(int combo) {
		timer.stop();
		comboLabel.setText("Combo: " + combo);
        timer.start();
	}
}
