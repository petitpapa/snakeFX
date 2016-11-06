package gameFX;

import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
	private static int x; 
	private static int y;
	
	public Cell() {
		super(x  * Config.CELL_SIZE, y * Config.CELL_SIZE, Config.CELL_SIZE, Config.CELL_SIZE);
	}
}
