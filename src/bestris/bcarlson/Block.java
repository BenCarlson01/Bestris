package bestris.bcarlson;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Block {
	private static final Character[] SET_TYPES =
			new Character[] {'I', 'J', 'L', 'O', 'S', 'T', 'Z', 'C', 'G' };
	private final Set<Character> TYPES = new HashSet<>(Arrays.asList(SET_TYPES));
	private char type;
	private boolean visible;
	
	/**
	 * Represents a single block. All Tetris blocks are composed of 4 blocks.
	 */
	public Block() {
		type = 'C';
		visible = false;
	}
	
	public void setVisible() {
		visible = true;
	}
	
	public void setInvisible() {
		visible = false;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * Sets the type of a Block
	 * Type is used to determine color by other methods
	 * Checks to see if block type exists
	 * 
	 * @param type: change current block type to type
	 */
	public void setType(char type) {
		if (!TYPES.contains(type)) {
			throw new GameException("Error in Block.setType(): Non-existent type");
		}
		this.type = type;
		if (type == 'C') {
			setInvisible();
		}
	}
	
	public char getType() {
		return type;
	}
}
