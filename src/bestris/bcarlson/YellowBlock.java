package bestris.bcarlson;

public class YellowBlock implements Block4{
	//Top Left Corner
	//For Y values, 0 is top, 22 is bot
	private int x;
	private int y;
	private Block[][] blocks;
	
	public YellowBlock(Block[][] b) {
		x = 4;
		y = 0;
		blocks = b;
		blocks[x][y].setColor("yellow");
		blocks[x][y + 1].setColor("yellow");
		blocks[x + 1][y].setColor("yellow");
		blocks[x + 1][y + 1].setColor("yellow");
	}
	
	public YellowBlock(int x, int y, Block[][] b) {
		this.x = x;
		this.y = y;
		blocks = b;
		blocks[x][y].setColor("yellow");
		blocks[x][y + 1].setColor("yellow");
		blocks[x + 1][y].setColor("yellow");
		blocks[x + 1][y + 1].setColor("yellow");
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
		blocks[x][y].setColor("yellow");
		blocks[x][y + 1].setColor("yellow");
		blocks[x + 2][y].setColor("clear");
		blocks[x + 2][y + 1].setColor("clear");
	}
	
	public void moveRight() {
		if (x < 8) {
			x += 1;
		}	
		blocks[x + 1][y].setColor("yellow");
		blocks[x + 1][y + 1].setColor("yellow");
		blocks[x - 1][y].setColor("clear");
		blocks[x - 1][y + 1].setColor("clear");
	}
	
	public void moveDown() {
		if (y < 20) {
			y += 1;
		}
		blocks[x][y + 1].setColor("yellow");
		blocks[x + 1][y + 1].setColor("yellow");
		blocks[x][y - 1].setColor("clear");
		blocks[x + 1][y - 1].setColor("clear");
	}
}
