package bestris.bcarlson;

public abstract class Block4 {
	private Block[][] blocks;
	private boolean[][] full;
	private int[] cur;
	private int[] prev;
	
	public Block4(Block[][] b, boolean[][] f) {
		blocks = b;
		full = f;
	}
	
	public void setCurPrev(int[] c, int[] p) {
		cur = c;
		prev = p;
	}
	
	public abstract void turnLeft();
	public abstract void turnRight();
	public abstract void moveLeft();
	public abstract void moveRight();
	public abstract void moveDown();
	public abstract void hardDrop();
	
	protected void updateColor(String color) {
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
}
