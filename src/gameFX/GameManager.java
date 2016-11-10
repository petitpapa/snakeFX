package gameFX;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.scene.Group;
import javafx.scene.layout.VBox;

public class GameManager extends Group {
	private final Board board;
	private Snake snake;

	public GameManager() {
		board = new Board();
		getChildren().add(board);

		snake = new Snake(this);
		addSnakeHead();

		snake.init();

		addRandomFoodToBoard();
		Snake.snakeEatFood.addListener((ov, oldValue, newValue) ->{
			if(newValue){
				addRandomFoodToBoard();
				board.getScorePoint().textProperty().bind(snake.getPoints().asString());
			}
		});

	}

	private void addRandomFoodToBoard() {
		Cell food = findRandomAvailableFood();
		food.setState(State.FOOD);
	}

	private Cell findRandomAvailableFood() {
		List<Cell> availables = filterBoard(
				(cell -> cell.getState().equals(State.EMPTY)))
						.collect(Collectors.toList());
		if (availables.isEmpty())
			return null;
		java.util.Collections.shuffle(availables);
		return availables.get(0);
	}

	private void addSnakeHead() {
		getNextCell(new Location(10, 10)).ifPresent(c -> {
			snake.setHead(c);
		});
	}

	public Optional<Cell> getNextCell(Location offset) {
		return filterBoard(cell -> cell.getLacation().equals(offset)).findAny();

	}
	private Stream<Cell> filterBoard(Predicate<Cell> predicate) {
		return board.getCells().stream().filter(predicate);
	}

	public Group getBoard() {
		return board;
	}

	public void startGame() {
	}

	public Object pauseGame() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object saveSession() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object restoreGame() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object tryAgain() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object quitGame() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setToolBar(VBox toolbar) {
		board.setToolBar(toolbar);
	}
}
