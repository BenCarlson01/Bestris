package bestris.bcarlson;

public class YellowBlock extends Block4{
	private static final String color = "yellow";
	//Top Left Corner
	//For Y values, 0 is top, 22 is bot
	private int[] cur;
	private int[] prev;
	
	public YellowBlock(Block[][] b, boolean[][] f) {
		super(b, f, color);
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
