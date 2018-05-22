package bestris.bcarlson;

public class Block4 {
	private String color;
	private Block[][] blocks;
	private boolean[][] full;
	private int[] cur;
	private int[] prev;
	private int turn;
	
	/*
	 * Let cur[2] and cur[3] be center for all blocks
	 * cur[i % 2 == 0] represent x coordinates
	 * cur[i % 2 != 0] represent y coordinates
	 */
	public Block4(Block[][] b, boolean[][] f, String c) {
		blocks = b;
		full = f;
		color = c;
		turn = 0;
	}
	
	public void setCurPrev(int[] c, int[] p) {
		cur = c;
		prev = p;
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
	
	public void hardDrop() {
		System.arraycopy(cur, 0, prev, 0, 8);
		int[] temp = new int[8];
		while (!invalidMove()) {
			System.arraycopy(cur, 0, temp, 0, 8);
			moveBlock(0, -1);
		}
		System.arraycopy(temp, 0, cur, 0, 8);
		updateColor();
	}
	
	protected void updateColor() {
		for (int i = 0; i < 8; i += 2) {
			blocks[prev[i]][prev[i + 1]].setColor("clear"); 
		}
		for (int i = 0; i < 8; i += 2) {	
			blocks[cur[i]][cur[i + 1]].setColor(color); 
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
		turn %= 4;
		updateColor();
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
	
}
