package bestris.bcarlson;

import static org.junit.Assert.*;

import org.junit.Test;

public class Tester {
	
	private Block[][] createBlocks() {
		Block[][] blocks = new Block[10][22];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 22; j++) {
				blocks[i][j] = new Block();
				blocks[i][j].setType('C');
			}
		}
		return blocks;
	}
	
	private boolean[][] createFull() {
		boolean[][] full = new boolean[10][22];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 22; j++) {
				full[i][j] = false;
				
			}
		}
		return full;
	}
	
	@Test
	public void testIBlock() {
		Block[][] blocks = createBlocks();
		boolean[][] full = createFull();
		Block4 cur = new IBlock(blocks, full);
		//Test Move Down
		for (int i = 0; i < 20; i++) {
			cur.moveDown();
		}
		cur.updateFull();
		assertTrue(full[3][21]);
		assertTrue(full[4][21]);
		assertTrue(full[5][21]);
		assertTrue(full[6][21]);
		
		//Test Hard Drop
		cur = new IBlock(blocks, full);
		cur.hardDrop();
		cur.updateFull();
		assertTrue(full[3][20]);
		assertTrue(full[4][20]);
		assertTrue(full[5][20]);
		assertTrue(full[6][20]);
	}
}
