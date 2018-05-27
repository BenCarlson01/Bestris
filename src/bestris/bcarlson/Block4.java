package bestris.bcarlson;

public class Block4 {
	//Type
	private char type;
	private Block[][] blocks;
	private boolean[][] full;
	private int[] cur;
	private int[] prev;
	private int[] ghost;
	private int[] prevGhost;
	private int turn;
	
	/**
	 * Block4 is a basic class that provides all the underlying methods
	 *  for Tetris block movement. This is the parent of every block type
	 *  and each individual blocks need only to determine the initial
	 *  positions of the 4 blocks they are composed of. The I and O blocks
	 *  also need to define their own turning, as they follow a different
	 *  set of rules.
	 *  
	 * Let cur[2] and cur[3] be center for all blocks.
	 * cur[i % 2 == 0] represent x coordinates.
	 * cur[i % 2 != 0] represent y coordinates.
	 * 
	 * 
	 */
	public Block4(Block[][] b, boolean[][] f, char t) {
		blocks = b;
		full = f;
		type = t;
		turn = 0;
		
		ghost = new int[8];
		prevGhost = new int[8];
	}
	
	/*
	 * Must be called to initialize Block4
	 * Creates arrays that represent current and previous positions of blocks
	 */
	public void setCurPrev(int[] c, int[] p) {
		cur = c;
		prev = p;
	}
	
	public char getType() {
		return type;
	}
	
	public void moveLeft() {
		System.arraycopy(cur, 0, prev, 0, 8);
		moveBlock(-1, 0);
		if (invalidMove()) {
			System.arraycopy(prev, 0, cur, 0, 8);
		}
		updateColor();
	}
	
	public void moveRight() {
		System.arraycopy(cur, 0, prev, 0, 8);
		moveBlock(1, 0);
		if (invalidMove()) {
			System.arraycopy(prev, 0, cur, 0, 8);
		}
		updateColor();
	}
	
	public void moveDown() {
		System.arraycopy(cur, 0, prev, 0, 8);
		moveBlock(0, -1);
		if (invalidMove()) {
			System.arraycopy(prev, 0, cur, 0, 8);
		}
		updateColor();
	}
	
	public int hardDrop() {
		System.arraycopy(cur, 0, prev, 0, 8);
		int[] temp = new int[8];
		int count = 0;
		while (!invalidMove()) {
			System.arraycopy(cur, 0, temp, 0, 8);
			moveBlock(0, -1);
			count += 1;
		}
		System.arraycopy(temp, 0, cur, 0, 8);
		updateColor();
		return 2 * (count - 1);
	}
	
	protected void updateColor() {
		for (int i = 0; i < 8; i += 2) {
			blocks[prev[i]][prev[i + 1]].setType('C'); 
		}
		updateGhost();
		for (int i = 0; i < 8; i += 2) {
			blocks[prevGhost[i]][prevGhost[i + 1]].setType('C'); 
		}
		for (int i = 0; i < 8; i += 2) {
			blocks[ghost[i]][ghost[i + 1]].setType('G'); 
		}
		for (int i = 0; i < 8; i += 2) {	
			blocks[cur[i]][cur[i + 1]].setType(type); 
		}
	}
	
	public boolean isStuck() {
		if (cur[1] >= 21 || cur[3] >= 21 || cur[5] >= 21 || cur[7] >= 21) {
			return true;
		}
		if (full[cur[0]][cur[1] + 1] || full[cur[2]][cur[3] + 1]
				|| full[cur[4]][cur[5] + 1] || full[cur[6]][cur[7] + 1]) {
			return true;
		}
		return false;
	}
	
	public void updateFull() {
		full[cur[0]][cur[1]] = true;
		full[cur[2]][cur[3]] = true;
		full[cur[4]][cur[5]] = true;
		full[cur[6]][cur[7]] = true;
	}
	
	/*
	 * Moves a Block4 x spaces to the right and y spaces down
	 */
	public void moveBlock(int x, int y) {
		cur[0] += x;
		cur[2] += x;
		cur[4] += x;
		cur[6] += x;
		cur[1] -= y;
		cur[3] -= y;
		cur[5] -= y;
		cur[7] -= y;
	}
	
