package gameFX;

import java.util.Optional;


import javafx.scene.Group;

public class GameManager extends Group {
	private final Board board;
	private Snake snake;
	
	public GameManager() {
		board = new Board();
		getChildren().add(board);
		
		snake = new Snake(this);
		addSnakeHead();
		
		snake.init();
		
	}

	private void addSnakeHead() {
		getNextCell(new Location(10, 10)).ifPresent(c ->{
			snake.setHead(c);
		});
	}

	public Optional<Cell> getNextCell(Location offset) {	
		return board.getCells().stream().parallel()
				.filter(cell -> cell.getLacation().equals(offset))
				.findAny();
	}

	public Group getBoard() {
		return board;
	}
}
