package bestris.bcarlson;

public class YellowBlock extends Block4{
	private static final String color = "yellow";
	//Top Left Corner
	//For Y values, 0 is top, 22 is bot
	private boolean[][] full;
	private int[] cur;
	private int[] prev;
	
	public YellowBlock(Block[][] b, boolean[][] f) {
		super(b, f);
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
		setCurPrev(cur, prev);
		updateColor(color);
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
		updateColor(color);
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
		updateColor(color);
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
		updateColor(color);
	}
	
	public void hardDrop() {
		System.arraycopy(cur, 0, prev, 0, 8);
		while (cur[5] < 21 && !full[cur[0]][cur[5] + 1] && !full[cur[2]][cur[7] + 1]) {
			cur[5] += 1;
			cur[7] += 1;
		}
		cur[1] = cur[5] - 1;
		cur[3] = cur[7] - 1;
		updateColor(color);
	}
}
