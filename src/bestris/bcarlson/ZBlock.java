package bestris.bcarlson;

public class ZBlock extends Block4 {
	private int[] cur;
	private int[] prev;
	
	public ZBlock(Block[][] b, boolean[][] f) {
		super(b, f, 'Z');
		cur = new int[8];
		prev = new int[8];
		cur[0] = 3;
		cur[1] = 0;
		cur[2] = 4;
		cur[3] = 1;
		cur[4] = 4;
		cur[5] = 0;
		cur[6] = 5;
		cur[7] = 1;
		System.arraycopy(cur, 0, prev, 0, 8);
		setCurPrev(cur, prev);
		updateColor();
	}
}
