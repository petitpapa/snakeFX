package gameFX;

import javafx.scene.Group;

public class GameManager extends Group {
	private final Board board;
	
	public GameManager() {
		board = new Board();
		getChildren().add(board);
	}
}
