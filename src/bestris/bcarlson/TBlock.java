package bestris.bcarlson;

public class TBlock extends Block4 {
	private int[] cur;
	private int[] prev;
	private boolean lastSpin;
	
	public TBlock(Block[][] b, boolean[][] f) {
		super(b, f, 'T');
		cur = new int[8];
		prev = new int[8];
		lastSpin = false;
		cur[0] = 4;
		cur[1] = 0;
		cur[2] = 4;
		cur[3] = 1;
		cur[4] = 3;
		cur[5] = 1;
		cur[6] = 5;
		cur[7] = 1;
		System.arraycopy(cur, 0, prev, 0, 8);
		setCurPrev(cur, prev);
		updateColor();
	}
	
	@Override
	public void turnRight() {
		super.turnRight();
		lastSpin = true;
	}
	
	@Override
	public void turnLeft() {
		super.turnLeft();
		lastSpin = true;
	}
	
	@Override
	public void moveBlock(int x, int y) {
		super.moveBlock(x, y);
		lastSpin = false;
	}
	
	@Override
	public boolean spun() {
		return lastSpin;
	}
}
