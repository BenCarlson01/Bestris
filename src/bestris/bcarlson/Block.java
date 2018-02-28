package bestris.bcarlson;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Block {
	public static final String[] SET_VALUES =
			new String[] {"red", "green", "dblue", "orange", "yellow", "purple", "lblue", "clear" };
	private final Set<String> COLORS = new HashSet<>(Arrays.asList(SET_VALUES));
	private String color;
	private boolean visible;
	
	public Block() {
		color = "clear";
		visible = false;
	}
	
	public void setVisible() {
		visible = true;
	}
	
	public void setInvisible() {
		visible = false;
	}
	
	public void setColor(String color) {
		if (!COLORS.contains(color)) {
			return;
		}
		this.color = color;
		if (color.equals("clear")) {
			setInvisible();
		}
	}
	
	

}
