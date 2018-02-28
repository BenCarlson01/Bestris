package bestris.bcarlson;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Background {
	/**
	 * Block Information:
	 * 	B: part of block
	 * 	C: center of block
	 * T-Block:
	 * 	Color: Purple ("purple")
	 * 	Shape:	 B
	 * 			BCB
	 * I-Block:
	 * 	Color: Light Blue ("lblue")
	 * 	Shape:	 BBCB
	 * L-Block:
	 * 	Color: Orange
	 * 	Shape:	BCB
	 * 			B
	 * Backwards L-Block:
	 * 	Color: Dark Blue
	 * 	Shape:	B
	 * 			BCB
	 * Square-Block:
	 * 	Color: Yellow
	 * 	Shape:	BB
	 * 			BB
	 * 	Rotates, but stays in the same order
	 * S-Block:
	 * 	Color: Green
	 * 	Shape:	 CB
	 * 			BB
	 * Backwards S-Block:
	 * 	Color: Red
	 * 	Shape:	BC
	 * 			 BB
	 */
	public static final String[] SET_VALUES =
			new String[] {"red", "green", "dblue", "orange", "yellow", "purple", "lblue", "clear" };
	private final Set<String> BLOCKTYPES = new HashSet<>(Arrays.asList(SET_VALUES));
	
	private Block[][] blocks;
	
	public Background() {
		blocks = new Block[10][22];
	}
	
	public void dropBlock(String blocktype, int row, int col) {
		if (!BLOCKTYPES.contains(blocktype)) {
			return;
		}
		if (blocktype.equals("purple")) {
			
		} else {
			System.out.println("Unsupported Operation, Please Contact Creator");
			return;
		}
	}

}
