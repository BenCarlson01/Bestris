package bestris.bcarlson;

public class PurpleBlock extends Block4 {
	//BCBB
	//For Y values, 0 is top, 22 is bot
	private static final String color = "purple";
	
	private boolean[][] full;
	private int[] cur;
	private int[] prev;
	
	public PurpleBlock(Block[][] b, boolean[][] f) {
		super(b, f);
		full = f;
		cur = new int[8];
		prev = new int[8];
		cur[0] = 3;
		cur[1] = 0;
		cur[2] = 4;
		cur[3] = 0;
		cur[4] = 5;
		cur[5] = 0;
		cur[6] = 6;
		cur[7] = 0;
		System.arraycopy(cur, 0, prev, 0, 8);
		setCurPrev(cur, prev);
		updateColor(color);
	}
	
	public void turnLeft() {
		if (cur[0] <= 0 || cur[6] <= 0) {
			return;
		}
		System.arraycopy(cur, 0, prev, 0, 8);
		double avgX = (cur[0] + cur[2] + cur[4] + cur[6]) / 4;
		double avgY = (cur[1] + cur[3] + cur[5] + cur[7]) / 4;
		cur[0] = (int) (-(prev[1] - avgY) + avgX);
		cur[1] = (int) ((prev[0] - avgX) + avgY);
		cur[2] = (int) (-(prev[3] - avgY) + avgX);
		cur[3] = (int) ((prev[2] - avgX) + avgY);
		cur[4] = (int) (-(prev[5] - avgY) + avgX);
		cur[5] = (int) ((prev[4] - avgX) + avgY);
		cur[6] = (int) (-(prev[7] - avgY) + avgX);
		cur[7] = (int) ((prev[6] - avgX) + avgY);
		updateColor(color);
	}
	
	public void turnRight() {
		//Does nothing
	}
	
	public void moveLeft() {
		if (cur[0] <= 0 || cur[6] <= 0
				|| full[cur[0] - 1][cur[1]] || full[cur[2] - 1][cur[3]]
				|| full[cur[4] - 1][cur[5]] || full[cur[6] - 1][cur[7]]) {
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
		if (cur[0] >= 9 || cur[6] >= 9
				|| full[cur[0] + 1][cur[1]] || full[cur[2] + 1][cur[3]]
				|| full[cur[4] + 1][cur[5]] || full[cur[6] + 1][cur[7]]) {
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
		if (cur[1] >= 21 || cur[7] >= 21
				|| full[cur[0]][cur[1] + 1] || full[cur[2]][cur[3] + 1]
				|| full[cur[4]][cur[5] + 1] || full[cur[6]][cur[7] + 1]) {
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
		while (cur[3] < 21 
				&& !full[cur[0]][cur[1] + 1] && !full[cur[2]][cur[3] + 1]
				&& !full[cur[4]][cur[5] + 1] && !full[cur[6]][cur[7] + 1]) {
			cur[1] += 1;
			cur[3] += 1;
			cur[5] += 1;
			cur[7] += 1;
		}
		updateColor(color);
	}
}