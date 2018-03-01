package bestris.bcarlson;

public class YellowBlock implements Block4{
	//Bottom Left Corner
	private int x;
	private int y;
	private Block[][] blocks;
	
	public YellowBlock(Block[][] b) {
		x = 4;
		y = 20;
		blocks = b;
	}
	
	public void turnLeft() {
		//Does nothing
	}
	
	public void turnRight() {
		//Does nothing
	}
	
	public void moveLeft() {
		if (x > 0) {
			x -= 1;
		}		
	}
	
	public void moveRight() {
		if (x < 8) {
			x += 1;
		}	
	}
	
	public void moveDown() {
		if (y > 0) {
			y -= 1;
		}
	}
}
