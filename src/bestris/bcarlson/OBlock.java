package bestris.bcarlson;

public class OBlock extends Block4{
	private int[] cur;
	private int[] prev;
	
	public OBlock(Block[][] b, boolean[][] f) {
		super(b, f, 'O');
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
		updateColor();
	}
	
	@Override
	public void turnLeft() {
		//Does nothing
	}
	
	@Override
	public void turnRight() {
		//Does nothing
	}
}
