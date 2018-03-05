package bestris.bcarlson;

public class YellowBlock extends Block4{
	//Top Left Corner
	//For Y values, 0 is top, 22 is bot
	private Block[][] blocks;
	private boolean[][] full;
	private int[] cur;
	private int[] prev;
	
	public YellowBlock(Block[][] b, boolean[][] f) {
		blocks = b;
		full = f;
		cur = new int[8];
		prev = new int[8];
		cur[0] = 4;
		cur[1] = 0;
		cur[2] = 5;
		cur[3] = 0;
		cur[4] = 4;
		cur[5] = 1;
		cur[6] = 5;
		cur[7] = 1;
		System.arraycopy(cur, 0, prev, 0, 8);
		updateColor();
	}
	
	public void turnLeft() {
		//Does nothing
	}
	
	public void turnRight() {
		//Does nothing
	}
	
	public void moveLeft() {
		if (cur[0] <= 0 || cur[4] <= 0) {
			return;
		}	
		if (full[cur[0] - 1][cur[1]] || full[cur[4] - 1][cur[5]]) {
			return;
		}
		System.arraycopy(cur, 0, prev, 0, 8);
		cur[0] -= 1;
		cur[2] -= 1;
		cur[4] -= 1;
		cur[6] -= 1;
		updateColor();
	}
	
	public void moveRight() {
		if (cur[2] >= 9 || cur[6] >= 9) {
			return;
		}	
		if (full[cur[2] + 1][cur[3]] || full[cur[6] + 1][cur[7]]) {
			return;
		}
		System.arraycopy(cur, 0, prev, 0, 8);
		cur[0] += 1;
		cur[2] += 1;
		cur[4] += 1;
		cur[6] += 1;
		updateColor();
	}
	
	public void moveDown() {
		if (cur[5] >= 21 || cur[7] >= 21) {
			return;
		}	
		if (full[cur[4]][cur[5] + 1] || full[cur[6]][cur[7] + 1]) {
			return;
		}
		System.arraycopy(cur, 0, prev, 0, 8);
		cur[1] += 1;
		cur[3] += 1;
		cur[5] += 1;
		cur[7] += 1;
		updateColor();
	}
	
	public void hardDrop() {
		System.arraycopy(cur, 0, prev, 0, 8);
		while (cur[5] < 21 && !full[cur[0]][cur[5] + 1] && !full[cur[2]][cur[7] + 1]) {
			cur[5] += 1;
			cur[7] += 1;
		}
		cur[1] = cur[5] - 1;
		cur[3] = cur[7] - 1;
		updateColor();
	}
	
	private void updateColor() {
		for (int i = 0; i < 8; i += 2) {
			blocks[prev[i]][prev[i + 1]].setColor("clear"); 
		}
		for (int i = 0; i < 8; i += 2) {	
			blocks[cur[i]][cur[i + 1]].setColor("yellow"); 
		}
	}
	
	public boolean isStuck() {
		if (cur[5] >= 21 || cur[7] >= 21) {
			return true;
		}
		if (full[cur[4]][cur[5] + 1] || full[cur[6]][cur[7] + 1]) {
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
}
