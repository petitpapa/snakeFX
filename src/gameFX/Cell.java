package gameFX;

import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
	private static  int x; 
	private static  int y;
	
	private State state = State.EMPTY;
	public Cell(int x, int y) {
		super(x  * Config.CELL_SIZE, y * Config.CELL_SIZE, Config.CELL_SIZE, Config.CELL_SIZE);
		this.x= x;
		this.y = y;
		setFill(state.getColor());
	}
}
