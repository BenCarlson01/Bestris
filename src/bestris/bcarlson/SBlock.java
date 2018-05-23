package bestris.bcarlson;

public class SBlock extends Block4 {
	private static final String color = "green";
	// BB
	//BC
	//For Y values, 0 is top, 22 is bot
	
	private int[] cur;
	private int[] prev;
	
	public SBlock(Block[][] b, boolean[][] f) {
		super(b, f, color, 'S');
		cur = new int[8];
		prev = new int[8];
		cur[0] = 4;
		cur[1] = 0;
		cur[2] = 4;
		cur[3] = 1;
		cur[4] = 3;
		cur[5] = 1;
		cur[6] = 5;
		cur[7] = 0;
		System.arraycopy(cur, 0, prev, 0, 8);
		setCurPrev(cur, prev);
		updateColor();
	}
}
