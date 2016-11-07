package gameFX;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
	
	private State state = State.EMPTY;
	private Location location;
	
	public Cell(int x, int y) {
		super(x  * Config.CELL_SIZE, y * Config.CELL_SIZE, Config.CELL_SIZE, Config.CELL_SIZE);
		setFill(state.getColor());
	}
	
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
		setFill(state.getColor());
	}

	public void setLocation(Location loc) {
		this.location = loc;
	}

	public Location getLacation() {
		return location;
	}

	public int getLayoutX(int cellSize) {
		return location.getX() * cellSize;
	}

	public int getLayoutY(int cellSize) {
		return location.getY() * cellSize;
	}
}
