package bestris.bcarlson;

public class IBlock extends Block4 {
	//BBBB
	//For Y values, 0 is top, 22 is bot
	private static final String color = "lblue";
	
	private boolean[][] full;
	private int[] cur;
	private int[] prev;
	private int turn;
	
	public IBlock(Block[][] b, boolean[][] f) {
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
	
	@Override
	public void turnLeft() {
		//Does nothing yet
	}
	
	@Override
	public void turnRight() {
		//Does nothing yet
	}
}
