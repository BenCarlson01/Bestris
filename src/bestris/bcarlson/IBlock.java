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
		super(b, f, color, 'I');
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
		System.arraycopy(cur, 0, prev, 0, 8);
		//initial turn
		switch (turn) {
		case 0:
			cur[0] += 1;
			cur[1] += 2;
			cur[2] += 0;
			cur[3] += 1;
			cur[4] += -1;
			cur[5] += 0;
			cur[6] += -2;
			cur[7] += -1;
			break;
		case 1:
			cur[0] += -2;
			cur[1] += 1;
			cur[2] += -1;
			cur[3] += 0;
			cur[4] += 0;
			cur[5] += -1;
			cur[6] += 1;
			cur[7] += -2;
			break;
		case 2:
			cur[0] += -1;
			cur[1] += -2;
			cur[2] += 0;
			cur[3] += -1;
			cur[4] += 1;
			cur[5] += 0;
			cur[6] += 2;
			cur[7] += 1;
			break;
		case 3:
			cur[0] += 2;
			cur[1] += -1;
			cur[2] += 1;
			cur[3] += 0;
			cur[4] += 0;
			cur[5] += 1;
			cur[6] += -1;
			cur[7] += 2;
			break;
		default:
			System.out.println("Improper I turn case");
		}
		
		//Keep temp of turn
		int[] temp = new int[8];
		System.arraycopy(cur, 0, temp, 0, 8);
		
		//check for collisions
		switch (turn) {
		case 0:
			if (invalidMove()) {
				moveBlock(-1, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(2, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(-1, 2);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(2, -1);
			}
			if (invalidMove()) {
				System.arraycopy(prev, 0, cur, 0, 8);
				turn += 1;
			}
			break;
		case 1:
			if (invalidMove()) {
				moveBlock(2, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(-1, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(2, 1);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(-1, -2);
			}
			if (invalidMove()) {
				System.arraycopy(prev, 0, cur, 0, 8);
				turn += 1;
			}
			break;
		case 2:
			if (invalidMove()) {
				moveBlock(1, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(-2, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(1, -2);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(-2, 1);
			}
			if (invalidMove()) {
				System.arraycopy(prev, 0, cur, 0, 8);
				turn += 1;
			}
			break;
		case 3:
			if (invalidMove()) {
				moveBlock(-2, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(1, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(-2, -1);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(1, 2);
			}
			if (invalidMove()) {
				System.arraycopy(prev, 0, cur, 0, 8);
				turn += 1;
			}
			break;
		default:
			System.out.println("Error - Impossible turn case");
			break;
		}
		turn -= 1;
		turn = ((turn % 4) + 4) % 4;
		updateColor();
	}
	
	@Override
	public void turnRight() {
		System.arraycopy(cur, 0, prev, 0, 8);
		//initial turn
		switch (turn) {
		case 0:
			cur[0] += 2;
			cur[1] += -1;
			cur[2] += 1;
			cur[3] += 0;
			cur[4] += 0;
			cur[5] += 1;
			cur[6] += -1;
			cur[7] += 2;
			break;
		case 1:
			cur[0] += 1;
			cur[1] += 2;
			cur[2] += 0;
			cur[3] += 1;
			cur[4] += -1;
			cur[5] += 0;
			cur[6] += -2;
			cur[7] += -1;
			break;
		case 2:
			cur[0] += -2;
			cur[1] += 1;
			cur[2] += -1;
			cur[3] += 0;
			cur[4] += 0;
			cur[5] += -1;
			cur[6] += 1;
			cur[7] += -2;
			break;
		case 3:
			cur[0] += -1;
			cur[1] += -2;
			cur[2] += 0;
			cur[3] += -1;
			cur[4] += 1;
			cur[5] += 0;
			cur[6] += 2;
			cur[7] += 1;
			break;
		default:
			System.out.println("Improper I turn case");
		}
		
		//Keep temp of turn
		int[] temp = new int[8];
		System.arraycopy(cur, 0, temp, 0, 8);
		
		//check for collisions
		switch (turn) {
		case 0:
			if (invalidMove()) {
				moveBlock(-2, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(1, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(-2, -1);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(1, 2);
			}
			if (invalidMove()) {
				System.arraycopy(prev, 0, cur, 0, 8);
				turn -= 1;
			}
			break;
		case 1:
			if (invalidMove()) {
				moveBlock(-1, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(2, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(-1, 2);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(2, -1);
			}
			if (invalidMove()) {
				System.arraycopy(prev, 0, cur, 0, 8);
				turn -= 1;
			}
			break;
		case 2:
			if (invalidMove()) {
				moveBlock(2, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(-1, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(2, 1);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(-1, -2);
			}
			if (invalidMove()) {
				System.arraycopy(prev, 0, cur, 0, 8);
				turn -= 1;
			}
			break;
		case 3:
			if (invalidMove()) {
				moveBlock(1, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(-2, 0);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(1, -2);
			}
			if (invalidMove()) {
				System.arraycopy(temp, 0, cur, 0, 8);
				moveBlock(-2, 1);
			}
			if (invalidMove()) {
				System.arraycopy(prev, 0, cur, 0, 8);
				turn -= 1;
			}
			break;
		default:
			System.out.println("Error - Impossible turn case");
			break;
		}
		turn += 1;
		turn %= 4;
		updateColor();
	}
}
