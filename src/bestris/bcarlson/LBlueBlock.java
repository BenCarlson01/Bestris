package bestris.bcarlson;

public class LBlueBlock extends Block4 {
	//BBBB
	//For Y values, 0 is top, 22 is bot
	private static final String color = "lblue";
	
	private boolean[][] full;
	private int[] cur;
	private int[] prev;
	private int turn;
	
	public LBlueBlock(Block[][] b, boolean[][] f) {
		super(b, f, color);
		full = f;
		cur = new int[8];
		prev = new int[8];
		turn = 0;
		cur[0] = 3;
		cur[1] = 2;
		cur[2] = 4;
		cur[3] = 2;
		cur[4] = 5;
		cur[5] = 2;
		cur[6] = 6;
		cur[7] = 2;
		System.arraycopy(cur, 0, prev, 0, 8);
		setCurPrev(cur, prev);
		updateColor();
	}
	
	public void turnLeft() {
		if (cur[0] <= 0 || cur[6] <= 0) {
			return;
		}
		System.arraycopy(cur, 0, prev, 0, 8);
		switch (turn) {
			case 0:
				cur[0] += 2;
				cur[1] -= 1;
				cur[2] += 1;
				cur[3] -= 0;
				cur[4] -= 0;
				cur[5] += 1;
				cur[6] -= 1;
				cur[7] += 2;
				break;
			case 1:
				cur[0] += 1;
				cur[1] += 2;
				cur[2] -= 0;
				cur[3] += 1;
				cur[4] -= 1;
				cur[5] -= 0;
				cur[6] -= 2;
				cur[7] -= 1;
				break;
			case 2:
				cur[0] -= 2;
				cur[1] += 1;
				cur[2] -= 1;
				cur[3] -= 0;
				cur[4] += 0;
				cur[5] -= 1;
				cur[6] += 1;
				cur[7] -= 2;
				break;
			default:
				cur[0] -= 1;
				cur[1] -= 2;
				cur[2] -= 0;
				cur[3] -= 1;
				cur[4] += 1;
				cur[5] -= 0;
				cur[6] += 2;
				cur[7] += 1;
				turn = -1;
				break;
		}
		turn += 1;
		updateColor();
	}
	
	public void turnRight() {
		//Does nothing
	}
}
