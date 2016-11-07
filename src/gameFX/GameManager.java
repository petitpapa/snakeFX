package gameFX;

import javafx.scene.Group;

public class GameManager extends Group {
	private final Board board;
	
	public GameManager() {
		board = new Board();
		getChildren().add(board);
		
		addSnakeHead();
	}

	private void addSnakeHead() {
		
		board.getCells().stream().parallel()
		.filter(cell -> cell.getLacation().equals(new Location(10, 10)))
		.findAny().ifPresent(c ->{
			c.setState(State.HEAD);
		});
	}
}
