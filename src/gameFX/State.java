package gameFX;

import javafx.scene.paint.Color;

public enum State {
	HEAD(Color.RED),
	EMPTY(Color.WHITE),
	TAIL(Color.GREEN),
	FOOD(Color.BLACK);
	
	private final Color color;
	private State(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
}
