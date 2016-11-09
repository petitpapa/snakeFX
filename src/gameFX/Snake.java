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
	public static ObjectProperty<Direction> snakeDirectionProperty = new SimpleObjectProperty<Direction>();
	public static BooleanProperty snakeEatFood = new SimpleBooleanProperty();
	private  IntegerProperty pointsProperty = new SimpleIntegerProperty(0);
	private ObjectProperty<SpeedLevel> speedProperty = new SimpleObjectProperty<>(SpeedLevel.SLOW);

	private List<Cell> tail = new ArrayList<>();

	public Snake(GameManager gameManager) {
		this.gameManager = gameManager;
		snakeDirectionProperty.set(Direction.UP);
	}

	void init() {

		Timeline timeline = new Timeline(move());

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	private KeyFrame move() {
		int speed = speedProperty.get().getSpeedLevel();
		FPS = FPS.divide(speed) ;
		KeyFrame frame = new KeyFrame(FPS , e -> {
			Location offset = head.getLacation()
					.offset(snakeDirectionProperty.get());
			gameManager.getNextCell(offset).ifPresent(next -> {

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

		});
		return frame;
	}

	private void addPoints() {
		pointsProperty.set(pointsProperty.get() + 1);
	}

	public IntegerProperty getPoints(){
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

}
