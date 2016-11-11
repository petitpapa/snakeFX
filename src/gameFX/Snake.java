package gameFX;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.Duration;

public class Snake {

	private Duration FPS = Duration.millis(1000);
	private Cell head;
	private GameManager gameManager;
	public static ObjectProperty<Direction> snakeDirectionProperty = new SimpleObjectProperty<Direction>(
			Direction.UP);
	public static BooleanProperty snakeEatFood = new SimpleBooleanProperty();
	private IntegerProperty pointsProperty = new SimpleIntegerProperty(0);
	private ObjectProperty<SpeedLevel> speedProperty = new SimpleObjectProperty<>(
			SpeedLevel.SLOW);

	private Direction previoustDirection = snakeDirectionProperty.get();

	private List<Cell> tail = new ArrayList<>();
	private Timeline timeline;

	public Snake(GameManager gameManager) {
		this.gameManager = gameManager;

		speedProperty.addListener((obs, old, newValue) -> {
			if (newValue.ordinal() <= 3 && !newValue.equals(old)) {
				timeline.stop();
				timeline.getKeyFrames().add(move());
				timeline.play();
			}
		});
	}

	void init() {

		timeline = new Timeline(move());

		timeline.setCycleCount(Animation.INDEFINITE);
	}

	private KeyFrame move() {
		KeyFrame frame = new KeyFrame(
				FPS.divide(speedProperty.get().getSpeedLevel()), e -> {
					Direction nextDirection = snakeDirectionProperty.get();
					if (hasOppositeUp(nextDirection)
							|| hasOppositeDown(nextDirection))
						nextDirection = previoustDirection;

					Location offset = head.getLacation().offset(nextDirection);
					
					gameManager.getNextCell(offset).ifPresent(next -> {
						if(next.getState().equals(State.TAIL))
							gameManager.setGameOver(true);

						Cell last = head;

						for (int i = 0; i < tail.size(); i++) {
							Cell c = tail.get(i);
							last.setState(State.TAIL);
							tail.set(i, last);
							last = c;
						}
						if (next.getState().equals(State.FOOD)) {
							tail.add(last);
							snakeEatFood.set(true);
							addPoints();
						}
						snakeEatFood.set(false);
						last.setState(State.EMPTY);
						setHead(next);
					});
					previoustDirection = nextDirection;
				});
		return frame;
	}

	private boolean hasOppositeDown(Direction nextDirection) {
		return nextDirection.equals(Direction.LEFT)
				&& previoustDirection.equals(Direction.RIGHT)
				|| nextDirection.equals(Direction.RIGHT)
						&& previoustDirection.equals(Direction.LEFT);
	}

	private boolean hasOppositeUp(Direction nextDirection) {
		return nextDirection.equals(Direction.UP)
				&& previoustDirection.equals(Direction.DOWN)
				|| nextDirection.equals(Direction.DOWN)
						&& previoustDirection.equals(Direction.UP);
	}

	private void addPoints() {
		pointsProperty.set(pointsProperty.get() + 1);
		if (pointsProperty.get() % 8 == 0) {
			int index = speedProperty.get().ordinal();
			if (index < 3)
				speedProperty.setValue(SpeedLevel.values()[index + 1]);
		}
	}

	public IntegerProperty getPoints() {
		return pointsProperty;
	}
	public void setHead(Cell next) {
		this.head = next;
		head.setState(State.HEAD);
	}

	public void setLocation(Location loc) {
		this.head.setLocation(loc);
	}

	public Cell getHead() {
		return head;
	}

	public void startGame() {
		timeline.play();
	}

	public void stopTimer() {
		timeline.stop();
	}

}