	/*
	 * Turn a J, L, S, T, Z block left
	 * Should override for I and O blocks
	 * Follows Super Rotation System guidelines
	 */
	public void turnRight() {
		System.arraycopy(cur, 0, prev, 0, 8);
		//initial turn
		double avgX = cur[2];
		double avgY = cur[3];
		cur[0] = (int) (-(prev[1] - avgY) + avgX);
		cur[1] = (int) ((prev[0] - avgX) + avgY);
		cur[2] = (int) (-(prev[3] - avgY) + avgX);
		cur[3] = (int) ((prev[2] - avgX) + avgY);
		cur[4] = (int) (-(prev[5] - avgY) + avgX);
		cur[5] = (int) ((prev[4] - avgX) + avgY);
		cur[6] = (int) (-(prev[7] - avgY) + avgX);
		cur[7] = (int) ((prev[6] - avgX) + avgY);
		
		//Keep temp of turn
		int[] temp = new int[8];
		System.arraycopy(cur, 0, temp, 0, 8);
		
		//check for collisions
		switch (turn) {
		case 0:
			if (invalidMove()) {
				moveBlock(-1, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(-1, 1);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(0, -2);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(-1, -2);
			}
			if (invalidMove()) {
				System.arraycopy(prev, 0, cur, 0, 8);
				turn -= 1;
			}
			break;
		case 1:
			if (invalidMove()) {
				moveBlock(1, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(1, -1);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(0, 2);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(1, 2);
			}
			if (invalidMove()) {
				System.arraycopy(prev, 0, cur, 0, 8);
				turn -= 1;
			}
			break;
		case 2:
			if (invalidMove()) {
				moveBlock(1, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(1, 1);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(0, -2);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(1, -2);
			}
			if (invalidMove()) {
				System.arraycopy(prev, 0, cur, 0, 8);
				turn -= 1;
			}
			break;
		case 3:
			if (invalidMove()) {
				moveBlock(-1, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(-1, -1);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(0, 2);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(-1, 2);
			}
			if (invalidMove()) {
				System.arraycopy(prev, 0, cur, 0, 8);
				turn -= 1;
			}
			break;
		default:
			System.out.println("Error - Impossible turn case");
			break;
		}
		turn += 1;
		turn %= 4;
		updateColor();
		
	}
	
	/*
	 * Turns a Block4 left
	 */
	public void turnLeft() {
		System.arraycopy(cur, 0, prev, 0, 8);
		//initial turn
		double avgX = cur[2];
		double avgY = cur[3];
		cur[0] = (int) ((prev[1] - avgY) + avgX);
		cur[1] = (int) (-(prev[0] - avgX) + avgY);
		cur[2] = (int) ((prev[3] - avgY) + avgX);
		cur[3] = (int) (-(prev[2] - avgX) + avgY);
		cur[4] = (int) ((prev[5] - avgY) + avgX);
		cur[5] = (int) (-(prev[4] - avgX) + avgY);
		cur[6] = (int) ((prev[7] - avgY) + avgX);
		cur[7] = (int) (-(prev[6] - avgX) + avgY);
		
		//Keep temp of turn
		int[] temp = new int[8];
		System.arraycopy(cur, 0, temp, 0, 8);
		
		//check for collisions
		switch (turn) {
		case 0:
			if (invalidMove()) {
				moveBlock(1, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(1, 1);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(0, -2);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(1, -2);
			}
			if (invalidMove()) {
				System.arraycopy(prev, 0, cur, 0, 8);
				turn += 1;
			}
			break;
		case 1:
			if (invalidMove()) {
				moveBlock(1, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(1, -1);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(0, 2);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(1, 2);
			}
			if (invalidMove()) {
				System.arraycopy(prev, 0, cur, 0, 8);
				turn += 1;
			}
			break;
		case 2:
			if (invalidMove()) {
				moveBlock(-1, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(-1, 1);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(0, -2);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(-1, -2);
			}
			if (invalidMove()) {
				System.arraycopy(prev, 0, cur, 0, 8);
				turn += 1;
			}
			break;
		case 3:
			if (invalidMove()) {
				moveBlock(-1, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(-1, -1);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(0, 2);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(-1, 2);
			}
			if (invalidMove()) {
				System.arraycopy(prev, 0, cur, 0, 8);
				turn += 1;
			}
			break;
		default:
			System.out.println("Error - Impossible turn case");
			break;
		}
		turn -= 1;
		turn = ((turn % 4) + 4) % 4;
		updateColor();
	}
	
	private void updateGhost() {
		System.arraycopy(ghost, 0, prevGhost, 0, 8);
		System.arraycopy(cur, 0, ghost, 0, 8);
		int[] temp = new int[8];
		while (!invalidGhostMove()) {
			System.arraycopy(ghost, 0, temp, 0, 8);
			moveGhost();
		}
		System.arraycopy(temp, 0, ghost, 0, 8);
	}
	
	private void moveGhost() {
		ghost[1] += 1;
		ghost[3] += 1;
		ghost[5] += 1;
		ghost[7] += 1;
	}
	
	private boolean invalidGhostMove() {
		for (int i = 1; i < ghost.length; i += 2) {
			if (ghost[i] > 21) {
				return true;
			}
		}
		return full[ghost[0]][ghost[1]] || full[ghost[2]][ghost[3]]
				|| full[ghost[4]][ghost[5]] || full[ghost[6]][ghost[7]];
	}
	
	public boolean invalidMove() {
		for (int i = 0; i < cur.length; i++) {
			if (i % 2 == 0) {
				if (cur[i] < 0 || cur[i] > 9) {
					return true;
				}
			} else {
				if (cur[i] > 21) {
					return true;
				}
			}
		}
		return full[cur[0]][cur[1]] || full[cur[2]][cur[3]]
				|| full[cur[4]][cur[5]] || full[cur[6]][cur[7]];
	}
	
	public void eraseBlock() {
		for (int i = 0; i < 8; i += 2) {
			blocks[ghost[i]][ghost[i + 1]].setType('C'); 
		}
		for (int i = 0; i < 8; i += 2) {	
			blocks[cur[i]][cur[i + 1]].setType('C'); 
		}
	}
	
}
