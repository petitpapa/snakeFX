package gameFX;

import javafx.scene.input.KeyCode;

public enum Direction {
	UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);
	
	private final int x;
	private final int y;
	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public static Direction valueFor(KeyCode keyCode) {
		return valueOf(keyCode.name());
	}
}
